package in.co.trapps.superhero.search;

import in.co.trapps.superhero.BasePresenter;
import in.co.trapps.superhero.BaseView;
import in.co.trapps.superhero.model.CharacterModel;

/**
 * @author Akash Patra
 */
public interface SearchContract {

    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showCharacter(CharacterModel model);
    }

    interface Presenter extends BasePresenter<View> {
        void doSearch(String query);
    }
}
