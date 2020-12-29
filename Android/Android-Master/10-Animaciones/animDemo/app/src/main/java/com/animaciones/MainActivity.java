package com.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvFadeAnim;    //1

    private AnimationDrawable animationDrawable;    //2
    private ImageView ivDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFadeAnim = findViewById(R.id.tvFadeAnim);     //1

        ivDrawable = findViewById(R.id.ivDrawable);     //2
        ivDrawable.setBackgroundResource(R.drawable.anim_check);
        animationDrawable = (AnimationDrawable) ivDrawable.getBackground();

    }

    public void startFadeAnimation(View view) {
        if(tvFadeAnim.getVisibility() == View.VISIBLE) tvFadeAnim.setVisibility(View.GONE);
        else tvFadeAnim.setVisibility(View.VISIBLE);
    }   //1

    public void startDrawableAnimation(View view) {
        animationDrawable.start();
    }    //2
}