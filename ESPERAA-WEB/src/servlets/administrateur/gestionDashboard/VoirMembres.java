package servlets.administrateur.gestionDashboard;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DonateurMinDTO;
import facade.IAdministrateurFacade;
import facade.IFacadeCommune;

/**
 * Servlet implementation class VoirMembres
 */
@WebServlet( "/Admin/VoirMembres" )
public class VoirMembres extends HttpServlet {
    private static final long     serialVersionUID      = 1L;

    private static final String   LIST_MEMBRES          = "listMembres";
    private static final String   REDIRECT_VOIR_MEMBRES = "/WEB-INF/admin/pageVoirMembres.jsp";

    @EJB
    private IAdministrateurFacade facade;
    @EJB
    private IFacadeCommune        facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoirMembres() {
        super();
    }

    /**
     * Affiche la liste de tous les membres
     * 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        List<DonateurMinDTO> listMembres = facade.recupererTousLesMembres();
        request.setAttribute( LIST_MEMBRES, listMembres );
        request.getRequestDispatcher( REDIRECT_VOIR_MEMBRES ).forward( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
    }
}
