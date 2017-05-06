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

import dto.ChevalDTO;
import facade.IAdministrateurFacade;
import facade.IDonateurFacade;
import facade.IFacadeCommune;
import servlets.financeurPorteur.forms.ChevalForm;

/**
 * Servlet implementation class ModifChevalServlet
 */
@WebServlet( urlPatterns = { "/Membre/ModifCheval" }, initParams = @WebInitParam( name = "chemin", value = "../images/" ) )
@MultipartConfig( maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class ModifierChevalServlet extends HttpServlet {
    private static final long       serialVersionUID       = 1L;

    private static final String     PAGE_MODIFIER_CHEVAL   = "/WEB-INF/financeurPorteur/pageModifierCheval.jsp";
    private static final String     REDIRECT_MES_CHEVAUX   = "MonProfil";

    private static final String     CHEMIN                 = "chemin";

    private static final String     CHAMP_TITRE            = "titre";
    private static final String     CHAMP_DESCRIPTION      = "description";
    private static final String     CHAMP_BUT              = "butArgent";
    private static final String     CHAMP_MONTANT_DEMANDE  = "montantDemande";
    private static final String     CHAMP_CATEGORIE        = "titreCategorie";
    private static final String     CHAMP_TAG              = "tagText";

    private static final String     CHAMP_COMPENSATION     = "compensation";
    private static final String     CHAMP_MONTANT_TRANCHE  = "montantTranche";

    private static final String     ATT_LIST_CATEGORIE     = "categorieList";
    private static final String     ATT_ID_CHEVAL          = "idCheval";
    private static final String     ATT_TRANCHE_LIST       = "trancheList";
    private static final String     ATT_FORM               = "form";
    private static final String     ATT_ACTION             = "action";

    private static final String     ACTION_MODIFIER_CHEVAL = "Modifier le cheval";
    private static final String     ACTION_AJOUTER_TRANCHE = "Ajouter la tranche";

    private ChevalForm              form;
    private int                     idCheval;

    @EJB
    private IDonateurFacade 		facadeMembre;
    @EJB
    private IAdministrateurFacade 	facadeAdmin;
    @EJB
    private IFacadeCommune          facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifierChevalServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        ChevalDTO chevalDto = facadeCommune.findChevalDTOById( idCheval );
        setAttribute( chevalDto, request );
        form = new ChevalForm();
        form.setTrancheDtoList( chevalDto.getTrancheList() );
        request.setAttribute( ATT_TRANCHE_LIST, chevalDto.getTrancheList() );
        request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
        request.setAttribute( ATT_FORM, form );
        request.getRequestDispatcher( PAGE_MODIFIER_CHEVAL ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String action = request.getParameter( ATT_ACTION );
        request.setAttribute( ATT_FORM, form );
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
        form.setChemin( chemin );
        switch ( action ) {
        case ACTION_AJOUTER_TRANCHE:
            ajoutTranche( request, response );
            break;
        case ACTION_MODIFIER_CHEVAL:
            ajoutCheval( request, response );
            break;
        }
    }

    private void ajoutCheval( HttpServletRequest request,
            HttpServletResponse response ) throws IOException, ServletException {
        form.verificationModifierCheval( request, idCheval );
        if ( form.getErreursCheval().isEmpty() ) {
            String titre = (String) request.getAttribute( CHAMP_TITRE );
            String description = (String) request.getAttribute( CHAMP_DESCRIPTION );
            String butArgent = (String) request.getAttribute( CHAMP_BUT );
            int montantDemande = Integer.parseInt( (String) request.getAttribute( CHAMP_MONTANT_DEMANDE ) );
            String titreCategorie = (String) request.getAttribute( CHAMP_CATEGORIE );
            String tagString = (String) request.getAttribute( CHAMP_TAG );
            facadeAdmin.modifierCheval( idCheval, titre, description, butArgent,
                    montantDemande, titreCategorie, tagString, form.getTrancheList(), form.getImage() );
            response.sendRedirect( REDIRECT_MES_CHEVAUX );
        } else {
            request.setAttribute( ATT_TRANCHE_LIST, form.getTrancheList() );
            request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
            request.getRequestDispatcher( PAGE_MODIFIER_CHEVAL ).forward( request, response );
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
        form.verificationModifierCheval( request, idCheval );
        request.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.findAllCategorieDto() );
        request.setAttribute( ATT_TRANCHE_LIST, form.getTrancheList() );
        request.getRequestDispatcher( PAGE_MODIFIER_CHEVAL ).forward( request, response );
    }

    private void setAttribute( ChevalDTO chevalDto, HttpServletRequest request ) {
        request.setAttribute( CHAMP_TITRE, chevalDto.getNomCheval() );
        request.setAttribute( CHAMP_DESCRIPTION, chevalDto.getDescription() );
        request.setAttribute( CHAMP_BUT, chevalDto.getButArgent() );
        request.setAttribute( CHAMP_MONTANT_DEMANDE, chevalDto.getMontantDemande() );
        request.setAttribute( CHAMP_CATEGORIE, chevalDto.getCategorie() );
        String tagString = "";
        for ( String tagName : chevalDto.getTagList() ) {
            tagString = tagString + " " + tagName;
        }
        request.setAttribute( CHAMP_TAG, tagString );
    }
}
