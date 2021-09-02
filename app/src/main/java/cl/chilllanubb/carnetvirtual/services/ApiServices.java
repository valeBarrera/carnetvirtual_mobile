package cl.chilllanubb.carnetvirtual.services;

import androidx.annotation.Nullable;

import cl.chilllanubb.carnetvirtual.utils.models.ResponseCita;
import cl.chilllanubb.carnetvirtual.utils.models.ResponseLogin;
import cl.chilllanubb.carnetvirtual.utils.models.ResponseReceta;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("/api/usuario/login")
    Call<ResponseLogin> login(@Field("username") @Nullable String username, @Field("email") @Nullable String email, @Field("password") String password,
                              @Field("token_telefono") String token_telefono);

    @FormUrlEncoded
    @POST("/api/usuario/logout")
    Call<ResponseLogin> logout(@Field("empty") @Nullable String empty, @Header("Authorization") String token_ws);

    @FormUrlEncoded
    @POST("/api/usuario/change_password")
    Call<ResponseLogin> changePassword(@Field("password") @Nullable String password, @Field("password_confirmation") @Nullable String password_confirmation, @Header("Authorization") String token_ws);

    @FormUrlEncoded
    @POST("/api/paciente/citas")
    Call<ResponseCita> citas(@Field("empty") String empty, @Header("Authorization") String token_ws);

    @FormUrlEncoded
    @POST("/api/paciente/recetas")
    Call<ResponseReceta> recetas(@Field("empty") String empty, @Header("Authorization") String token_ws);
}
