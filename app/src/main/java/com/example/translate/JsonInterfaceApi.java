package com.example.translate;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonInterfaceApi {
    /*
   @GET("users")
   Call<List<Users>> get();

   @GET("posts")
   Call<List<Users>> getPosts(@QueryMap Map<String, String> parameters);

   @GET
   Call<List<Comment>> getComments(@Url String url);
   @POST("posts")
   Call<Users> createPost(@Body Users post);

   @FormUrlEncoded
   @POST("")
   Call<Users> createPost(
           @Field("username") String name,
           @Field("friends") int friends
   );
   @FormUrlEncoded
   @POST("users")
   Call<Users> createPost(@FieldMap Map<String, String> fields);


     */
    @Headers({
            "Content-Type: application/x-www-form-urlencoded",
            "User-Agent: AndroidTranslate/5.3.0.RC02.130475354-53000263 5.1 phone TRANSLATE_OPM5_TEST_1"
    })


    @POST("single?")
    Call<String> get(@Query("dt") List<String> vals, @QueryMap Map<String,String> filters);



}
