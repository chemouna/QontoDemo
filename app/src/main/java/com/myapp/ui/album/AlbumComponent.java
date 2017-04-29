package com.myapp.ui.album;

import com.myapp.annotation.ScopeSingleton;
import dagger.Subcomponent;

@ScopeSingleton(AlbumComponent.class)
@Subcomponent(modules = { AlbumModule.class })
public interface AlbumComponent {

    void inject(AlbumView albumView);
}
