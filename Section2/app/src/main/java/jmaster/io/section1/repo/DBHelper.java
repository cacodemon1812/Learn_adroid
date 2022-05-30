package jmaster.io.section1.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import jmaster.io.section1.model.PostDTO;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String FAV_POST_TABLE_NAME = "fav_post";
    public static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table fav_post(id integer primary key, title text, description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fav_post");
        onCreate(db);
    }

    public boolean insert(String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", name);
        contentValues.put("description", description);
        db.insert(FAV_POST_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from fav_post where id = ? ", new String[]{Integer.toString(id)});
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FAV_POST_TABLE_NAME);
        return numRows;
    }

    public boolean update(Integer id, String name, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", name);
        contentValues.put("description", description);
        db.update(FAV_POST_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer delete(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("fav_post",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<PostDTO> getAll() {
        ArrayList<PostDTO> list = new ArrayList<PostDTO>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from fav_post", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(res.getLong(0));
            postDTO.setTitle(res.getString(1));
            postDTO.setDescription(res.getString(2));

            list.add(postDTO);
            res.moveToNext();
        }
        return list;
    }
}
