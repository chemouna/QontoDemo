package com.myapp.ui.users;

import com.myapp.api.TypicodeApi;
import com.myapp.model.User;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import java.util.List;

class UsersPresenter {

    private final TypicodeApi api;
    private final Scheduler observeOnScheduler;

    UsersPresenter(TypicodeApi api, Scheduler observeOnScheduler) {
        this.api = api;
        this.observeOnScheduler = observeOnScheduler;
    }

    //TODO: handle displaying errors & compositeSubscription
    void fetchUsers(Consumer<List<User>> consumer) {
        api.getUsers().observeOn(observeOnScheduler).subscribe(consumer);
    }
}
