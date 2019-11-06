package eric.app.cabaca.home.writer.writerDetail;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import eric.app.cabaca.MainActivity;
import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.home.writer.writerDetail.writerDetailMore.WriterDetailMore;
import eric.app.cabaca.response.WriterDetailResponse;
import io.reactivex.disposables.CompositeDisposable;

public class WriterDetailActivity extends AppCompatActivity implements WriterDetailView{
    public static final String TAG="logv"+WriterDetailActivity.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private WriterDetailPresenter presenter;
    private ProgressBar progressBar;
    private String key,id,covernya,base_image,key_image,namanya;
    private TextView name,username,follower,following,email,nohp,desc,link,birthday,gender;
    private Button more;
    private ImageView photo;
    private Integer user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer_detail);
        this.setTitle("Detail Penulis");

        initLayout();
        getDetailWriter();

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriterDetailActivity.this, WriterDetailMore.class);
                intent.putExtra("user_id", user_id);
                intent.putExtra("name",namanya);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initLayout(){
        progressBar = findViewById(R.id.progress_bar);
        name = findViewById(R.id.nama);
        username = findViewById(R.id.username);
        follower = findViewById(R.id.follower);
        following = findViewById(R.id.following);
        email = findViewById(R.id.email);
        nohp = findViewById(R.id.phone);
        desc = findViewById(R.id.desc);
        more = findViewById(R.id.more);
        photo = findViewById(R.id.photo);
        link = findViewById(R.id.link);
        birthday = findViewById(R.id.ultah);
        gender = findViewById(R.id.gender);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new WriterDetailPresenter(this,compositeDisposable,apiInterface);

        user_id = getIntent().getIntExtra("user_id",0);
        key = getString(R.string.key);
    }

    private void getDetailWriter(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getDetailWriter(String.valueOf(user_id),key);
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
    public void getNewDetailWriter(WriterDetailResponse writerDetailResponse) {
        if (writerDetailResponse.getSuccess().equalsIgnoreCase("true")){
            name.setText(writerDetailResponse.result.getName());
            namanya = writerDetailResponse.result.getName();
            username.setText(writerDetailResponse.result.getUsername());
            covernya = writerDetailResponse.result.getPhoto_url();
            base_image = getString(R.string.image_url);
            key_image = getString(R.string.image_key);
            Glide.with(this)
                    .load(base_image+covernya+key_image)
                    .into(photo);
            Log.d(TAG, "getNewDetailWriter: "+base_image+covernya+key_image);
            follower.setText(""+writerDetailResponse.result.getCount_follower());
            following.setText(""+writerDetailResponse.result.getCount_following());
            email.setText(writerDetailResponse.result.getEmail());
            nohp.setText(writerDetailResponse.result.getPhone());
            desc.setText(writerDetailResponse.result.getDeskripsi());

            if (writerDetailResponse.result.getLink_user()!=null){
                link.setText(writerDetailResponse.result.getLink_user());
            } else if(writerDetailResponse.result.getLink_user()==null) {
                link.setText("Tidak Tersedia");
            }
            if (writerDetailResponse.result.getGender()!=null){
                gender.setText(writerDetailResponse.result.getGender());
            } else if(writerDetailResponse.result.getGender()==null){
                gender.setText("Tidak Tersedia");
            }
            if (writerDetailResponse.result.getBirthday()!=null){
                birthday.setText(writerDetailResponse.result.getBirthday());
            } else if(writerDetailResponse.result.getBirthday()==null) {
                birthday.setText("Tidak Tersedia");
            }
        } else {
            Toast.makeText(this, "Kesalahan Koneksi", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(WriterDetailActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
