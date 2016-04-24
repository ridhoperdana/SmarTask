package net.ridhoperdana.sqlite;


/**
 * Created by RIDHO on 12/5/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "crudse.db";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE_TUGAS = "CREATE TABLE " + Tugas.TABLE + "(" + Tugas.KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT ," + Tugas.KEY_nama + " TEXT, "
                + Tugas.KEY_tanggalDikasih + " TEXT, " + Tugas.KEY_waktuDikasih + " TEXT, "
                + Tugas.KEY_tanggalDikumpul + " TEXT, " +
                Tugas.KEY_waktuDikumpul + " TEXT, " + Tugas.KEY_kompleksitas + " NUMBER, "
                + Tugas.KEY_status + " INTEGER)";

        db.execSQL(CREATE_TABLE_TUGAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Tugas.TABLE);
        onCreate(db);
    }

}
