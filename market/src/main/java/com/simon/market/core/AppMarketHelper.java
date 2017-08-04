package com.simon.market.core;

import android.content.Context;
import android.content.Intent;

import com.simon.market.utils.AppMarket;
import com.simon.market.utils.IntentUtils;

import java.lang.ref.WeakReference;

/**
 * description: appmarket 开启应用商店的辅助工具类
 * author: Simon
 * created at 2017/8/3 下午6:04
 */
public class AppMarketHelper {
    private WeakReference<Context> context = null;//上下文环境,弱引用，防止内存泄漏
    private String[] appMarketArray = null; //app上线的商城，如果此处null/size=0 等价于all
    private String url = null;//app 的在线地址
    private Intent intent = null;//筛选后的intent
    private String packageName = null;

    /**
     * 真正的初始化类
     *
     * @param context -上下文环境
     */
    private AppMarketHelper(Context context) {
        this.context = new WeakReference<Context>(context);
    }

    /**
     * 生成一个 助手类
     *
     * @param context --当前的上下文环境
     */
    public static AppMarketHelper with(Context context) {
        return new AppMarketHelper(context);
    }

    /**
     * 设置 可选的app market
     *
     * @param appMarketArray --app market array
     */
    public AppMarketHelper setAppMarket(String... appMarketArray) {
        this.appMarketArray = appMarketArray;
        return this;
    }

    /**
     * 设置当可选的app market 不在当前手机的安装范围的时候，跳转的网址
     *
     * @param url --网址
     */
    public AppMarketHelper setAppUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * 此处为可选方法，在app market 打开指定的包名应用(默认是自己的app 包名)
     *
     * @param packageName --包名
     */
    public AppMarketHelper setPackage(String packageName) {
        this.packageName = packageName;
        return this;
    }

    /**
     * 真正运行的方法，设置完成后最后调用此方法
     */
    public void go() {
        intent = getCurrentIntent();
        if (intent == null) {
            IntentUtils.goToIntent(context.get(), getWebIntent());
        } else {
            IntentUtils.goToIntent(context.get(), intent);
        }
    }


    //---------------内部私有方法---------------------------

    /**
     * 获取当前的intent,根据用户设置的app market信息
     */
    private Intent getCurrentIntent() {
        //没有设置就默认为全部
        if (appMarketArray == null || appMarketArray.length == 0 || (appMarketArray.length == 1 && appMarketArray[0].equals(""))) {
            return defaultIntent();
        } else {
            return normalIntent();
        }
    }

    /**
     * 获取当前的web intent
     */
    private Intent getWebIntent() {
        Intent intent = IntentUtils.createWebIntent(url);
        if (IntentUtils.isIntentAvaileble(context.get(), intent)) {
            return intent;
        } else {
            return null;
        }
    }

    /**
     * 一般情况下的intent 的生成方法
     */
    private Intent normalIntent() {
        for (String pack : appMarketArray) {
            Intent intent = IntentUtils.createIntent(context.get(), pack, packageName);
            if (IntentUtils.isIntentAvaileble(context.get(), intent)) {
                return intent;
            }
        }
        return null;
    }

    /**
     * 默认的intent的生成方法
     */
    private Intent defaultIntent() {
        Intent intent = IntentUtils.createIntent(context.get(), AppMarket.ALL, packageName);
        if (IntentUtils.isIntentAvaileble(context.get(), intent)) {
            return intent;
        } else {
            return null;
        }
    }


}
