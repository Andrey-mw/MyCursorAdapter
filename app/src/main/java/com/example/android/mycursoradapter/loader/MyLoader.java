package com.example.android.mycursoradapter.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.example.android.mycursoradapter.DBQueryHelper;

/**
 * Created by Android on 06.11.2015.
 */
public class MyLoader extends AsyncTaskLoader<Cursor> {
    public MyLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        DBQueryHelper helper = new DBQueryHelper(getContext());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return helper.getAll();
    }
}

