package eric.app.cabaca.home.genre;

import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.GenreResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GenrePresenter {
    private GenreView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public GenrePresenter(GenreView view,CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getGenreBook(String key){
        compositeDisposable.add(apiInterface.getBookGenre(key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<GenreResponse>() {
                    @Override
                    public void onNext(GenreResponse resource) {
                        view.getGenreBook(resource);
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
