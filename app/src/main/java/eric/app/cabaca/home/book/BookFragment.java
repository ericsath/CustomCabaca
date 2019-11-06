package eric.app.cabaca.home.book;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.home.book.bookdetail.BookDetail;
import eric.app.cabaca.response.BookResponse;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment implements BookAdapter.ItemListener, BookView{
    private static final String TAG = "logv"+BookFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLiLayoutManager;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private BookAdapter adapter;
    private BookPresenter presenter;
    private String key;

    public BookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Daftar Buku Baru");
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
        getBukuBaru();
    }

    private void initLayout(View view){
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressBar = view.findViewById(R.id.progress_bar);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new BookPresenter(this,compositeDisposable,apiInterface);

        key = getString(R.string.key);
    }

    private void getBukuBaru() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getNewBook(key);
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
    public void getNewBook(BookResponse resource) {
        if (resource != null){
            progressBar.setVisibility(View.GONE);
            adapter = new BookAdapter(getActivity(), resource.getResult(),this);
            recyclerView.setAdapter(adapter);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void userClick(BookResponse.Result item) {
        Intent intent = new Intent(getActivity(), BookDetail.class);
        intent.putExtra("bookid",item.getBook_id());
        startActivity(intent);
        getActivity().finish();
    }
}
