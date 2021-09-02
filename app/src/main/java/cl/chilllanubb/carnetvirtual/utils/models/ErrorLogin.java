package cl.chilllanubb.carnetvirtual.utils.models;

import java.util.Arrays;

public class ErrorLogin {
    private String[] username;
    private String[] email;
    private String[] password;
    private String[] token_telefono;

    public String[] getUsername() {
        return username;
    }

    public void setUsername(String[] username) {
        this.username = username;
    }

    public String[] getPassword() {
        return password;
    }

    public void setPassword(String[] password) {
        this.password = password;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public String[] getToken_telefono() {
        return token_telefono;
    }

    public void setToken_telefono(String[] token_telefono) {
        this.token_telefono = token_telefono;
    }

    @Override
    public String toString() {
        return "ErrorLogin{" +
                "username=" + Arrays.toString(username) +
                ", email=" + Arrays.toString(email) +
                ", password=" + Arrays.toString(password) +
                ", token_telefono=" + Arrays.toString(token_telefono) +
                '}';
    }
}
