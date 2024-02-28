package com.luccasroid.like.nastya.videos.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.luccasroid.like.nastya.videos.app.Fragment.FavouriteFragment;
import com.luccasroid.like.nastya.videos.app.Fragment.HomeFragment;

public class MainApp extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        navigationView=findViewById(R.id.my_nav_view);
        drawerLayout = findViewById(R.id.drawapp);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open,
                R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fagment_layout, new HomeFragment())
                .commit();
        getSupportActionBar().setTitle("Like Nastya Nova");
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemID=item.getItemId();
        if(itemID==R.id.fag1){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fagment_layout, new HomeFragment())
                    .commit();
            getSupportActionBar().setTitle("Like Nastya Nova");
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (itemID==R.id.fag2) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fagment_layout, new FavouriteFragment())
                    .commit();
            getSupportActionBar().setTitle("Favourites");
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(itemID==R.id.menu_star){
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.luccasroid.like.nastya.videos.app")));
        }
        else if(itemID==R.id.menu_info){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://eclipse.aimed.xyz/privacy_en.html")));
            drawerLayout.closeDrawer(GravityCompat.START);
        }else if(itemID==R.id.menu_share){
            String txt="Hãy tải ngay app để tận hưởng trải nghiệm dụng cùng chúng tôi !"+"\n"+"https://play.google.com/store/apps/details?id=com.luccasroid.like.nastya.videos.app";
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,txt);
            intent.setType("text/plan");
            startActivity(intent);
        }else if(itemID==R.id.logout){
            OutApp();
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {}
        return false;
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;

        }
        return  super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // out app
    public  void OutApp(){
        Dialog dialog=new Dialog(this);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout);
        TextView yes=dialog.findViewById(R.id.txt_yes);
        TextView no=dialog.findViewById(R.id.txt_no);
        yes.setOnClickListener(view->{
            finishAffinity();
        });
        no.setOnClickListener(view->{
            dialog.dismiss();
        });
        dialog.show();
    }
}