package irina.com.android_samples;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderCar {

    @BindView(R.id.carPicture) ImageView imageViewCarPicture;
    @BindView(R.id.carDescription) TextView textViewCarDescription;

    public ViewHolderCar(View view) {
        ButterKnife.bind(this, view);
    }
}
