package com.vita.home.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @FileName: com.vita.home.utils.KeyBoardUtils.java
 * @Author: Vita
 * @Date: 2018-01-16 10:50
 * @Usage:
 */
public class KeyBoardUtils {

    private KeyBoardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void openKeyBoard(Context context, EditText editText) {
        InputMethodManager imm = getInputMethodManager(context);
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void closeKeyBoard(Context context, EditText editText) {
        InputMethodManager imm = getInputMethodManager(context);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static boolean isShowSoftInput(Context context) {
        InputMethodManager imm = getInputMethodManager(context);
        return imm.isActive();
    }

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
