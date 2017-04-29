package com.myapp.ui.album;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.myapp.model.Album;

public class AlbumView extends LinearLayout {

    public AlbumView(Context context) {
        this(context, null);
    }

    public AlbumView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlbumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void display(Album album) {

    }
}
