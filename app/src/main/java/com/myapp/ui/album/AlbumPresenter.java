package com.myapp.ui.album;

import com.myapp.api.TypicodeApi;
import io.reactivex.Scheduler;

class AlbumPresenter {

    private final TypicodeApi api;
    private final Scheduler observeOnScheduler;

    AlbumPresenter(TypicodeApi api, Scheduler observeOnScheduler) {
        this.api = api;
        this.observeOnScheduler = observeOnScheduler;
    }

    void fetchPhotos(int albumId, PhotoAdapter photoAdapter) {
        api.getPhotos(albumId).observeOn(observeOnScheduler).subscribe(photoAdapter);
    }
}
