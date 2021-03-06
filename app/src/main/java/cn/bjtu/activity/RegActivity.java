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
import cn.bjtu.manager.UserManager;
import cn.bjtu.model.User;
import cn.bjtu.util.CountDownView;
import cn.bjtu.util.DefaultSubscriber;
import cn.bjtu.util.DialogUtil;
import cn.bjtu.util.TextUtil;

public class RegActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.layout_username)
    TextInputLayout mLayoutUsername;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.layout_phone)
    TextInputLayout mLayoutPhone;
    @BindView(R.id.btn_code)
    Button mBtnCode;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.layout_code)
    TextInputLayout mLayoutCode;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.layout_password)
    TextInputLayout mLayoutPassword;

    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews() {
        mToolbar.setTitle("用户注册");
        //点击后显示60秒倒计时
        mTimer = new CountDownView(mBtnCode);

    }

    /**
     * 点击“获取验证码”按钮时的处理
     */
    @OnClick(R.id.btn_code)
    public void onViewClick() {
        mTimer.start();
        mUserManager.requestSmsCode(mEtPhone.getText().toString());
    }

    /**
     * 手机号输入框文字发生改变时调用
     *
     * @param
     */
    @OnTextChanged(value = {R.id.et_phone}, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void onPhoneTextChanged(CharSequence s) {
        mBtnCode.setEnabled(TextUtil.isPhone(s.toString()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reg, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reg) {
            User user = new User();
            user.setUsername(mEtUsername.getText().toString());
            user.setMobilePhoneNumber(mEtPhone.getText().toString());
            String password = mEtPassword.getText().toString();
            user.setPassword(password);
            String smsCode = mEtCode.getText().toString();
            //输入无效
            if (TextUtil.isEmpty(user.getUsername()) ||
                    TextUtil.isEmpty(smsCode) ||
                    TextUtil.isEmpty(password) ||
                    !TextUtil.isPhone(user.getMobilePhoneNumber())) {
                DialogUtil.snackbar(mToolbar, "输入错误");
                return true;
            }
            UserManager.getInstance()
                    .signOrLogin(user, smsCode)
                    .subscribe(new DefaultSubscriber<User>() {
                        //注册成功，切换到主页
                        @Override
                        public void onNext(User user) {
                            mUserManager.setUser(user);
                            startActivity(new Intent(RegActivity.this, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
                            DialogUtil.snackbar(mToolbar, "验证码错误，注册失败");
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
}
