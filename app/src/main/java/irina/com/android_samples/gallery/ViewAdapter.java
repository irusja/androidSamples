package irina.com.android_samples.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import irina.com.android_samples.interfaces.PhotoItem;

public class ViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private List data;

    public ViewAdapter(Context context, int layoutResourceId, List data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
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
        Glide.with(convertView).load(photoItem.getImgUrl()).into(itemViewHolder.imageViewPhotoPicture);
        //Picasso.get().load(photoItem.getImgUrl()).into(itemViewHolder.imageViewPhotoPicture);
        itemViewHolder.textViewPhotoDescription.setText(photoItem.getUserName());
        itemViewHolder.textViewLocation.setText(photoItem.getLocation());

        return convertView;
    }
}
