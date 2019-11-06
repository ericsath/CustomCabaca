package eric.app.cabaca.home.genre;

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
import eric.app.cabaca.response.GenreResponse;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    private Context context;
    private List<GenreResponse.Resource> resourceList;
    private ItemListener itemListener;

    public GenreAdapter(Context context, List<GenreResponse.Resource> resourceList, ItemListener itemListener){
        this.context = context;
        this.resourceList = resourceList;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder){
            final GenreResponse.Resource item = resourceList.get(position);
            viewHolder.genre.setText(item.getTitle());
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.clickUser(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (resourceList == null)
            return 0;
        else
            return  resourceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView genre;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            genre = itemView.findViewById(R.id.tv_genre);
            cardView = itemView.findViewById(R.id.card1);
        }
    }

    public interface ItemListener{
        void clickUser(GenreResponse.Resource item);
    }
}
