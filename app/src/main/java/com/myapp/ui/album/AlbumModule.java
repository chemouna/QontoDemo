package com.myapp.ui.album;

import android.content.Context;
import com.myapp.annotation.ApplicationContext;
import com.myapp.annotation.ScopeSingleton;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;

@Module public class AlbumModule {

    @Provides
    @ScopeSingleton(AlbumComponent.class)
    Picasso providePicasso(@ApplicationContext Context context) {
        return Picasso.with(context);
    }
}
