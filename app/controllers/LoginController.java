package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.LoginFormData;

import javax.inject.Inject;

public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result telaLogin() {
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class);
        return ok(views.html.login.render(loginForm));
    }
}
