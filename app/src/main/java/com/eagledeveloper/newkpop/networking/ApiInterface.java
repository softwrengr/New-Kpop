package com.eagledeveloper.newkpop.networking;

import com.eagledeveloper.newkpop.models.WallPaperResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by eapple on 29/08/2018.
 */

public interface ApiInterface {

    @GET("categories_details?")
    Call<WallPaperResponseModel> showWallPapers(@Query("page") int page);

}
