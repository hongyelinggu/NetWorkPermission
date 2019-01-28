# 欢迎使用 红叶岭谷-(网络、权限)  封装包 

------

在应用程序app开发过程中,我们经常用到网络的判断,网络 以及 **Android6.0后的权限申请** 每次开发都是一个不小的工程量,因此为了方便我专门将他们封装成了一个包以便于今后使用,包含两个类:

> * 类 HRequestPermissions(权限申请和检查)
> * 类 NetWorkUtil(网络类型获取和检查)

![最新版本](https://img.shields.io/badge/Maven-version%201.0.0-brightgreen.svg) 

**使用:**`compile 'com.chaoqianhong.NetWorkPermission:mplibrary:1.0.0'`



## 快速使用
### 1. HRequestPermissions(权限申请和检查) 

#### 1.1功能介绍
- [X] 动态申请权限 **checkPermissions()**
- [x] 判断是否缺失权限集合中的权限 **判断是否缺失权限集合中的权限()**
- [x] 启动应用的设置,进入手动配置权限页面 **startAppSettings()**

#### 1.2使用方法
    
```
//1.获取类对象
HRequestPermissions rPermission = HRequestPermissions.getRPermission(this);
//检查权限完整性(举例)
permissions = rPermission.lacksPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE);
//申请权限
rPermission.checkPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
```
### 注意:
使用动态申请权限时,一定要重新Activity的方法**onRequestPermissionsResult**

    @Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		//返回申请结果
	}




### 2. NetWorkUtil(网络类型获取和检查)

#### 1.1功能介绍
- [X] 判断当前网络是否存在，并可用于数据传输 **getConnected()**
- [x] 获取当前网络类型 **getNetworkInfo() **
- [x] 查询当前网络状态 ** getNetworkInfoState() **
- [x] 判断当前网络是否可用 **isNetworkAvailable() **
- [x] 网络变化监听 **netWorkListener **

#### 1.2使用方法(所有方法都为静态方法,都可以通过类名直接调用)

```
// 举例:
NetWorkUtil.getConnected(this)
```

