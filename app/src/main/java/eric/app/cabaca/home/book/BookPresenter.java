package eric.app.cabaca.home.book;

import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.BookResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BookPresenter {
    private BookView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public BookPresenter(BookView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getNewBook(String key){
        compositeDisposable.add(apiInterface.getNewBook(key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<BookResponse>() {
                    @Override
                    public void onNext(BookResponse result) {
                        view.getNewBook(result);
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
