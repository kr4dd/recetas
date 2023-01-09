package es.uvigo.dagss.recetas.controladores.dtos;

import java.io.Serializable;
import java.util.Set;

public class DatosRegistro implements Serializable {
    private String login;
    private String password;
    private Set<String> roles;

    public DatosRegistro() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }


}