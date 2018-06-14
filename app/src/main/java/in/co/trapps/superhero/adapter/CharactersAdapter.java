package in.co.trapps.superhero.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.activity.MainActivity;
import in.co.trapps.superhero.model.CharacterModel;

/**
 * @author Akash Patra
 */
public class CharactersAdapter extends RecyclerView.Adapter<CharactersAdapter.ViewHolder> {

    private List<CharacterModel> charactersList;
    private Context mContext;

    public CharactersAdapter(Context context, List<CharacterModel> charactersList) {
        mContext = context;
        this.charactersList = charactersList;
    }

    @Override
    public CharactersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CharacterModel characterModel = charactersList.get(position);

        holder.tvName.setText(characterModel.getDescription());

        Glide.with(mContext).load(characterModel.getThumbnail())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImage);

        /*holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) mContext).showCharacter(characterModel);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivImage;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.image);
            tvName = itemView.findViewById(R.id.name);
        }
    }
}
