package com.example.ieas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    // 定义2个Fragment对象
    private NormalFragment nfg;
    private MachineFragment mfg;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private ImageView firstImage;
    private ImageView secondImage;
    private TextView firstText;
    private TextView secondText;
    // 定义颜色
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡
    }
    /**
     * 初始化页面
     */
    private void initView() {
    // 初始化底部导航栏的控件
    firstImage = (ImageView) findViewById(R.id.first_image);
    secondImage = (ImageView) findViewById(R.id.second_image);
    firstText = (TextView) findViewById(R.id.first_text);
    secondText = (TextView) findViewById(R.id.second_text);
    firstLayout = (RelativeLayout) findViewById(R.id.first_layout);
    secondLayout = (RelativeLayout) findViewById(R.id.second_layout);
    firstLayout.setOnClickListener(MainActivity.this);
    secondLayout.setOnClickListener(MainActivity.this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_layout:
                setChioceItem(0);
                break;
            case R.id.second_layout:
                setChioceItem(1);
                break;
            default:
                break;
        }
    }
    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
                //  firstImage.setImageResource(R.drawable.XXXX);
                firstText.setTextColor(dark);
                firstLayout.setBackgroundColor(gray);
                // 如果fg1为空，则创建一个并添加到界面上
                if (nfg == null) {
                    nfg = new NormalFragment();
                    fragmentTransaction.add(R.id.content, nfg);
                } else {
                // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(nfg);
                }
                break;
            case 1:
                // secondImage.setImageResource(R.drawable.XXXX);
                secondText.setTextColor(dark);
                secondLayout.setBackgroundColor(gray);
                if (mfg == null) {
                    mfg = new MachineFragment();
                    fragmentTransaction.add(R.id.content, mfg);
                } else {
                    fragmentTransaction.show(mfg);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
        // firstImage.setImageResource(R.drawable.XXX);
        firstText.setTextColor(gray);
        firstLayout.setBackgroundColor(whirt);
        // secondImage.setImageResource(R.drawable.XXX);
        secondText.setTextColor(gray);
        secondLayout.setBackgroundColor(whirt);
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (nfg != null) {
            fragmentTransaction.hide(nfg);
        }
        if (mfg != null) {
            fragmentTransaction.hide(mfg);
        }
    }
}
