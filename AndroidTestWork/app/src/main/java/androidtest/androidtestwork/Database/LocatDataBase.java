package androidtest.androidtestwork.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import androidtest.androidtestwork.Modal.Articale;
import androidtest.androidtestwork.Modal.LoginSignupModal;

import static androidtest.androidtestwork.Utilitiy.Utils.iSInsert;

/**
 * Created by Aru on 24-03-2018.
 */

public class LocatDataBase extends SQLiteOpenHelper{
    public static final String DATABASE_NAME="mLocal";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME="Jsondata";
    public static final String ID="id";
    public static final String AUTHOR="Author";
    public static final String DESCRIPTION="Description";
    public static final String TITLE="Title";
    public static final String IMAGEURL="urlToimage";
    public static final String PUBLISHED="PublishedAt";
    long InsertResponse;
    Cursor c;
    ArrayList<Articale> arraylogin = new ArrayList<>();
    public LocatDataBase(Context loginActivity) {
        super(loginActivity,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY," + AUTHOR + " TEXT,"
                + DESCRIPTION + " TEXT," + TITLE + " TEXT,"
                + IMAGEURL + " TEXT," + PUBLISHED + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

    }
    public long InsetRecord(Articale mArticale) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AUTHOR, mArticale.getAuthor()); // Contact Name
        values.put(DESCRIPTION, mArticale.getDescription()); // Contact Phone
        values.put(TITLE, mArticale.getTitle()); // Contact Email
        values.put(IMAGEURL, mArticale.getUrlToimage());
        values.put(PUBLISHED, mArticale.getPublishedAt());
        if(iSInsert){
            InsertResponse = db.insert(TABLE_NAME, null, values);
        }
        db.close();
        return InsertResponse;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Articale> GetRecordFromLocal() {
        try {
            SQLiteDatabase MSqLiteDatabase = this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_NAME;
            c = MSqLiteDatabase.rawQuery(selectQuery, null);
            int count = c.getCount();
            if (c.moveToFirst()) {
                do {
                    Articale loginSignupModal = new Articale();
                    loginSignupModal.setId(Integer.parseInt(c.getString(0)));
                    loginSignupModal.setAuthor(c.getString(1));
                    loginSignupModal.setDescription(c.getString(2));
                    loginSignupModal.setTitle(c.getString(3));
                    loginSignupModal.setUrlToimage(c.getString(4));
                    loginSignupModal.setPublishedAt(c.getString(5));
                    arraylogin.add(loginSignupModal);
                } while (c.moveToNext());
            }
            c.close();
            MSqLiteDatabase.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return arraylogin;
    }
}
