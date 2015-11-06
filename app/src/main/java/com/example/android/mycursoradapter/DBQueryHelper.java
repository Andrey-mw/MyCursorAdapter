package com.example.android.mycursoradapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Android on 06.11.2015.
 */
public class DBQueryHelper {

    // Класс запросов
    private static final String[] ALL_COLUMNS = {MySQLiteHelper._ID, MySQLiteHelper.NAME_COLUMN, MySQLiteHelper.NUMBER_COLUMN};
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbhelper;
    private Context context;

    // Чтобы использовать реализацию вспомогательного класса MySQLiteHelper, нужно создать новый экземпляр SQLiteOpenHelper dbhelper;
    // Вызываем getReadableDatabase() или getWritableDatabase(), чтобы открыть и вернуть экземпляр базы данных, с которой мы имеем дело
    // (он будет доступен как для чтения, так и для записи).
    // Вызов метода getWritableDatabase() может завершиться неудачно из-за проблем с полномочиями или нехваткой места на диске,
    // поэтому лучше предусмотреть откат к методу getReadableDatabase().
    public DBQueryHelper(Context context) {
        dbhelper = new MySQLiteHelper(context);
        try {
            db = dbhelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbhelper.getReadableDatabase();
        }
    }

//    public void openDB() {
//        dbhelper = new MySQLiteHelper(context);
//
//        db = dbhelper.getWritableDatabase();
//
//    }


    // Закрываем подключение к БД
    public void closeDB() {
        if (dbhelper != null)
            dbhelper.close();
    }

    //  Делаем запрос всех данных из таблицы TABLE_NAME, получаем Cursor
    public Cursor getAll() {
        return db.query(MySQLiteHelper.TABLE_NAME, ALL_COLUMNS, null, null, null, null, null);

    }

    // Удаляем все записи
    public void delete() {
        db.delete(MySQLiteHelper.TABLE_NAME, null, null);
    }

    // Фильтруем данные БД по NAME_COLUMN
    public Cursor getFilteredItemsByName(String name) {
        return db.query(MySQLiteHelper.TABLE_NAME, ALL_COLUMNS, MySQLiteHelper.NUMBER_COLUMN + " LIKE ?", new String[]{"%" + name + "%"},
                null, null, null);
    }
}
