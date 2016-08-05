package controllers;

import com.avaje.ebean.Ebean;
import models.Area;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.AreaFormData;

import javax.inject.Inject;
import java.util.List;

public class AreaController extends Controller {

    @Inject
    FormFactory formFactory;

    /**
     * @return noticia form if auth OK or not authorized
     */
    public Result telaNovo() {
        Form<AreaFormData> areaForm = formFactory.form(AreaFormData.class);
        return ok(views.html.areas.create.render(areaForm));
    }

    /**
     * @return render a detail form with a noticia data
     */
    public Result telaDetalhe(Long id) {
        try {
            Area area = Ebean.find(Area.class, id);

            if (area == null) {
                return notFound(views.html.mensagens.erro.render());
            }

            return ok(views.html.areas.detail.render(area));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.erro.render());
        }
    }

    /**
     * @return render edit form with a noticia data
     */
    public Result telaEditar(Long id) {
        Form<Area> areaForm = formFactory.form(Area.class);
        return ok(views.html.areas.edit.render(id,areaForm));
    }

    /**
     * Retrieve a list of all noticias
     *
     * @return a list of all noticias in a render template
     */
    public Result telaLista() {
        try {
            List<Area> areas = Ebean.find(Area.class).findList();
            return ok(views.html.areas.list.render(areas, ""));
        } catch (Exception e) {
            Logger.error(e.toString());
            return badRequest(views.html.mensagens.erro.render());
        }
    }

    /**
     * Save a noticia
     *
     * @return a render view to inform OK
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
     * Update a noticia from id
     *
     * @param id variavel identificadora
     * @return a noticia updated with a form
     */
    public Result editar(Long id) {
        return TODO;
    }

    /**
     * Remove a noticia from a id
     *
     * @param id variavel identificadora
     * @return ok noticia removed
     */
    public Result remover(Long id) {
        return TODO;
    }


}
