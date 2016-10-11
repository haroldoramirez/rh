package controllers;

import com.avaje.ebean.Ebean;
import models.Area;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.AreaFormData;
import views.html.areas.list;

import javax.inject.Inject;
import java.util.Date;

public class AreaController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * @return object form
     */
    public Result telaNovo() {
        Form<AreaFormData> areaForm = formFactory.form(AreaFormData.class);
        return ok(views.html.areas.create.render(areaForm));
    }

    /**
     * @return render a detail form with data
     */
    public Result telaDetalhe(Long id) {
        try {
            Area area = Ebean.find(Area.class, id);

            if (area == null) {
                return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado."));
            }

            return ok(views.html.areas.detail.render(area));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.area.erro.render(e.toString()));
        }
    }

    /**
     * @return render a edit form with data
     */
    public Result telaEditar(Long id) {
        try {
            //logica onde instanciamos um objeto que esteja cadastrado na base de dados
            AreaFormData areaFormData = (id == 0) ? new AreaFormData() : models.Area.makeAreaFormData(id);

            //apos o objeto ser instanciado levamos os dados para o formData e os dados serao carregados no form edit
            Form<AreaFormData> formData = formFactory.form(AreaFormData.class).fill(areaFormData);

            return ok(views.html.areas.edit.render(id,formData));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.area.erro.render(e.toString()));
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
                        Area.page(page, 16, sortBy, order, filter),
                        sortBy, order, filter
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
        Form<AreaFormData> formData = formFactory.form(AreaFormData.class).bindFromRequest();

        //se existir erros nos campos do formulario retorne os erros
        if (formData.hasErrors()) {
            return badRequest(views.html.areas.create.render(formData));
        } else {
            try {
                //Converte os dados do formulario para uma instancia a ser salva na base de dados
                Area area = Area.makeInstance(formData.get());
                area.setDataCadastro(new Date());
                area.save();
                return created(views.html.mensagens.area.cadastrado.render(area.getNome()));
            } catch (Exception e) {
                Logger.error(e.toString());
                formData.reject(e.toString());
                return badRequest(views.html.areas.create.render(formData));
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
        Form<AreaFormData> formData = formFactory.form(AreaFormData.class).bindFromRequest();

        //verificar se tem erros no formData, caso tiver retornar o formulario com os erros e caso não tiver continua o processo de alteracao
        if (formData.hasErrors()) {
            return badRequest(views.html.areas.edit.render(id,formData));
        } else {
            try {
                Area areaBusca = Ebean.find(Area.class, id);

                if (areaBusca == null) {
                    return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado."));
                }

                //Converte os dados do formulario para uma instancia
                Area area = Area.makeInstance(formData.get());

                area.setId(id);
                area.setDataAlteracao(new Date());
                area.update();
                return ok(views.html.mensagens.area.alterado.render(area.getNome()));
            } catch (Exception e) {
                Logger.error(e.toString());
                return badRequest(views.html.mensagens.area.erro.render(e.toString()));
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

        String areaNome;

        try {
            //busca para ser excluido
            Area area = Ebean.find(Area.class, id);

            if (area == null) {
                return notFound(views.html.mensagens.area.erro.render("Objeto não encontrado."));
            }

            areaNome = area.getNome();

            Ebean.delete(area);
            return ok(views.html.mensagens.area.removido.render(areaNome));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.area.erro.render(e.toString()));
        }
    }

}
