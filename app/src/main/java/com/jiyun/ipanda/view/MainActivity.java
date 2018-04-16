package com.jiyun.ipanda.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.jiyun.ipanda.R;
import com.jiyun.ipanda.base.BaseActivity;
import com.jiyun.ipanda.view.cctvview.fragment.CCTVFragment;
import com.jiyun.ipanda.view.chinaliveview.fragment.ChinaLiveFragment;
import com.jiyun.ipanda.view.homeview.fragment.HomeFragment;
import com.jiyun.ipanda.view.pandaeyeview.fragment.PandaEyeFragment;
import com.jiyun.ipanda.view.pandaliveview.fragment.PandaLiveFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.image_sign)
    ImageView imageSign;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.image_person)
    ImageView imagePerson;
    @BindView(R.id.image_hudong)
    ImageView imageHudong;
    @BindView(R.id.homeBtn)
    RadioButton homeBtn;
    @BindView(R.id.pandaEyeBtn)
    RadioButton pandaEyeBtn;
    @BindView(R.id.pandaLiveBtn)
    RadioButton pandaLiveBtn;
    @BindView(R.id.liveChinaBtn)
    RadioButton liveChinaBtn;
    @BindView(R.id.cctvBtn)
    RadioButton cctvBtn;

    @Override
    protected int getLayOutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setContentView(HomeFragment.class).updateTitle();

    }

    @Override
    protected void loadData() {

    }


    @OnClick({R.id.homeBtn, R.id.pandaEyeBtn, R.id.pandaLiveBtn, R.id.liveChinaBtn, R.id.cctvBtn,R.id.image_hudong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeBtn:
                setContentView( HomeFragment.class).updateTitle();
                break;
            case R.id.pandaEyeBtn:
                setContentView( PandaEyeFragment.class).updateTitle();
                break;
            case R.id.pandaLiveBtn:
                setContentView(PandaLiveFragment.class).updateTitle();
                break;
            case R.id.liveChinaBtn:
                setContentView(ChinaLiveFragment.class).updateTitle();
                break;
            case R.id.cctvBtn:
                setContentView(CCTVFragment.class).updateTitle();
                break;
            case R.id.image_hudong:
                startActivity(new Intent(this,InteractionActivity.class));
                break;
        }
    }

    public ImageView getImageSign() {
        return imageSign;
    }
    public TextView getTvTitle() {
        return tvTitle;
    }
    public ImageView getImageHudong() {
        return imageHudong;
    }

}
