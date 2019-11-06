package eric.app.cabaca.home.writer.writerDetail.writerDetailMore;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.home.writer.writerDetail.WriterDetailActivity;
import eric.app.cabaca.home.writer.writerDetail.WriterDetailPresenter;
import io.reactivex.disposables.CompositeDisposable;

public class WriterDetailMore extends AppCompatActivity{
    public static final String TAG="logv"+ WriterDetailMore.class.getSimpleName();
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private WriterDetailPresenter presenter;
    private ProgressBar progressBar;
    private String key,nama;
    private Integer user_id;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writer_detail_more);
        this.setTitle("Detail Penulis");
        initLayout();
    }

    private void initLayout() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new KaryaFragment(), "Karya");
        adapter.addFragment(new ReadingListFragment(), "Daftar Bacaan");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        user_id = getIntent().getIntExtra("user_id",0);
        key = getString(R.string.key);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(WriterDetailMore.this, WriterDetailActivity.class);
        startActivity(intent);
        finish();
    }
}
