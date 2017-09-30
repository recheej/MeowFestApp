package com.example.rechee.meowfest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rechee.meowfest.models.Cat;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Rechee on 9/30/2017.
 */

class CatListAdapter extends RecyclerView.Adapter<CatListAdapter.ViewHolder> {
    private final List<Cat> cats;
    private final Context context;
    SimpleDateFormat dateFormattter;

    CatListAdapter(List<Cat> cats, Context context) {
        this.cats = cats;
        dateFormattter = new SimpleDateFormat("MM/dd/yy");
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView date;
        private TextView title;
        private TextView description;
        private ImageView catImageView;

        ViewHolder(final View itemView) {
            super(itemView);

            catImageView = (ImageView) itemView.findViewById(R.id.imageView_cat);
            date = (TextView) itemView.findViewById(R.id.textView_date);
            title = (TextView) itemView.findViewById(R.id.textView_title);
            description = (TextView) itemView.findViewById(R.id.textView_description);
        }

        void bind(Cat catToBind){
            this.date.setText(dateFormattter.format(catToBind.timestamp));
            this.title.setText(catToBind.title);
            this.description.setText(catToBind.description);

            Picasso.with(context)
                    .load(catToBind.image_url)
                    .fit()
                    .centerCrop()
                    .into(catImageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cat_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(this.cats.get(position));
    }

    @Override
    public int getItemCount() {
        return this.cats.size();
    }
}
