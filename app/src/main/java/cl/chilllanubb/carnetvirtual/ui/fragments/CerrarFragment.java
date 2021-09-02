package cl.chilllanubb.carnetvirtual.ui.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import cl.chilllanubb.carnetvirtual.R;
import cl.chilllanubb.carnetvirtual.services.ApiServices;
import cl.chilllanubb.carnetvirtual.ui.LoginActivity;
import cl.chilllanubb.carnetvirtual.ui.MainActivity;
import cl.chilllanubb.carnetvirtual.utils.models.ResponseLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CerrarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CerrarFragment extends Fragment {

    private String token_ws;

    private View loading;
    private View info_session;

    private ApiServices apiServices;
    private SharedPreferences.Editor editor;

    public CerrarFragment() {
        // Required empty public constructor
    }

    public static CerrarFragment newInstance() {
        CerrarFragment fragment = new CerrarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(),getActivity().MODE_PRIVATE);
        editor = sharedPreferences.edit();
        token_ws = String.format("Bearer %s",sharedPreferences.getString("token_ws", ""));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_connection))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.cerrar_fragment, container, false);

        loading = root.findViewById(R.id.loading);
        info_session = root.findViewById(R.id.info_session);

        Button cerrarSesion = root.findViewById(R.id.closeSession);

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeSession();
            }
        });

        return root;
    }

    public void loading(@NonNull boolean load){
        if(load){
            info_session.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        }else {
            info_session.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }

    private void closeSession(){
        loading(true);
        apiServices.logout("", token_ws).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Log.d("LOGOUT", response.toString());
                if(response.isSuccessful() && response.body() != null){
                    ResponseLogin resp = response.body();
                    Log.d("LOGOUT", token_ws);
                    Log.d("LOGOUT", resp.toString());
                    if (!resp.getStatus().equalsIgnoreCase("error")) {
                        editor.remove("user");
                        editor.remove("token_ws");
                        editor.commit();
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                        builder.setMessage(resp.getMensaje())
                                .setTitle(R.string.title_dialog_info)
                                .setNegativeButton(R.string.close_dialog, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        loading(false);
                        dialog.show();

                    }

                } else {
                    loading(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                loading(false);
                Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}