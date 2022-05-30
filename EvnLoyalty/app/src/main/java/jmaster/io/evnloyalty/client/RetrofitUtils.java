package jmaster.io.evnloyalty.client;

import jmaster.io.evnloyalty.service.BlogService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public  final static String URL = "http://54.150.115.104:8080/";

    public  static  Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build();

    public static PostClient postClient() {
        return retrofit.create(PostClient.class);
    }

    public static UserClient userClient() {
        return retrofit.create(UserClient.class);
    }

    public static LoginClient loginClient() {
        return retrofit.create(LoginClient.class);
    }

    public static NotificaitonClient notificaitonClient() {
        return retrofit.create(NotificaitonClient.class);
    }

}
