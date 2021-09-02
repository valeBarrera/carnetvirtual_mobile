package cl.chilllanubb.carnetvirtual.utils.models;

public class ResponseLogin {
    private int code;
    private String status;
    private String mensaje;
    private String token;
    private ErrorLogin errores;
    private Usuario usuario;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ErrorLogin getErrores() {
        return errores;
    }

    public void setErrores(ErrorLogin errores) {
        this.errores = errores;
    }

    @Override
    public String toString() {
        return "ResponseLogin{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", token='" + token + '\'' +
                ", errores=" + errores +
                ", usuario=" + usuario +
                '}';
    }
}
