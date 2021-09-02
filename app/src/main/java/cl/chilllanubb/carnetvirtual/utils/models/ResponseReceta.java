package cl.chilllanubb.carnetvirtual.utils.models;

import java.util.Arrays;

public class ResponseReceta {
    private int code;
    private String status;
    private String mensaje;
    private Receta[] recetas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Receta[] getRecetas() {
        return recetas;
    }

    public void setRecetas(Receta[] recetas) {
        this.recetas = recetas;
    }

    @Override
    public String toString() {
        return "ResponseReceta{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", recetas=" + Arrays.toString(recetas) +
                '}';
    }
}
