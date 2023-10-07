package com.harkandavez.android.apepo.Favorite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.harkandavez.android.apepo.Tree;

import java.util.ArrayList;

public class DBDataSource {

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] alllColumns = {
            DBHelper.COLUMN_ID,
            DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_DESC,
            DBHelper.COLUMN_IMAGEURL,
            DBHelper.COLUMN_IMAGE1,
            DBHelper.COLUMN_IMAGE2,
            DBHelper.COLUMN_IMAGE3,
            DBHelper.COLUMN_QURL
    };

    public DBDataSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    //insert data
    public boolean insertTree(String id,
                              String nama,
                              String desc,
                              String imageurl,
                              String image1,
                              String image2,
                              String image3,
                              String qurl){

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_ID, id);
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_DESC, desc);
        values.put(DBHelper.COLUMN_IMAGEURL, imageurl);
        values.put(DBHelper.COLUMN_IMAGE1, image1);
        values.put(DBHelper.COLUMN_IMAGE2, image2);
        values.put(DBHelper.COLUMN_IMAGE3, image3);
        values.put(DBHelper.COLUMN_QURL, qurl);

        long insertId = database.insert(DBHelper.TABLE_NAME,null,values);

        if (insertId == -1) return false;
        else return true;
    }

    //set data
    private Tree cursorToTree(Cursor cursor){
        Tree tree = new Tree();
        //debug LOGCAT
        tree.setId(cursor.getInt(0));
        tree.setTreeName(cursor.getString(1));
        tree.setTreeDesc(cursor.getString(2));
        tree.setImageURL(cursor.getString(3));
        tree.setImage1(cursor.getString(4));
        tree.setImage2(cursor.getString(5));
        tree.setImage3(cursor.getString(6));
        tree.setQrUrl(cursor.getString(7));

        return tree;
    }

    //mengambil senua data pohon
    public ArrayList<Tree> getTree(){
        ArrayList<Tree> listTree = new ArrayList<Tree>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME,alllColumns,
                null,null,null,null,null);

        //pindah ke data paling pertama
        cursor.moveToFirst();
        //jika masih ada data, masukan data ke list
        while (!cursor.isAfterLast()){
            Tree tree = cursorToTree(cursor);
            listTree.add(tree);
            cursor.moveToNext();
        }
        cursor.close();
        return listTree;
    }

    //ambil sesuai id
    public boolean isFavorite(Integer id){
        Tree tree = new Tree();
        //select query
        Cursor cursor = database.query(DBHelper.TABLE_NAME,alllColumns, "_id = "+id,
                                        null,null,null,null);
        if (cursor.getCount()>0){
            return true;
        } else
        {
            return false;
        }
    }
    //update pohon yg di edit
    public void updateTree(Tree b){
        //ambil id barang
        String strFilter = "_id=" + b.getId();
        //memasukan ke content values
        ContentValues args = new ContentValues();
        //masukan data sesuai dengan kolom pada table
        args.put(DBHelper.COLUMN_NAME, b.getTreeName());
        args.put(DBHelper.COLUMN_DESC, b.getTreeDesc());
        args.put(DBHelper.COLUMN_IMAGEURL, b.getImageURL());
        args.put(DBHelper.COLUMN_IMAGE1, b.getImage1());
        args.put(DBHelper.COLUMN_IMAGE2, b.getImage2());
        args.put(DBHelper.COLUMN_IMAGE3, b.getImage3());
        args.put(DBHelper.COLUMN_QURL, b.getQRUrl());

        //update query
        database.update(DBHelper.TABLE_NAME, args, strFilter,null);
    }

    //delete
    public void deleteTree(Integer id){
        String strFilter = "_id=" +id;
        database.delete(DBHelper.TABLE_NAME, strFilter,null);
    }
}
