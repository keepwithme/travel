package cn.bjtu.util;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by wsg on 2017/6/23.
 */

public class SimpleSubscriber<T> extends Subscriber<T> {
    public static final String TAG = "cn.bjtu";

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {
        Log.d(TAG, throwable.getMessage(), throwable);
    }

    @Override
    public void onNext(T t) {

    }
}
