package com.myapp.ui.users;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.MyApp;
import com.myapp.R;
import com.myapp.api.TypicodeApi;
import com.myapp.ui.recyclerview.ClickItemTouchListener;
import com.myapp.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.myapp.ui.user.UserActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;

public class UsersView extends LinearLayout implements UsersScreen {

    private UserAdapter userAdapter;
    private UsersPresenter presenter;

    @Inject TypicodeApi api;

    @BindView(R.id.usersRv) RecyclerViewWithEmptyProgress usersRv;
    @BindView(R.id.tv_empty) TextView tvEmpty;
    @BindView(R.id.progress) ProgressBar progressBar;

    public UsersView(Context context) {
        this(context, null);
    }

    public UsersView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UsersView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view = LayoutInflater.from(context).inflate(R.layout.users_view, this, true);

        ButterKnife.bind(this, view);
        MyApp.getAppComponent().inject(this);

        presenter = new UsersPresenter(api, AndroidSchedulers.mainThread());
        setupUsersRv();
        presenter.fetchUsers(userAdapter);
    }

    private void setupUsersRv() {
        usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
        userAdapter = new UserAdapter();
        usersRv.setAdapter(userAdapter);
        usersRv.setEmptyView(tvEmpty);
        usersRv.setProgress(progressBar);

        usersRv.addOnItemTouchListener(new ClickItemTouchListener(usersRv) {
            @Override
            protected boolean performItemClick(RecyclerView parent, View view, int position,
                long id) {
                UserActivity.start(getContext(), userAdapter.getUser(position));
                return true;
            }

            @Override
            public boolean performItemLongClick(RecyclerView parent, View view, int position,
                long id) {
                //NO-OP
                return false;
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                //NO-OP
            }
        });
    }
}
