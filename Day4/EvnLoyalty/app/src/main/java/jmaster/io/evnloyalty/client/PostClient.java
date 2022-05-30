package jmaster.io.evnloyalty.client;

import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.model.Response;
import jmaster.io.evnloyalty.model.Search;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostClient {
    @POST("/api/post/search")
    Call<Response<Post>> find(@Body Search search);

    @GET("/api/post/{id}")
    Call<Post> get(@Path("id") long id);

}
