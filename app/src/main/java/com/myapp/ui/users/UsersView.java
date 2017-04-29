package com.myapp.ui.users;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.MyApp;
import com.myapp.R;
import com.myapp.api.TypicodeApi;
import com.myapp.data.UsersManager;
import com.myapp.ui.recyclerview.ClickItemTouchListener;
import com.myapp.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.myapp.ui.recyclerview.decoration.DividerItemDecoration;
import com.myapp.ui.user.UserActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;

public class UsersView extends LinearLayout implements UsersScreen {

    @Inject TypicodeApi api;
    @Inject UsersManager usersManager;

    @BindView(R.id.usersRv) RecyclerViewWithEmptyProgress usersRv;
    @BindView(R.id.tv_empty) TextView tvEmpty;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindDimen(R.dimen.divider_padding_start) float dividerPaddingStart;

    private UserAdapter userAdapter;
    private UsersPresenter presenter;

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

        presenter = new UsersPresenter(api, usersManager, AndroidSchedulers.mainThread(), this);
        setupUsersRv();

        presenter.fetchUsers(userAdapter, isNetworkAvailable());

    }

    private void setupUsersRv() {
        usersRv.setLayoutManager(new LinearLayoutManager(getContext()));
        userAdapter = new UserAdapter();
        usersRv.setAdapter(userAdapter);
        usersRv.setEmptyView(tvEmpty);
        usersRv.setProgress(progressBar);

        usersRv.addItemDecoration(
            new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL,
                dividerPaddingStart, false));

        usersRv.addOnItemTouchListener(new ClickItemTouchListener(usersRv) {
            @Override
            protected boolean performItemClick(RecyclerView parent, View view, int position,
                long id) {
                if(isNetworkAvailable()) {
                    UserActivity.start(getContext(), userAdapter.getUser(position));
                }
                else {
                    displayError(R.string.network_unavailable);
                }
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

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
            = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.unbind();
        super.onDetachedFromWindow();
    }

    @Override
    public void onError(Throwable throwable) {
        if(isNetworkAvailable()) {
            displayError(R.string.error_occured);
        }
        else {
            displayError(R.string.network_unavailable);
        }
    }

    private void displayError(@StringRes int resId) {
        Snackbar.make(usersRv, resId, Snackbar.LENGTH_LONG).show();
    }
}
