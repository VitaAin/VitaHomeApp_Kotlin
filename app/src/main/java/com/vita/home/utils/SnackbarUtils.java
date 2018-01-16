package com.vita.home.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * @FileName: com.vita.home.utils.SnackbarUtils.java
 * @Author: Vita
 * @Date: 2018-01-16 13:41
 * @Usage:
 */
public class SnackbarUtils {

    private SnackbarUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
