package servlets.financeurPorteur.gestionProfils;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FinanceurPorteurDTO;
import dto.InvestissementDTO;
import dto.TousLesProjetsDTO;
import facade.IFacadeCommune;
import facade.IFinanceurPorteurFacade;

/**
 * Servlet implementation class AfficherMonProfilServlet
 */
@WebServlet( "/Membre/MonProfil" )
public class AfficherMonProfilServlet extends HttpServlet {
    private static final long       serialVersionUID        = 1L;

    private static final String     PAGE_AFFICHER_PROFIL    = "/WEB-INF/financeurPorteur/pageMonProfil.jsp";

    private static final String     ATT_SESSION_MEMBRE      = "financeur";
    private static final String     ATT_INVESTISSEMENT_LIST = "investissements";
    private static final String     ATT_MES_PROJETS_LIST    = "listeProjets";
    private static final String     ATT_NB_PROJETS          = "nbProjets";

    @EJB
    private IFinanceurPorteurFacade facadeMembre;
    @EJB
    private IFacadeCommune          facadeCommune;

    public AfficherMonProfilServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        FinanceurPorteurDTO membreCourant = (FinanceurPorteurDTO) request.getSession()
                .getAttribute( ATT_SESSION_MEMBRE );
        List<TousLesProjetsDTO> mesProjets = facadeCommune.recupererMesProjets( membreCourant.getLogin() );
        List<InvestissementDTO> mesInvestissements = facadeMembre.recupererInvesissementParFinanceur( membreCourant
                .getLogin() );
        request.setAttribute( ATT_NB_PROJETS, facadeCommune.recupererMesProjets( membreCourant.getLogin() ).size() );
        request.setAttribute( ATT_INVESTISSEMENT_LIST, mesInvestissements );
        request.setAttribute( ATT_MES_PROJETS_LIST, mesProjets );
        request.getRequestDispatcher( PAGE_AFFICHER_PROFIL ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }
}
