package cn.bjtu.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

public class DialogUtil {

    private static ProgressDialog sProgressDialog;

    /**
     * 创建加载对话框
     *
     * @param context 上下文
     * @return 加载对话框
     */
    public static void createProgressDialog(Context context) {
        dismissProgressDialog();
        sProgressDialog = new ProgressDialog(context);
        sProgressDialog.setCancelable(false);
        sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        sProgressDialog.setIndeterminate(true);
        sProgressDialog.show();
    }

    public static void dismissProgressDialog() {
        if (sProgressDialog != null) {
            sProgressDialog.dismiss();
            sProgressDialog = null;
        }
    }

    public static void snackbar(View v, Object msg) {
        Snackbar.make(v, String.valueOf(msg), Snackbar.LENGTH_SHORT).show();
    }

}
