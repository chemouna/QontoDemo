package com.myapp.ui.users;

import com.myapp.api.TypicodeApi;
import com.myapp.data.UsersManager;
import com.myapp.model.User;
import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;

class UsersPresenter {

    private final TypicodeApi api;
    private final UsersManager usersManager;
    private final Scheduler observeOnScheduler;
    private final UsersScreen screen;
    private Disposable disposable;

    UsersPresenter(TypicodeApi api, UsersManager usersManager, Scheduler observeOnScheduler,
        UsersScreen screen) {
        this.api = api;
        this.usersManager = usersManager;
        this.observeOnScheduler = observeOnScheduler;
        this.screen = screen;
    }

    void fetchUsers(Consumer<List<User>> consumer, boolean isNetworkAvailable) {
        if (isNetworkAvailable) {
            disposable = api.getUsers()
                .observeOn(observeOnScheduler)
                .doOnNext(usersManager::save)
                .subscribe(consumer, screen::onError);
        } else {
            disposable = RxJavaInterop.toV2Observable(usersManager.getUsers())
                .observeOn(observeOnScheduler)
                .subscribe(consumer, screen::onError);
        }
    }

    void unbind() {
        disposable.dispose();
    }
}
