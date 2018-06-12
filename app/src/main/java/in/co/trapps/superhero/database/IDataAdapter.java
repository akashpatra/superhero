package in.co.trapps.superhero.database;

import java.sql.SQLException;
import java.util.List;

import in.co.trapps.superhero.model.CharacterModel;

/**
 * @author Akash Patra
 */
public interface IDataAdapter {
    int addCharacter(CharacterModel character) throws SQLException;

    List<CharacterModel> selectLast5Characters() throws SQLException;

    List<CharacterModel> selectAllCharacters() throws SQLException;
}
