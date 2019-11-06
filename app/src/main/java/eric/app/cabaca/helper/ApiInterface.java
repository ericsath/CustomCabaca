package eric.app.cabaca.helper;


import eric.app.cabaca.response.BookIdResponse;
import eric.app.cabaca.response.BookResponse;
import eric.app.cabaca.response.GenreResponse;
import eric.app.cabaca.response.WriterDetailResponse;
import eric.app.cabaca.response.WriterResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("cabaca/_table/genre")
    Observable<GenreResponse> getBookGenre(@Header("x-dreamfactory-api-key")String key);

    @GET("book/uptodate?limit=7")
    Observable<BookResponse> getNewBook(@Header("x-dreamfactory-api-key")String key);

    @GET("writer/popular?limit=10")
    Observable<WriterResponse> getWriter(@Header("x-dreamfactory-api-key")String key);

    @GET("book/category")
    Observable<BookResponse> getBookByGenre(@Query("id") String id , @Header("x-dreamfactory-api-key")String key);

    @GET("book/detail/{book_id}")
    Observable<BookIdResponse> getBookDetail(@Path("book_id") String book_id , @Header("x-dreamfactory-api-key")String key);

    @GET("writer/detail/{user_id}")
    Observable<WriterDetailResponse> getWriterDetail(@Path("user_id") String user_id , @Header("x-dreamfactory-api-key")String key);
}
