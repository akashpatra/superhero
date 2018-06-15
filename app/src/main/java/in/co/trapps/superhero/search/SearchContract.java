package in.co.trapps.superhero.search;

import in.co.trapps.superhero.BasePresenter;
import in.co.trapps.superhero.BaseView;

/**
 * @author Akash Patra
 */
public interface SearchContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
