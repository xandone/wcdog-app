package com.xandone.dog.wcapp.ui.jokedetails;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xandone.dog.wcapp.App;
import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseRxActivity;
import com.xandone.dog.wcapp.cache.UserInfoCache;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.model.base.BaseResponse;
import com.xandone.dog.wcapp.model.bean.JokeBean;
import com.xandone.dog.wcapp.ui.comment.JokeCommentActivity;
import com.xandone.dog.wcapp.uitils.ToastUtils;
import com.xandone.dog.wcapp.uitils.XString;
import com.xandone.dog.wcapp.uitils.imgload.XGlide;
import com.xandone.dog.wcapp.widget.UserCircleIcon;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Admin
 * created on: 2019/6/25 14:08
 * description:
 */
public class JokeDetailsActivity extends BaseRxActivity<JokeDetailsPresenter> implements JokeDetailsContact.View {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.joke_details_like)
    TextView jokeLike;
    @BindView(R.id.joke_details_comment)
    TextView jokeComment;
    @BindView(R.id.joke_details_title)
    TextView jokeTitle;
    @BindView(R.id.joke_details_user_name)
    TextView jokeUserName;
    @BindView(R.id.joke_details_user_icon)
    UserCircleIcon jokeUserIcon;

    private JokeBean jokeBean;
    private int mPosition;
    private RequestManager requestManager;
    private Drawable drawable1, drawable2;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_jokedetails_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolbar, "详情");
        requestManager = Glide.with(this);
        jokeBean = (JokeBean) getIntent().getSerializableExtra(Constants.KEY_JOKEBEAN);
        mPosition = getIntent().getIntExtra(Constants.KEY_JOKEBEAN_POSITION, 0);
        if (jokeBean == null) {
            return;
        }

        jokeUserName.setText(String.valueOf(jokeBean.getJokeUserNick()));
        jokeTitle.setText(String.valueOf(jokeBean.getTitle()));
        jokeLike.setText(String.valueOf(jokeBean.getArticleLikeCount()));
        jokeComment.setText(String.valueOf(jokeBean.getArticleCommentCount()));
        XGlide.loadImage(requestManager, jokeUserIcon, jokeBean.getJokeUserIcon(), R.mipmap.df_icon);

        initWebView();
        webView.loadDataWithBaseURL(null, jokeBean.getContentHtml(), "text/html", "UTF-8", null);

        if (UserInfoCache.isLogin()) {
            drawable1 = getResources().getDrawable(R.mipmap.approval);
            drawable2 = getResources().getDrawable(R.mipmap.approval_sel);
            drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getMinimumHeight());
            drawable2.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getMinimumHeight());
            mPresenter.getThumbsJoke(jokeBean.getJokeId(), UserInfoCache.getUserBean().getUserId());
        }
    }

    private void initWebView() {
        WebSettings ws = webView.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(false);
        //  页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        ws.setJavaScriptEnabled(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //修改图片大小
                int screenWidth = App.SCREEN_WIDTH;
                String width = String.valueOf(DensityUtil.px2dp(screenWidth) - 20);

                String javascript = "javascript:function ResizeImages() {" +
                        "var myimg,oldwidth;" +
                        "var maxwidth = document.body.clientWidth;" +
                        "for(i=0;i <document.images.length;i++){" +
                        "myimg = document.images[i];" +
                        "if(myimg.width > " + width + "){" +
                        "oldwidth = myimg.width;" +
                        "myimg.width =" + width + ";" +
                        "}" +
                        "}" +
                        "}";
                view.loadUrl(javascript);
                view.loadUrl("javascript:ResizeImages();");

                view.loadUrl("javascript:function modifyTextColor(){" +
                        "document.getElementsByTagName('body')[0].style.webkitTextFillColor='#6A6868'" +
                        "};modifyTextColor();");

            }
        });
    }

    @Override
    public void showContent(BaseResponse baseResponse) {
        if (baseResponse.getCode() == 200) {
            jokeLike.setCompoundDrawables(null, drawable1, null, null);
        } else {
            jokeLike.setCompoundDrawables(null, drawable2, null, null);
        }
    }

    @Override
    public void thumbsJokeResult(BaseResponse baseResponse) {
        if (baseResponse.getCode() == 200) {
            ToastUtils.showShort("点赞成功");
            jokeLike.setCompoundDrawables(null, drawable2, null, null);
            jokeLike.setText(String.valueOf(jokeBean.getArticleLikeCount() + 1));

        } else if (!XString.isEmpty(baseResponse.getMsg())) {
            ToastUtils.showShort(baseResponse.getMsg());
        }
    }

    @Override
    public void showCollectionResult(boolean success, String msg) {

    }

    @OnClick({R.id.joke_details_like, R.id.joke_details_comment})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.joke_details_like:
                if (!UserInfoCache.isLogin()) {
                    ToastUtils.showShort("你还未登录");
                    break;
                }
                mPresenter.thumbsJoke(jokeBean.getJokeId(), UserInfoCache.getUserBean().getUserId());
                break;
            case R.id.joke_details_comment:
                Intent intent = new Intent(this, JokeCommentActivity.class);
                intent.putExtra(Constants.KEY_JOKEBEAN, jokeBean);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
