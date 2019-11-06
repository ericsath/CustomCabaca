package eric.app.cabaca.home.writer.writerDetail.writerDetailMore;


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
import android.widget.TextView;
import android.widget.Toast;

import eric.app.cabaca.R;
import eric.app.cabaca.helper.ApiClient;
import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.helper.Utils;
import eric.app.cabaca.home.book.bookdetail.BookDetail;
import eric.app.cabaca.home.writer.writerDetail.WriterDetailPresenter;
import eric.app.cabaca.home.writer.writerDetail.WriterDetailView;
import eric.app.cabaca.response.WriterDetailResponse;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingListFragment extends Fragment implements ReadingListAdapter.ItemListener, WriterDetailView {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLiLayoutManager;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private ReadingListAdapter adapter;
    private WriterDetailPresenter presenter;
    private String key;
    private String user_id;
    private TextView notfound;

    public ReadingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reading_list, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
        getDetailWriter();
    }

    private void getDetailWriter() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.getDetailWriter(user_id,key);
    }

    private void initLayout(View view) {
        notfound = view.findViewById(R.id.notfound);
        recyclerView = view.findViewById(R.id.recyler);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progressBar = view.findViewById(R.id.progress_bar);
        user_id = Utils.readSharedSetting(getActivity(),"user_id",null);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new WriterDetailPresenter(this,compositeDisposable,apiInterface);

        key = getString(R.string.key);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getNewDetailWriter(WriterDetailResponse writerDetailResponse) {
        if (writerDetailResponse.result.getReading()!=null){
            progressBar.setVisibility(View.GONE);
            adapter = new ReadingListAdapter(getActivity(),writerDetailResponse.result.getReading(),this);
            recyclerView.setAdapter(adapter);
        } else {
            notfound.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void userClick(WriterDetailResponse.Result.Reading item) {
        Intent intent = new Intent(getActivity(), BookDetail.class);
        intent.putExtra("bookid", String.valueOf(item.getId()));
        startActivity(intent);
        getActivity().finish();
    }
}
