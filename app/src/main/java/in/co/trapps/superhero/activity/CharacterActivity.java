package in.co.trapps.superhero.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.logger.Logger;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.utils.Constants;

public class CharacterActivity extends AppCompatActivity {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.SuperHeroDAO;

    private static final String ARG_CHARACTER = "characterModel";

    public static Intent newIntent(Context context, CharacterModel character) {
        Intent intent = new Intent(context, CharacterActivity.class);
        intent.putExtra(ARG_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        if (null == getIntent()
                || null == getIntent().getExtras()
                || null == getIntent().getExtras().getSerializable(ARG_CHARACTER)) {
            finish();
            return;
        }

        // get args
        CharacterModel character = (CharacterModel) getIntent().getExtras().getSerializable(ARG_CHARACTER);

        ImageView image = findViewById(R.id.image);

        Glide.with(this).load(character.getThumbnail())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
        Logger.logD(Constants.TAG, CLASS_NAME, "onCreate()");
    }
}
