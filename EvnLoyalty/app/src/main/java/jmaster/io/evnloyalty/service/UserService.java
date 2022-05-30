package jmaster.io.evnloyalty.service;

import jmaster.io.evnloyalty.client.RetrofitUtils;
import jmaster.io.evnloyalty.model.User;
import retrofit2.Call;
import retrofit2.Callback;

public class UserService {
    public interface OnDataChangeListener {
        default void setUser(User user) {
        }
    }

    public void register(User user, OnDataChangeListener changeListener) {
        Call<User> userCall = RetrofitUtils.userClient().register(user);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                changeListener.setUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                changeListener.setUser(null);
            }
        });
    }

    public void me(String token, OnDataChangeListener changeListener) {
        Call<User> postCall = RetrofitUtils.userClient().me(token);

        postCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                changeListener.setUser(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
