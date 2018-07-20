package id.sch.rbs.app;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class AboutActivity extends AppCompatActivity {

    TextView ver_about;
    String[] A = {
            "Call Center",
            "SDIT (Amrul Tauhid)",
            "SMPI (Azis Yanuar)",
            "SMKI (Ahmad Suryadi)",
            "Facebook",
            "Email",
            "Website",
            "Maps"
    };
    String[] B = {
            "02129089400",
            "08568903937",
            "08157109452",
            "083899899334",
            "https://www.facebook.com/SmkRabbaaniiIslamicSchool",
            "info@rbs.sch.id",
            "http://rbs.sch.id",
            "Jl. Cimandiri 8B RT 06/02 Graha Asri 17550 Jatireja, Cikarang Timur – Bekasi"
    };
    RecyclerView rv_about;
    ArrayList<String> arrayListA;
    ArrayList<String> arrayListB;

    @SuppressLint({"SetJavaScriptEnabled", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ver_about = findViewById(R.id.ver_about);
        ver_about.setText("version " + BuildConfig.VERSION_NAME);
        rv_about = findViewById(R.id.rv_about);
        rv_about.setHasFixedSize(true);
        rv_about.setLayoutManager(new LinearLayoutManager(this));
        arrayListA = new ArrayList<>();
        arrayListB = new ArrayList<>();
        for (int x = 0; x < A.length; x++) {
            arrayListA.add(A[x]);
            arrayListB.add(B[x]);
        }
        rv_about.setAdapter(new AboutAdapter(arrayListA, arrayListB));
    }
}
