package in.co.trapps.superhero.logger;

import android.util.Log;

import in.co.trapps.superhero.BuildConfig;

/**
 * Common class to print Logs
 *
 * @author Akash Patra
 */
public class Logger {

    public static void logD(String tag, ILoggerActivator iLoggerActivator, Object... msg) {
        if (BuildConfig.DEBUG && iLoggerActivator.isEnabled()) {
            StringBuilder fullMsg = new StringBuilder();
            fullMsg.append(iLoggerActivator);
            for (Object str : msg) {
                fullMsg.append(str);
            }
            Log.d(tag, fullMsg.toString());
        }
    }

    public static void logI(String tag, ILoggerActivator iLoggerActivator, Object... msg) {
        if (BuildConfig.DEBUG && iLoggerActivator.isEnabled()) {
            StringBuilder fullMsg = new StringBuilder();
            fullMsg.append(iLoggerActivator);
            for (Object str : msg) {
                fullMsg.append(str);
            }
            Log.i(tag, fullMsg.toString());
        }
    }

    public static void logE(String tag, Throwable throwableException, ILoggerActivator iLoggerActivator, Object... msg) {
        if (BuildConfig.DEBUG && iLoggerActivator.isEnabled()) {
            StringBuilder fullMsg = new StringBuilder();
            fullMsg.append(iLoggerActivator);
            for (Object str : msg) {
                fullMsg.append(str);
            }
            fullMsg.append(" >> Exception: ").append(throwableException.getMessage());
            Log.e(tag, fullMsg.toString());
        }
    }
}