package cl.chilllanubb.carnetvirtual.ui.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cl.chilllanubb.carnetvirtual.utils.models.Cita;

public class CitasViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Cita>> citas;

    public CitasViewModel() {
        citas = new MutableLiveData<>();
        citas.setValue(new ArrayList<>());
    }

    public LiveData<ArrayList<Cita>> getCitas() {
        return citas;
    }

    public void setCitasPost(ArrayList<Cita> citas){
        this.citas.postValue(citas);
    }

    public void setCitas(ArrayList<Cita> citas){
        this.citas.setValue(citas);
    }
}