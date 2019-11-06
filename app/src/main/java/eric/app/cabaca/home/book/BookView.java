package eric.app.cabaca.home.book;

import eric.app.cabaca.response.BookResponse;

public interface BookView {
    void onSuccess();
    void onError(String message);
    void getNewBook(BookResponse resource);
}
