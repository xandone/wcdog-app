package com.xandone.dog.wcapp.ui.search;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.xandone.dog.wcapp.R;
import com.xandone.dog.wcapp.base.BaseActivity;
import com.xandone.dog.wcapp.eventbus.SearchCountEvent;
import com.xandone.dog.wcapp.ui.joke.JokeContact;
import com.xandone.dog.wcapp.ui.joke.JokeTagFragment;
import com.xandone.dog.wcapp.uitils.SimpleUtils;
import com.xandone.dog.wcapp.uitils.ToastUtils;
import com.xandone.dog.wcapp.uitils.XString;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Admin
 * created on: 2019/6/27 11:15
 * description:
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.result_count)
    TextView resultCount;

    private JokeTagFragment jokeTagFragment;

    @Override
    public int setLayout() {
        return R.layout.act_search_layout;
    }

    @Override
    public void initData() {
        searchEt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    loadSearchJokes();
                }
                return false;
            }
        });
        initFragment();
    }

    private void initFragment() {
        jokeTagFragment = JokeTagFragment.newInstance(JokeTagFragment.TYPE_JOKE_ALL, true);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.search_main, jokeTagFragment)
                .commit();
    }

    private void loadSearchJokes() {
        SimpleUtils.hideSoftInput(this);
        String et = searchEt.getText().toString().trim();
        if (XString.isEmpty(et)) {
            ToastUtils.showShort("请输入搜索内容");
            return;
        }
        if (jokeTagFragment != null) {
            jokeTagFragment.loadData(et, JokeContact.MODE_ONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchReceived(SearchCountEvent event) {
        resultCount.setText(String.format(Locale.ENGLISH, "共搜索出%d条数据", event.getCount()));
    }

    @OnClick({R.id.search_btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.search_btn:
                loadSearchJokes();
                break;
            default:
                break;
        }
    }
}
