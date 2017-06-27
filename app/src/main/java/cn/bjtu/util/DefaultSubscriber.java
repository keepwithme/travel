package cn.bjtu.util;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by wsg on 2017/6/27.
 */

public abstract class DefaultSubscriber<T> extends Subscriber<T> {
    public static final String  TAG="cn.bjtu";
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {
        Log.d(TAG,throwable.getMessage(),throwable);
    }


}
