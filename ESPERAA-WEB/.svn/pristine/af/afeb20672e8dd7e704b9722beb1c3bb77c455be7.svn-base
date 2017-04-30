package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.financeurPorteur.forms.FormValidationException;
import servlets.utilitaire.ImageUtilitaire;
import facade.IFinanceurPorteurFacade;

/**
 * Servlet implementation class Inscription
 */
@WebServlet( urlPatterns = { "/Inscription" }, initParams = @WebInitParam( name = "chemin", value = "../images/" ) )
@MultipartConfig( maxFileSize = 2 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024, fileSizeThreshold = 1024 * 1024 )
public class InscriptionServlet extends HttpServlet {
    private static final long       serialVersionUID       = 1L;

    private static final String     PAGE_INSCRIPTION       = "/WEB-INF/pageInscription.jsp";
    private static final String     PAGE_CONNEXION         = "/WEB-INF/pageConnexion.jsp";

    private static final String     CHEMIN                 = "chemin";

    private static final String     EXTENSION_IMAGE_MEMBRE = "profils/";

    private static final String     ATT_ERREUR             = "erreurMap";

    private static final String     CHAMP_LOGIN            = "login";
    private static final String     CHAMP_NOM              = "nom";
    private static final String     CHAMP_EMAIL            = "email";
    private static final String     CHAMP_MDP              = "password";
    private static final String     CHAMP_MDP_CONF         = "confirmation_password";
    private static final String     CHAMP_IMAGE            = "imageMembre";

    @EJB
    private IFinanceurPorteurFacade facadeMembre;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        request.getSession().setAttribute( ATT_ERREUR, null );
        request.getRequestDispatcher( PAGE_INSCRIPTION ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        Map<String, String> erreurMap = new HashMap<String, String>();
        String nom = request.getParameter( CHAMP_NOM );
        String email = request.getParameter( CHAMP_EMAIL );
        String password = request.getParameter( CHAMP_MDP );
        String confirmation_password = request.getParameter( CHAMP_MDP_CONF );
        String login = request.getParameter( CHAMP_LOGIN );
        String chemin = this.getServletConfig().getInitParameter( CHEMIN );
        String image = null;
        try {
            image = ImageUtilitaire.validationImage( request, CHAMP_IMAGE, chemin, EXTENSION_IMAGE_MEMBRE );

            if ( !password.equals( confirmation_password ) ) {
                erreurMap.put( CHAMP_MDP, "Les mots de passe sont différents" );
            }
        } catch ( FormValidationException e ) {
            erreurMap.put( CHAMP_IMAGE, e.getMessage() );
        }
        if ( erreurMap.isEmpty() ) {
            facadeMembre.inscription( nom, login, password, email, image );
            request.getRequestDispatcher( PAGE_CONNEXION ).forward( request, response );
        } else {
            request.getSession().setAttribute( ATT_ERREUR, erreurMap );
            request.getSession().setAttribute( CHAMP_NOM, nom );
            request.getSession().setAttribute( CHAMP_EMAIL, email );
            request.getSession().setAttribute( CHAMP_LOGIN, login );
            request.getRequestDispatcher( PAGE_INSCRIPTION ).forward( request, response );
        }
    }
}
