package eric.app.cabaca.home.writer.writerDetail;

import eric.app.cabaca.response.WriterDetailResponse;

public interface WriterDetailView {
    void onSuccess();
    void onError(String message);
    void getNewDetailWriter(WriterDetailResponse writerDetailResponse);
}
