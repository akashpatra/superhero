package in.co.trapps.superhero.mapper;

import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.model.CharactersResponse;
import in.co.trapps.superhero.utils.Constants;

/**
 * @author Akash Patra
 */
public class Mapper {

    public static CharacterModel mapCharacterResponseToCharacter(CharactersResponse charactersResponse) {
        CharacterModel character = new CharacterModel();

        character.setName(charactersResponse.getData().getResults()[0].getName());
        character.setDescription(charactersResponse.getData().getResults()[0].getDescription());
        character.setThumbnail(String.format("%s/%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                Constants.STANDARD_XLARGE,
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));
        character.setImage(String.format("%s/%s.%s",
                charactersResponse.getData().getResults()[0].getThumbnail().getPath(),
                Constants.PORTRAIT_XLARGE,
                charactersResponse.getData().getResults()[0].getThumbnail().getExtension()));

        return character;
    }
}
