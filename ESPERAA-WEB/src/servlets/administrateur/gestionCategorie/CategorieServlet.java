package servlets.administrateur.gestionCategorie;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CategorieDTO;
import facade.IAdministrateurFacade;
import facade.IFacadeCommune;

import com.google.gson.Gson;

/**
 * Servlet implementation class AjouterCategorie
 */
@WebServlet( "/Admin/Categories" )
public class CategorieServlet extends HttpServlet {
    private static final long     serialVersionUID      = 1L;

    private static final String   PAGE_CATEGORIE        = "/WEB-INF/admin/pageCategories.jsp";

    private static final String   ATT_LIST_CATEGORIE    = "listeCategoriesDto";
    private static final String   ATT_ERREUR            = "erreur";

    private static final String   CHAMP_TITRE_CATEGORIE = "titreCategorie";

    @EJB
    private IAdministrateurFacade facadeAdmin;
    @EJB
    private IFacadeCommune        facadeCommune;

    public CategorieServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        dispatchPage( request, response );
    }

    /**
     * Ajoute une catégorie si le titre n'est pas déjà utilisé
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String titreCategorie = request.getParameter( CHAMP_TITRE_CATEGORIE );
        if ( facadeAdmin.verifierCategorie( titreCategorie ) ) {
            request.setAttribute( ATT_ERREUR, "Ce nom de catégorie existe déjà " );
        }
        else {
            facadeAdmin.creerCategorie( titreCategorie );
        }
        dispatchPage( request, response );
    }

    private void dispatchPage( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        List<CategorieDTO> listCategorie = facadeCommune.findAllCategorieDto();
        Gson gson = new Gson();

        request.setAttribute( ATT_LIST_CATEGORIE, gson.toJson( listCategorie ) );
        request.getRequestDispatcher( PAGE_CATEGORIE ).forward( request, response );
    }
}
