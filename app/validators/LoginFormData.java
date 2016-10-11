package validators;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class LoginFormData {
    public String email = "";
    public String senha = "";

    /** Necessario para instanciar o form */
    public LoginFormData() {
    }

    public LoginFormData(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (email == null || email.length() == 0) {
            errors.add(new ValidationError("email", "Preencha o email"));
        }

        if (senha == null || senha.length() == 0) {
            errors.add(new ValidationError("senha", "Preencha a senha"));
        }

        return errors.isEmpty() ? null : errors;
    }
}
