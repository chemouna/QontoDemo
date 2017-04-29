package com.myapp.ui.user;

import android.content.Context;
import android.support.annotation.Nullable;
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
import com.myapp.model.User;
import com.myapp.ui.album.AlbumActivity;
import com.myapp.ui.recyclerview.ClickItemTouchListener;
import com.myapp.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.myapp.ui.recyclerview.decoration.DividerItemDecoration;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;

public class UserView extends LinearLayout implements UserScreen {

    @Inject TypicodeApi api;

    @BindView(R.id.albumsRv) RecyclerViewWithEmptyProgress albumsRv;
    @BindView(R.id.tv_empty) TextView tvEmpty;
    @BindView(R.id.progress) ProgressBar progressBar;
    @BindDimen(R.dimen.divider_padding_start) float dividerPaddingStart;

    UserPresenter presenter;
    private AlbumAdapter albumAdapter;

    public UserView(Context context) {
        this(context, null);
    }

    public UserView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UserView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final View view = LayoutInflater.from(context).inflate(R.layout.user_view, this, true);
        ButterKnife.bind(this, view);
        MyApp.getAppComponent().inject(this);

        presenter = new UserPresenter(api, AndroidSchedulers.mainThread(), this);
        setupAlbumsRv();
    }

    private void setupAlbumsRv() {
        albumsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        albumAdapter = new AlbumAdapter();
        albumsRv.setAdapter(albumAdapter);
        albumsRv.setEmptyView(tvEmpty);
        albumsRv.setProgress(progressBar);

        albumsRv.addItemDecoration(
            new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL,
                dividerPaddingStart, false));

        albumsRv.addOnItemTouchListener(new ClickItemTouchListener(albumsRv) {
            @Override
            protected boolean performItemClick(RecyclerView parent, View view, int position,
                long id) {
                AlbumActivity.start(getContext(), albumAdapter.getAlbum(position));
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

    public void display(User user) {
        presenter.fetchAlbums(user.getId(), albumAdapter);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.unbind();
        super.onDetachedFromWindow();
    }

    @Override
    public void onError(Throwable throwable) {
        Snackbar.make(albumsRv, R.string.error_occured, Snackbar.LENGTH_LONG).show();
    }
}
