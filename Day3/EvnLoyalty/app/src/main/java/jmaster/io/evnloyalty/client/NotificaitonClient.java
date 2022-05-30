package jmaster.io.evnloyalty.client;

import jmaster.io.evnloyalty.model.Notification;
import jmaster.io.evnloyalty.model.Response;
import jmaster.io.evnloyalty.model.Search;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NotificaitonClient {
    @POST("/api/notification/search")
    Call<Response<Notification>> find(@Body Search search);
}
