package eric.app.cabaca.home.genre.bookgenre;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import eric.app.cabaca.MainActivity;
import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.home.book.bookdetail.BookDetail;
import eric.app.cabaca.response.BookResponse;
import io.reactivex.disposables.CompositeDisposable;

public class BookGenre extends AppCompatActivity implements BookGenreAdapter.ItemListener, BookGenreView {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLiLayoutManager;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private BookGenrePresenter presenter;
    private BookGenreAdapter adapter;
    private String id,key,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getIntent().getStringExtra("title");
        this.setTitle("Genre Buku "+title);
        setContentView(R.layout.activity_book_detail);

        initLayout();
        getbookbygenre();
    }
    private void initLayout(){
        recyclerView = findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressBar = findViewById(R.id.progress_bar);

        id = getIntent().getStringExtra("id");

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new BookGenrePresenter(this,compositeDisposable,apiInterface);

        key = getString(R.string.key);
    }

    private void getbookbygenre(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getNewBook(id,key);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getBookByGenre(BookResponse resource) {
        if (resource != null){
            progressBar.setVisibility(View.GONE);
            adapter = new BookGenreAdapter(this, resource.getResult(),this);
            recyclerView.setAdapter(adapter);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void userClick(BookResponse.Result item) {
        Intent intent = new Intent(BookGenre.this, BookDetail.class);
        intent.putExtra("bookid", String.valueOf(item.getId()));
        startActivity(intent);
        finish();
    }
}
