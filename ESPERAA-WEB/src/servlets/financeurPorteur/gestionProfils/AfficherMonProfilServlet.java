package servlets.financeurPorteur.gestionProfils;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DonateurDTO;
import dto.InvestissementDTO;
import facade.IFacadeCommune;
import facade.IDonateurFacade;

/**
 * Servlet implementation class AfficherMonProfilServlet
 */
@WebServlet( "/Membre/MonProfil" )
public class AfficherMonProfilServlet extends HttpServlet {
    private static final long       serialVersionUID        = 1L;

    private static final String     PAGE_AFFICHER_PROFIL    = "/WEB-INF/financeurPorteur/pageMonProfil.jsp";

    private static final String     ATT_SESSION_MEMBRE      = "donateur";
    private static final String     ATT_INVESTISSEMENT_LIST = "investissements";

    @EJB
    private IDonateurFacade facadeMembre;
    @EJB
    private IFacadeCommune          facadeCommune;

    public AfficherMonProfilServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        DonateurDTO membreCourant = (DonateurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
        List<InvestissementDTO> mesInvestissements = facadeMembre.recupererInvestissementParDonateur( membreCourant.getLogin() );
        request.setAttribute( ATT_INVESTISSEMENT_LIST, mesInvestissements );
        request.getRequestDispatcher( PAGE_AFFICHER_PROFIL ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }
}
