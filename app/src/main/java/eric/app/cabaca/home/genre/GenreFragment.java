package eric.app.cabaca.home.genre;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.home.genre.bookgenre.BookGenre;
import eric.app.cabaca.response.GenreResponse;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragment extends Fragment implements GenreAdapter.ItemListener, GenreView {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLiLayoutManager;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private GenrePresenter presenter;
    private GenreAdapter adapter;
    private String key;

    public GenreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Genre Buku");
        return inflater.inflate(R.layout.fragment_genre, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
        getGenreBuku();
    }

    private void initLayout(View view){
        recyclerView = view.findViewById(R.id.recyler);
        rvLiLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLiLayoutManager);
        progressBar = view.findViewById(R.id.progress_bar);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new GenrePresenter(this,compositeDisposable,apiInterface);

        key = getString(R.string.key);
    }

    private void getGenreBuku() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getGenreBook(key);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getGenreBook(GenreResponse resource) {
        if (resource != null){
            progressBar.setVisibility(View.GONE);
            adapter = new GenreAdapter(getActivity(), resource.getResource(),this);
            recyclerView.setAdapter(adapter);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void clickUser(GenreResponse.Resource item) {
        Intent intent = new Intent(getActivity(), BookGenre.class);
        intent.putExtra("id",item.getId());
        intent.putExtra("title",item.getTitle());
        startActivity(intent);
        getActivity().finish(); }
}
