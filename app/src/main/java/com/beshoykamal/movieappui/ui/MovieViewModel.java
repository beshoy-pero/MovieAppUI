package com.beshoykamal.movieappui.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.beshoykamal.movieappui.data.MoviesData;
import com.beshoykamal.movieappui.data.Response;
import com.beshoykamal.movieappui.model.Modelretro;
import com.beshoykamal.movieappui.model.MovieAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MovieViewModel extends ViewModel {
    private static final String TAG = "MovieViewModel";
    MutableLiveData<List<Modelretro>> postmutableLiveData = new MutableLiveData<>();

      public void getPosts() {

        Observable<Response> observable =  MoviesData.getINSTANCE().getPosts()
                .subscribeOn(Schedulers.io())                        // UpStream
                .observeOn(AndroidSchedulers.mainThread());      // DownStream

          Observer<Response> observer=new Observer<Response>() {
              @Override
              public void onSubscribe(Disposable d) {
                  Log.d(TAG, "onSubscribe: pero "+d);
              }

              @Override
              public void onNext(Response response) {
                  List<Modelretro> modelretros = response.getStudents();
                  postmutableLiveData.setValue(modelretros);
                  Log.d(TAG, "onNext: pero "+response.toString());
              }
              @Override
              public void onError(Throwable e) {
                  Log.d(TAG, "onError: pero "+e);
              }

              @Override
              public void onComplete() {
                  Log.d(TAG, "onComplete: pero");
              }
          };
          observable.subscribe(observer);


    }

//      observable.subscribe(o -> postmutableLiveData.setValue(o), e -> Log.d(TAG, "getPosts: " + e));


}

//  public void getPosts() {
//
//        Observable<List<Modelretro>> observable = MoviesData.getINSTANCE().getPosts()
//                .subscribeOn(Schedulers.io())                        // UpStream
//                .observeOn(AndroidSchedulers.mainThread());          // DownStream
//
//
//
//        Observer<List<Modelretro>> observer=new Observer<List<Modelretro>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: pero ");
//            }
//
//            @Override
//            public void onNext(List<Modelretro> modelretros) {
//                Log.d(TAG, "onNext: pero "+modelretros);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: pero"+e );
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: pero");
//            }
//        };
//        observable.subscribe(observer);
//    }



