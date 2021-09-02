package cl.chilllanubb.carnetvirtual.utils.models;

import java.util.Date;

public class Receta {
    private int id;
    private String fecha_emision;
    private Date ultima_fecha_surtido;
    private Date proximo_retiro;
    private String prescripcion;
    private String indicaciones;
    private String estado;
    private Medico medico;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Date getUltima_fecha_surtido() {
        return ultima_fecha_surtido;
    }

    public void setUltima_fecha_surtido(Date ultima_fecha_surtido) {
        this.ultima_fecha_surtido = ultima_fecha_surtido;
    }

    public Date getProximo_retiro() {
        return proximo_retiro;
    }

    public void setProximo_retiro(Date proximo_retiro) {
        this.proximo_retiro = proximo_retiro;
    }

    public String getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(String prescripcion) {
        this.prescripcion = prescripcion;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "id=" + id +
                ", fecha_emision='" + fecha_emision + '\'' +
                ", ultima_fecha_surtido=" + ultima_fecha_surtido +
                ", proximo_retiro=" + proximo_retiro +
                ", prescripcion='" + prescripcion + '\'' +
                ", indicaciones='" + indicaciones + '\'' +
                ", estado='" + estado + '\'' +
                ", medico=" + medico +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}