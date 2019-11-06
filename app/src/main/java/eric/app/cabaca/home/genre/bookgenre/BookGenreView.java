package eric.app.cabaca.home.genre.bookgenre;

import eric.app.cabaca.response.BookResponse;

public interface BookGenreView {
    void onSuccess();
    void onError(String message);
    void getBookByGenre(BookResponse resource);
}
