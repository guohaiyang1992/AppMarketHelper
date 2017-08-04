package com.simon.appmarket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.simon.market.core.AppMarketHelper;
import com.simon.market.utils.AppMarket;

public class MainActivity extends AppCompatActivity {
    AppMarketHelper appMarkertHelper = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goMarket(View view) {
        //--测试所有商城都可以--
//        testGoAllMarket();

        //--测试跳转到华为商城--
//        testGoToHuaweiMarket();
//        testGoWebMarket();
//        testHuaweiAndBaiduMarket();
        testGoOtherPackge();

//        testTecent();


    }

    private void testTecent() {
        AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.TECENT_MARKET).setAppUrl("www.baidu.com").go();
    }

    private void testGoToHuaweiMarket() {
        //设置商城标志为华为，设置备选地址为www.baidu.com
        AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.HUAWEI_MARKET).setAppUrl("www.baidu.com").go();
    }

    private void testGoAllMarket() {
        //设置商城标志为任意，设置备选地址为www.baidu.com
        AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.ALL).setAppUrl("www.baidu.com").go();
    }

    private void testGoWebMarket() {
        //设置商城标志为一个没有的，设置备选地址为www.baidu.com
        AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.BAIDU_MARKET).setAppUrl("www.baidu.com").go();
    }

    private void testHuaweiAndBaiduMarket() {
        //设置商城标志为百度和华为商城（按照此顺序依次，如果都没有则使用网络的），设置备选地址为www.baidu.com
        AppMarketHelper.with(MainActivity.this).setAppMarket(AppMarket.BAIDU_MARKET, AppMarket.HUAWEI_MARKET).setAppUrl("www.baidu.com").go();
    }

    private void testRecycleUse() {
        if (appMarkertHelper == null) {
            appMarkertHelper = AppMarketHelper.with(MainActivity.this);
            appMarkertHelper.setAppMarket(AppMarket.BAIDU_MARKET, AppMarket.HUAWEI_MARKET);
            appMarkertHelper.setAppUrl("http://www.baidu.com"); //加不加http:// 都可以，后面会判断加入
        }
        appMarkertHelper.go();
    }

    private void testGoOtherPackge() {
        //设置商城标志为百度和华为商城（按照此顺序依次，如果都没有则使用网络的），设置备选地址为www.baidu.com
        //特殊设置为其他包,下方是应用宝下载华为商店 --仅仅测试
        AppMarketHelper.with(MainActivity.this).setPackage(AppMarket.HUAWEI_MARKET).setAppMarket(AppMarket.BAIDU_MARKET, AppMarket.TECENT_MARKET).setAppUrl("www.baidu.com").go();
    }
}
