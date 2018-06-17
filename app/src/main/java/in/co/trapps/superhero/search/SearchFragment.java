package in.co.trapps.superhero.search;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.interfaces.IFragmentInteraction;
import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.utils.Constants;
import in.co.trapps.superhero.utils.Fragments;

import static in.co.trapps.superhero.logger.Logger.logD;

/**
 * @author Akash Patra
 */
public class SearchFragment extends Fragment implements SearchContract.View {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SearchFragment;

    private IFragmentInteraction listener;
    private ProgressDialog progressDialog;

    private SearchPresenter presenter;

    public SearchFragment() {
        logD(Constants.TAG, CLASS_NAME, " >> SearchFragment");
        presenter = new SearchPresenter();
    }

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
                presenter.doSearch(query);
            }
        });
    }

    @Override
    public void showProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Loading data, please wait...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();
    }

    @Override
    public void showCharacter(CharacterModel character) {
        // Tell Activity to show Character
        Bundle b = new Bundle();
        b.putBoolean(Constants.OPEN_CHARACTER_EXTRA, true);
        b.putSerializable(Constants.CHARACTER_EXTRA, character);
        listener.onInteraction(Fragments.SEARCH_FRAGMENT, b);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logD(Constants.TAG, CLASS_NAME, " >> onAttach");

        if (context instanceof IFragmentInteraction) {
            listener = (IFragmentInteraction) context;
        } else {
            throw new Error("Please implement IFragmentInteraction");
        }

        presenter.bind(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        logD(Constants.TAG, CLASS_NAME, " >> onDetach");
        listener = null;
        presenter.unbind();
    }
}
