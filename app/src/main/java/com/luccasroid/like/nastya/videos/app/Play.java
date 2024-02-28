package com.luccasroid.like.nastya.videos.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.luccasroid.like.nastya.videos.app.DataBase.DataApp;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class Play extends AppCompatActivity {
    //khai báo
    YouTubePlayerView youtube;
    String id="";
    String name;
    ImageView tym;
    DataApp dataApp;
    boolean isLove=true;
    String position;
    TextView title;
    String thumb;
    String pl;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play_video);
        tym=findViewById(R.id.tym);
        title=findViewById(R.id.title);
        dataApp=new DataApp(this);
        pl=getIntent().getStringExtra("pl");

    //chạy video
       youtube=(YouTubePlayerView)findViewById(R.id.youtube);
        this.getLifecycle().addObserver(youtube);
        youtube.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady( YouTubePlayer youTubePlayer) {
                Intent intent=getIntent();
                id=intent.getStringExtra("id_video");
                String videoId = id;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });
        name=getIntent().getStringExtra("name");
        position=getIntent().getStringExtra("position");
        thumb=getIntent().getStringExtra("thumb");
        title.setText(name);
        if(pl.equals("a")){
            tym.setVisibility(View.VISIBLE);
        }else {
            tym.setBackgroundResource(R.drawable.baseline_delete_24);
        }

        tym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(pl.equals("a")){
                   if(isLove){
                       tym.setBackgroundResource(R.drawable.tym_do);
                       dataApp.insetData(id,name,thumb);
                       Toast.makeText(Play.this, "Bạn đã thêm món này vào danh sách video yêu thích !", Toast.LENGTH_SHORT).show();
                       isLove=false;
                   }else {
                       Toast.makeText(Play.this, "Bạn đã xáo video khỏi danh sách yêu thích !", Toast.LENGTH_SHORT).show();
                       tym.setBackgroundResource(R.drawable.baseline_favorite_24);
                       dataApp.deleteData(position);
                   }
               }else if(pl.equals("b")) {
                   Toast.makeText(Play.this, "Bạn đã xáo video khỏi danh sách yêu thích !", Toast.LENGTH_SHORT).show();
                   dataApp.deleteData(position);

               }else {}

            }
        });

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Play.this,MainApp.class));
    }
}
