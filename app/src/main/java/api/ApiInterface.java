package api;

import models.LinkedUsers;
import models.TvEmac;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Accept-Version: 1.0.0"})
    @POST("getlinkedusers")
    Call<LinkedUsers> GetLinkedUser(@Body TvEmac tvEmac);
}

