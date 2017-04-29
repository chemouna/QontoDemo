package com.myapp.ui.album;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myapp.R;
import com.myapp.model.Photo;
import com.squareup.picasso.Picasso;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>
    implements Consumer<List<Photo>> {

    private final Picasso picasso;
    private List<Photo> photos = new ArrayList<>();

    PhotoAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.photo_item_layout, parent, false);
        return new PhotoAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bind(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    @Override
    public void accept(@NonNull List<Photo> photos) throws Exception {
        this.photos.addAll(photos);
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photoIv) ImageView photoIv;

        PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Photo photo) {
            picasso.load(photo.getUrl()).into(photoIv);
        }
    }
}
