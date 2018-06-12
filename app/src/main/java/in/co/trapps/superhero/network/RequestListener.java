package in.co.trapps.superhero.network;

import retrofit2.Response;

/**
 * @author Akash Patra
 */
public interface RequestListener<T> {
    void onSuccess(Response<T> response);

    void onFailure(Throwable t);
}