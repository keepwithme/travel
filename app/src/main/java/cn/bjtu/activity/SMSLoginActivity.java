package cn.bjtu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.bjtu.R;
import cn.bjtu.model.User;
import cn.bjtu.util.CountDownView;
import cn.bjtu.util.DefaultSubscriber;
import cn.bjtu.util.DialogUtil;
import cn.bjtu.util.TextUtil;

public class SMSLoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.layout_phone)
    TextInputLayout mLayoutPhone;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.layout_code)
    TextInputLayout mLayoutCode;
    @BindView(R.id.btn_code)
    Button mBtnCode;

    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smslogin);
        ButterKnife.bind(this);
        mToolbar.setTitle("登录");
        mCountDownTimer = new CountDownView(mBtnCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 点击获取短信验证码
     */
    @OnClick(R.id.btn_code)
    public void onViewClicked() {
        mCountDownTimer.start();
        mUserManager.requestSmsCode(mEtPhone.getText().toString());
    }

    /**
     * 监控手机号输入
     * @param s
     */
    @OnTextChanged(value = R.id.et_phone, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onPhoneEntered(CharSequence s) {
        mBtnCode.setEnabled(TextUtil.isPhone(s.toString()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login) {
            String phone = mEtPhone.getText().toString();
            String code = mEtCode.getText().toString();
            if (!TextUtil.isPhone(phone) || TextUtil.isEmpty(code)) {
                DialogUtil.snackbar(mToolbar, "请输入电话号码和短信验证码");
                return true;
            }
            DialogUtil.createProgressDialog(this);
            //进行登录
            mUserManager.loginBySMS(phone, code)
                    .subscribe(new DefaultSubscriber<User>() {
                        @Override
                        public void onNext(User user) {
                            DialogUtil.dismissProgressDialog();
                            mUserManager.setUser(user);
                            startActivity(new Intent(SMSLoginActivity.this, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
                            DialogUtil.dismissProgressDialog();
                            DialogUtil.snackbar(mToolbar, "电话号码或短信验证码错误");
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
}
