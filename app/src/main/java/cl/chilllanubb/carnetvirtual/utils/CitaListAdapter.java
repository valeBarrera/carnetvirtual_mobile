package cl.chilllanubb.carnetvirtual.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cl.chilllanubb.carnetvirtual.R;
import cl.chilllanubb.carnetvirtual.utils.models.Cita;

public class CitaListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Cita> citas;

    public CitaListAdapter(Context mContext, List<Cita> citas) {
        this.mContext = mContext;
        this.citas = citas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cita, parent, false);
        return new CitaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cita cita = citas.get(position);

        ((CitaHolder) holder).bind(cita);
    }

    @Override
    public int getItemCount() {
        return citas.size();
    }

    private class CitaHolder extends RecyclerView.ViewHolder {

        private TextView dia, hora, medico, especialidad, estado;
        private SimpleDateFormat simpleDateFormat;

        public CitaHolder(@NonNull View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.dia);
            hora = itemView.findViewById(R.id.hora);
            medico = itemView.findViewById(R.id.medico);
            especialidad = itemView.findViewById(R.id.especialidad);
            estado = itemView.findViewById(R.id.estado);
            simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        }

        void bind(Cita cita) {
           dia.setText(simpleDateFormat.format(cita.getFecha()));
           hora.setText(cita.getHora());
           medico.setText(String.format("%s %s", cita.getMedico().getNombres(), cita.getMedico().getApellidos()));
           especialidad.setText(cita.getMedico().getEspecialidad());
           estado.setText(cita.getEstado());
        }
    }
}
