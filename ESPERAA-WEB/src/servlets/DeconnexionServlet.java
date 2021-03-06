package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeconnexionServlet
 */
@WebServlet( "/Deconnexion" )
public class DeconnexionServlet extends HttpServlet {
    private static final long   serialVersionUID   = 1L;

    private static final String REDIRECT_CONNEXION = "/ESPERAA-WEB/Connexion";

    private static final String ATT_SESSION_ADMIN  = "admin";
    private static final String ATT_SESSION_MEMBRE = "financeur";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeconnexionServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        request.getSession().setAttribute( ATT_SESSION_MEMBRE, null );
        request.getSession().setAttribute( ATT_SESSION_ADMIN, null );
        response.sendRedirect( REDIRECT_CONNEXION );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }

}
