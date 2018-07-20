package id.sch.rbs.app.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private static OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build();

    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder().baseUrl("https://rbs.sch.id/wp-json/").client(client).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static RestAPI getInstance() {
        return getRetrofit().create(RestAPI.class);
    }

}
