package jmaster.io.section1.service;

import jmaster.io.section1.model.PostDTO;
import jmaster.io.section1.model.ResponseDTO;
import jmaster.io.section1.model.SearchPostDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NotificaitonService {
    @POST("/api/notification/search")
    Call<ResponseDTO<NotificationDTO>> search(@Body SearchDTO searchDTO);

    @GET("/api/notification/{id}")
    Call<NotificationDTO> me(@Path("id") int id);

    @DELETE("/api/notification/{id}")
    Call<Void> delete(@Path("id") int id);
}
