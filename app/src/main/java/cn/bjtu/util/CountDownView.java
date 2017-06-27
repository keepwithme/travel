package cn.bjtu.util;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by wsg on 2017/6/27.
 */

public class CountDownView extends CountDownTimer {
    private TextView mTextView;

    public CountDownView(TextView view) {
        super(60000, 1000);
        mTextView = view;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setEnabled(false);
        mTextView.setText(millisUntilFinished / 1000 + "秒后重新获取");
    }

    @Override
    public void onFinish() {
        mTextView.setText("获取验证码");
        mTextView.setEnabled(true);
    }
}
