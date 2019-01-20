package beks.androidcourse.kz.aida.controller;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import beks.androidcourse.kz.aida.R;
public class BlogView extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blog_view);
        ImageView but1 = findViewById(R.id.sub);
        final ImageView but2 = findViewById(R.id.sub2);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.alpha);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                but2.startAnimation(animation);
                v.setVisibility(View.GONE);
                but2.setVisibility(View.GONE);

            }
        });
    }
}
