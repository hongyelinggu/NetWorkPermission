package com.hylg.mplibrary;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * 作者:红叶岭谷
 * <br/>时间 2017/8/15.
 * <br/>此类适用于对Android6.0以上的权限进行检查和申请
 */

public class HRequestPermissions {
    /**权限申请返回码*/
    public static final int PERMISSION_REQUEST_CODE = 101;
    private static HRequestPermissions permissions = new HRequestPermissions();
    private static Activity perActivity;
    private HRequestPermissions(){}

    /**
     * 饿汉式单利加载对象
     * @param activity
     * @return
     */
    public static HRequestPermissions getRPermission(Activity activity){
        perActivity = activity;
        return permissions;
    }

    /**
     * 动态申请权限
     * @param permissions 举例:Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    public void checkPermissions(String... permissions){
        //版本的兼容
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && lacksPermissions(permissions)){
            requestPermissions(permissions);
        }
    }

    /**
     * 判断是否缺失权限集合中的权限
     * @param permissions 检查的权限
     * @return 返回真表明是缺失权限
     */
    public boolean lacksPermissions(String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否缺少某个权限
     * @param permission 举例:Manifest.permission.WRITE_EXTERNAL_STORAGE
     * @return
     */
    private boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(perActivity, permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    /**
     * 请求权限
     * @param permissions 举例:Manifest.permission.WRITE_EXTERNAL_STORAGE
     */
    private void requestPermissions(String... permissions) {
        ActivityCompat.requestPermissions(perActivity, permissions, PERMISSION_REQUEST_CODE);
    }

    /**
     * 启动应用的设置,进入手动配置权限页面
     */
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", perActivity.getPackageName(), null);
        intent.setData(uri);
        perActivity.startActivity(intent);
    }
}
