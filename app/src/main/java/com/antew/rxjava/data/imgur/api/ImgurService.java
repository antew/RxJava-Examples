package com.antew.rxjava.data.imgur.api;

import com.antew.rxjava.data.imgur.model.ImgurGallery;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ImgurService {

    @GET("/gallery/{section}")
    Observable<ImgurGallery> getGallery(@Path("section") Section section);

    enum Section {
        HOT("hot"), TOP("top"), USER("user");
        private final String section;

        Section(String section) {
            this.section = section;
        }


        @Override
        public String toString() {
            return section;
        }
    }
}
