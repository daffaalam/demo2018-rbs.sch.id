package id.sch.rbs.app.network;

import java.util.List;

import id.sch.rbs.app.model.Root;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestAPI {
    @GET("wp/v2/posts?page=1")
    Call<List<Root>> getPosts();
}
