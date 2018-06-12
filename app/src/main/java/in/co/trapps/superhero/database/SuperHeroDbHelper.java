package in.co.trapps.superhero.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.utils.Constants;

/**
 * @author Akash Patra
 */
public class SuperHeroDbHelper extends SQLiteOpenHelper {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SuperHeroDbHelper;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "superhero.db";

    private static final String CREATE_CHARACTER_TABLE =
            "CREATE TABLE " + DbContract.CharactersEntry.TABLE_NAME_CHARACTER + " (" +
                    DbContract.CharactersEntry.FIELD_CHARACTER_ID + " INTEGER PRIMARY KEY," +
                    DbContract.CharactersEntry.FIELD_CHARACTER_NAME + " TEXT," +
                    DbContract.CharactersEntry.FIELD_CHARACTER_DESCRIPTION + " TEXT," +
                    DbContract.CharactersEntry.FIELD_CHARACTER_THUMBNAIL + " TEXT," +
                    DbContract.CharactersEntry.FIELD_CHARACTER_IMAGE + " TEXT)";

    private static final String DELETE_CHARACTER_TABLE =
            "DROP TABLE IF EXISTS " + DbContract.CharactersEntry.TABLE_NAME_CHARACTER;

    public SuperHeroDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Logger.logD(Constants.TAG, CLASS_NAME, " >> onCreate");

        // Create Required Tables
        db.execSQL(CREATE_CHARACTER_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.logD(Constants.TAG, CLASS_NAME, " >> onUpgrade");

        // Delete Required Tables
        db.execSQL(DELETE_CHARACTER_TABLE);

        // Create New Tables
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}