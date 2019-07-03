package com.xandone.dog.wcapp.api;

import com.orhanobut.logger.Logger;
import com.xandone.dog.wcapp.config.Constants;
import com.xandone.dog.wcapp.exception.ApiException;
import com.xandone.dog.wcapp.model.base.BaseResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Admin
 * created on: 2019/7/3 14:44
 * description:
 */
public class RxHelper {
    public static <T> FlowableTransformer<T, T> handleIO() {

        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> FlowableTransformer<BaseResponse<T>, T> handleRespose() {
        return new FlowableTransformer<BaseResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<BaseResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<BaseResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(BaseResponse<T> response) {
                        if (response.getCode() == Constants.SUCCESS) {
                            return createData(response.getData());
                        } else {
                            return Flowable.error(new ApiException(response.getMsg(), response.getCode()));
                        }
                    }
                });
            }
        };
    }

    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                    Logger.e(e.toString());
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
