package filter;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.FinanceurPorteurDTO;
import facade.IFacadeCommune;
import facade.IFinanceurPorteurFacade;

/**
 * Servlet Filter implementation class UtilisateurFilter
 */

@WebFilter( urlPatterns = "/Membre/*" )
public class MembreFilter implements Filter {

    private static final String     REDIRECT_CONNEXION     = "/ESPERAA-WEB/Connexion";

    private static final String     ATT_SESSION_MEMBRE     = "financeur";
    private static final String     ATT_NOTIFICATIONS      = "notifications";
    private static final String     ATT_LIST_CATEGORIE     = "listeCategories";
    private static final String     ATT_COMPLETION_TAG     = "listeTag";
    private static final String     ATT_COMPLETION_PORTEUR = "listePorteurs";
    private static final String     ATT_COMPLETION_PROJET  = "autoCompletionProjet";

    @EJB
    private IFinanceurPorteurFacade financeurPorteurFacade;

    @EJB
    private IFacadeCommune          facadeCommune;

    /**
     * Default constructor.
     */
    public MembreFilter() {

    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {

    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        /* R�cup�ration de la session depuis la requ�te */
        HttpSession session = req.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connect�.
         */
        if ( session.getAttribute( ATT_SESSION_MEMBRE ) == null ) {
            /* Redirection vers la page publique */
            res.sendRedirect( REDIRECT_CONNEXION );
        } else {
            /* Affichage de la page restreinte */
            FinanceurPorteurDTO membreDto = (FinanceurPorteurDTO) session.getAttribute( ATT_SESSION_MEMBRE );
            request.setAttribute( ATT_COMPLETION_TAG, facadeCommune.recupererTousLesTagName() );
            request.setAttribute( ATT_COMPLETION_PROJET, facadeCommune.recupererTousLesTitresDesProjets() );
            req.setAttribute( ATT_LIST_CATEGORIE, facadeCommune.getAllCategoriesNames() );
            req.setAttribute( ATT_COMPLETION_PORTEUR, facadeCommune.recupererTousLesNomsDeFinanceurs() );
            req.setAttribute( ATT_NOTIFICATIONS,
                    financeurPorteurFacade.recupererNotificationParLoginFinanceur( membreDto.getLogin() ) );
            chain.doFilter( req, res );
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init( FilterConfig fConfig ) throws ServletException {

    }
}
