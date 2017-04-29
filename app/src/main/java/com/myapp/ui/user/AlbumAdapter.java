package com.myapp.ui.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.R;
import com.myapp.model.Album;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>
    implements Consumer<List<Album>> {

    private List<Album> albums = new ArrayList<>();

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.album_item_layout, parent, false);
        return new AlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        holder.bind(albums.get(position));
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public void accept(@NonNull List<Album> albums) throws Exception {
        this.albums.addAll(albums);
        notifyDataSetChanged();
    }

    public Album getAlbum(int position) {
        return albums.get(position);
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView titleTv;

        AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Album album) {
            titleTv.setText(album.getTitle());
        }
    }
}
