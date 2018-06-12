package in.co.trapps.superhero.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.SQLException;
import java.util.List;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.adapter.CharactersAdapter;
import in.co.trapps.superhero.database.SuperHeroDAO;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.utils.Constants;
import in.co.trapps.superhero.utils.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class CacheFragment extends Fragment {

    public static final int COLUMN_COUNT = 2;

    ViewGroup empty;
    RecyclerView list;

    CharactersAdapter adapter;
    GridLayoutManager gridLayoutManager;
    GridSpacingItemDecoration gridSpacingItemDecoration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cache, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        empty = view.findViewById(R.id.empty);
        list = view.findViewById(R.id.list);

        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            List<CharacterModel> charactersList = SuperHeroDAO.with().selectLast5Characters();
            adapter = new CharactersAdapter(getActivity(), charactersList);
            list.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView() {
        initLayoutManager();
        initGridSpacingItemDecoration();

        list.setLayoutManager(gridLayoutManager);
        list.addItemDecoration(gridSpacingItemDecoration);
    }

    public void initLayoutManager() {
        gridLayoutManager = new GridLayoutManager(getActivity(), COLUMN_COUNT);
        // Create a custom SpanSizeLookup where the first item spans both columns
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? COLUMN_COUNT : 1;
            }
        });
    }

    public void initGridSpacingItemDecoration() {
        gridSpacingItemDecoration = new GridSpacingItemDecoration(COLUMN_COUNT,
                Constants.RECYCLER_VIEW_ITEM_SPACE, true, 1);
    }
}
