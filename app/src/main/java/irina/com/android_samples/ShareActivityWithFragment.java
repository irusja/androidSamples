package irina.com.android_samples;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import irina.com.android_samples.fragments.InfoFragment;
import irina.com.android_samples.fragments.ShareFragment;

public class ShareActivityWithFragment extends BaseActivity implements ShareFragment.ShareFragmentListener {

    //TODO 1: Pass PhotoImage object to info InfoFragment
    //TODO 2: Show on InfoFragment UI author name
    //TODO 3: Add button "close info" to InfoFragment
    //TODO 4: Add "close info" button press listener to ShareActivityWithFragments
    //TODO 5: Handle "close info" in ShareActivityWithFragments, on its press show ShareFragment
    //TODO 6 Extra*: Handle back button in fragments :)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_with_fragment);

        showShareFragment();
    }

    private void showShareFragment() {
        // Get fragment manager and begin replacing
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // Create fragment to show
        ShareFragment fragment = new ShareFragment();
        // Assign needed values
        fragment.photoItem = this.photoItem;
        // Set fragment to layout
        ft.replace(R.id.frameLayout, fragment).addToBackStack(null);
        // Perform changes
        ft.commit();
    }

    private void showInfoFragment() {
        // Get fragment manager and begin replacing
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // Create fragment to show
        InfoFragment fragment = new InfoFragment();
        // Pass PhotoImage object to info InfoFragment
        fragment.photoItem = this.photoItem;
        // Set fragment to layout
        ft.replace(R.id.frameLayout, fragment).addToBackStack(null);
        // Perform changes
        ft.commit();
    }

    @Override
    public void onInfoPress() {
        showInfoFragment();
    }

    @Override
    public void onSharePress() {
        // Create intent with action
        Intent i = new Intent(Intent.ACTION_SEND);
        // Set additions
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        // Start intent
        startActivity(Intent.createChooser(i, "Share URL"));
    }

    @Override
    public void onClosePress() {
        showShareFragment();
    }

//    @Override
//    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
//    }

    /// lecture 7//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //call showFragment with flag value dependency
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
