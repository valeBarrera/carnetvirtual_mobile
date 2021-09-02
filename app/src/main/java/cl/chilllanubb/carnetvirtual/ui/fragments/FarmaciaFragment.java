package cl.chilllanubb.carnetvirtual.ui.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;

import cl.chilllanubb.carnetvirtual.R;
import cl.chilllanubb.carnetvirtual.services.ApiServices;
import cl.chilllanubb.carnetvirtual.utils.CitaListAdapter;
import cl.chilllanubb.carnetvirtual.utils.RecetaListAdapter;
import cl.chilllanubb.carnetvirtual.utils.models.Cita;
import cl.chilllanubb.carnetvirtual.utils.models.Receta;
import cl.chilllanubb.carnetvirtual.utils.models.ResponseCita;
import cl.chilllanubb.carnetvirtual.utils.models.ResponseReceta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FarmaciaFragment extends Fragment {

    private FarmaciaViewModel mViewModel;
    private View loading;
    private View viewMessage;

    private ApiServices apiServices;

    private RecyclerView mMessageRecycler;
    private RecetaListAdapter mRecetaListAdapter;

    private ImageButton refreshBtn;

    private String token_ws;

    public static FarmaciaFragment newInstance() {
        return new FarmaciaFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url_connection))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(),getActivity().MODE_PRIVATE);
        token_ws = String.format("Bearer %s",sharedPreferences.getString("token_ws", ""));

        apiServices = retrofit.create(ApiServices.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.farmacia_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(FarmaciaViewModel.class);

        mMessageRecycler = root.findViewById(R.id.recetas);

        viewMessage = root.findViewById(R.id.viewMessage);
        loading = root.findViewById(R.id.loading);

        refreshBtn = root.findViewById(R.id.refresh);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });

        mViewModel.getRecetas().observe(getActivity(), new Observer<ArrayList<Receta>>() {
            @Override
            public void onChanged(ArrayList<Receta> recetas) {
                if(recetas.size() > 0){
                    mRecetaListAdapter = new RecetaListAdapter(recetas, getContext());
                    mMessageRecycler.setAdapter(mRecetaListAdapter);
                    mMessageRecycler.setHasFixedSize(true);
                    mMessageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    mMessageRecycler.scrollToPosition(recetas.size() - 1);
                }
            }
        });

        loadData();

        return root;
    }

    public void loadData(){
        loading(true);
        apiServices.recetas(null, token_ws).enqueue(new Callback<ResponseReceta>() {
            @Override
            public void onResponse(Call<ResponseReceta> call, Response<ResponseReceta> response) {
                ResponseReceta recetasResponce = response.body();
                if(recetasResponce.getCode() == 200){
                    mViewModel.setRecetas(new ArrayList<Receta>(Arrays.asList(recetasResponce.getRecetas())));
                }

                loading(false);
            }

            @Override
            public void onFailure(Call<ResponseReceta> call, Throwable t) {

            }
        });
    }

    public void loading(@NonNull boolean load){
        if(load){
            viewMessage.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        }else {
            viewMessage.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }


}