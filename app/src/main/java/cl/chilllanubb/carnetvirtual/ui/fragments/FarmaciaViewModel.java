package cl.chilllanubb.carnetvirtual.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import cl.chilllanubb.carnetvirtual.utils.models.Receta;

public class FarmaciaViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Receta>> recetas;

    public FarmaciaViewModel() {
        recetas = new MutableLiveData<>();
        recetas.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<Receta>> getRecetas() {
        return recetas;
    }

    public void setRecetasPost(ArrayList<Receta> recetas){
        this.recetas.postValue(recetas);
    }

    public void setRecetas(ArrayList<Receta> recetas){
        this.recetas.setValue(recetas);
    }
}