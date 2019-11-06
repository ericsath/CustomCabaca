package eric.app.cabaca.home.book.bookdetail;

import eric.app.cabaca.response.BookIdResponse;

public interface BookDetailView {
    void onSuccess();
    void onError(String message);
    void getNewDetailBook(BookIdResponse bookIdResponse);
}
