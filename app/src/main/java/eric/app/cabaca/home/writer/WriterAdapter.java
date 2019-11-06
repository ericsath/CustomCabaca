package eric.app.cabaca.home.writer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import eric.app.cabaca.R;
import eric.app.cabaca.response.WriterResponse;

public class WriterAdapter extends RecyclerView.Adapter<WriterAdapter.ViewHolder> {
    private Context context;
    private List<WriterResponse.Result> results;
    private ItemListener itemListener;

    public WriterAdapter(Context context, List<WriterResponse.Result> results, ItemListener itemListener){
        this.context = context;
        this.results = results;
        this.itemListener = itemListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.rv_genre,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder){
            final WriterResponse.Result item = results.get(i);
            viewHolder.name.setText(item.getName());
            viewHolder.cdl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.clickWriter(item);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (results == null)
            return 0;
        else
            return  results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public CardView cdl;

        public ViewHolder(View view) {
            super(view);
            name = itemView.findViewById(R.id.tv_genre);
            cdl = itemView.findViewById(R.id.card1);

        }
    }

    public interface ItemListener{
        void clickWriter(WriterResponse.Result item);
    }
}
