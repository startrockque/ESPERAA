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
 * Servlet implementation class RepondreMessageServlet
 */
@WebServlet( "/Admin/RepondreMessage" )
public class RepondreMessageServlet extends HttpServlet {
    private static final long   serialVersionUID         = 1L;

    private static final String REDIRECT_AFFICHER_PROJET = "/ESPERAA-WEB/Admin/AfficherProjet";

    private static final String ATT_SESSION_MEMBRE       = "admin";
    private static final String ATT_ID_CONVERSATION      = "idConversation";
    private static final String ATT_ID_PROJET            = "idProjet";
    private static final String CHAMP_CONTENU_REPONSE    = "contenuReponse";

    @EJB
    private IFacadeCommune      facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RepondreMessageServlet() {
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
     * Permet de repondre a un message (à une conversation)
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String contenuReponse = request.getParameter( CHAMP_CONTENU_REPONSE );
        int idProjet = Integer.parseInt( request.getParameter( ATT_ID_PROJET ) );
        if ( !contenuReponse.trim().equals( "" ) ) {
            AdminDTO admin = (AdminDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
            int idConversation = Integer.parseInt( request.getParameter( ATT_ID_CONVERSATION ) );
            facadeCommune.repondreMessage( admin.getLogin(), idConversation, contenuReponse );
        }
        response.sendRedirect( REDIRECT_AFFICHER_PROJET + "?" + ATT_ID_PROJET + "=" + idProjet );
    }
}
