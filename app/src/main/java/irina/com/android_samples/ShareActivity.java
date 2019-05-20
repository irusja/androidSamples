package irina.com.android_samples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import irina.com.android_samples.interfaces.PhotoItem;

public class ShareActivity extends BaseActivity {

    @BindView(R.id.textViewAuthor) TextView textViewAuthor;
    @BindView(R.id.imageView) ImageView imageView;

    protected PhotoItem photoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI() {
        this.textViewAuthor.setText(photoItem.getUserName());
        Glide.with(this).load(photoItem.getImgUrl()).into(this.imageView);
    }

    public void onShareButton(View view) {
        // Create intent with action
        Intent i = new Intent(Intent.ACTION_SEND);
        // Set additions
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL");
        i.putExtra(Intent.EXTRA_TEXT, photoItem.getImgUrl());
        // Start intent
        startActivityForResult(Intent.createChooser(i, "Share URL"),INTENT_SHARE_CODE,null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) { //before screen rotation
        super.onSaveInstanceState(outState);
        outState.putCharSequence(COMMENT_KEY, textViewAuthor.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) { //after screen rotation
        super.onRestoreInstanceState(savedInstanceState);
        textViewAuthor.setText(savedInstanceState.getCharSequence(COMMENT_KEY));
    }
}
