package jmaster.io.section1.service;

import retrofit2.Retrofit;

public class RetrofitUtils {
    final static String URL = "http://54.150.115.104:8080/";

    public static UserService userService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        return retrofit.create(UserService.class);
    }

    public static PostService postService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        return retrofit.create(PostService.class);
    }

    public static NotificaitonService notificaitonService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();
        return retrofit.create(NotificaitonService.class);
    }
}
