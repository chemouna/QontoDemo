package com.myapp.ui.user;

import com.myapp.api.TypicodeApi;
import com.myapp.model.Album;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import java.util.List;

public class UserPresenter {

    private final TypicodeApi api;
    private final Scheduler observeOnScheduler;

    public UserPresenter(TypicodeApi api, Scheduler scheduler) {
        this.api = api;
        observeOnScheduler = scheduler;
    }

    public void fetchAlbums(int userId, Consumer<List<Album>> consumer) {
        api.getAlbums(userId).observeOn(observeOnScheduler).subscribe(consumer);
    }
}
