package actions;

import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import validators.LoginFormData;

import javax.inject.Inject;

public class Secured extends Security.Authenticator {

    @Inject
    FormFactory formFactory;

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        Form<LoginFormData> loginForm = formFactory.form(LoginFormData.class);
        loginForm.reject("Autenticação necessária!");
        return forbidden(views.html.login.render(loginForm));
    }
}
