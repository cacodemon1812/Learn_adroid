package jmaster.io.evnloyalty.client;

import jmaster.io.evnloyalty.model.AccessToken;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginClient {
    @FormUrlEncoded
    @POST("/api/login")
    Call<AccessToken> login(@Field("username") String username,
                            @Field("password") String password,
                            @Field("deviceToken") String deviceToken);
}
