package cn.bjtu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bjtu.R;
import cn.bjtu.model.User;
import cn.bjtu.util.DefaultSubscriber;
import cn.bjtu.util.DialogUtil;
import cn.bjtu.util.TextUtil;

public class AccountLoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_phone)
    TextInputEditText mEtPhone;
    @BindView(R.id.layout_phone)
    TextInputLayout mLayoutPhone;
    @BindView(R.id.et_password)
    TextInputEditText mEtPassword;
    @BindView(R.id.layout_password)
    TextInputLayout mLayoutPassword;
    @BindView(R.id.iv_help)
    ImageView mIvHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mToolbar.setTitle("登录");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.iv_help)
    public void onSmsClicked() {
        startActivity(new Intent(this,SMSLoginActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.login) {
            String phone = mEtPhone.getText().toString();
            String password = mEtPassword.getText().toString();
            if (!TextUtil.isPhone(phone)) {
                DialogUtil.snackbar(mToolbar, "手机号码格式错误");
                return true;
            }
            if (TextUtil.isEmpty(password)) {
                DialogUtil.snackbar(mToolbar, "请输入密码");
                return true;
            }
            DialogUtil.createProgressDialog(this);
            TextUtil.closeKeybord(this);
            mUserManager.login(phone, password)
                    .subscribe(new DefaultSubscriber<User>() {
                        @Override
                        public void onNext(User user) {
                            DialogUtil.dismissProgressDialog();
                            mUserManager.setUser(user);
                            //登录成功，转到主界面
                            startActivity(new Intent(AccountLoginActivity.this, MainActivity.class));
                            finish();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
                            DialogUtil.dismissProgressDialog();
                            DialogUtil.snackbar(mToolbar, "帐号或密码错误");
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
}
