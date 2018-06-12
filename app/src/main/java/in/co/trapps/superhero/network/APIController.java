package in.co.trapps.superhero.network;

import in.co.trapps.superhero.model.CharactersResponse;
import in.co.trapps.superhero.utils.Constants;
import retrofit2.Call;

/**
 * All network related requests are present here.
 *
 * @author Akash Patra
 */
public class APIController extends BaseAPIController {
    private APIInterface apiInterface;

    public void loadCharacter(String query, RequestListener<CharactersResponse> listener) {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CharactersResponse> call = apiInterface.getCharacters(query, Constants.PUBLIC_KEY,
                getHash(), getCurrentTimestamp());
        call.enqueue(new RequestCallback<CharactersResponse>(listener));
    }
}
