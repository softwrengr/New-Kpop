package com.eagledeveloper.newkpop.networking;

import com.eagledeveloper.newkpop.models.WallPaperResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by eapple on 29/08/2018.
 */

public interface ApiInterface {

    @GET()
    Call<WallPaperResponseModel> showWallPapers();

}
