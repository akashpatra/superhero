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
    }
}
