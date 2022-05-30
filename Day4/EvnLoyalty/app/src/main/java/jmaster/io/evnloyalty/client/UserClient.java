package jmaster.io.evnloyalty.client;

import jmaster.io.evnloyalty.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClient {
    @POST("/api/user/register")
    Call<User> register(@Body User user);

    @GET("/api/member/me")
    Call<User> me(@Header("Authorization") String authorization);
}
