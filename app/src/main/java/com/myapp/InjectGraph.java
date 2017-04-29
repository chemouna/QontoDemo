package com.myapp;

import com.myapp.ui.user.UserView;
import com.myapp.ui.users.UsersView;

public interface InjectGraph {
    void inject(MyApp myApp);

    void inject(UsersView usersView);

    void inject(UserView userView);
}
