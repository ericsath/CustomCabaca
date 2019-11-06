package eric.app.cabaca.home.writer;

import eric.app.cabaca.response.WriterResponse;

public interface WriterView {
    void onSuccess();
    void onError(String message);
    void getWriterPopular(WriterResponse result);
}
