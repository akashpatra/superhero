package in.co.trapps.superhero.network;

import in.co.trapps.superhero.utils.Constants;
import in.co.trapps.superhero.utils.HashGenerator;

/**
 * All network related requests are present here.
 *
 * @author Akash Patra
 */
public class BaseAPIController {

    public String getHash() {
        // generate hash using timestamp and API keys
        return HashGenerator.generate(getCurrentTimestamp(), Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);
    }

    /**
     * get device current timestamp
     *
     * @return isPortrait
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }
}
