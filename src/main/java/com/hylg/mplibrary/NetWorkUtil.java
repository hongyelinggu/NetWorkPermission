package com.hylg.mplibrary;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

/**
 * @author 红叶岭谷
 * <br/> 创建时间 2019/1/22
 * <br/> 类阐述 网络判断获取对象封装类
 */
public class NetWorkUtil {

    private static String LOG_TAG = "com.hylg.mplibrary.NetWorkUtil";

    /**
     * 判断当前网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.w(LOG_TAG, "无法获得ConnectivityManager");
        } else {
            NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
            return networkInfo.isAvailable();
        }
        return false;
    }


    /**
     * 判断当前网络是否存在，并可用于数据传输
     *
     * @param context
     * @return
     */
    public static boolean getConnected(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        return networkInfo.isConnected();
    }

    /**
     * 查询当前网络状态
     *
     * @param context
     * @return NetworkInfo.State.CONNECTED(已连接),
     * <br/>NetworkInfo.State.CONNECTING(正在连接),
     * <br/>NetworkInfo.State.DISCONNECTED(已断开),
     * <br/>NetworkInfo.State.DISCONNECTING(正在断开),
     * <br/>NetworkInfo.State.SUSPENDED(网络延缓),
     * <br/>NetworkInfo.State.UNKNOWN(未知状态)
     */
    public static NetworkInfo.State getNetworkInfoState(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo().getState();
    }

    /**
     * 获取当前网络类型
     *
     * @param context
     * @return ConnectivityManager.TYPE_MOBILE,
     * <br/>ConnectivityManager.TYPE_WIFI,
     * <br/>ConnectivityManager.TYPE_WIMAX,
     * <br/>ConnectivityManager.TYPE_ETHERNET,
     * <br/>ConnectivityManager.TYPE_BLUETOOTH
     */
    public static int getNetworkInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo().getType();
    }

    /**
     * 网络变化监听
     * @param context
     * @param networkCallback ConnectivityManager.NetworkCallback
     */
    public static void netWorkListener(Context context,ConnectivityManager.NetworkCallback networkCallback){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                connectivityManager.requestNetwork(new NetworkRequest.Builder().build(),networkCallback);
            }
        }
    }
}
