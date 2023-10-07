package com.harkandavez.android.apepo.Favorite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Tree";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nama";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_IMAGEURL = "imageurl";
    public static final String COLUMN_IMAGE1 = "image1";
    public static final String COLUMN_IMAGE2 = "image2";
    public static final String COLUMN_IMAGE3 = "image3";
    public static final String COLUMN_QURL = "qurl";

    private static final String db_name = "apepo.db";
    private static final int db_version=1;
    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " varchar(50) not null,"
            + COLUMN_DESC + "text not null, "
            + COLUMN_IMAGEURL + " varchar(50) not null,"
            + COLUMN_IMAGE1 + " varchar(50),"
            + COLUMN_IMAGE2 + " varchar(50),"
            + COLUMN_IMAGE3 + " varchar(50),"
            + COLUMN_QURL + " varchar(50) not null);";

    public DBHelper(Context context){
        super(context, db_name, null, db_version);
        //auto generate
    }

    //mengeksekusi perintah sql di atas unntuk membuat table database baru


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database form version " + oldVersion + "to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
