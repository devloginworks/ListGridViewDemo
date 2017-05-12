package com.loginworks.gridwithlistviewdemo.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loginworks.gridwithlistviewdemo.R;

public class ShareElementActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageUrl;
    private TextView txtRevealAnimation;
    private RelativeLayout shareElement;
    private String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element);
        mImageUrl = (ImageView) findViewById(R.id.imgFullItem);
        txtRevealAnimation = (TextView) findViewById(R.id.txtRevealAnimation);
        shareElement = (RelativeLayout) findViewById(R.id.activity_share_element);

        Intent intent = getIntent();
        imageUrl = intent.getStringExtra("imageurl");
        Glide.with(this)
                .load(imageUrl)
                .into(mImageUrl);

        shareElement.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();

                makeCircularRevealAnimation(shareElement, x, y);

                return false;
            }
        });

    }

    private void makeCircularRevealAnimation(View view, int x, int y) {


        int centerX = (view.getLeft() + view.getRight()) / 2;//For takting circle  positon of view
        int centerY = (view.getTop() + view.getBottom()) / 2;

        // You can replace centerX,centerY from x , y;

        float radius = Math.max(mImageUrl.getWidth(), mImageUrl.getHeight()) * 2.0f;

        if (mImageUrl.getVisibility() == View.INVISIBLE) {
            mImageUrl.setVisibility(View.VISIBLE);



            ViewAnimationUtils.createCircularReveal(mImageUrl, x, y, 0, radius).start();
        } else {
            Animator reveal = ViewAnimationUtils.createCircularReveal(mImageUrl, x, y, radius, 0);
            reveal.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    mImageUrl.setVisibility(View.INVISIBLE);
                }
            });
            reveal.start();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_share_element:

                //   makeCircularRevealAnimation(shareElement);
                break;


        }
    }
}
