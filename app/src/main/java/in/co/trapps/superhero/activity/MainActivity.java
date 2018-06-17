package in.co.trapps.superhero.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.database.SuperHeroDAO;
import in.co.trapps.superhero.fragments.CacheFragment;
import in.co.trapps.superhero.search.SearchFragment;
import in.co.trapps.superhero.interfaces.IFragmentInteraction;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.utils.Constants;
import in.co.trapps.superhero.utils.FragUtils;
import in.co.trapps.superhero.utils.Fragments;

/**
 * @author Akash Patra
 */
public class MainActivity extends AppCompatActivity implements IFragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SuperHeroDAO.init(this);

        FragUtils.addFragment(getSupportFragmentManager(), R.id.search_fragment, new SearchFragment());
        FragUtils.addFragment(getSupportFragmentManager(), R.id.cache_fragment, new CacheFragment());
    }

    private void showCharacter(CharacterModel character) {
        startActivity(CharacterActivity.newIntent(this, character));
    }

    @Override
    public void onInteraction(Fragments fragments, Bundle b) {
        switch (fragments) {
            case SEARCH_FRAGMENT:
            case CACHE_FRAGMENT:
                boolean openCharacterExtra = b.getBoolean(Constants.OPEN_CHARACTER_EXTRA);
                if (openCharacterExtra) {
                    CharacterModel characterModel = (CharacterModel) b.getSerializable(Constants.CHARACTER_EXTRA);
                    showCharacter(characterModel);
                }
                break;
        }
    }
}
