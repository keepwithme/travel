package cn.bjtu.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    /**
     * 关闭软键盘
     *
     * @param activity Activity
     */
    public static void closeKeybord(Activity activity) {
        InputMethodManager manager = ((InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE));
        if (activity.getWindow().getAttributes().softInputMode !=
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 判断字符串是否为空
     *
     * @param s 字符串
     * @return 字符串空返回true，否则返回false
     */
    public static boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }


    /**
     * 判断是否为电话号码
     *
     * @param s 字符串
     * @return 字符串是电话号码返回true，否则返回false
     */
    public static boolean isPhone(String s) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

    public static String toString(String s) {
        return ifNull(s);
    }

    public static String toString(Integer s) {
        return String.valueOf(ifNull(s));
    }

    public static String toString(Boolean s) {
        return String.valueOf(ifNull(s));
    }


    public static String ifNull(String obj) {
        return obj == null ? "" : obj;
    }

    public static int ifNull(Integer t) {
        return t == null ? 0 : t;
    }

    public static boolean ifNull(Boolean t) {
        return t == null ? false : t;
    }

    public static boolean length(String str, int min, int max) {
        return str != null && str.length() >= min && str.length() <= max;
    }

    public static boolean length(String str, int max) {
        return length(str, 0, max);
    }
}
