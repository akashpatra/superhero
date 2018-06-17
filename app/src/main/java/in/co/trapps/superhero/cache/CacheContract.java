package in.co.trapps.superhero.cache;

import java.util.List;

import in.co.trapps.superhero.BasePresenter;
import in.co.trapps.superhero.BaseView;
import in.co.trapps.superhero.model.CharacterModel;

/**
 * @author Akash Patra
 */
public interface CacheContract {

    interface View extends BaseView<CacheContract.Presenter> {
        void showCachedData(List<CharacterModel> charactersList);
    }

    interface Presenter extends BasePresenter<CacheContract.View> {
        void queryCachedData();
    }
}
