package com.brunoocasali.mynotes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.brunoocasali.mynotes.constants.Constant;
import com.brunoocasali.mynotes.helper.OpenHelper;
import com.brunoocasali.mynotes.vo.NotebookVO;

import java.util.Date;

/**
 * Created by bruno on 02/05/15.
 */
public class NotebookDAO {
    private OpenHelper helper;

    public NotebookDAO(Context context){
        this.helper = new OpenHelper(context, Constant.DATABASE_NAME, null,
                Constant.DATABASE_CURRENT_VERSION);
    }

    public void save(NotebookVO notebook){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constant.COLUMN_TITLE, notebook.getTitle());
        values.put(Constant.COLUMN_ON_CREATE, new Date().getTime());
        values.put(Constant.COLUMN_ON_UPDATE, (Integer) null);
        values.put(Constant.COLUMN_DESCRIPTION, notebook.getDescription());

        db.insert(Constant.TABLE_NOTEBOOKS, null, values);
        db.close();
    }

    public Cursor list(){
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] columns = {
                Constant.COLUMN_ID,
                Constant.COLUMN_TITLE,
                Constant.COLUMN_ON_CREATE,
                Constant.COLUMN_ON_UPDATE,
                Constant.COLUMN_DESCRIPTION
        };

        return db.query(Constant.TABLE_NOTEBOOKS, columns, null, null, null, null, Constant.COLUMN_ON_CREATE);
    }

    public void destroy(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] condition = { String.valueOf(id) };

        db.delete(Constant.TABLE_NOTEBOOKS, "_id = ?", condition);
        db.close();
    }
}
