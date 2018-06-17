package in.co.trapps.superhero.search;

import java.sql.SQLException;

import in.co.trapps.superhero.database.SuperHeroDAO;
import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.mapper.Mapper;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.model.CharactersResponse;
import in.co.trapps.superhero.network.APIController;
import in.co.trapps.superhero.network.RequestListener;
import in.co.trapps.superhero.utils.Constants;
import retrofit2.Response;

/**
 * @author Akash Patra
 */
public class SearchPresenter implements SearchContract.Presenter,
        RequestListener<CharactersResponse> {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SearchPresenter;

    private SearchContract.View view;

    @Override
    public void bind(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void doSearch(String query) {
        view.showProgress();
        new APIController().loadCharacter(query, this);
    }

    @Override
    public void onSuccess(Response<CharactersResponse> response) {
        Logger.logD(Constants.TAG, CLASS_NAME, " >> Response Received");
        CharacterModel characterModel = Mapper.mapCharacterResponseToCharacter(response.body());
        // Store Data in Database
        try {
            SuperHeroDAO.with().addCharacter(characterModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        view.hideProgress();
        view.showCharacter(characterModel);
    }

    @Override
    public void onFailure(Throwable t) {
        Logger.logD(Constants.TAG, CLASS_NAME, " >> Error Occurred");
        view.hideProgress();
    }
}
