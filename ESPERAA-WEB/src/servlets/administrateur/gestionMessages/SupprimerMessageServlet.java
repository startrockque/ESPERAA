package servlets.administrateur.gestionMessages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.IFacadeCommune;

/**
 * Servlet implementation class SupprimerMessage
 */
@WebServlet( "/Admin/SupprimerMessage" )
public class SupprimerMessageServlet extends HttpServlet {
    private static final long   serialVersionUID     = 1L;

    private static final String PAGE_AFFICHER_CHEVAL = "/WEB-INF/Admin/pageAfficherCheval.jsp";

    private static final String ATT_ID_CHEVAL        = "idCheval";
    private static final String ATT_ID_CONVERSATION  = "idConversation";
    private static final String ATT_ID_MESSAGE       = "idMessage";
    private static final String ATT_CHEVAL           = "cheval";

    @EJB
    private IFacadeCommune      facadeCommune;

    public SupprimerMessageServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }

    /**
     * Permet de supprimer un message. Si c'est le premier message de la
     * conversation, supprime la conversation
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        int idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        int idConversation = Integer.parseInt( request.getParameter( ATT_ID_CONVERSATION ) );
        int idMessage = Integer.parseInt( request.getParameter( ATT_ID_MESSAGE ) );
        facadeCommune.supprimerMessage( idMessage, idConversation, idCheval, false );
        request.setAttribute( ATT_CHEVAL, facadeCommune.findChevalDTOById( idCheval ) );
        request.getRequestDispatcher( PAGE_AFFICHER_CHEVAL ).forward( request, response );
    }

}
