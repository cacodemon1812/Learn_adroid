package jmaster.io.evnloyalty.service;

import jmaster.io.evnloyalty.client.RetrofitUtils;
import jmaster.io.evnloyalty.model.AccessToken;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginService {
    public interface OnDataChangeListener {
        default void setToken(AccessToken accessToken) {
        }
    }

    public void login(String username, String password, String deviceToken, OnDataChangeListener changeListener) {
        Call<AccessToken> tokenCall = RetrofitUtils.loginClient().login(username, password, deviceToken);

        tokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, retrofit2.Response<AccessToken> response) {
                changeListener.setToken(response.body());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                changeListener.setToken(null);
            }
        });
    }

}
