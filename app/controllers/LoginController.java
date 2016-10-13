package controllers;

import models.Usuario;
import models.Usuarios;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.LoginFormData;

import javax.inject.Inject;
import java.util.Optional;

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result telaLogin() {
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class);
        return ok(views.html.login.render(loginForm));
    }

    public Result autenticar() {
        //Resgata os dados do formulario atraves de uma requisicao
        Form<LoginFormData> formData = formFactory.form(LoginFormData.class).bindFromRequest();

        //se existir erros nos campos do formulario retorne os erros
        if (formData.hasErrors()) {
            return badRequest(views.html.login.render(formData));
        } else {

            String email = formData.data().get("email");
            String senha = formData.data().get("senha");

            Optional<Usuario> talvesUmUsuario = Usuarios.existe(email, senha);

            if (talvesUmUsuario.isPresent()) {
                session().put("email", talvesUmUsuario.get().getEmail());
                return redirect(routes.HomeController.inicio());
            }

        }

        formData.reject("Email ou Senha inválidos!");
        return forbidden(views.html.login.render(formData));
    }

    /**
     * @return redirect telaLogout
     */
    public Result logout() {
        session().clear();
        return redirect(routes.HomeController.inicio());
    }
}
