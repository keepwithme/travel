package cn.bjtu.manager;

import cn.bjtu.model.User;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.UpdateListener;
import rx.Observable;
import rx.Subscriber;

/**
 * 分离User类型对象业务处理逻辑、与云端交互逻辑，减轻Activity负担
 * Created by wsg on 2017/6/23.
 */

public class UserManager extends BaseManager {
    private UserManager() {
    }

    private static UserManager instance;

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }


    /**
     * 注册用户，注册成功后自动登录
     *
     * @param user          ：注册用户
     * @param smsCode：短信验证码
     * @return 包含注册用户的Observable
     */
    public Observable<User> signOrLogin(User user, String smsCode) {
        return user.signOrLoginObservable(User.class, smsCode);
    }

    /**
     * 使用帐号与密码登录.
     *
     * @param user 登录用户，包含账号与密码
     * @return
     */
    public Observable<User> login(String phone ,String password) {
        return BmobUser.loginByAccountObservable(User.class,phone,password);
    }

    /**
     * 使用短信验证码登录
     *
     * @param phone
     * @param smsCode
     * @return
     */
    public Observable<User> loginBySMS( final String phone, final String smsCode) {

        //转换为Observable对象，方便使用RxJava处理
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(final Subscriber<? super User> subscriber) {
                BmobUser.loginBySMSCode(phone, smsCode, new LogInListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e == null) subscriber.onNext(user);
                        else subscriber.onError(e);
                    }
                });
            }
        });
    }

    /**
     * 请求短信验证码，发送到指定号码的手机上
     * @param phone
     */
    public void requestSmsCode(String phone) {
        BmobSMS.requestSMSCodeObservable(phone, "one")
                .subscribe();
    }

    /**
     * 更改当前登录用户的密码。
     * 将回调接口转换为Observable为难点
     * @param passwordOld
     * @param passwordNew
     * @return
     */
    public Observable<Void>  updatePassword(final String passwordOld, final String passwordNew){
       return  Observable.create(new Observable.OnSubscribe<Void>() {
           @Override
           public void call(final Subscriber<? super Void> subscriber) {
                BmobUser.updateCurrentUserPassword(passwordOld, passwordNew, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null) subscriber.onNext(null);
                        else subscriber.onError(e);
                    }
                });
           }
       });
    }

}
