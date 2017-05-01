package servlets.financeurPorteur.gestionProjets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.DonateurDTO;
import dto.ChevalDTO;
import facade.IFacadeCommune;
import facade.IDonateurFacade;

/**
 * Servlet implementation class FinancerProjetServlet
 */
@WebServlet( "/Membre/FinancerProjet" )
public class FinancerProjetServlet extends HttpServlet {
    private static final long       serialVersionUID               = 1L;

    private static final String     PAGE_AFFICHER_PROJET           = "/WEB-INF/financeurPorteur/pageAfficherProjet.jsp";

    private static final String     ATT_SESSION_MEMBRE             = "financeur";
    private static final String     ATT_ID_PROJET                  = "idProjet";
    private static final String     ATT_MESSAGE                    = "message";
    private static final String     ATT_ERREUR                     = "erreur";
    private static final String     ATT_PROJET                     = "projet";

    private static final String     CHAMP_MONTANT                  = "montant";

    private static final int        CONST_SOMME_MINIMUM_A_INVESTIR = 0;

    @EJB
    private IFacadeCommune          facadeCommune;
    @EJB
    private IDonateurFacade facadeMembre;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinancerProjetServlet() {
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
        DonateurDTO membre = (DonateurDTO) request.getSession().getAttribute( ATT_SESSION_MEMBRE );
        int idProjetAFinancer = Integer.parseInt( request.getParameter( ATT_ID_PROJET ) );
        String montantIvestString = request.getParameter( CHAMP_MONTANT ).trim();
        request.setAttribute( ATT_ID_PROJET, idProjetAFinancer );
        int montantIvest = 0;
        if ( !montantIvestString.isEmpty() ) {
            montantIvest = Integer.parseInt( montantIvestString );
        }
        if ( montantIvest > CONST_SOMME_MINIMUM_A_INVESTIR ) {
            String nomFinanceur = membre.getLogin();
            if ( montantIvest <= membre.getMontantAInvestir() ) {
                ChevalDTO projet = facadeCommune.findProjetDTOById( idProjetAFinancer );
                if ( projet.getMontantInvesti() < Integer.MAX_VALUE ) {
                    if ( projet.getMontantInvesti() + montantIvest < 0 ) {
                        montantIvest = Integer.MAX_VALUE - projet.getMontantInvesti();
                    }
                    facadeMembre.financerProjet( nomFinanceur, idProjetAFinancer, montantIvest );
                    request.getSession().setAttribute( ATT_SESSION_MEMBRE,
                            facadeCommune.findFinanceurDTOByLogin( nomFinanceur ) );
                    projet = facadeCommune.findProjetDTOById( idProjetAFinancer );
                    request.setAttribute( ATT_MESSAGE, "Merci de votre aide " + membre.getLogin() );
                } else {
                    request.setAttribute( ATT_ERREUR, "Ce projet a atteint la somme de financement maximum." );
                }
            } else {
                request.setAttribute( ATT_ERREUR, "Vous ne disposez que de " + membre.getMontantAInvestir()
                        + " pour investir." );
            }
        } else {
            request.setAttribute( ATT_ERREUR, "Veuillez entrer une somme supérieure à  0." );
        }
        ChevalDTO projet = facadeCommune.findProjetDTOById( idProjetAFinancer );
        request.setAttribute( ATT_PROJET, projet );
        request.getRequestDispatcher( PAGE_AFFICHER_PROJET ).forward( request, response );
    }

}
