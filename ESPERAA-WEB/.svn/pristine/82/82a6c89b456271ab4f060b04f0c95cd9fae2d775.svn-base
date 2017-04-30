package servlets.financeurPorteur.gestionProjets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.FinanceurPorteurDTO;
import facade.IFacadeCommune;
import facade.IFinanceurPorteurFacade;

/**
 * Servlet implementation class DesAimeProjetServlet
 */
@WebServlet( "/Membre/DesAimeProjet" )
public class DesAimeProjetServlet extends HttpServlet {
    private static final long       serialVersionUID   = 1L;

    private static final String     ATT_SESSION_MEMBRE = "financeur";

    @EJB
    private IFacadeCommune          facadeCommune;
    @EJB
    private IFinanceurPorteurFacade facadeMembre;

    public DesAimeProjetServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        int idProjet = Integer.parseInt( request.getParameter( "idProjet" ) );
        String loginFinanceur = ( (FinanceurPorteurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE ) )
                .getLogin();
        facadeMembre.nePlusAimerProjet( idProjet, loginFinanceur );
        request.getSession().setAttribute( ATT_SESSION_MEMBRE, facadeCommune.findFinanceurDTOByLogin( loginFinanceur ) );
        response.sendRedirect( request.getHeader( "referer" ) );
    }

}
