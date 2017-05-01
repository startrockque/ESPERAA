package servlets.financeurPorteur.gestionProjets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.financeurPorteur.forms.ProjetForm;
import dto.DonateurDTO;
import facade.IFacadeCommune;
import facade.IDonateurFacade;

/**
 * Servlet implementation class AjoutProjetServlet
 */
@WebServlet( urlPatterns = { "/Membre/CreationProjet" }, initParams = @WebInitParam( name = "chemin", value = "../images/" ) )
@MultipartConfig( maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class AjoutProjetServlet extends HttpServlet {
    private static final long       serialVersionUID       = 1L;

    private static final String     PAGE_AJOUT_PROJET      = "/WEB-INF/financeurPorteur/pageAjouterProjet.jsp";
    private static final String     REDIRECT_MON_PROFIL    = "MonProfil";

    private static final String     CHEMIN                 = "chemin";

    private static final String     CHAMP_TITRE            = "titre";
    private static final String     CHAMP_DESCRIPTION      = "description";
    private static final String     CHAMP_BUT              = "butArgent";
    private static final String     CHAMP_MONTANT_DEMANDE  = "montantDemande";
    private static final String     CHAMP_CATEGORIE        = "titreCategorie";
    private static final String     CHAMP_TAG              = "tagText";
    private static final String     CHAMP_DATE_FIN         = "dateFin";

    private static final String     CHAMP_COMPENSATION     = "compensation";
    private static final String     CHAMP_MONTANT_TRANCHE  = "montantTranche";

    private static final String     ATT_SESSION_MEMBRE     = "financeur";
    private static final String     ATT_LIST_CATEGORIE     = "categorieList";
    private static final String     ATT_TRANCHE_LIST       = "trancheList";
    private static final String     ATT_FORM               = "form";
    private static final String     ATT_LIST_PROJETS       = "listeProjets";
    private static final String     ATT_AJOURDHUI          = "aujourdhui";
    private static final String     ATT_DATE_MAX           = "dateMax";
    private static final String     ATT_ACTION             = "action";

    private static final String     ACTION_AJOUTER_PROJET  = "Ajouter le projet";
    private static final String     ACTION_AJOUTER_TRANCHE = "Ajouter la tranche";

    private ProjetForm              form;

    @EJB
    private IDonateurFacade facadeMembre;
    @EJB
    private IFacadeCommune          facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutProjetServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
        form = new ProjetForm( facadeMembre );
        calcDates( request );
        request.getRequestDispatcher( PAGE_AJOUT_PROJET ).forward( request, response );
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
        calcDates( request );
        switch ( action ) {
        case ACTION_AJOUTER_TRANCHE:
            ajoutTranche( request, response );
            break;
        case ACTION_AJOUTER_PROJET:
            ajoutProjet( request, response );
            break;
        }
    }

    private void ajoutProjet( HttpServletRequest request, HttpServletResponse response ) throws IOException,
            ServletException {
        form.verificationProjet( request );
        String loginPorteur = ( (DonateurDTO) request.getSession().getAttribute( ( ATT_SESSION_MEMBRE ) ) )
                .getLogin();
        if ( form.getErreursProjet().isEmpty() ) {
            String titre = (String) request.getAttribute( CHAMP_TITRE );
            String description = (String) request.getAttribute( CHAMP_DESCRIPTION );
            String butArgent = (String) request.getAttribute( CHAMP_BUT );
            int montantDemande = Integer.parseInt( (String) request.getAttribute( CHAMP_MONTANT_DEMANDE ) );
            String titreCategorie = (String) request.getAttribute( CHAMP_CATEGORIE );
            String tagString = (String) request.getAttribute( CHAMP_TAG );
            String dateFin = (String) request.getAttribute( CHAMP_DATE_FIN );
            facadeMembre.ajouterProjet( titre, description, butArgent,
                    montantDemande, titreCategorie, tagString, dateFin, loginPorteur, form.getTrancheList(),
                    form.getImage() );
            response.sendRedirect( REDIRECT_MON_PROFIL );
        } else {
            request.setAttribute( ATT_TRANCHE_LIST, form.getTrancheList() );
            request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
            request.setAttribute( ATT_LIST_PROJETS, facadeCommune.recupererMesProjets( loginPorteur ) );
            request.getRequestDispatcher( PAGE_AJOUT_PROJET ).forward( request, response );
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
        form.verificationProjet( request );
        request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
        request.setAttribute( ATT_TRANCHE_LIST, form.getTrancheList() );
        request.getRequestDispatcher( PAGE_AJOUT_PROJET ).forward( request, response );
    }

    /**
     * Méthode de calcul de la date actuelle et de la date max de fin de projet
     * (+2 mois)
     * 
     * @param request
     * @param response
     */
    private void calcDates( HttpServletRequest request ) {
        Calendar aujourdhui = Calendar.getInstance();
        Calendar dateMax = Calendar.getInstance();
        dateMax.add( Calendar.MONTH, 2 );

        SimpleDateFormat formater = new SimpleDateFormat( "yyyy-MM-dd" );
        request.setAttribute( ATT_AJOURDHUI, formater.format( aujourdhui.getTime() ) );
        request.setAttribute( ATT_DATE_MAX, formater.format( dateMax.getTime() ) );
    }
}
