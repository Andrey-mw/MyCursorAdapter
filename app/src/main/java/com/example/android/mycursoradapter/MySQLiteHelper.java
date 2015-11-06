package com.example.android.mycursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Android on 06.11.2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper implements BaseColumns {

    // <<<< Create DB>>>>
    // Создаем статические финальные переменные

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "My DB";
    public static final String NAME_COLUMN = "name";
    public static final String NUMBER_COLUMN = "number";
    public static final String TABLE_NAME = "table_name";

    // Создание новой таблицы с помощью команды CREATE TABLE после которой указывается имя таблицы,
    // а затем в круглых скобках указываются имена столбцов с параметрами
    public static final String DATABASE_CREATE_SCRIPT = "CREATE TABLE " + TABLE_NAME + " ( "
            + _ID + " INTEGER primary key autoincrement, "
            + NAME_COLUMN + " TEXT, "
            + NUMBER_COLUMN + " TEXT);";

    // Конструктор
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Метод, который будет вызван, если БД, к которой мы хотим подключиться – не существует
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
        addData(db);

    }

    // Будет вызван в случае, если мы пытаемся подключиться к БД более новой версии, чем существующая
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Добавление записи в таблицу
    public void addData(SQLiteDatabase db) {

        // Класс ContentValues используется для указания полей таблицы и значений, которые мы в эти поля будем вставлять
        // Создаем объект для данных
        ContentValues cv = new ContentValues();

        // Мы заполняем объект cv парами: имя поля и значение
        cv.put(NAME_COLUMN, "Asus");
        cv.put(NUMBER_COLUMN, "This is Asus");

        // Вызываем метод insert – передаем ему имя таблицы и объект cv с вставляемыми значениями.
        // Второй аргумент метода используется, при вставке в таблицу пустой строки
        db.insert(TABLE_NAME, null, cv);

        // Удаляет все значения
        cv.clear();

        cv.put(NAME_COLUMN, "LG");
        cv.put(NUMBER_COLUMN, "This is LG");
        db.insert(TABLE_NAME, null, cv);
        cv.clear();

        cv.put(NAME_COLUMN, "Huawei");
        cv.put(NUMBER_COLUMN, "This is Huawei 8815");
        db.insert(TABLE_NAME, null, cv);
        cv.clear();

        cv.put(NAME_COLUMN, "Samsung");
        cv.put(NUMBER_COLUMN, "This is Samsung S4");
        db.insert(TABLE_NAME, null, cv);
        cv.clear();

        cv.put(NAME_COLUMN, "Lenovo");
        cv.put(NUMBER_COLUMN, "This is Lenevo");
        db.insert(TABLE_NAME, null, cv);

    }


}
