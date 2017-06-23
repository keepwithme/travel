package cn.bjtu.manager;

import cn.bjtu.model.User;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by wsg on 2017/6/23.
 */

public class UserManager {
    private UserManager(){}
    private static UserManager instance;
    private static UserManager getInstance(){
        if(instance==null){
            instance=new UserManager();
        }
        return instance;
    }

    public void signOrLogin(User user,String smsCode){

         user.signOrLogin(smsCode, new SaveListener<String>() {
             @Override
             public void done(String s, BmobException e) {

             }
         });

    }
}
