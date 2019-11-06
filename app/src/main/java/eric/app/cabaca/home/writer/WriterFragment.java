package eric.app.cabaca.home.writer;


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
import eric.app.cabaca.helper.Utils;
import eric.app.cabaca.home.writer.writerDetail.WriterDetailActivity;
import eric.app.cabaca.response.WriterResponse;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriterFragment extends Fragment implements WriterAdapter.ItemListener, WriterView {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLiLayoutManager;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;
    private ProgressBar progressBar;
    private WriterPresenter presenter;
    private WriterAdapter adapter;
    private String key;


    public WriterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Daftar Penulis Populer");
        return inflater.inflate(R.layout.fragment_writer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLayout(view);
        getWriter();
    }

    private void initLayout(View view){
        recyclerView = view.findViewById(R.id.recyler);
        rvLiLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(rvLiLayoutManager);
        progressBar = view.findViewById(R.id.progress_bar);

        compositeDisposable = new CompositeDisposable();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        presenter = new WriterPresenter(this,compositeDisposable,apiInterface);

        key = getString(R.string.key);
    }

    private void getWriter(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getWriterPopular(key);
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
    public void getWriterPopular(WriterResponse result) {
        if (result != null) {
            progressBar.setVisibility(View.GONE);
            adapter = new WriterAdapter(getActivity(), result.getResult(),this);
            recyclerView.setAdapter(adapter);
        } else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void clickWriter(WriterResponse.Result item) {
        Intent intent = new Intent(getActivity(), WriterDetailActivity.class);
        intent.putExtra("user_id",item.getUser_id());
        Utils.saveSharedSetting(getActivity(),"user_id",String.valueOf(item.getUser_id()));
//        Toast.makeText(getActivity(), ""+item.getUser_id(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
        getActivity().finish();
    }
}
