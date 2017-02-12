package com.angelo.databasetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

/**
 * Created by AngeloDesktop on 12/02/2017.
 */

public class databasehelper extends SQLiteOpenHelper {
    public static  final  String DATABASE_NAME = "student.db";
    public static  final String TABLLE_NAME="student_table";
    public static  final String COL_1 = "id";
    public static  final String COL_2="fname";
    public static  final String COL_3="lname";
    public static  final String COL_4="marks";




    public databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLLE_NAME + "("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String stfname, String stlname, String stmarks){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,stfname);
        contentValues.put(COL_3,stlname);
        contentValues.put(COL_4,stmarks);

        long result = db.insert(TABLLE_NAME,null,contentValues);
        if (result == -1)
            return  false;
        else
            return  true;
    }

    public boolean updateData(String stid, String stfname, String stlname, String stmarks){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,stid);
        contentValues.put(COL_2,stfname);
        contentValues.put(COL_3,stlname);
        contentValues.put(COL_4,stmarks);

        db.update(TABLLE_NAME,contentValues, "ID = ?",new String[] { stid } );
        return true;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLLE_NAME,"id = ?",new String[] {id});

    }


    public Cursor get_all_data(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor show_data = db.rawQuery("SELECT * FROM "+ TABLLE_NAME,null);
        return  show_data;

    }


}
