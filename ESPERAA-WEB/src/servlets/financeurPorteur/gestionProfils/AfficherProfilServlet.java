package servlets.financeurPorteur.gestionProfils;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AdminDTO;
import dto.DonateurDTO;
import dto.InvestissementDTO;
import facade.IDonateurFacade;
import facade.IFacadeCommune;

/**
 * Servlet implementation class AfficherCheval
 */
@WebServlet( "/Membre/AfficherProfil" )
public class AfficherProfilServlet extends HttpServlet {
    private static final long   serialVersionUID            = 1L;

    private static final String PAGE_AFFICHER_PROFIL_ADMIN  = "/WEB-INF/financeurPorteur/pageAfficherProfilAdmin.jsp";
    private static final String PAGE_AFFICHER_PROFIL_MEMBRE = "/WEB-INF/financeurPorteur/pageAfficherProfilFinanceur.jsp";
    private static final String REDIRECT_MON_PROFIL         = "MonProfil";

    private static final String ATT_SESSION_MEMBRE          = "donateur";
    private static final String ATT_MEMBRE_PROFIL           = "donateur";
    private static final String ATT_ADMIN_PROFIL            = "admin";
    private static final String ATT_INVESTISSEMENT_LIST     = "investissements";
    private static final String CHAMP_LOGIN_DONATEUR        = "loginDonateur";

    @EJB
    private IFacadeCommune      facadeCommune;
    @EJB
    private IDonateurFacade facadeMembre;

    private String              login;

    public AfficherProfilServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        login = request.getParameter( CHAMP_LOGIN_DONATEUR );
        DonateurDTO membreSession = (DonateurDTO) request.getSession()
                .getAttribute( ATT_SESSION_MEMBRE );
        String loginMembreCourant = membreSession.getLogin();
        if ( loginMembreCourant.equals( login ) ) {
            response.sendRedirect( REDIRECT_MON_PROFIL );
        } else {
            DonateurDTO membreProfil = facadeCommune.findDonateurDTOByLogin( login );

            if ( membreProfil != null ) {
                request.setAttribute( ATT_MEMBRE_PROFIL, membreProfil );
                DonateurDTO membreCourant = (DonateurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
                List<InvestissementDTO> mesInvestissements = facadeMembre.recupererInvestissementParDonateur( membreCourant.getLogin() );
                request.setAttribute( ATT_INVESTISSEMENT_LIST, mesInvestissements );
                request.getRequestDispatcher( PAGE_AFFICHER_PROFIL_MEMBRE ).forward( request, response );
                
            } else {
                AdminDTO adminDTO = facadeCommune.findAdminDTOByLogin( login );
                request.setAttribute( ATT_ADMIN_PROFIL, adminDTO );
                request.getRequestDispatcher( PAGE_AFFICHER_PROFIL_ADMIN ).forward( request, response );
            }
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }
}
