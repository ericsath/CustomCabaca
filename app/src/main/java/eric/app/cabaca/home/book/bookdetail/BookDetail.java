package eric.app.cabaca.home.book.bookdetail;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import eric.app.cabaca.MainActivity;
import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.BookIdResponse;
import io.reactivex.disposables.CompositeDisposable;

public class BookDetail extends AppCompatActivity implements BookDetailView {
    private BookDetailPresenter presenter;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private String key,book_id,covernya,base_image,key_image,genreList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLiLayoutManager;
    private TextView judul,penulis,synopsis,genre,ket;
    private ImageView photo;
    private BookDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail2);
        this.setTitle("Detail Buku");

        recyclerView = findViewById(R.id.recyler);
        rvLiLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvLiLayoutManager);
        progressBar = findViewById(R.id.progress_bar);
        judul = findViewById(R.id.judul);
        penulis = findViewById(R.id.penulis);
        genre = findViewById(R.id.genres);
        synopsis = findViewById(R.id.synopsis);
        photo = findViewById(R.id.cover);
        ket = findViewById(R.id.ket);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new BookDetailPresenter(this,compositeDisposable,apiInterface);
        key = getString(R.string.key);
        book_id = getIntent().getStringExtra("bookid");
        getDetail();
    }

    private void getDetail() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getDetail(book_id,key);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getNewDetailBook(BookIdResponse bookIdResponse) {
        progressBar.setVisibility(View.GONE);
        if (bookIdResponse.getResult()!=null){
            covernya = bookIdResponse.result.getCover_url();
            covernya.replace("%2F","/");
            base_image = getString(R.string.image_url);
            key_image = getString(R.string.image_key);
            Glide.with(this)
                    .load(base_image+covernya+key_image)
                    .into(photo);
            judul.setText(bookIdResponse.result.getTitle());
            penulis.setText(bookIdResponse.result.getWriter_by_writer_id().User_by_user_id.getName());
            for (int i=0;i<bookIdResponse.result.getGenres().size();i++){
                genreList = bookIdResponse.result.getGenres().get(i).getTitle();
                genre.append(genreList+System.lineSeparator());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                synopsis.setText(Html.fromHtml(bookIdResponse.result.getSynopsis(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                synopsis.setText(Html.fromHtml(bookIdResponse.result.getSynopsis()));
            }

            if (bookIdResponse.result.getReviews()!=null){
                adapter = new BookDetailAdapter(this,bookIdResponse.result.getReviews());
                recyclerView.setAdapter(adapter);
            } else if (bookIdResponse.result.getReviews().size()==0){
                ket.setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(this, "Detail Buku Kosong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BookDetail.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
