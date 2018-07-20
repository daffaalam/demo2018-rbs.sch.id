package id.sch.rbs.app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import id.sch.rbs.app.model.Root;
import id.sch.rbs.app.network.Client;
import id.sch.rbs.app.network.RestAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_main;
    Call<List<Root>> rootCall;
    ArrayList<String> title_main;
    ArrayList<String> excerpt_main;
    ArrayList<String> link_main;
    ProgressDialog progdia_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progdia_main = new ProgressDialog(this);
        progdia_main.show();
        progdia_main.setMessage("loading...");
        rv_main = findViewById(R.id.rv_main);
        rv_main.setHasFixedSize(true);
        rv_main.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        title_main = new ArrayList<>();
        excerpt_main = new ArrayList<>();
        link_main = new ArrayList<>();
        rootCall = Client.getInstance().getPosts();
        rootCall.enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                progdia_main.dismiss();
                if (response.isSuccessful()) {
                    for (int x = 0; x < Objects.requireNonNull(response.body()).size(); x++) {
                        title_main.add(Objects.requireNonNull(response.body()).get(x).getTitle().getRendered());
                        excerpt_main.add(Objects.requireNonNull(response.body()).get(x).getExcerpt().getRendered());
                        link_main.add(Objects.requireNonNull(response.body()).get(x).getLink());
                        rv_main.setAdapter(new MainAdapter(title_main, excerpt_main, link_main));
                    }
                } else {
                    rv_main.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                progdia_main.dismiss();
                rv_main.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
