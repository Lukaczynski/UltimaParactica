package pru.lukasz.login;

import java.io.Serializable;

/**
 * Objeto de usuario que contiene el nombre de usuario y la contraseña
 * @version 0.0.1
 * @since 0.0.1
 * @author Lukasz
 */
public class User implements Serializable {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return username;
    }
}
