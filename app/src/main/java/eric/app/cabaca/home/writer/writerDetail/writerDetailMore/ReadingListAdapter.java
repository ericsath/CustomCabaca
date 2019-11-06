package eric.app.cabaca.home.writer.writerDetail.writerDetailMore;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import eric.app.cabaca.R;
import eric.app.cabaca.response.WriterDetailResponse;

public class ReadingListAdapter extends RecyclerView.Adapter<ReadingListAdapter.ViewHolder> {
    public static final String TAG = "logv"+KaryaAdapter.class.getSimpleName();
    private Context context;
    private List<WriterDetailResponse.Result.Reading> readingList;
    private String cover_url,base_image, key_image;
    private ItemListener itemListener;


    public ReadingListAdapter(Context context,List<WriterDetailResponse.Result.Reading> readingList,ItemListener itemListener) {
        this.context = context;
        this.readingList = readingList;
        this.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_book,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final WriterDetailResponse.Result.Reading item = readingList.get(position);
        holder.name.setText(item.getTitle());
        holder.rating.setText(item.getRate_sum());

        cover_url = item.getCover_url();
        cover_url.replace("%2F","/");
        base_image = context.getString(R.string.image_url);
        key_image = context.getString(R.string.image_key);

        Glide.with(context)
                .load(base_image+cover_url+key_image)
                .into(holder.thumbnail);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.userClick(item);
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener.userClick(item);

            }
        });
    }

    @Override
    public int getItemCount() {
        return readingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, rating;
        public ImageView thumbnail;
        public CardView cardView;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.title);
            rating = view.findViewById(R.id.rating);
            thumbnail = view.findViewById(R.id.thumbnail);
            cardView = view.findViewById(R.id.card_view);
        }
    }
    public interface ItemListener{
        void userClick(WriterDetailResponse.Result.Reading item);
    }
}
