package eric.app.cabaca.home.genre;

import eric.app.cabaca.response.GenreResponse;

public interface GenreView {
    void onSuccess();
    void onError(String message);
    void getGenreBook(GenreResponse resource);
}
