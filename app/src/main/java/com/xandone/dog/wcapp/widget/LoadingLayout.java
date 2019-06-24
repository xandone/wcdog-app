package com.xandone.dog.wcapp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.uitils.XString;


/**
 * author: xandone
 * created on: 2018/3/13 9:34
 */
public class LoadingLayout extends LinearLayout {
    private ImageView img_tip_logo;
    private ProgressBar progress;
    private TextView tv_tips;
    private TextView bt_operate;

    private OnReloadListener onReloadListener;
    private String errorMsg;
    private int nullPic;

    public static final int netError = 1;
    public static final int serverError = 2;
    public static final int empty = 3;
    public static final int loading = 4;
    public static final int finish = 5;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void initView(Context context) {
        View.inflate(context, R.layout.loading_tip_layout, this);
        img_tip_logo = (ImageView) findViewById(R.id.img_tip_logo);
        progress = (ProgressBar) findViewById(R.id.progress);
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        bt_operate = (TextView) findViewById(R.id.bt_operate);

        setVisibility(GONE);

        bt_operate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadListener != null) {
                    onReloadListener.reLoad();
                }
            }
        });

    }

    public void setTips(String tips) {
        if (tv_tips == null) {
            return;
        }
        tv_tips.setText(tips);
    }

    public int getNullPic() {
        return nullPic;
    }

    public void setNullPic(int nullPic) {
        this.nullPic = nullPic;
    }

    public void setLoadingTips(int loadStatus) {
        switch (loadStatus) {
            case netError:
                setVisibility(VISIBLE);
                img_tip_logo.setVisibility(VISIBLE);
                progress.setVisibility(GONE);
                bt_operate.setVisibility(VISIBLE);
                if (XString.isEmpty(errorMsg)) {
                    tv_tips.setText("数据加载失败");
                } else {
                    tv_tips.setText(errorMsg);
                }
                img_tip_logo.setImageResource(R.mipmap.icon_net_error);
                break;
            case serverError:
                setVisibility(View.VISIBLE);
                img_tip_logo.setVisibility(VISIBLE);
                progress.setVisibility(View.GONE);
                bt_operate.setVisibility(VISIBLE);
                if (XString.isEmpty(errorMsg)) {
                    tv_tips.setText("服务器异常");
                } else {
                    tv_tips.setText(errorMsg);
                }
                img_tip_logo.setImageResource(R.mipmap.icon_server_error);
                break;
            case empty:
                setVisibility(VISIBLE);
                img_tip_logo.setVisibility(VISIBLE);
                progress.setVisibility(GONE);
                bt_operate.setVisibility(VISIBLE);
                tv_tips.setText("暂无数据");
                if (nullPic <= 0) {
                    img_tip_logo.setImageResource(R.mipmap.icon_net_nodata
                    );
                } else {
                    img_tip_logo.setImageResource(nullPic);
                }
                break;
            case loading:
                setVisibility(VISIBLE);
                img_tip_logo.setVisibility(GONE);
                progress.setVisibility(VISIBLE);
                bt_operate.setVisibility(GONE);
                tv_tips.setText("加载..");
                break;
            case finish:
                setVisibility(GONE);
                break;
            default:
                break;
        }
    }

    public interface OnReloadListener {
        void reLoad();
    }

    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

}
