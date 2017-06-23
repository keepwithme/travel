package cn.bjtu.event;

/**
 * Created by wsg on 2017/6/23.
 */

public class LoginEvent {
    public boolean logined; //true:登录,false:登出

    public LoginEvent(boolean logined) {
        this.logined = logined;
    }
}
