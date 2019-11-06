package eric.app.cabaca.home.genre.bookgenre;

import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.BookResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BookGenrePresenter {
    private BookGenreView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public BookGenrePresenter(BookGenre view, CompositeDisposable compositeDisposable, ApiInterface apiInterface) {
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getNewBook(String inih, String key){
        compositeDisposable.add(apiInterface.getBookByGenre(inih,key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<BookResponse>() {
                    @Override
                    public void onNext(BookResponse result) {
                        view.getBookByGenre(result);
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
