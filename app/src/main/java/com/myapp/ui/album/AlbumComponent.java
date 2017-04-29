package com.myapp.ui.album;

import com.myapp.annotation.ScopeSingleton;
import dagger.Subcomponent;

/**
 * Scoped component to provide dependencies specific to album screen (Picasso for example is needed
 * only for album screen so we provide it only during the lifetime of the album screen).
 *
 * @see AlbumView and {@link AlbumModule}
 */
@ScopeSingleton(AlbumComponent.class) @Subcomponent(modules = { AlbumModule.class })
public interface AlbumComponent {

    void inject(AlbumView albumView);
}
