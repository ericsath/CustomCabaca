package eric.app.cabaca.home.book.bookdetail;

import eric.app.cabaca.helper.ApiInterface;
import eric.app.cabaca.response.BookIdResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BookDetailPresenter {
    private BookDetailView view;
    private CompositeDisposable compositeDisposable;
    private ApiInterface apiInterface;

    public BookDetailPresenter(BookDetailView view, CompositeDisposable compositeDisposable, ApiInterface apiInterface){
        this.view = view;
        this.compositeDisposable = compositeDisposable;
        this.apiInterface = apiInterface;
    }

    public void getDetail(String book_id,String key){
        compositeDisposable.add(apiInterface.getBookDetail(book_id,key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<BookIdResponse>() {
                    @Override
                    public void onNext(BookIdResponse bookIdResponse) {
                        view.getNewDetailBook(bookIdResponse);
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
