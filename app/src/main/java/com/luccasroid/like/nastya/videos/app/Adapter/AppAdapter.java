package com.luccasroid.like.nastya.videos.app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.luccasroid.like.nastya.videos.app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<AppLive> applist;

    public AppAdapter(Context context, int layout, List<AppLive> applist) {
        this.context = context;
        this.layout = layout;
        this.applist = applist;
    }

    @Override
    public int getCount() {
        return applist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHoder{
        ImageView imgbanner;
        TextView txtTitle;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder hoder;
        if(view==null){
            hoder=new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            hoder.txtTitle=(TextView) view.findViewById(R.id.name);
            hoder.imgbanner=(ImageView) view.findViewById(R.id.banner);
            view.setTag(hoder);

        }else {
            hoder=(ViewHoder) view.getTag();
        }
        AppLive appLive=applist.get(i);
        hoder.txtTitle.setText(appLive.getTitle());
        Picasso.get().load(appLive.getThumb()).into(hoder.imgbanner);

        return view;
    }
}

