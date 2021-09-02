package cl.chilllanubb.carnetvirtual.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import cl.chilllanubb.carnetvirtual.R;
import cl.chilllanubb.carnetvirtual.services.ApiServices;
import cl.chilllanubb.carnetvirtual.utils.models.ErrorLogin;
import cl.chilllanubb.carnetvirtual.utils.models.ResponseLogin;
import cl.chilllanubb.carnetvirtual.utils.models.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private View loading;
    private View loginForm;

    private Button login;
    private Button register;

    private TextInputLayout layoutUsername;
    private TextInputLayout layoutPassword;

    private TextInputEditText username;
    private TextInputEditText password;

    private ApiServices apiServices;

    private SharedPreferences.Editor editSP;
    private SharedPreferences sp;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        gson = new Gson();

        loading = findViewById(R.id.loading);
        loginForm = findViewById(R.id.login_form);

        layoutPassword = findViewById(R.id.layout_password);
        layoutUsername= findViewById(R.id.layout_username);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        sp = getSharedPreferences(getPackageName(),MODE_PRIVATE);
        editSP = sp.edit();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_connection))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                initLogin();
                break;
        }
    }

    public void loading(@NonNull boolean load){
        if(load){
            loginForm.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        }else {
            loginForm.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }

    private void initLogin(){
        loading(true);
        if(verifyLogin()){
            String username_str = username.getText().toString();
            String password_str = password.getText().toString();
            doLogin(username_str, password_str);
        }else{
            loading(false);
        }
    }

    private boolean verifyLogin(){
        boolean response = true;
        String username_str = username.getText().toString();
        String password_str = password.getText().toString();
        resetError();

        if(username_str.isEmpty()){
            layoutUsername.setError(getText(R.string.error_empty_username));
            response = false;
        }

        if(password_str.isEmpty()){
            layoutPassword.setError(getText(R.string.error_empty_password));
            response = false;
        }

        return response;
    }

    private void doLogin(@NonNull String username, @NonNull String password){
        String deviceId = sp.getString("token_telefono", null);

        Log.d("LOGIN", "DO LOGIN");
        if(false){
            apiServices.login(null, username, password, deviceId).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    Log.d("LOGIN", response.toString());
                    if(response.isSuccessful() && response.body() != null){
                        ResponseLogin resp = response.body();
                        Log.d("LOGIN", resp.toString());

                        if (!resp.getStatus().equalsIgnoreCase("error")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                            loadDataUser(resp.getUsuario(), resp.getToken());

                            loading(false);
                            LoginActivity.this.startActivity(intent);
                            LoginActivity.this.finish();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                            builder.setMessage(resp.getMensaje())
                                    .setTitle(R.string.title_dialog_info)
                                    .setNegativeButton(R.string.close_dialog, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    if(resp.getErrores() != null){
                                        loadErrores(resp.getErrores());
                                    }
                                }
                            });

                            AlertDialog dialog = builder.create();
                            loading(false);
                            dialog.show();

                        }

                    } else {
                        loading(false);
                        try {
                            ResponseLogin resp = gson.fromJson(response.errorBody().string(), ResponseLogin.class);

                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, resp.getMensaje(), Snackbar.LENGTH_LONG).show();
                        }catch (Exception e){}
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    loading(false);
                    Toast.makeText(LoginActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }
            });

        }else{
            apiServices.login(username, null, password, deviceId).enqueue(new Callback<ResponseLogin>() {
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                    Log.d("LOGIN", response.toString());
                    if(response.isSuccessful() && response.body() != null){
                        ResponseLogin resp = response.body();
                        Log.d("LOGIN", resp.toString());

                        if (!resp.getStatus().equalsIgnoreCase("error")) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                            loadDataUser(resp.getUsuario(), resp.getToken());

                            loading(false);
                            LoginActivity.this.startActivity(intent);
                            LoginActivity.this.finish();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                            builder.setMessage(resp.getMensaje())
                                    .setTitle(R.string.title_dialog_info)
                                    .setNegativeButton(R.string.close_dialog, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    if(resp.getErrores() != null){
                                        loadErrores(resp.getErrores());
                                    }
                                }
                            });

                            AlertDialog dialog = builder.create();
                            loading(false);
                            dialog.show();

                        }

                    } else {
                        loading(false);
                        try {
                            ResponseLogin resp = gson.fromJson(response.errorBody().string(), ResponseLogin.class);

                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout, resp.getMensaje(), Snackbar.LENGTH_LONG).show();
                        }catch (Exception e){}
                    }
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    loading(false);
                    Toast.makeText(LoginActivity.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }

    private void loadDataUser(Usuario user, String token){
        editSP.putString("token_ws", token);
        editSP.putString("user", gson.toJson(user));
        editSP.commit();
    }

    private void resetError(){
        layoutUsername.setError(null);
        layoutPassword.setError(null);
    }

    private void loadErrores(ErrorLogin errores) {
        resetError();
        if(errores.getEmail() != null && errores.getEmail().length > 0){
            layoutUsername.setError(errores.getEmail()[0]);
        }

        if(errores.getUsername() != null && errores.getUsername().length > 0){
            layoutUsername.setError(errores.getUsername()[0]);
        }

        if(errores.getPassword() != null && errores.getPassword().length > 0){
            layoutPassword.setError(errores.getPassword()[0]);
        }

    }

}