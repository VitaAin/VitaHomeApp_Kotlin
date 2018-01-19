package com.vita.home.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @FileName: com.vita.home.utils.SystemUtils.java
 * @Author: Vita
 * @Date: 2018-01-19 13:20
 * @Usage:
 */
public class SystemUtils {

    public static final int REQUEST_CODE_CHOOSE_IMAGE = 1;

    private SystemUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void openImage(Activity activity, String title) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(Intent.createChooser(intent, title),
                REQUEST_CODE_CHOOSE_IMAGE);
    }

    public static String getImagePath(Activity activity, Uri uri) {
        //这里开始的第二部分，获取图片的路径：
        String[] imgPath = {MediaStore.Images.Media.DATA};
        //好像是android多媒体数据库的封装接口，具体的看Android文档
        Cursor cursor = activity.managedQuery(uri, imgPath, null, null, null);
        //按我个人理解 这个是获得用户选择的图片的索引值
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        //将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();

        //最后根据索引值获取图片路径
        return cursor.getString(columnIndex);
    }
}
