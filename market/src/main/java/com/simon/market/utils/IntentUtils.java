package com.simon.market.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * description: 判断intent 是否存在
 * author: Simon
 * created at 2017/8/4 上午10:49
 */

public class IntentUtils {
    private static final String TAG = "IntentUtils";
    private static final String HTTP_HEADER = "http://";
    private static final String HTTPS_HEADER = "https://";

    private IntentUtils() {
        throw new AssertionError("you can't init me!");
    }

    /**
     * @param context 上下文
     * @param intent  意图
     * @return
     */
    public static boolean isIntentAvaileble(Context context, Intent intent) {
        if (context != null && intent != null) {
            List<ResolveInfo> resolves = context.getPackageManager().queryIntentActivities(intent, 0);
            return resolves.size() > 0;
        } else {
            return false;
        }
    }

    /**
     * 接收app 包名创建Intent
     *
     * @param appMarket
     * @return
     */
    public static Intent createIntent(Context context, String appMarket, String packageName) {
        //--校验当前环境--
        if (context == null) {
            throw new NullPointerException("context 不能为null=>createIntent(Context context, String appMarket) ");
        }
        if (appMarket == null) {
            appMarket = "";
        }

        //--获取当前的包名--
        if (packageName == null) { //当默认的时候，使用当前的package 信息，反之使用指定的包名
            packageName = context.getPackageName();
        }
        //--开始生成 intent--
        Intent intent = new Intent();
        Uri uri = null;
        switch (appMarket) {
            case AppMarket.SAMSUNG_MARKET://三星 market
                uri = Uri.parse("http://www.samsungapps.com/appquery/appDetail.as?appId=" + packageName);
                intent.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
                intent.setData(uri);
                break;
            case AppMarket.LETV_MARKET:// 乐视tv
                intent.setClassName("com.letv.app.appstore", "com.letv.app.appstore.appmodule.details.DetailsActivity");
                intent.setAction("com.letv.app.appstore.appdetailactivity");
                intent.putExtra("packageName", packageName);
                break;
            case AppMarket.TECENT_MARKET://腾讯的应用宝
                uri = Uri.parse("market://details?id=" + packageName);
                intent.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                break;
            case AppMarket.ALL:
                uri = Uri.parse("market://details?id=" + packageName);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                break;
            default://默认的
                uri = Uri.parse("market://details?id=" + packageName);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setPackage(appMarket);
                intent.setData(uri);
                break;
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;

    }

    /**
     * 创建web intent
     *
     * @param url --url
     * @return
     */
    public static Intent createWebIntent(String url) {
        if (TextUtils.isEmpty(url)) {
            Log.e(TAG, "url出错，创建web intent出错，createWebIntent(String url)!");
            return null;
        } else {
            Uri uri = Uri.parse(createUsefulUrl(url));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            return intent;
        }
    }

    /**
     * 跳转到market
     *
     * @param context --上下文
     * @param intent  --意图
     */
    public static void goToIntent(Context context, Intent intent) {
        if (context != null && intent != null) {
            context.startActivity(intent);
        } else {
            Log.e(TAG, "context或者intent为null，打开intent 出错!");
        }
    }

    /**
     * 创建为有效url
     *
     * @param url --原地址
     * @return --修改后地址
     */
    private static String createUsefulUrl(String url) {
        //原有地址有效
        if (!TextUtils.isEmpty(url)) {
            //切换为全小写
            url = url.toLowerCase();
            //判断是否包含http/https ，不包含进行添加
            if (!url.contains(HTTP_HEADER) && !url.contains(HTTPS_HEADER)) {//缺少http 前缀
                return HTTP_HEADER + url;
            }
        }
        return url;
    }

}
