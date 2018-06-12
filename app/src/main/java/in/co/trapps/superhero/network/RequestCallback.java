package in.co.trapps.superhero.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Akash Patra
 */
public class RequestCallback<T> implements Callback<T> {

    protected RequestListener<T> listener;

    public RequestCallback(RequestListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        listener.onSuccess(response);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        listener.onFailure(t);
    }
}
