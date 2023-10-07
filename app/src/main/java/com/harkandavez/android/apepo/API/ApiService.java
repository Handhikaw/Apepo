package com.harkandavez.android.apepo.API;


import com.harkandavez.android.apepo.Model.ModelData;
import com.harkandavez.android.apepo.Tree;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by JhonDev on 05/10/2016.
 */

public interface ApiService {

    /*@FormUrlEncoded
    @POST("tambah_data.php")
    Call<ResponseBody> tambahData(@Field("nama") String nama, @Field("kelas") String kelas);

    @FormUrlEncoded
    @POST("edit_data.php")
    Call<ResponseBody> editData(@Field("id_mhs") String id, @Field("nama_mhs") String nama, @Field("kelas_mhs") String kelas);
//
    @FormUrlEncoded
    @POST("hapus_data.php")
    Call<ResponseBody> hapusData(@Field("id_mhs") String id_mhs);

    @GET("semua_data.php")
    Call<List<Tree>> getSemuaMhs();*/

    @GET("singletree.php")
    Call<List<ModelData>> getSingleData(@Query("id_tree") String id);

}
