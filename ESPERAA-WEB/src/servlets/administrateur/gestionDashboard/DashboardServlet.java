package servlets.administrateur.gestionDashboard;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facade.IAdministrateurFacade;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet( "/Admin/Dashboard" )
public class DashboardServlet extends HttpServlet {
    private static final long     serialVersionUID        = 1L;

    private static final String   PAGE_AFFICHER_DASHBOARD = "/WEB-INF/admin/pageTableauDeBord.jsp";

    private static final String   NB_CHEVAUX              = "nbChevaux";
    private static final String   NB_CATEGORIES           = "nbCategories";
    private static final String   NB_MEMBRES              = "nbMembres";
    private static final String   NB_MESSAGES             = "nbMessages";
    private static final String   NB_INVESTISSEMENTS      = "nbInvestissements";
    private static final String   NB_TAGS                 = "nbTags";

    @EJB
    private IAdministrateurFacade facade;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
    }

    /**
     * Affiche la page du Dashboard
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        request.setAttribute( NB_CHEVAUX, facade.recupererNbChevaux() );
        request.setAttribute( NB_CATEGORIES, facade.recupererNbCategories() );
        request.setAttribute( NB_MEMBRES, facade.recupererNbMembres() );
        request.setAttribute( NB_MESSAGES, facade.recupererNbMessages() );
        request.setAttribute( NB_INVESTISSEMENTS, facade.recupererTotalInvestissements() );
        request.setAttribute( NB_TAGS, facade.recupererNbTags() );
        request.getRequestDispatcher( PAGE_AFFICHER_DASHBOARD ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
    }

}
