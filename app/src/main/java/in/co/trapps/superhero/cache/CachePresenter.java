package in.co.trapps.superhero.cache;

import java.sql.SQLException;

import in.co.trapps.superhero.database.SuperHeroDAO;
import in.co.trapps.superhero.logger.LoggerEnable;

/**
 * @author Akash Patra
 */
public class CachePresenter implements CacheContract.Presenter {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.CachePresenter;

    private CacheContract.View view;

    @Override
    public void bind(CacheContract.View view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        view = null;
    }

    @Override
    public void queryCachedData() {
        try {
            view.showCachedData(SuperHeroDAO.with().selectLast5Characters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
