package eric.app.cabaca.home.book.bookdetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import eric.app.cabaca.R;
import eric.app.cabaca.response.BookIdResponse;

public class BookDetailAdapter extends RecyclerView.Adapter<BookDetailAdapter.ViewHolder> {
    private Context context;
    private List<BookIdResponse.Result.Reviews> reviewsList;
    private String covernya,base_image,key_image;

    public BookDetailAdapter(Context context, List<BookIdResponse.Result.Reviews> reviewsList){
        this.context = context;
        this.reviewsList = reviewsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_detail_book,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder){
            final BookIdResponse.Result.Reviews item = reviewsList.get(position);
            viewHolder.name.setText(item.User_by_reviewer_id.getName());
            viewHolder.review.setText(item.getReview());
            covernya = item.User_by_reviewer_id.getPhoto_url();
            covernya.replace("%2F","/");
            base_image = context.getString(R.string.image_url);
            key_image = context.getString(R.string.image_key);
            Glide.with(context)
                    .load(base_image+covernya+key_image)
                    .into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (reviewsList == null)
            return 0;
        else
            return  reviewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,review;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_nama);
            review = itemView.findViewById(R.id.tv_review);
            imageView = itemView.findViewById(R.id.user);
        }
    }
}
