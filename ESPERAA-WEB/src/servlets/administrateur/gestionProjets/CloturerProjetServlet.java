package servlets.administrateur.gestionProjets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ProjetDTO;
import facade.IAdministrateurFacade;
import facade.IFacadeCommune;

/**
 * Servlet implementation class CloturerProjetServlet
 */
@WebServlet( "/Admin/CloturerProjet" )
public class CloturerProjetServlet extends HttpServlet {
    private static final long     serialVersionUID                 = 1L;
    private static final String   PAGE_CLOTURER_PROJET             = "/WEB-INF/admin/pageConfirmerClotureProjet.jsp";
    private static final String   PAGE_CONFIRMATION_CLOTURE_PROJET = "/WEB-INF/admin/pageConfirmationClotureProjet.jsp";
    private static final String   CHAMP_CLOTURER                   = "cloturer";
    private static final String   CHAMP_VERIFICATION_CLOTURER      = "verificationCloturer";

    private static final String   ATT_ID_PROJET                    = "idProjet";
    private static final String   ATT_PROJET                       = "projet";

    @EJB
    private IFacadeCommune        facadeCommune;
    @EJB
    private IAdministrateurFacade facadeAdmin;

    private ProjetDTO             projet;
    private int                   idProjet;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloturerProjetServlet() {
        super();
    }

    /**
     * Affiche la page pour confirmer l'abandon de projet
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        idProjet = Integer.parseInt( request.getParameter( ATT_ID_PROJET ) );
        projet = facadeCommune.findProjetDTOById( idProjet );
        request.setAttribute( ATT_PROJET, projet );
        request.getRequestDispatcher( PAGE_CLOTURER_PROJET ).forward( request, response );
    }

    /**
     * Affiche la page pour confirmer l'abandon de projet en cas d'echec pour la
     * confirmation ou affiche la page de succes de la supression du projet
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String verificationCloturer = request.getParameter( CHAMP_VERIFICATION_CLOTURER );
        if ( verificationCloturer.equals( CHAMP_CLOTURER ) ) {
            facadeAdmin.cloturerProjet( idProjet );
            idProjet = -1;
            projet = null;
            request.getRequestDispatcher( PAGE_CONFIRMATION_CLOTURE_PROJET ).forward( request, response );
        } else {
            request.getRequestDispatcher( PAGE_CLOTURER_PROJET ).forward( request, response );
        }
    }
}
