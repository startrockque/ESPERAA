package servlets.administrateur.gestionChevaux;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChevalDTO;
import facade.IFacadeCommune;

/**
 * Servlet implementation class AfficherCheval
 */
@WebServlet( "/Admin/AfficherCheval" )
public class AfficherChevalServlet extends HttpServlet {
    private static final long   serialVersionUID     = 1L;
    private int                 idCheval;

    private static final String PAGE_AFFICHER_CHEVAL = "/WEB-INF/admin/pageAfficherCheval.jsp";

    private static final String ATT_ID_CHEVAL        = "idCheval";
    private static final String ATT_CHEVAL           = "cheval";

    @EJB
    private IFacadeCommune      facade;

    public AfficherChevalServlet() {
        super();
    }

    /**
     * Affiche la page complete d'un cheval
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        ChevalDTO cheval = facade.findChevalById( idCheval );
        request.setAttribute( ATT_CHEVAL, cheval );
        request.getRequestDispatcher( PAGE_AFFICHER_CHEVAL ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }
}
