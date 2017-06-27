package cn.bjtu.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bjtu.R;
import cn.bjtu.model.User;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (!mUserManager.isLogined()) {
            //获取上次登录的用户
            mUserManager.setUser(BmobUser.getCurrentUser(User.class));
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }
}
