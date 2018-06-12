package in.co.trapps.superhero.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.utils.Constants;

import static in.co.trapps.superhero.database.DbContract.CharactersEntry.FIELD_CHARACTER_DESCRIPTION;
import static in.co.trapps.superhero.database.DbContract.CharactersEntry.FIELD_CHARACTER_ID;
import static in.co.trapps.superhero.database.DbContract.CharactersEntry.FIELD_CHARACTER_IMAGE;
import static in.co.trapps.superhero.database.DbContract.CharactersEntry.FIELD_CHARACTER_NAME;
import static in.co.trapps.superhero.database.DbContract.CharactersEntry.FIELD_CHARACTER_THUMBNAIL;
import static in.co.trapps.superhero.database.DbContract.CharactersEntry.TABLE_NAME_CHARACTER;

/**
 * @author Akash Patra
 */
public class SuperHeroDAO implements IDataAdapter {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SuperHeroDAO;

    private static SuperHeroDAO superHeroDAO;
    private static SuperHeroDbHelper superHeroDbHelper;

    private SuperHeroDAO() {
    }

    public static void init(Context context) {
        if (null == superHeroDAO) {
            Logger.logD(Constants.TAG, CLASS_NAME, " >> init");
            superHeroDAO = new SuperHeroDAO();
            superHeroDbHelper = new SuperHeroDbHelper(context);
        }
    }

    public static SuperHeroDAO with() {
        if (null == superHeroDAO) {
            Logger.logD(Constants.TAG, CLASS_NAME, " >> Ignoring SuperHeroDAO.with() " +
                    "invoked before SuperHeroDAO.init() called.");
        }
        return superHeroDAO;
    }

    @Override
    public int addCharacter(CharacterModel character) throws SQLException {
        Logger.logD(Constants.TAG, CLASS_NAME, " >> addCharacter");

        SQLiteDatabase db = superHeroDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FIELD_CHARACTER_NAME, character.getName());
        values.put(FIELD_CHARACTER_DESCRIPTION, character.getDescription());
        values.put(FIELD_CHARACTER_THUMBNAIL, character.getThumbnail());
        values.put(FIELD_CHARACTER_IMAGE, character.getImage());

        db.insert(TABLE_NAME_CHARACTER, null, values);
        return 0;
    }

    @Override
    public List<CharacterModel> selectLast5Characters() throws SQLException {
        Logger.logD(Constants.TAG, CLASS_NAME, " >> selectLast5Characters");

        SQLiteDatabase db = superHeroDbHelper.getReadableDatabase();

        String[] projection = {
                FIELD_CHARACTER_ID,
                FIELD_CHARACTER_NAME,
                FIELD_CHARACTER_DESCRIPTION,
                FIELD_CHARACTER_THUMBNAIL,
                FIELD_CHARACTER_IMAGE
        };

        Cursor cursor = db.query(
                TABLE_NAME_CHARACTER,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                FIELD_CHARACTER_ID + " DESC",
                String.valueOf(5)
        );
        List<CharacterModel> characterModelArrayList = new ArrayList<>();
        CharacterModel characterModel;

        if (cursor != null) {
            while (cursor.moveToNext()) {
                characterModel = new CharacterModel();
                characterModel.setId(cursor.getInt(cursor.getColumnIndex(FIELD_CHARACTER_ID)));
                characterModel.setName(cursor.getString(cursor.getColumnIndex(FIELD_CHARACTER_NAME)));
                characterModel.setDescription(cursor.getString(cursor.getColumnIndex(FIELD_CHARACTER_DESCRIPTION)));
                characterModel.setThumbnail(cursor.getString(cursor.getColumnIndex(FIELD_CHARACTER_THUMBNAIL)));
                characterModel.setImage(cursor.getString(cursor.getColumnIndex(FIELD_CHARACTER_IMAGE)));

                characterModelArrayList.add(characterModel);
            }
        }

        cursor.close();
        return characterModelArrayList;
    }

    @Override
    public List<CharacterModel> selectAllCharacters() throws SQLException {
        return null;
    }
}
