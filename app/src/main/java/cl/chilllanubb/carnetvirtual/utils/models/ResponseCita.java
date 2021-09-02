package cl.chilllanubb.carnetvirtual.utils.models;

import java.util.Arrays;

public class ResponseCita {
    private int code;
    private String status;
    private String mensaje;
    private Cita[] citas;

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

    public Cita[] getCitas() {
        return citas;
    }

    public void setCitas(Cita[] citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "ResponseCita{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", citas=" + Arrays.toString(citas) +
                '}';
    }
}
