package in.co.trapps.superhero.cache;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.co.trapps.superhero.R;
import in.co.trapps.superhero.adapter.CharactersAdapter;
import in.co.trapps.superhero.interfaces.IFragmentInteraction;
import in.co.trapps.superhero.logger.LoggerEnable;
import in.co.trapps.superhero.model.CharacterModel;
import in.co.trapps.superhero.utils.Constants;
import in.co.trapps.superhero.utils.Fragments;
import in.co.trapps.superhero.utils.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class CacheFragment extends Fragment implements CacheContract.View {

    // For Logging
    private static final LoggerEnable CLASS_NAME = LoggerEnable.CacheFragment;

    public static final int COLUMN_COUNT = 2;

    private ViewGroup empty;
    private RecyclerView list;

    private CharactersAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private GridSpacingItemDecoration gridSpacingItemDecoration;

    private IFragmentInteraction listener;

    private CachePresenter presenter;

    public CacheFragment() {
        presenter = new CachePresenter();
    }

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
        presenter.queryCachedData();
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
        listener = null;

        presenter.unbind();
    }

    @Override
    public void showCachedData(List<CharacterModel> charactersList) {
        adapter = new CharactersAdapter(getActivity(), charactersList);
        adapter.setOnClickListener(new CharactersAdapter.OnAdapterListener() {
            @Override
            public void onItemClick(CharacterModel characterModel) {
                Bundle b = new Bundle();
                b.putBoolean(Constants.OPEN_CHARACTER_EXTRA, true);
                b.putSerializable(Constants.CHARACTER_EXTRA, characterModel);
                listener.onInteraction(Fragments.CACHE_FRAGMENT, b);
            }
        });
        list.setAdapter(adapter);
    }
}
