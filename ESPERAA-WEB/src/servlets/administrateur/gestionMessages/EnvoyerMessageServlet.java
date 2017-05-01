package servlets.administrateur.gestionMessages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AdminDTO;
import facade.IFacadeCommune;

/**
 * Servlet implementation class EnvoyerMessageServlet
 */
@WebServlet( "/Admin/EnvoyerMessage" )
public class EnvoyerMessageServlet extends HttpServlet {
    private static final long   serialVersionUID         = 1L;

    private static final String REDIRECT_AFFICHER_CHEVAL = "/ESPERAA-WEB/Admin/AfficherCheval";

    private static final String ATT_ID_CHEVAL            = "idCheval";
    private static final String ATT_SESSION_MEMBRE       = "admin";

    private static final String CHAMP_CONTENU_MESSAGE    = "contenuMessage";

    @EJB
    private IFacadeCommune      facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnvoyerMessageServlet() {
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
     * Permet d'envoyer un message (crée une conversation) de soutien ou une
     * question
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String contenuMessage = request.getParameter( CHAMP_CONTENU_MESSAGE );
        int idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        if ( !contenuMessage.trim().equals( "" ) ) {
            AdminDTO admin = (AdminDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
            String loginEmetteur = admin.getLogin();
            facadeCommune.envoyerMessage( loginEmetteur, idCheval, contenuMessage );
        }
        response.sendRedirect( REDIRECT_AFFICHER_CHEVAL + "?" + ATT_ID_CHEVAL + "=" + idCheval );
    }

}
