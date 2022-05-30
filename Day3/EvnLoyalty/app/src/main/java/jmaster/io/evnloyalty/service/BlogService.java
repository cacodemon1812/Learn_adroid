package jmaster.io.evnloyalty.service;

import java.util.ArrayList;
import java.util.List;

import jmaster.io.evnloyalty.client.RetrofitUtils;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.model.Response;
import jmaster.io.evnloyalty.model.Search;
import retrofit2.Call;
import retrofit2.Callback;

public class BlogService {
    public interface OnDataChangeListener {
        default  void setListPosts(List<Post> posts) {} ;

        default void setPost(Post post) {};
    }

    public void find(int start, int size, OnDataChangeListener changeListener) {
        Search search = new Search();
        search.setStart(start);
        search.setLength(size);

        Call<Response<Post>> postCall = RetrofitUtils.postClient().find(search);

        postCall.enqueue(new Callback<Response<Post>>() {
            @Override
            public void onResponse(Call<Response<Post>> call, retrofit2.Response<Response<Post>> response) {
                Response<Post> body = response.body();
                changeListener.setListPosts(body.getData());
            }

            @Override
            public void onFailure(Call<Response<Post>> call, Throwable t) {

            }
        });

    }

    public  void getOne(long id, OnDataChangeListener changeListener) {
        Call<Post> postCall = RetrofitUtils.postClient().get(id);

        postCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, retrofit2.Response<Post> response) {
                changeListener.setPost(response.body());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

}
