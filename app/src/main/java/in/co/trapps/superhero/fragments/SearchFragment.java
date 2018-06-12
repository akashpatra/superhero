package in.co.trapps.superhero.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.model.CharactersResponse;
import in.co.trapps.superhero.network.APIController;
import in.co.trapps.superhero.network.RequestListener;
import in.co.trapps.superhero.utils.Constants;
import retrofit2.Response;

/**
 * @author Akash Patra
 */
public class SearchFragment extends Fragment implements RequestListener<CharactersResponse> {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SearchFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = ((EditText) view.findViewById(R.id.character)).getText().toString();

                new APIController().loadCharacter(query, SearchFragment.this);
            }
        });
    }

    @Override
    public void onSuccess(Response<CharactersResponse> response) {
        Logger.logD(Constants.TAG, CLASS_NAME, "Response Received");
    }

    @Override
    public void onFailure(Throwable t) {
        Logger.logD(Constants.TAG, CLASS_NAME, "Error Occurred");
    }
}
