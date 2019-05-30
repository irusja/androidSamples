package irina.com.android_samples.presenters.recyclerViewPresenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.R;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderRecyclerView> {

    List<PhotoItem> photoItems;
    private PhotoItemsPresenterCallback callback;

    public Adapter(List<PhotoItem> photoItems, PhotoItemsPresenterCallback callback) {
        this.photoItems = photoItems;
        this.callback = callback;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolderRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_item, parent, false);
        return new ViewHolderRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderRecyclerView holder, int position) {
        PhotoItem photoItem = this.photoItems.get(position);

        //set favorite button behaviour
        updateFavoriteButton(holder.buttonFavorite, photoItem);
        ViewHolderRecyclerView finalItemViewHolder = holder;
        holder.buttonFavorite.setOnClickListener(view -> {
            callback.onItemToggleFavorite(photoItem);
            updateFavoriteButton(finalItemViewHolder.buttonFavorite, photoItem);
        });

        holder.textViewLocation.setText(photoItem.getLocation());
        holder.textViewAuthor.setText(photoItem.getUserName());
        Glide.with(holder.itemView).load(photoItem.getImgUrl()).placeholder(R.drawable.placeholder).into(holder.imageViewPhoto);
    }

    @Override
    public int getItemCount() {
        return this.photoItems.size();
    }

    private void updateFavoriteButton(ImageButton imageButton, PhotoItem photoItem) {
        boolean isFavorited = photoItem.isSavedToDatabase();
        if(isFavorited) {
            imageButton.setImageResource(R.drawable.favorite_on);
        } else {
            imageButton.setImageResource(R.drawable.favorite_off);
        }
    }

    public static class ViewHolderRecyclerView extends RecyclerView.ViewHolder {

        @BindView(R.id.photoPicture)
        public ImageView imageViewPhoto;
        @BindView(R.id.photoDescription)
        public TextView textViewAuthor;
        @BindView(R.id.photoLocation)
        TextView textViewLocation;
        @BindView(R.id.buttonFavorite)
        public ImageButton buttonFavorite;

        ViewHolderRecyclerView(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
