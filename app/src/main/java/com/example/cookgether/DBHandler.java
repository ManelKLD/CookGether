package com.example.cookgether;

import static com.example.cookgether.DBTables.InsForm.COLUMN_MAIL;
import static com.example.cookgether.DBTables.InsForm.COLUMN_MDP;
import static com.example.cookgether.DBTables.InsForm.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    //change version when upgraded
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CookGether.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + TABLE_NAME + " (" +
                DBTables.InsForm._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                //DBTables.InsForm.COLUMN_NAME + " VARCHAR(50)," +
               // DBTables.InsForm.COLUMN_FIRSTNAME + " VARCHAR(50)," +
                COLUMN_MAIL + " VARCHAR(50),"+
                COLUMN_MDP + " VARCHAR(50))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        onCreate(db);
    }

    public Boolean insertData (/* String nom, String prenom, */ String mail, String mdp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues ligne = new ContentValues();
       // ligne.put(DBTables.InsForm.COLUMN_NAME, nom);
       // ligne.put(DBTables.InsForm.COLUMN_FIRSTNAME, prenom);
        ligne.put(COLUMN_MAIL, mail);
        ligne.put(COLUMN_MDP, mdp);
        long ligneID = db.insert(TABLE_NAME,null,ligne);

        //-1 est une erreur
        if (ligneID==-1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean checkclientMail (String mailClient){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_MAIL +"=?", new String[]{mailClient} );
        if (cursor.getCount()>0){
            return true;
        }else
            return false;
    }

    public Boolean checkclientMailMdp (String mailClient, String mdpClient){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_MAIL +"=? AND "+ COLUMN_MDP +"=?", new String[]{mailClient, mdpClient} );
        if (cursor.getCount()>0){
            return true;
        }else
            return false;
    }

}
