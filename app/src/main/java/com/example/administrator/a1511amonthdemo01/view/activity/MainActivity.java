package com.example.administrator.a1511amonthdemo01.view.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.administrator.a1511amonthdemo01.R;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.image);

        //属性动画
        //移动
        ObjectAnimator translation = ObjectAnimator.ofFloat(image, "translationY", 0f,500f);
        //缩放
        ObjectAnimator scale = ObjectAnimator.ofFloat(image, "scaleX", 2f, 1f);
        //透明
        ObjectAnimator alpha = ObjectAnimator.ofFloat(image, "alpha", 0f, 1f);
        //旋转
        ObjectAnimator rotation = ObjectAnimator.ofFloat(image, "rotation", 360, 0);

        AnimatorSet set = new AnimatorSet();
        set.play(translation).with(scale).with(alpha).with(rotation);
        set.setDuration(3000);
        set.start();


        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
              //动画结束的时候跳转到详情页面
                finish();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                //销毁当前页面

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
