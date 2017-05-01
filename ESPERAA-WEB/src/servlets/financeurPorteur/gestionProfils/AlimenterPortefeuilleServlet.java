package servlets.financeurPorteur.gestionProfils;

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
 * Servlet implementation class AlimenterPortefeuilleServlet
 */
@WebServlet( "/Membre/AlimenterPortefeuille" )
public class AlimenterPortefeuilleServlet extends HttpServlet {
    private static final long       serialVersionUID             = 1L;

    private static final String     REDIRECT_AFFICHER_MON_PROFIL = "/ESPERAA-WEB/Membre/MonProfil";

    private static final String     ATT_SESSION_MEMBRE           = "financeur";

    private static final String     CHAMP_MONTANT_A_CREDITER     = "montant";

    @EJB
    private IDonateurFacade facadeMembre;
    @EJB
    private IFacadeCommune          facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlimenterPortefeuilleServlet() {
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
        DonateurDTO membreCourant = (DonateurDTO) request.getSession()
                .getAttribute( ATT_SESSION_MEMBRE );
        String montantDemandeString = request.getParameter( CHAMP_MONTANT_A_CREDITER ).trim();
        if ( !montantDemandeString.isEmpty() ) {
            int montantDemande = Integer.parseInt( montantDemandeString );
            facadeMembre.alimenterPortefeuille( membreCourant.getLogin(), montantDemande );
            request.getSession().setAttribute( ATT_SESSION_MEMBRE,
                    facadeCommune.findFinanceurDTOByLogin( membreCourant.getLogin() ) );
        }
        response.sendRedirect( REDIRECT_AFFICHER_MON_PROFIL );
    }

}
