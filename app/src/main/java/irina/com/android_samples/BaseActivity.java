package irina.com.android_samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import irina.com.android_samples.interfaces.PhotoItem;

public class BaseActivity extends Activity {

    private static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";
    protected static String COMMENT_KEY = "COMMENT_KEY";
    protected static int INTENT_SHARE_CODE = 888;

    protected PhotoItem photoItem;

    public static Intent buildIntent(Context context, PhotoItem photoItem, Class className) {
        Intent intent = new Intent(context, className);
        intent.putExtra(PHOTO_ITEM_KEY, photoItem);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.photoItem = (PhotoItem) getIntent().getSerializableExtra(PHOTO_ITEM_KEY);
    }
}
