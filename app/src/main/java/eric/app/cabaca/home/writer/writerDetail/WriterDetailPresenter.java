package eric.app.cabaca.home.writer.writerDetail;

import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.WriterDetailResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WriterDetailPresenter {
    private WriterDetailView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public WriterDetailPresenter(WriterDetailView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getDetailWriter(String user_id,String key){
        compositeDisposable.add(apiInterface.getWriterDetail(user_id,key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<WriterDetailResponse>() {
                    @Override
                    public void onNext(WriterDetailResponse writerDetailResponse) {
                        view.getNewDetailWriter(writerDetailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        view.onSuccess();

                    }
                })
        );
    }
}
