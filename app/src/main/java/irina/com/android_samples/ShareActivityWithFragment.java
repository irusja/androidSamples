package irina.com.android_samples;

import android.app.FragmentTransaction;
import android.os.Bundle;

import irina.com.android_samples.fragments.InfoFragment;
import irina.com.android_samples.fragments.ShareFragment;

public class ShareActivityWithFragment extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_fragment);

        showShareFragment();
    }

    private void showShareFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        ShareFragment fragment = new ShareFragment();
        fragment.photoItem = this.photoItem;
        fragment.callback = () -> showInfoFragment();

        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    private void showInfoFragment() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        InfoFragment fragment = new InfoFragment();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

}
