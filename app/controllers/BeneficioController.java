package controllers;

import actions.Secured;
import com.avaje.ebean.Ebean;
import models.Beneficio;
import models.Usuario;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Security;
import validators.BeneficioFormData;
import views.html.beneficios.*;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Date;

import static play.mvc.Controller.session;
import static play.mvc.Results.*;

@Security.Authenticated(Secured.class)
public class BeneficioController {

    @Inject
    FormFactory formFactory;

    /**
     * @return a object user authenticated
     */
    @Nullable
    private Usuario atual() {
        String username = session().get("email");

        try {
            //retorna o usuário atual que esteja logado no sistema
            return Ebean.createQuery(Usuario.class, "find usuario where email = :email")
                    .setParameter("email", username)
                    .findUnique();
        } catch (Exception e) {
            Logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * @return object form
     */
    public Result telaNovo() {
        Form<BeneficioFormData> beneficioForm = formFactory.form(BeneficioFormData.class);
        return ok(views.html.beneficios.create.render(beneficioForm, atual()));
    }

    /**
     * @return render a detail form with data
     */
    public Result telaDetalhe(Long id) {
        try {
            Beneficio beneficio = Ebean.find(Beneficio.class, id);

            if (beneficio == null) {
                return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado.", atual()));
            }

            return ok(views.html.beneficios.detail.render(beneficio, atual()));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.beneficio.erro.render(e.toString(), atual()));
        }
    }

    /**
     * @return render a edit form with data
     */
    public Result telaEditar(Long id) {
        try {
            //logica onde instanciamos um objeto que esteja cadastrado na base de dados
            BeneficioFormData beneficioFormData = (id == 0) ? new BeneficioFormData() : models.Beneficio.makeBeneficioFormData(id);

            //apos o objeto ser instanciado levamos os dados para o formData e os dados serao carregados no form edit
            Form<BeneficioFormData> formData = formFactory.form(BeneficioFormData.class).fill(beneficioFormData);

            return ok(views.html.beneficios.edit.render(id, formData, atual()));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.beneficio.erro.render(e.toString(), atual()));
        }

    }

    /**
     * Display the paginated list of object.
     *
     * @param page Current page number (starts from 0)
     * @param sortBy Column to be sorted
     * @param order Sort order (either asc or desc)
     * @param filter Filter applied on computer names
     */
    public Result telaLista(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        Beneficio.page(page, 16, sortBy, order, filter),
                        sortBy, order, filter, atual()
                )
        );
    }

    /**
     * Save a object
     *
     * @return a render created view to inform OK
     */
    public Result inserir() {
        //Resgata os dados do formulario atraves de uma requisicao
        Form<BeneficioFormData> formData = formFactory.form(BeneficioFormData.class).bindFromRequest();

        //se existir erros nos campos do formulario retorne os erros
        if (formData.hasErrors()) {
            return badRequest(views.html.beneficios.create.render(formData, atual()));
        } else {
            try {
                //Converte os dados do formulario para uma instancia a ser salva na base de dados
                Beneficio beneficio = Beneficio.makeInstance(formData.get());
                beneficio.setDataCadastro(new Date());
                beneficio.save();
                return created(views.html.mensagens.beneficio.cadastrado.render(beneficio.getNome(), atual()));
            } catch (Exception e) {
                Logger.error(e.toString());
                formData.reject(e.toString());
                return badRequest(views.html.beneficios.create.render(formData, atual()));
            }
        }

    }

    /**
     * Update a object from id
     *
     * @param id variavel identificadora
     * @return updated object with a form
     */
    public Result editar(Long id) {
        //Resgata os dados do formulario atraves de uma requisicao e realiza a validacao dos campos
        Form<BeneficioFormData> formData = formFactory.form(BeneficioFormData.class).bindFromRequest();

        //verificar se tem erros no formData, caso tiver retornar o formulario com os erros e caso não tiver continua o processo de alteracao
        if (formData.hasErrors()) {
            return badRequest(views.html.beneficios.edit.render(id,formData, atual()));
        } else {
            try {
                Beneficio beneficioBusca = Ebean.find(Beneficio.class, id);

                if (beneficioBusca == null) {
                    return notFound(views.html.mensagens.beneficio.erro.render("Objeto não encontrado.", atual()));
                }

                //Converte os dados do formulario para uma instancia
                Beneficio beneficio = Beneficio.makeInstance(formData.get());

                beneficio.setId(id);
                beneficio.setDataAlteracao(new Date());
                beneficio.update();
                return ok(views.html.mensagens.beneficio.alterado.render(beneficio.getNome(), atual()));
            } catch (Exception e) {
                Logger.error(e.toString());
                return badRequest(views.html.mensagens.beneficio.erro.render(e.toString(), atual()));
            }
        }

    }

    /**
     * Remove a object from a id
     *
     * @param id variavel identificadora
     * @return ok if removed
     */
    public Result remover(Long id) {

        String beneficioNome;

        try {
            //busca para ser excluido
            Beneficio beneficio = Ebean.find(Beneficio.class, id);

            if (beneficio == null) {
                return notFound(views.html.mensagens.beneficio.erro.render("Objeto não encontrado.", atual()));
            }

            beneficioNome = beneficio.getNome();

            Ebean.delete(beneficio);
            return ok(views.html.mensagens.beneficio.removido.render(beneficioNome, atual()));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.beneficio.erro.render(e.toString(), atual()));
        }
    }
}
