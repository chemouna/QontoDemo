package com.myapp.ui.album;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.myapp.model.Album;
import com.myapp.ui.recyclerview.RecyclerViewWithEmptyProgress;
import com.myapp.ui.recyclerview.decoration.OffsetDecoration;
import com.squareup.picasso.Picasso;
import io.reactivex.android.schedulers.AndroidSchedulers;
import javax.inject.Inject;

public class AlbumView extends LinearLayout implements AlbumScreen {

    @BindView(R.id.photoRv) RecyclerViewWithEmptyProgress photosRv;
    @BindView(R.id.tv_empty) TextView tvEmpty;
    @BindView(R.id.progress) ProgressBar progressBar;

    @Inject TypicodeApi api;
    @Inject Picasso picasso;

    AlbumPresenter presenter;
    private PhotoAdapter photoAdapter;

    public AlbumView(Context context) {
        this(context, null);
    }

    public AlbumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlbumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final View view = LayoutInflater.from(context).inflate(R.layout.album_view, this, true);

        ButterKnife.bind(this, view);

        MyApp.getAppComponent().plus(new AlbumModule()).inject(this);

        presenter = new AlbumPresenter(api, AndroidSchedulers.mainThread());
        setupPhotosRv();
    }

    private void setupPhotosRv() {
        photosRv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        photoAdapter = new PhotoAdapter(picasso);
        photosRv.setAdapter(photoAdapter);
        photosRv.setEmptyView(tvEmpty);
        photosRv.setProgress(progressBar);

        final int spacing = getResources().getDimensionPixelSize(R.dimen.spacing_very_small);
        photosRv.addItemDecoration(new OffsetDecoration(spacing));
    }

    public void display(Album album) {
        //TODO: handle error cases
        presenter.fetchPhotos(album.getId(), photoAdapter);
    }
}
