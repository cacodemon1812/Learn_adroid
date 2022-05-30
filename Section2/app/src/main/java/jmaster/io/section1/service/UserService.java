package jmaster.io.section1.service;

import jmaster.io.section1.model.Token;
import jmaster.io.section1.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    @POST("/api/user/register")
    Call<User> register(@Body User user);

    @FormUrlEncoded
    @POST("/api/login")
    Call<Token> login(@Field("username ") String username,
                      @Field("password ") String password,
                      @Field("deviceToken ") String deviceToken);


    @GET("/api/member/me")
    Call<User> me(@Header("Authorization") String authorization);
}
