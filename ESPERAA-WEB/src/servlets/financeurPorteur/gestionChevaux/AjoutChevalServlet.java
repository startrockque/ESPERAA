package servlets.financeurPorteur.gestionChevaux;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.IAdministrateurFacade;
import facade.IFacadeCommune;
import servlets.financeurPorteur.forms.ChevalForm;

/**
 * Servlet implementation class AjoutChevalServlet
 */
@WebServlet( urlPatterns = { "/Membre/CreationCheval" }, initParams = @WebInitParam( name = "chemin", value = "../images/" ) )
@MultipartConfig( maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class AjoutChevalServlet extends HttpServlet {
    private static final long       serialVersionUID       = 1L;

    private static final String     PAGE_AJOUT_CHEVAL      = "/WEB-INF/financeurPorteur/pageAjouterCheval.jsp";
    private static final String     REDIRECT_MON_PROFIL    = "MonProfil";

    private static final String     CHEMIN                 = "chemin";

    private static final String     CHAMP_NOM              = "nom";
    private static final String     CHAMP_DESCRIPTION      = "description";
    private static final String     CHAMP_BUT              = "butArgent";
    private static final String     CHAMP_MONTANT_DEMANDE  = "montantDemande";
    private static final String     CHAMP_CATEGORIE        = "titreCategorie";
    private static final String     CHAMP_TAG              = "tagText";

    private static final String     CHAMP_COMPENSATION     = "compensation";
    private static final String     CHAMP_MONTANT_TRANCHE  = "montantTranche";

    private static final String     ATT_LIST_CATEGORIE     = "categorieList";
    private static final String     ATT_TRANCHE_LIST       = "trancheList";
    private static final String     ATT_FORM               = "form";
    private static final String     ATT_ACTION             = "action";

    private static final String     ACTION_AJOUTER_CHEVAL  = "Ajouter le cheval";
    private static final String     ACTION_AJOUTER_TRANCHE = "Ajouter la tranche";

    private ChevalForm              form;

    @EJB
    private IAdministrateurFacade	facadeAdmin;
    @EJB
    private IFacadeCommune      	facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutChevalServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
        form = new ChevalForm();
        request.getRequestDispatcher( PAGE_AJOUT_CHEVAL ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String action = request.getParameter( ATT_ACTION );
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
        form.setChemin( chemin );
        request.setAttribute( ATT_FORM, form );
        switch ( action ) {
        case ACTION_AJOUTER_TRANCHE:
            ajoutTranche( request, response );
            break;
        case ACTION_AJOUTER_CHEVAL:
            ajoutCheval( request, response );
            break;
        }
    }

    private void ajoutCheval( HttpServletRequest request, HttpServletResponse response ) throws IOException,
            ServletException {
        form.verificationCheval( request );
        if ( form.getErreursCheval().isEmpty() ) {
            String nom = (String) request.getAttribute( CHAMP_NOM );
            String description = (String) request.getAttribute( CHAMP_DESCRIPTION );
            String butArgent = (String) request.getAttribute( CHAMP_BUT );
            int montantDemande = Integer.parseInt( (String) request.getAttribute( CHAMP_MONTANT_DEMANDE ) );
            String titreCategorie = (String) request.getAttribute( CHAMP_CATEGORIE );
            String tagString = (String) request.getAttribute( CHAMP_TAG );
            facadeAdmin.ajouterCheval( nom, description, butArgent,
                    montantDemande, titreCategorie, tagString, form.getTrancheList(),
                    form.getImage() );
            response.sendRedirect( REDIRECT_MON_PROFIL );
        } else {
            request.setAttribute( ATT_TRANCHE_LIST, form.getTrancheList() );
            request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
            request.getRequestDispatcher( PAGE_AJOUT_CHEVAL ).forward( request, response );
        }
    }

    private void ajoutTranche( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException {
        form.verificationTranche( request );
        if ( form.getErreursTranche().isEmpty() ) {
            String compensation = (String) request.getAttribute( CHAMP_COMPENSATION );
            int montant = Integer.parseInt( (String) request.getAttribute( CHAMP_MONTANT_TRANCHE ) );
            form.ajouterTranche( compensation, montant );
        }
        form.verificationCheval( request );
        request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
        request.setAttribute( ATT_TRANCHE_LIST, form.getTrancheList() );
        request.getRequestDispatcher( PAGE_AJOUT_CHEVAL ).forward( request, response );
    }
}
