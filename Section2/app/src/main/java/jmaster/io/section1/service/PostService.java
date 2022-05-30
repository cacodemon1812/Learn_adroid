package jmaster.io.section1.service;

import jmaster.io.section1.model.PostDTO;
import jmaster.io.section1.model.ResponseDTO;
import jmaster.io.section1.model.SearchPostDTO;
import jmaster.io.section1.model.Token;
import jmaster.io.section1.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {
    @POST("/api/post/search")
    Call<ResponseDTO<PostDTO>> search(@Body SearchPostDTO user);

    @GET("/api/post/{id}")
    Call<PostDTO> get(@Path("id") int id);
}
