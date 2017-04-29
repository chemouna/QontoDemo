package com.myapp.data;

import com.myapp.model.User;
import com.squareup.sqlbrite.BriteDatabase;
import java.util.List;
import rx.Observable;

/**
 * Contains the model logic for users.
 */
public class UsersManager {

    private final BriteDatabase db;

    public UsersManager(BriteDatabase db) {
        this.db = db;
    }

    public Observable<List<User>> getUsers() {
        return db.createQuery(User.TABLE, User.GET_USERS)
            .mapToList(User.MAPPER);
    }

    public void save(List<User> users) {
        for (User user : users) {
            user.insertInto(db);
        }
    }
}
