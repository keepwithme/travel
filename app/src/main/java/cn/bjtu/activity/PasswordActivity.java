package cn.bjtu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bjtu.R;
import cn.bjtu.util.DefaultSubscriber;
import cn.bjtu.util.DialogUtil;
import cn.bjtu.util.TextUtil;

public class PasswordActivity extends BaseActivity {

    @BindView(R.id.et_password_old)
    EditText mEtPasswordOld;
    @BindView(R.id.et_password_new)
    EditText mEtPasswordNew;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        mToolbar.setTitle("修改密码");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String passwordOld = mEtPasswordOld.getText().toString();
            String passwordNew = mEtPasswordNew.getText().toString();
            if (TextUtil.isEmpty(passwordNew) || TextUtil.isEmpty(passwordOld)) {
                DialogUtil.snackbar(mToolbar, "新旧密码都必须输入");
                return true;
            }
            DialogUtil.createProgressDialog(this);
            TextUtil.closeKeybord(this);
            mUserManager.updatePassword(passwordOld, passwordNew)
                    .subscribe(new DefaultSubscriber<Void>() {
                        @Override
                        public void onNext(Void aVoid) {
                            DialogUtil.dismissProgressDialog();
                            startActivity(new Intent(PasswordActivity.this, AccountLoginActivity.class));
                            finish();
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            super.onError(throwable);
                            DialogUtil.dismissProgressDialog();
                            DialogUtil.snackbar(mToolbar, "原密码错误");
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
}
