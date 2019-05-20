package irina.com.android_samples.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.R;
import irina.com.android_samples.interfaces.PhotoItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    public PhotoItem photoItem;
    public ShareFragment.ShareFragmentListener listener;

    @BindView(R.id.imageAuthor) TextView imageAuthor;
    @BindView(R.id.imageLocation) TextView imageLocation;
    @BindView(R.id.buttonCloseInfo) Button buttonCloseInfo;

    public InfoFragment() {
        // Required empty public constructor
    }

    // Method that would connect callbacks of
    // Fragment to activity, no need to do that
    // Inside activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ShareFragment.ShareFragmentListener) {
            listener = (ShareFragment.ShareFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement ShareFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);
        if (photoItem != null) {
            imageAuthor.setText(photoItem.getUserName());
            imageLocation.setText(photoItem.getLocation());
        }

        // Set button
        buttonCloseInfo.setOnClickListener(button -> {
            listener.onClosePress();
        });

        return view;
    }

}
