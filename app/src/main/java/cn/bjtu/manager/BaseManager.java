package cn.bjtu.manager;

import cn.bjtu.model.User;

/**
 * Created by wsg on 2017/6/27.
 */

public class BaseManager {
    protected  User mUser;

    public boolean isLogined(){
        return mUser!=null;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
