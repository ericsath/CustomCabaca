package eric.app.cabaca.home.writer;

import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.WriterResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WriterPresenter {
    private WriterView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public WriterPresenter(WriterView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getWriterPopular(String key){
        compositeDisposable.add(apiInterface.getWriter(key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<WriterResponse>() {
                    @Override
                    public void onNext(WriterResponse result) {
                        view.getWriterPopular(result);
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
