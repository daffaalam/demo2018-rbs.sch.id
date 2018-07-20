package id.sch.rbs.app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyHolder> {

    private ArrayList<String> title_adapter;
    private ArrayList<String> excerpt_adapter;
    private ArrayList<String> link_adapter;

    MainAdapter(ArrayList<String> title_adapter, ArrayList<String> excerpt_adapter, ArrayList<String> link_adapter) {
        this.title_adapter = title_adapter;
        this.excerpt_adapter = excerpt_adapter;
        this.link_adapter = link_adapter;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.title_adapter.setText(title_adapter.get(position));
        holder.excerpt_adapter.setText(excerpt_adapter.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(), ContentActivity.class).putExtra("LINK", link_adapter.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return title_adapter.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView title_adapter;
        TextView excerpt_adapter;

        MyHolder(View itemView) {
            super(itemView);
            title_adapter = itemView.findViewById(R.id.title_posts_main);
            excerpt_adapter = itemView.findViewById(R.id.excerpt_posts_main);
        }
    }
}
