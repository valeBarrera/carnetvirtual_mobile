package cl.chilllanubb.carnetvirtual.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import cl.chilllanubb.carnetvirtual.R;
import cl.chilllanubb.carnetvirtual.utils.models.Receta;

public class RecetaListAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Receta> recetas;
    private Context context;

    public RecetaListAdapter(List<Receta> recetas, Context context) {
        this.recetas = recetas;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receta, parent, false);
        return new RecetaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Receta receta = recetas.get(position);

        ((RecetaHolder) holder).bind(receta);
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }

    private class RecetaHolder extends RecyclerView.ViewHolder {

        private TextView medicamento, indicaciones, medico, especialidad, estado, proximo_retiro;
        private View container_proximo_retiro;
        private SimpleDateFormat simpleDateFormat;

        public RecetaHolder(@NonNull View itemView) {
            super(itemView);
            medicamento = itemView.findViewById(R.id.medicamento);
            indicaciones = itemView.findViewById(R.id.indicaciones);
            medico = itemView.findViewById(R.id.medico);
            especialidad = itemView.findViewById(R.id.especialidad);
            estado = itemView.findViewById(R.id.estado);
            proximo_retiro = itemView.findViewById(R.id.proximo_retiro);
            container_proximo_retiro = itemView.findViewById(R.id.container_proximo_retiro);
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        }

        void bind(Receta receta) {
            medicamento.setText(receta.getPrescripcion());
            indicaciones.setText(receta.getIndicaciones());
            medico.setText(String.format("%s %s", receta.getMedico().getNombres(), receta.getMedico().getApellidos()));
            especialidad.setText(receta.getMedico().getEspecialidad());
            estado.setText(receta.getEstado());
            if(receta.getProximo_retiro() != null){
                proximo_retiro.setText(simpleDateFormat.format(receta.getProximo_retiro()));
            }else{
                container_proximo_retiro.setVisibility(View.GONE);
            }
        }
    }
}
