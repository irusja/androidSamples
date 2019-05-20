package irina.com.android_samples.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.R;
import irina.com.android_samples.interfaces.PhotoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShareFragment extends Fragment {

    public interface ShareFragmentListener {
        void onInfoPress();
        void onSharePress();
        void onClosePress();
    }

    public PhotoItem photoItem;
    public ShareFragmentListener listener;

    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.textViewAuthor) TextView textViewAuthor;
    @BindView(R.id.buttonShare) Button buttonShare;
    @BindView(R.id.buttonInfo) Button buttonInfo;

    public ShareFragment() {
        // Required empty public constructor
    }

    // Method that would connect callbacks of
    // Fragment to activity, no need to do that
    // Inside activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShareFragmentListener) {
            listener = (ShareFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement ShareFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        ButterKnife.bind(this, view);

        if (photoItem != null) {
            Glide.with(view).load(photoItem.getImgUrl()).into(this.imageView);
            textViewAuthor.setText(photoItem.getUserName());
        }

        // Set buttons
        buttonShare.setOnClickListener(button -> {
            listener.onSharePress();
        });
        buttonInfo.setOnClickListener(button -> {
            listener.onInfoPress();
        });

        return view;
    }

}
