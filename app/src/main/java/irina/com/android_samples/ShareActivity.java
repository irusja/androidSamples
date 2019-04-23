package irina.com.android_samples;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.interfaces.PhotoItem;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.textViewAuthor) TextView textViewAuthor;
    @BindView(R.id.imageView) ImageView imageView;

    private static String PHOTO_ITEM_KEY = "PHOTO_ITEM_KEY";
    private static String TEXT_VIEW_DATA_KEY = "TEXT_VIEW_DATA_KEY";

    protected PhotoItem photoItem;

    public static Intent buildIntent(Context context, PhotoItem photoItem) {
        Intent intent = new Intent(context, ShareActivityWithFragment.class); //should be ShareActivity
        intent.putExtra(PHOTO_ITEM_KEY, photoItem);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);

        if(getIntent().getSerializableExtra(PHOTO_ITEM_KEY) != null) {
            this.photoItem = (PhotoItem) getIntent().getSerializableExtra(PHOTO_ITEM_KEY);
            this.textViewAuthor.setText(photoItem.getUserName());
            Picasso.get().load(photoItem.getImgUrl()).into(this.imageView);
        }

    }

//    public void onSetButtonClick(View view) {
//        textView.setText(editText.getText());
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) { //before screen rotation
        super.onSaveInstanceState(outState);
        //outState.putCharSequence(TEXT_VIEW_DATA_KEY, textView.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) { //after screen rotation
        super.onRestoreInstanceState(savedInstanceState);
        //textView.setText(savedInstanceState.getCharSequence(TEXT_VIEW_DATA_KEY));
    }
}
