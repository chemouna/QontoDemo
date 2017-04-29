package com.myapp.model;

import javax.annotation.concurrent.Immutable;

@Immutable public final class Photo {

    private final int albumId;
    private final int id;
    private final String title;
    private final String url;
    private final String thumbnailUrl;

    public Photo(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
