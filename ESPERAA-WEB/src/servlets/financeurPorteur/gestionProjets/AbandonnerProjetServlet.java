package servlets.financeurPorteur.gestionProjets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChevalDTO;
import facade.IFacadeCommune;
import facade.IDonateurFacade;

/**
 * Servlet implementation class AbandonnerProjetServlet
 */
@WebServlet( "/Membre/AbandonnerProjet" )
public class AbandonnerProjetServlet extends HttpServlet {
    private static final long       serialVersionUID                 = 1L;

    private static final String     PAGE_ABANDONNER_PROJET           = "/WEB-INF/financeurPorteur/pageConfirmerAbandonProjet.jsp";
    private static final String     PAGE_CONFIRMATION_ABANDON_PROJET = "/WEB-INF/financeurPorteur/pageConfirmationAbandonProjet.jsp";

    private static final String     CHAMP_ABANDONNER                 = "abandonner";
    private static final String     CHAMP_VERIFICATION_ABANDONNER    = "verificationAbandonner";

    private static final String     ATT_ID_PROJET                    = "idProjet";
    private static final String     ATT_PROJET                       = "projet";

    private ChevalDTO               projet;
    private int                     idProjet;
    @EJB
    private IFacadeCommune          facadeCommune;
    @EJB
    private IDonateurFacade facadeMembre;

    public AbandonnerProjetServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        idProjet = Integer.parseInt( request.getParameter( ATT_ID_PROJET ) );
        projet = facadeCommune.findProjetDTOById( idProjet );
        request.setAttribute( ATT_PROJET, projet );
        request.getRequestDispatcher( PAGE_ABANDONNER_PROJET ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String verificationAbandonner = request.getParameter( CHAMP_VERIFICATION_ABANDONNER );
        if ( verificationAbandonner.equals( CHAMP_ABANDONNER ) ) {
            facadeMembre.cloturerProjet( idProjet );
            idProjet = -1;
            projet = null;
            request.getRequestDispatcher( PAGE_CONFIRMATION_ABANDON_PROJET ).forward( request, response );
        } else {
            request.getRequestDispatcher( PAGE_ABANDONNER_PROJET ).forward( request, response );
        }
    }
}
