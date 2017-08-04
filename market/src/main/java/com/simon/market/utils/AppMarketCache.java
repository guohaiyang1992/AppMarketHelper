package com.simon.market.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * description:  缓存数据
 * author: Simon
 * created at 2017/8/3 下午6:04
 */

public class AppMarketCache {
    private static List<String> packageCacheList = new ArrayList<>();

    /**
     * 添加数据到缓存
     *
     * @param packages
     */
    public static void addToCahche(String packages) {
        if (!TextUtils.isEmpty(packages)) {
            AppMarketCache.packageCacheList.add(packages);
        }
    }

    /**
     * 清理缓存
     */
    public static void clearCache() {
        if (packageCacheList != null) {
            packageCacheList.clear();
        }
    }

    public static List<String> getAll() {
        return packageCacheList;
    }
}
