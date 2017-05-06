package servlets.administrateur.gestionChevaux;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChevalDTO;
import facade.IAdministrateurFacade;
import facade.IFacadeCommune;

/**
 * Servlet implementation class SupprimerChevalServlet
 */
@WebServlet( "/Admin/SupprimerCheval" )
public class SupprimerChevalServlet extends HttpServlet {
    private static final long     serialVersionUID               	  = 1L;
    private static final String   PAGE_SUPPRIMER_CHEVAL            	  = "/WEB-INF/admin/pageConfirmerSuppressionCheval.jsp";
    private static final String   PAGE_CONFIRMATION_SUPRESSION_CHEVAL = "/WEB-INF/admin/pageConfirmationSuppressionCheval.jsp";
    private static final String   CHAMP_CONFIRMER                  	  = "confirmer";
    private static final String   CHAMP_VERIFICATION_SUPPRIMER        = "verificationSupprimer";

    private static final String   ATT_ID_CHEVAL                    	  = "idCheval";
    private static final String   ATT_CHEVAL                       	  = "cheval";

    @EJB
    private IFacadeCommune        facadeCommune;
    @EJB
    private IAdministrateurFacade facadeAdmin;

    private ChevalDTO             cheval;
    private int                   idCheval;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerChevalServlet() {
        super();
    }

    /**
     * Affiche la page pour confirmer la suppression du cheval
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        cheval = facadeCommune.findChevalDTOById( idCheval );
        request.setAttribute( ATT_CHEVAL, cheval );
        request.getRequestDispatcher( PAGE_SUPPRIMER_CHEVAL ).forward( request, response );
    }

    /**
     * Affiche la page pour confirmer la suppression du cheval en cas d'echec pour la
     * confirmation ou affiche la page de succes de la supression du cheval
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String verificationCloturer = request.getParameter( CHAMP_VERIFICATION_SUPPRIMER );
        if ( verificationCloturer.equals( CHAMP_CONFIRMER ) ) {
            facadeAdmin.supprimerCheval( idCheval );
            idCheval = -1;
            cheval = null;
            request.getRequestDispatcher( PAGE_CONFIRMATION_SUPRESSION_CHEVAL ).forward( request, response );
        } else {
            request.getRequestDispatcher( PAGE_SUPPRIMER_CHEVAL ).forward( request, response );
        }
    }
}
