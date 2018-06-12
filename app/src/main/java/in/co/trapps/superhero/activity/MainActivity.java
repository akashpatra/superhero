package in.co.trapps.superhero.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.fragments.CacheFragment;
import in.co.trapps.superhero.fragments.SearchFragment;
import in.co.trapps.superhero.utils.FragUtils;

/**
 * @author Akash Patra
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragUtils.addFragment(getSupportFragmentManager(), R.id.search_fragment, new SearchFragment());
        FragUtils.addFragment(getSupportFragmentManager(), R.id.cache_fragment, new CacheFragment());

    }
}
