package com.luccasroid.like.nastya.videos.app.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.luccasroid.like.nastya.videos.app.DataBase.DataApp;
import com.luccasroid.like.nastya.videos.app.Play;
import com.luccasroid.like.nastya.videos.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHoder> {
    private Context context;
    ArrayList id,id_video,name,thumb;

    public FavouriteAdapter(Context context, ArrayList id, ArrayList id_video, ArrayList name, ArrayList thumb) {
        this.context = context;
        this.id = id;
        this.id_video = id_video;
        this.name = name;
        this.thumb = thumb;
    }

    @NonNull
    @Override
    public FavouriteAdapter.ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view_menu=inflater.inflate(R.layout.iteam_app,parent,false);
        ViewHoder viewHoder=new ViewHoder(view_menu);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.ViewHoder holder, @SuppressLint("RecyclerView") int position) {
        String ID_VIDEO=(String) id_video.get(position);
        String ID_NAME= (String) name.get(position);
        String ID_THUMB=(String) thumb.get(position);
        Picasso.get().load(ID_THUMB).into(holder.imgbanner);
        holder.txtTitle.setText(ID_NAME);
        holder.app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Play.class);
                intent.putExtra("pl","b");
                intent.putExtra("position",String.valueOf(id.get(position)));
                intent.putExtra("id_video",ID_VIDEO);
                intent.putExtra("name",ID_NAME);


                context.startActivity(intent);
            }
        });
        holder.app.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                DataApp dataApp=new DataApp(context);
                dataApp.deleteData(String.valueOf(position));
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder{
        ImageView imgbanner;
        TextView txtTitle;
        LinearLayout app;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtTitle=(TextView) itemView.findViewById(R.id.name);
           imgbanner=(ImageView) itemView.findViewById(R.id.banner);
           app=itemView.findViewById(R.id.app);

        }
    }
}
