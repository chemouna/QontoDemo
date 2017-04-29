package com.myapp;

import com.myapp.api.ApiModule;
import com.myapp.ui.album.AlbumComponent;
import com.myapp.ui.album.AlbumModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { AppModule.class, ApiModule.class }) public interface AppComponent
    extends InjectGraph {

    AlbumComponent plus(AlbumModule albumModule);

    final class Initializer {
        private Initializer() {
        }

        static AppComponent init(MyApp app) {
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }
}
