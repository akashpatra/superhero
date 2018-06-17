package in.co.trapps.superhero.logger;

/**
 * Enum Class which will enable/disable printing of logs.
 * It implements {@link ILoggerActivator}
 *
 * @author Akash Patra
 */
public enum LoggerEnable implements ILoggerActivator {
    SuperHeroDbHelper {
        @Override
        public boolean isEnabled() {
            return true;
        }
    },
    SuperHeroDAO {
        @Override
        public boolean isEnabled() {
            return true;
        }
    },
    CharacterActivity {
        @Override
        public boolean isEnabled() {
            return true;
        }
    },
    SearchFragment {
        @Override
        public boolean isEnabled() {
            return true;
        }
    },
    SearchPresenter {
        @Override
        public boolean isEnabled() {
            return true;
        }
    },
    CacheFragment {
        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
