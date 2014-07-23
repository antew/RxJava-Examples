package com.antew.rxjava;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.antew.rxjava.data.imgur.api.ImgurService;
import com.antew.rxjava.util.StringUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RxJavaExampleActivity extends Activity {
    public static final String TAG = RxJavaExampleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_example);
    }

    public static class RetainedListFragment extends ListFragment implements Observer<List<String>> {
        @Inject
        ImgurService imgurService;

        private ArrayAdapter<String> adapter;

        public RetainedListFragment() {
            setRetainInstance(true);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            RxJavaApp.get(getActivity()).inject(this);

            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
            setListAdapter(adapter);

            imgurService.getGallery(ImgurService.Section.HOT)
                    .map(gallery -> gallery.getImages())
                    .flatMap(images -> Observable.from(images))
                    .filter(image -> (image.getUps() > 500))
                    .filter(image -> StringUtil.isNotEmpty(image.getTitle()))
                    .flatMap(imgurImage -> Observable.from(imgurImage.getTitle()))
                    .toList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }

        @Override
        public void onCompleted() {
            Toast.makeText(getActivity(), "onCompleted!", Toast.LENGTH_SHORT);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError", e);
        }

        @Override
        public void onNext(List<String> strings) {
            Log.i(TAG, "onNext with " + strings.size());
            adapter.addAll(strings);
        }
    }
}
