package servlets.administrateur;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AdminDTO;
import dto.FinanceurPorteurDTO;
import dto.TousLesProjetsDTO;
import facade.IFacadeCommune;

/**
 * Servlet implementation class AfficherProjet
 */
@WebServlet( "/Admin/AfficherProfil" )
public class AfficherProfilServlet extends HttpServlet {
    private static final long   serialVersionUID            = 1L;

    private static final String PAGE_AFFICHER_PROFIL_ADMIN  = "/WEB-INF/admin/pageAfficherProfilAdmin.jsp";
    private static final String PAGE_AFFICHER_PROFIL_MEMBRE = "/WEB-INF/admin/pageAfficherProfilFinanceur.jsp";

    private static final String ATT_MES_PROJETS_LIST        = "listeProjets";
    private static final String ATT_MEMBRE_PROFIL           = "financeur";
    private static final String ATT_NB_PROJETS              = "nbProjets";
    private static final String ATT_ADMIN_PROFIL            = "admin";

    private static final String CHAMP_LOGIN_FINANCEUR       = "loginFinanceur";

    @EJB
    private IFacadeCommune      facadeCommune;

    private String              login;

    public AfficherProfilServlet() {
        super();
    }

    /**
     * Affiche le profil d'un membre ou d'un admin
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        login = request.getParameter( CHAMP_LOGIN_FINANCEUR );
        FinanceurPorteurDTO membreProfil = facadeCommune.findFinanceurDTOByLogin( login );

        if ( membreProfil != null ) {
            request.setAttribute( ATT_MEMBRE_PROFIL, membreProfil );
            List<TousLesProjetsDTO> sesProjets = facadeCommune.recupererMesProjets( login );
            request.setAttribute( ATT_MES_PROJETS_LIST, sesProjets );
            request.setAttribute( ATT_NB_PROJETS, facadeCommune.recupererMesProjets( membreProfil.getLogin() )
                    .size() );
            request.getRequestDispatcher( PAGE_AFFICHER_PROFIL_MEMBRE ).forward( request, response );
        } else {
            AdminDTO adminDTO = facadeCommune.findAdminDTOByLogin( login );
            request.setAttribute( ATT_ADMIN_PROFIL, adminDTO );
            request.getRequestDispatcher( PAGE_AFFICHER_PROFIL_ADMIN ).forward( request, response );
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }
}
