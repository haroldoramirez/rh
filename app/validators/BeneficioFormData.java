package validators;

import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class BeneficioFormData {

    public String nome = "";

    /** Necessario para instanciar o form */
    public BeneficioFormData() {
    }

    public BeneficioFormData(String nome) {
        this.nome = nome;
    }

    public List<ValidationError> validate() {

        List<ValidationError> errors = new ArrayList<>();

        if (nome == null || nome.length() == 0) {
            errors.add(new ValidationError("nome", "Preencha o nome"));
        }

        return errors.isEmpty() ? null : errors;
    }
}
