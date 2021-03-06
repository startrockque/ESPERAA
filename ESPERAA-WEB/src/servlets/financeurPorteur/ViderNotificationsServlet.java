package servlets.financeurPorteur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DonateurDTO;
import facade.IFacadeCommune;
import facade.IDonateurFacade;

/**
 * Servlet implementation class ViderNotificationsServlet
 */
@WebServlet( "/Membre/ViderNotifications" )
public class ViderNotificationsServlet extends HttpServlet {
    private static final long       serialVersionUID   = 1L;

    private static final String     REDIRECT_ACCUEIL   = "Accueil";

    private static final String     ATT_SESSION_MEMBRE = "donateur";

    @EJB
    private IDonateurFacade facadeMembre;
    @EJB
    private IFacadeCommune          facadeCommune;

    public ViderNotificationsServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        DonateurDTO fPorteur = (DonateurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
        facadeMembre.viderNotification( fPorteur.getLogin() );
        request.getSession().setAttribute( ATT_SESSION_MEMBRE,
                facadeCommune.findDonateurDTOByLogin( fPorteur.getLogin() ) );
        response.sendRedirect( REDIRECT_ACCUEIL );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {

    }

}
