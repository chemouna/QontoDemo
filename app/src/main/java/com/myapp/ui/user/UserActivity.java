package com.myapp.ui.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.R;
import com.myapp.model.User;

public class UserActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "user";
    private User user;

    @BindView(R.id.user_view) UserView userView;

    public static void start(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra(EXTRA_USER, user);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        ButterKnife.bind(this);

        if (!getIntent().hasExtra(EXTRA_USER)) {
            throw new IllegalArgumentException("Can't display user screen without a user.");
        }

        user = getIntent().getParcelableExtra(EXTRA_USER);
        setTitle(user.getName());
        userView.display(user);
    }

}
