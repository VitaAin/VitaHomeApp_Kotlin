package com.vita.home.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

//import android.content.pm.MyPackageInstallObserver;

/**
 * @FileName: com.vita.deepred.utils.AppUtils.java
 * @Author: Vita
 * @Date: 2017-05-10 09:13
 * @Usage:
 */
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 安装应用
     *
     * @param apkFilePath apk完整路径
     * @return true则安装成功，false则安装失败
     */
    public static boolean installApk(Context context, String apkFilePath) {
        if (TextUtils.isEmpty(apkFilePath)) return false;
        if (!apkFilePath.endsWith(".apk")) return false;

//        File apkFile = new File(apkFilePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //设置intent的数据类型是应用程序application
        intent.setDataAndType(Uri.parse("file://" + apkFilePath), "application/vnd.android.package-archive");
        //为新apk开启一个新的activity栈
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //开始安装
        context.startActivity(intent);
        //关闭旧版本的应用程序的进程
        android.os.Process.killProcess(android.os.Process.myPid());

        return true;
    }

    /**
     * 通过反射安装apk
     * 注意：须建立IPackageInstallObserver.aidl(包名须为android.content.pm)文件，然后自定义类集成它，不能直接传null
     *
     * @param apkFilePath apk完整路径
     */
//    public static void installApkByReflect(Context context, String apkFilePath) {
//        PackageManager pm = context.getPackageManager();
//        String PACKAGEMANAGER_CLASS_NAME = "android.content.pm.PackageManager";
//        try {
//            Class packageManagerClass;
//            packageManagerClass = Class.forName(PACKAGEMANAGER_CLASS_NAME);
//            Method meths[] = packageManagerClass.getMethods();
//            Method dp = null;
//            for (int i = 0; i < meths.length; i++) {
//                if (meths[i].getName().endsWith("installPackage"/*"deletePackage"*/))
//                    dp = meths[i];
//            }
//
//            Log.d("AppUtils", dp.toString());
//
//            dp.invoke(pm, Uri.parse("file://" + apkFilePath), new MyPackageInstallObserver(), 0, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("AppUtils", e.toString());
//        }
//    }

    /**
     * 安装应用，通过运行adb命令
     * TODO To test
     * 需要root权限
     *
     * @param apkName apk名称（不带路径）
     */
//    public static void installApk(String apkName) {
//        if (TextUtils.isEmpty(apkName)) return;
//        if (!apkName.endsWith(".apk")) return;
//
//        boolean isRoot = ShellUtils.checkRootPermission();
//        LogUtils.d("AppUtils", "isRoot:: " + isRoot);
//        ShellUtils.CommandResult commandResult = ShellUtils.execCommand(
//                "adb install " + apkName, isRoot, true);
//        LogUtils.d("AppUtils", "command result:: " + commandResult.errorMsg);
//    }

    /**
     * 卸载应用
     *
     * @param pkgName 应用包名
     * @return true则卸载成功，false则卸载失败
     */
    public static boolean uninstallApk(Context context, String pkgName) {
        if (TextUtils.isEmpty(pkgName)) return false;

        Uri uri = Uri.parse("package:" + pkgName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        context.startActivity(intent);

        return true;
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageInfo packageInfo = getPackageInfo(context);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = getPackageInfo(context);
            return packageInfo.versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用程序版本名称信息
     *
     * @return 当前应用的版本名称
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo packageInfo = getPackageInfo(context);
            return packageInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取包信息
     *
     * @return 包信息
     */
    private static PackageInfo getPackageInfo(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.getPackageInfo(context.getPackageName(), 0);
    }
}

