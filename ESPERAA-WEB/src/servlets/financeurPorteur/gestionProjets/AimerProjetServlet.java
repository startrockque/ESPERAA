package servlets.financeurPorteur.gestionProjets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DonateurDTO;
import facade.IFacadeCommune;
import facade.IDonateurFacade;

/**
 * Servlet implementation class AimerProjetServlet
 */
@WebServlet( "/Membre/AimerProjet" )
public class AimerProjetServlet extends HttpServlet {
    private static final long       serialVersionUID   = 1L;

    private static final String     ATT_SESSION_MEMBRE = "financeur";

    private int                     idProjet;

    @EJB
    private IFacadeCommune          facadeCommune;
    @EJB
    private IDonateurFacade facadeMembre;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AimerProjetServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        idProjet = Integer.parseInt( request.getParameter( "idProjet" ) );
        DonateurDTO membre = (DonateurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
        facadeMembre.aimerProjet( idProjet, membre.getLogin() );
        request.getSession().setAttribute( ATT_SESSION_MEMBRE,
                facadeCommune.findFinanceurDTOByLogin( membre.getLogin() ) );
        response.sendRedirect( request.getHeader( "referer" ) );
    }

}
