package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.Logger;

import java.util.Optional;

public class Usuarios extends Model {

    public static Optional<Usuario> existe(String email, String senha) {

        try {
            Usuario usuario = Ebean.find(Usuario.class).where().eq("email", email).eq("senha", senha).findUnique();

            if (usuario == null) {
                return Optional.empty();

            }

            return Optional.of(usuario);
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return Optional.empty();
        }

    }
}
