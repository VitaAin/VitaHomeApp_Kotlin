package com.vita.home.utils;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.vita.home.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName: com.vita.deepred.utils.PermissionsUtils.java
 * @Author: Vita
 * @Date: 2017-04-01 14:15
 * @Usage:
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class PermissionsUtils {

    public static final int REQUEST_CODE_CAMERA = 1;
    public static final int REQUEST_CODE_READ_CALENDAR = 2;
    public static final int REQUEST_CODE_GET_ACCOUNTS = 3;
    public static final int REQUEST_CODE_ACCESS_FINE_LOCATION = 4;
    public static final int REQUEST_CODE_ACCESS_COARSE_LOCATION = 5;
    public static final int REQUEST_CODE_RECORD_AUDIO = 6;
    public static final int REQUEST_CODE_READ_PHONE_STATE = 7;
    public static final int REQUEST_CODE_CALL_PHONE = 8;
    public static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 9;
    public static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 10;
    public static final int REQUEST_CODE_MULTI_PERMISSION = 100;

    private static final String PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    private static final String PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;

    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;

    private static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    private static final String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    private static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;

    private static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;

    private static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    private static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    private static final String PERMISSION_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    private static final String PERMISSION_WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    private static final String PERMISSION_ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    private static final String PERMISSION_USE_SIP = Manifest.permission.USE_SIP;
    private static final String PERMISSION_PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;

    private static final String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;

    private static final String PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS;
    private static final String PERMISSION_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    private static final String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;
    private static final String PERMISSION_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    private static final String PERMISSION_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;

    private static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    private static final String[] requestPermissions = {
            PERMISSION_READ_CALENDAR,
            PERMISSION_CAMERA,
            PERMISSION_GET_ACCOUNTS,
            PERMISSION_ACCESS_FINE_LOCATION,
            PERMISSION_ACCESS_COARSE_LOCATION,
            PERMISSION_RECORD_AUDIO,
            PERMISSION_READ_PHONE_STATE,
            PERMISSION_CALL_PHONE,
            PERMISSION_BODY_SENSORS,
            PERMISSION_READ_SMS,
            PERMISSION_READ_EXTERNAL_STORAGE,
            PERMISSION_WRITE_EXTERNAL_STORAGE
    };

    public interface PermissionGrant {
        void onPermissionGranted(int requestCode);
    }

    /**
     * Requests permission.
     *
     * @param activity
     * @param requestCode request code, e.g. if you need request CAMERA permission,parameters is PermissionUtils.CODE_CAMERA
     */
    public static void requestPermission(final Activity activity, final int requestCode, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            return;
        }

        final String requestPermission = requestPermissions[requestCode];

        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED，
        // 但是，如果用户关闭了你申请的权限，ActivityCompat.checkSelfPermission(),会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
        // 你可以使用try{}catch(){},处理异常，也可以在这个地方，低于23就什么都不做，
        // 个人建议try{}catch(){}单独处理，提示用户开启权限。
//        if (Build.VERSION.SDK_INT < 23) {
//            return;
//        }

        int checkSelfPermission;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
        } catch (RuntimeException e) {
            ToastUtils.showShort(activity, "Please open this permission");
            return;
        }

        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                shouldShowRationale(activity, requestCode, requestPermission);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
            }

        } else {
            ToastUtils.showShort(activity, "opened:" + requestPermissions[requestCode]);
            permissionGrant.onPermissionGranted(requestCode);
        }
    }

    private static void requestMultiResult(Activity activity, String[] permissions, int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }

        Map<String, Integer> perms = new HashMap<>();

        ArrayList<String> notGranted = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            perms.put(permissions[i], grantResults[i]);
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                notGranted.add(permissions[i]);
            }
        }

        if (notGranted.size() == 0) {
            ToastUtils.showShort(activity, "All permission success");
            permissionGrant.onPermissionGranted(REQUEST_CODE_MULTI_PERMISSION);
        } else {
            openSettingActivity(activity, "Those permission need granted!");
        }

    }

    /**
     * 一次申请多个权限
     */
    public static void requestMultiPermissions(final Activity activity, PermissionGrant grant) {

        final List<String> permissionsList = getNoGrantedPermission(activity, false);
        final List<String> shouldRationalePermissionsList = getNoGrantedPermission(activity, true);

        //TODO checkSelfPermission
        if (permissionsList == null || shouldRationalePermissionsList == null) {
            return;
        }

        if (permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_MULTI_PERMISSION);

        } else if (shouldRationalePermissionsList.size() > 0) {
            showMessageOKCancel(activity, "Should open those permission",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, shouldRationalePermissionsList.toArray(new String[shouldRationalePermissionsList.size()]),
                                    REQUEST_CODE_MULTI_PERMISSION);
                        }
                    });
        } else {
            grant.onPermissionGranted(REQUEST_CODE_MULTI_PERMISSION);
        }

    }

    private static void shouldShowRationale(final Activity activity, final int requestCode, final String requestPermission) {
        //TODO
        String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
        showMessageOKCancel(activity, "Rationale: " + permissionsHint[requestCode], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{requestPermission},
                        requestCode);
            }
        });
    }

    /**
     * @param activity
     * @param requestCode  Need consistent with requestPermission
     * @param permissions
     * @param grantResults
     */
    public static void requestPermissionsResult(final Activity activity, final int requestCode, @NonNull String[] permissions,
                                                @NonNull int[] grantResults, PermissionGrant permissionGrant) {

        if (activity == null) {
            return;
        }

        if (requestCode == REQUEST_CODE_MULTI_PERMISSION) {
            requestMultiResult(activity, permissions, grantResults, permissionGrant);
            return;
        }

        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            ToastUtils.showShort(activity, "Illegal requestCode:" + requestCode);
            return;
        }

        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //TODO success, do something, can use callback
            permissionGrant.onPermissionGranted(requestCode);

        } else {
            //TODO hint user this permission function
            String[] permissionsHint = activity.getResources().getStringArray(R.array.permissions);
            openSettingActivity(activity, "Result" + permissionsHint[requestCode]);
        }

    }

    private static void openSettingActivity(final Activity activity, String message) {
        showMessageOKCancel(activity, message, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });
    }

    /**
     * @param activity
     * @param isShouldRationale true: return no granted and shouldShowRequestPermissionRationale permissions, false:return no granted and !shouldShowRequestPermissionRationale
     * @return
     */
    public static ArrayList<String> getNoGrantedPermission(Activity activity, boolean isShouldRationale) {

        ArrayList<String> permissions = new ArrayList<>();

        for (String requestPermission : requestPermissions) {
            //TODO checkSelfPermission
            int checkSelfPermission = -1;
            try {
                checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
            } catch (RuntimeException e) {
                ToastUtils.showShort(activity, "Please open these permission");
                return null;
            }

            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                    if (isShouldRationale) {
                        permissions.add(requestPermission);
                    }

                } else {

                    if (!isShouldRationale) {
                        permissions.add(requestPermission);
                    }
                }

            }
        }

        return permissions;
    }

    private static void showMessageOKCancel(final Activity context, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
