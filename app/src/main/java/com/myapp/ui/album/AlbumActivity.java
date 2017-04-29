package com.myapp.ui.album;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.R;
import com.myapp.model.Album;

public class AlbumActivity extends AppCompatActivity {

    private static final String EXTRA_ALBUM = "extra_album";
    @BindView(R.id.album_view) AlbumView albumView;
    private Album album;

    public static void start(Context context, Album album) {
        Intent intent = new Intent(context, AlbumActivity.class);
        intent.putExtra(EXTRA_ALBUM, album);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_activity);
        ButterKnife.bind(this);

        if (!getIntent().hasExtra(EXTRA_ALBUM)) {
            throw new IllegalArgumentException("Can't display album screen without an album.");
        }

        album = getIntent().getParcelableExtra(EXTRA_ALBUM);
        setTitle(album.getTitle());
        albumView.display(album);
    }
}
