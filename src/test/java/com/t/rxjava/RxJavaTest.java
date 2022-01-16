package com.t.rxjava;

import com.t.order2.ListUtils;
import io.reactivex.*;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by hutianhang on 2021/12/23
 */
public class RxJavaTest {

    static class Model {
        public String tag;

        public Model(String tag) {
            this.tag = tag;
        }
    }

    int time = 0;
    @Test
    void name() throws InterruptedException {

        CompositeDisposable disposable = new CompositeDisposable();
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(998);
                    System.out.println("time: " + (++time));
                }
//                Thread.sleep(2500);
//                System.out.println("!!!CLEAR!!!");
//                disposable.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Observer<String> observer = new Observer<>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable.add(d);
                System.out.println("final: onSubscribe");
            }

            @Override
            public void onNext(String value) {
                System.out.println("final onNext: " + Thread.currentThread().getName() + " " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("final: onError");
            }

            @Override
            public void onComplete() {
                System.out.println("final: onComplete");
            }
        };
        for (int i = 0; i < 1; i++) {
            doWork(disposable, observer);
        }
        Thread.sleep(50000);
    }

    void doWork(CompositeDisposable disposable, Observer<String> observer) {

        System.out.println(">>>>>>>>>>>>>>");
//            List<Model> list = ListUtils.newArrayList(new Model("A"), new Model("B"));
        List<Observable<String>> list = ListUtils.newArrayList(
                Observable.timer(5, TimeUnit.SECONDS).observeOn(Schedulers.single()).map(aLong -> {
                    System.out.println("w5: " + Thread.currentThread().getName());
                    return "Time-5";
                }),
                Observable.timer(1, TimeUnit.SECONDS).map(aLong -> {
                    System.out.println("w1: " + Thread.currentThread().getName());
                    return "Time-1";
                }),
                Observable.timer(3, TimeUnit.SECONDS).map(aLong -> {
                    System.out.println("w3: " + Thread.currentThread().getName());
                    return "Time-3";
                })
        );

        // 0
//            Observable.fromIterable(list).subscribe(stringObservable -> stringObservable.subscribe(System.out::println));


        // 1
//            Observable.fromIterable(list).flatMap(new Function<Observable<String>, ObservableSource<String>>() {
//                @Override
//                public ObservableSource<String> apply(Observable<String> stringObservable) throws Exception {
//                    System.out.println(stringObservable);
//                    return stringObservable;
//                }
//            }).subscribe(System.out::println);


        // 2
//            Observable.fromIterable(list).map(new Function<Observable<String>, String>() {
//                String result;
//                @Override
//                public String apply(Observable<String> stringObservable) {
//                    stringObservable.blockingSubscribe(s -> result = s);
//                    return result;
//                }
//            }).subscribe(System.out::println);

        // 3
//            System.out.println("in: " + Thread.currentThread().getName());
//            Observable.create((ObservableOnSubscribe<String>) emmit -> {
//                Observable.fromIterable(list).blockingSubscribe(stringObservable -> {
//                    System.out.println("exec: " + Thread.currentThread().getName());
//                    stringObservable.blockingSubscribe(new Observer<>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                        }
//
//                        @Override
//                        public void onNext(String value) {
//                            emmit.onNext(value);
//                            System.out.println("result: " + Thread.currentThread().getName() + " " + value);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            emmit.onError(e);
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                        }
//                    });
//                });
//                emmit.onComplete();
//            }).subscribeOn(Schedulers.newThread())
//                    .observeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
//                    .subscribe(new Observer<>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            System.out.println("final: onSubscribe");
//                        }
//
//                        @Override
//                        public void onNext(String value) {
//
//                            System.out.println("final onNext: " + Thread.currentThread().getName() + " " + value);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            System.out.println("final: onError");
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            System.out.println("final: onComplete");
//                        }
//                    });


//        Observable.create((ObservableOnSubscribe<String>) emitter -> {
//            Observable.fromIterable(list).subscribe(new Observer<>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                    System.out.println("onSubscribe");
//                    disposable.add(d);
//                }
//
//                @Override
//                public void onNext(Observable<String> observable) {
//                    System.out.println("onNext");
//                    observable.blockingSubscribe(new Observer<>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            System.out.println("onSubscribe 1");
//                        }
//
//                        @Override
//                        public void onNext(String value) {
//                            System.out.println("onNext 1 " + value);
//                            emitter.onNext(value);
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            System.out.println("onError 1 ");
//                            e.printStackTrace();
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            System.out.println("onComplete 1");
//                        }
//                    });
//                }
//
//                @Override
//                public void onError(Throwable e) {
//                    System.out.println("onError");
//                }
//
//                @Override
//                public void onComplete() {
//                    System.out.println("onComplete");
//                }
//            });
//            emitter.onComplete();
//        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(Schedulers.from(Executors.newFixedThreadPool(2)))
//        .subscribe(observer);

        Observable.concat(list).subscribe(observer);

        System.out.println("<<<<<<<<<<<<<<");

    }
}
