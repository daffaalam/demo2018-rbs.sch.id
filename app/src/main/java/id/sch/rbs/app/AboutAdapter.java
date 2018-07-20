package id.sch.rbs.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.MyHolder> {

    private ArrayList<String> a_adapter;
    private ArrayList<String> b_adapter;

    AboutAdapter(ArrayList<String> a_adapter, ArrayList<String> b_adapter) {
        this.a_adapter = a_adapter;
        this.b_adapter = b_adapter;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.a_adapter.setText(a_adapter.get(position));
        holder.b_adapter.setText(b_adapter.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClipboard(v.getContext(), b_adapter.get(position));
                Toast.makeText(v.getContext(), "\"" + b_adapter.get(position) + "\" is copied.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return a_adapter.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView a_adapter;
        TextView b_adapter;

        MyHolder(View itemView) {
            super(itemView);
            a_adapter = itemView.findViewById(R.id.a_about);
            b_adapter = itemView.findViewById(R.id.b_about);
        }
    }

    private void setClipboard(Context context, String text) {
        android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
        Objects.requireNonNull(clipboard).setPrimaryClip(clip);
    }
}
