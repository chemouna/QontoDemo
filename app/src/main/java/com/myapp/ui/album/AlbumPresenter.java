package com.myapp.ui.album;

import com.myapp.api.TypicodeApi;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

class AlbumPresenter {

    private final TypicodeApi api;
    private final Scheduler observeOnScheduler;
    private Disposable disposable;

    AlbumPresenter(TypicodeApi api, Scheduler observeOnScheduler) {
        this.api = api;
        this.observeOnScheduler = observeOnScheduler;
    }

    void fetchPhotos(int albumId, PhotoAdapter photoAdapter) {
        disposable = api.getPhotos(albumId).observeOn(observeOnScheduler).subscribe(photoAdapter);
    }

    void unbind() {
        disposable.dispose();
    }
}
