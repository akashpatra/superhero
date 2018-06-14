package in.co.trapps.superhero.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.sql.SQLException;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.database.SuperHeroDAO;
import in.co.trapps.superhero.interfaces.IFragmentInteraction;
import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.mapper.Mapper;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.model.CharactersResponse;
import in.co.trapps.superhero.network.APIController;
import in.co.trapps.superhero.network.RequestListener;
import in.co.trapps.superhero.utils.Constants;
import in.co.trapps.superhero.utils.Fragments;
import retrofit2.Response;

/**
 * @author Akash Patra
 */
public class SearchFragment extends Fragment implements RequestListener<CharactersResponse> {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SearchFragment;

    private IFragmentInteraction listener;

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
        CharacterModel characterModel = Mapper.mapCharacterResponseToCharacter(response.body());
        // Store Data in Database
        try {
            SuperHeroDAO.with().addCharacter(characterModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Tell Activity to show Character
        Bundle b = new Bundle();
        b.putBoolean(Constants.OPEN_CHARACTER_EXTRA, true);
        b.putSerializable(Constants.CHARACTER_EXTRA, characterModel);
        listener.onInteraction(Fragments.SEARCH_FRAGMENT, b);
    }

    @Override
    public void onFailure(Throwable t) {
        Logger.logD(Constants.TAG, CLASS_NAME, "Error Occurred");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentInteraction) {
            listener = (IFragmentInteraction) context;
        } else {
            throw new Error("Please implement IFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
