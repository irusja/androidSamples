package irina.com.android_samples.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import irina.com.android_samples.R;
import irina.com.android_samples.interfaces.PhotoItem;
import irina.com.android_samples.interfaces.PhotoItemsPresenterCallback;

public class ViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private List data;
    private PhotoItemsPresenterCallback callback;

    public ViewAdapter(Context context, int layoutResourceId, List data, PhotoItemsPresenterCallback callback) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        this.callback = callback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, null, false);
        }

        ItemViewHolder itemViewHolder = (ItemViewHolder) convertView.getTag();
        if (itemViewHolder == null) {
            itemViewHolder = new ItemViewHolder(convertView);
            convertView.setTag(itemViewHolder);
        }

        PhotoItem photoItem = (PhotoItem) data.get(position);

        //set favorite button behaviour
        updateFavoriteButton(itemViewHolder.buttonFavorite, photoItem);
        ItemViewHolder finalItemViewHolder = itemViewHolder;
        itemViewHolder.buttonFavorite.setOnClickListener(view -> {
            callback.onItemToggleFavorite(photoItem);//save to DB!!!!
            updateFavoriteButton(finalItemViewHolder.buttonFavorite, photoItem);
        });

        itemViewHolder.imageViewPhotoPicture.setOnClickListener(view -> callback.onItemSelected(photoItem));

        Glide.with(convertView).load(photoItem.getImgUrl()).into(itemViewHolder.imageViewPhotoPicture);
        itemViewHolder.textViewPhotoDescription.setText(photoItem.getUserName());
        itemViewHolder.textViewLocation.setText(photoItem.getLocation());

        return convertView;
    }

    private void updateFavoriteButton(ImageButton imageButton, PhotoItem photoItem) {
        boolean isFavorited = photoItem.isSavedToDatabase();
        if(isFavorited) {
            imageButton.setImageResource(R.drawable.favorite_on);
        } else {
            imageButton.setImageResource(R.drawable.favorite_off);
        }
    }
}
