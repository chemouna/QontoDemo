package com.myapp.ui.album;

import android.content.Context;
import android.net.Uri;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.myapp.annotation.ApplicationContext;
import com.myapp.annotation.ScopeSingleton;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module public class AlbumModule {

    @Provides
    @ScopeSingleton(AlbumComponent.class)
    Picasso providePicasso(@ApplicationContext Context context, OkHttpClient client) {
        return new Picasso.Builder(context).loggingEnabled(true)
            .downloader(new OkHttp3Downloader(client))
            .listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                    exception.printStackTrace();
                }
            })
            .build();
    }
}
