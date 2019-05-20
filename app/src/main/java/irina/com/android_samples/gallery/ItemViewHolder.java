package irina.com.android_samples.gallery;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.R;

public class ItemViewHolder {

    @BindView(R.id.photoPicture) ImageView imageViewPhotoPicture;
    @BindView(R.id.photoDescription) TextView textViewPhotoDescription;
    @BindView(R.id.photoLocation) TextView textViewLocation;
    @BindView(R.id.buttonFavorite) ImageButton buttonFavorite;

    public ItemViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
