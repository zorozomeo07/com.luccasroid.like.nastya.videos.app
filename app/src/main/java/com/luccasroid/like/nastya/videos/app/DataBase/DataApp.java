package com.luccasroid.like.nastya.videos.app.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataApp extends SQLiteOpenHelper {
    private static final String DBname="videoab.db";
    private  static  final int Version=1;
    // bảng
    private  static  final  String TABLE_NAME="videoapp";
    private  static  final  String ID="_id";
    private  static  final  String _IDVIDEO="_idvideo";
    private  static  final String _NAME ="_name";
    private static final  String _THUMB="_thumb";


    SQLiteDatabase myDB;

    public DataApp(@Nullable Context context) {
        super(context,DBname, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bang
        String db_table="CREATE TABLE "+ TABLE_NAME +
                " ("+
                ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                _IDVIDEO+" TEXT,"+
                _NAME +" TEXT,"+
                _THUMB+" TEXT )";
        db.execSQL(db_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
    // put data
    public  boolean insetData(String idvideo,String name,String thumb){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(_IDVIDEO,idvideo);
        contentValues.put(_NAME,name);
        contentValues.put(_THUMB,thumb);

        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return  false;

        }else {
            return  true;
        }
    }

    //get data
    public Cursor readData(){
        String query="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor=db.rawQuery(query,null);
        }
        return  cursor;

    }
    // xoa data
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,"_id = ?",new String[] {id});

    }
}
