package irina.com.android_samples.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import irina.com.android_samples.R;
import irina.com.android_samples.interfaces.PhotoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    public interface ShareFragmentCallback {
        void onInfoPress();
    }

    public PhotoItem photoItem;
    public ShareFragmentCallback callback;

    public ShareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        if (photoItem != null) {
            Picasso.get().load(photoItem.getImgUrl()).into(imageView);
        }
        view.findViewById(R.id.infoButton).setOnClickListener((view1) -> {
            callback.onInfoPress();
        });
        return view;
    }

}
