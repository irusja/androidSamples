package irina.com.android_samples.gallery;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.R;

public class GridItemViewHolder {

    @BindView(R.id.photoPicture) ImageView imageViewPhotoPicture;
    @BindView(R.id.photoDescription) TextView textViewPhotoDescription;
    @BindView(R.id.photoLocation) TextView textViewLocation;

    public GridItemViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
