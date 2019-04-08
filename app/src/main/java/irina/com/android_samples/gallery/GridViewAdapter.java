package irina.com.android_samples.gallery;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

import irina.com.android_samples.interfaces.PhotoItem;

public class GridViewAdapter extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private List data;

    public GridViewAdapter(Context context, int layoutResourceId, List data) {
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

        GridItemViewHolder gridItemViewHolder = (GridItemViewHolder) convertView.getTag();
        if (gridItemViewHolder == null) {
            gridItemViewHolder = new GridItemViewHolder(convertView);
            convertView.setTag(gridItemViewHolder);
        }

        PhotoItem photoItem = (PhotoItem) data.get(position);
        Picasso.get().load(photoItem.getImgUrl()).into(gridItemViewHolder.imageViewPhotoPicture);
        gridItemViewHolder.textViewPhotoDescription.setText(photoItem.getUserName());
        gridItemViewHolder.textViewLocation.setText(photoItem.getLocation());

        return convertView;
    }
}
