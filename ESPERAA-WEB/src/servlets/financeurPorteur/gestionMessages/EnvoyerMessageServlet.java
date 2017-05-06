package servlets.financeurPorteur.gestionMessages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DonateurDTO;
import facade.IFacadeCommune;

/**
 * Servlet implementation class EnvoyerMessageServlet
 */
@WebServlet( "/Membre/EnvoyerMessage" )
public class EnvoyerMessageServlet extends HttpServlet {
    private static final long   serialVersionUID         = 1L;

    private static final String REDIRECT_AFFICHER_CHEVAL = "/ESPERAA-WEB/Membre/AfficherCheval";

    private static final String ATT_ID_CHEVAL            = "idCheval";
    private static final String ATT_SESSION_MEMBRE       = "donateur";

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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String contenuMessage = request.getParameter( CHAMP_CONTENU_MESSAGE );
        int idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        if ( !contenuMessage.trim().equals( "" ) ) {
            DonateurDTO membre = (DonateurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
            String loginEmetteur = membre.getLogin();
            facadeCommune.envoyerMessage( loginEmetteur, idCheval, contenuMessage );
        }
        response.sendRedirect( REDIRECT_AFFICHER_CHEVAL + "?" + ATT_ID_CHEVAL + "=" + idCheval );
    }

}
