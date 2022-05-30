package jmaster.io.evnloyalty.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

import jmaster.io.evnloyalty.model.Post;

public class FavPostDBRepo extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String FAV_POST_TABLE_NAME = "fav_post";
    public static final int VERSION = 1;

    public FavPostDBRepo(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table fav_post(id long primary key, title text, description text, image text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS fav_post");
        onCreate(db);
    }

    public boolean insert(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", post.getId());
        contentValues.put("title", post.getTitle());
        contentValues.put("description", post.getDescription());
        contentValues.put("image", post.getImages().get(0));
        db.insert(FAV_POST_TABLE_NAME, null, contentValues);
        return true;
    }

    public Post getOne(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select id, title, description, image from fav_post where id = ? ", new String[]{Long.toString(id)});
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Post postDTO = new Post();
            postDTO.setId(res.getLong(0));
            postDTO.setTitle(res.getString(1));
            postDTO.setDescription(res.getString(2));
            postDTO.setImages(Arrays.asList(res.getString(3)));

            return postDTO;
        }

        return null;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FAV_POST_TABLE_NAME);
        return numRows;
    }

    public boolean update(Post post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", post.getTitle());
        contentValues.put("description", post.getDescription());
        contentValues.put("image", post.getImages().get(0));
        db.update(FAV_POST_TABLE_NAME, contentValues, "id = ? ", new String[]{Long.toString(post.getId())});
        return true;
    }

    public int delete(Long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(FAV_POST_TABLE_NAME,  "id = ? ", new String[]{Long.toString(id)});
    }

    public ArrayList<Post> findAll() {
        ArrayList<Post> list = new ArrayList<Post>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select id, title, description, image from fav_post", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Post postDTO = new Post();
            postDTO.setId(res.getLong(0));
            postDTO.setTitle(res.getString(1));
            postDTO.setDescription(res.getString(2));
            postDTO.setImages(Arrays.asList(res.getString(3)));

            list.add(postDTO);
            res.moveToNext();
        }
        return list;
    }
}
