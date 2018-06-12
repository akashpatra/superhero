package in.co.trapps.superhero.database;

import android.provider.BaseColumns;

/**
 * @author Akash Patra
 */
public class DbContract {
    private DbContract() {
    }

    public class CharactersEntry implements BaseColumns {
        public static final String TABLE_NAME_CHARACTER = "characters";
        public static final String FIELD_CHARACTER_ID = "_id";
        public static final String FIELD_CHARACTER_NAME = "name";
        public static final String FIELD_CHARACTER_DESCRIPTION = "description";
        public static final String FIELD_CHARACTER_THUMBNAIL = "thumbnail";
        public static final String FIELD_CHARACTER_IMAGE = "image";
    }
}
