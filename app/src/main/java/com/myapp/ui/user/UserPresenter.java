package com.myapp.ui.user;

import com.myapp.api.TypicodeApi;
import com.myapp.model.Album;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;

class UserPresenter {

    private final TypicodeApi api;
    private final Scheduler observeOnScheduler;
    private final UserScreen screen;
    private Disposable disposable;

    UserPresenter(TypicodeApi api, Scheduler scheduler, UserScreen screen) {
        this.api = api;
        this.observeOnScheduler = scheduler;
        this.screen = screen;
    }

    void fetchAlbums(int userId, Consumer<List<Album>> consumer) {
        disposable = api.getAlbums(userId).observeOn(observeOnScheduler)
            .subscribe(consumer, screen::onError);
    }

    void unbind() {
        disposable.dispose();
    }
}
