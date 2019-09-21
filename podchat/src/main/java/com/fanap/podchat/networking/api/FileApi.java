package com.fanap.podchat.networking.api;

import android.support.annotation.NonNull;

import com.fanap.podchat.mainmodel.FileUpload;
import com.fanap.podchat.model.FileImageUpload;

import java.util.ArrayList;

import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

public interface FileApi {
    @NonNull
    @Multipart
    @POST("nzh/uploadFile")
    Observable<Response<FileUpload>> sendFile(
            @Part MultipartBody.Part file
            , @Header("_token_") String token
            , @Header("_token_issuer_") int tokenIssuer
            , @Part("fileName") RequestBody fileName);


    @NonNull
    @Multipart
    @POST("nzh/uploadFile")
    Call<Response<FileUpload>> sendFileRet(
            @Part MultipartBody.Part file
            , @Header("_token_") String token
            , @Header("_token_issuer_") int tokenIssuer
            , @Part("fileName") RequestBody fileName);


    @NonNull
    @Multipart
    @POST("nzh/uploadImage")
    Observable<Response<FileImageUpload>> sendImageFile(
            @Part MultipartBody.Part image
            , @Header("_token_") String token
            , @Header("_token_issuer_") int tokenIssuer
            , @Part("fileName") RequestBody fileName);

    @NonNull
    @POST("nzh/drive/uploadFileFromUrl")
    Observable<Response<FileUpload>> uploadFileFromUrl(
            @Header("_token_") String token
            , @Header("_token_issuer_") int tokenIssuer
            , @Part("fileName") String fileName
            , @Part("folderHash") String folderHash
            , @Part("metadata") String metadata
            , @Part("description") String description
            , @Part("isPublic") boolean isPublic
            , @Part("tags") ArrayList<String> tags
    );

    @NonNull
    @GET("nzh/file/")
    Observable<Response<ResponseBody>> getFile
            (@Query("fileId") int fileId
                    , @Query("downloadable") boolean downloadable
                    , @Query("hashCode") String hashCode);


}
