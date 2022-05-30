package jmaster.io.evnloyalty.service;

import java.util.ArrayList;
import java.util.List;

import jmaster.io.evnloyalty.client.RetrofitUtils;
import jmaster.io.evnloyalty.model.Notification;
import jmaster.io.evnloyalty.model.Post;
import jmaster.io.evnloyalty.model.Response;
import jmaster.io.evnloyalty.model.Search;
import retrofit2.Call;
import retrofit2.Callback;

public class NotificationService {
    public interface OnDataChangeListener {
        default  void setListPosts(List<Notification> notifications) {} ;
    }

    public void find(int start, int size, OnDataChangeListener changeListener) {
        Search search = new Search();
        search.setStart(start);
        search.setLength(size);

        Call<Response<Notification>> postCall = RetrofitUtils.notificaitonClient().find(search);

        postCall.enqueue(new Callback<Response<Notification>>() {
            @Override
            public void onResponse(Call<Response<Notification>> call, retrofit2.Response<Response<Notification>> response) {
                Response<Notification> body = response.body();
                changeListener.setListPosts(body.getData());
            }

            @Override
            public void onFailure(Call<Response<Notification>> call, Throwable t) {

            }
        });

    }

}
