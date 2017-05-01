package servlets.administrateur.gestionChevaux;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TousLesChevauxDTO;
import facade.IAdministrateurFacade;
import facade.IFacadeCommune;

/**
 * Servlet implementation class MettreEnAvant
 */
@WebServlet( "/Admin/MettreEnAvant" )
public class MettreEnAvantChevalServlet extends HttpServlet {
    private static final long     serialVersionUID       = 1L;

    private static final String   ATT_LIST_CHEVAUX       = "listeChevaux";
    private static final String   ATT_MESSAGE            = "message";
    private static final String   ATT_ID_CHEVAL          = "idCheval";
    private static final String   ATT_MISE_EN_AVANT      = "mea";

    private static final String   TXT_ERR_MISE_EN_AVANT  = "Vous ne pouvez mettre en avant plus de 10 chevaux.";

    private static final String   ACTION_METTRE_EN_AVANT = "Mettre en avant";

    @EJB
    private IFacadeCommune        facadeCommune;
    @EJB
    private IAdministrateurFacade facadeAdmin;

    public MettreEnAvantChevalServlet() {
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
     * Met en avant ou en arriere un cheval
     * 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String mettreEnAvant = request.getParameter( ATT_MISE_EN_AVANT );
        if ( mettreEnAvant.equals( ACTION_METTRE_EN_AVANT ) ) {
            mettreEnAvant( request, response );
        } else {
            mettreEnArriere( request, response );
        }
        request.setAttribute( ATT_LIST_CHEVAUX, facadeCommune.recupererTousLesChevaux() );
        response.sendRedirect( request.getHeader( "referer" ) );
    }

    /**
     * Appel de la fonction pour mettre en avant les chevaux
     * 
     * @param request
     * @param response
     */
    private void mettreEnAvant( HttpServletRequest request, HttpServletResponse response ) {
        List<TousLesChevauxDTO> enAvant = facadeCommune.recupererChevauxEnAvant();
        if ( enAvant.size() < 10 ) {
            int idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
            facadeAdmin.mettreEnAvant( idCheval );
        } else {
            request.setAttribute( ATT_MESSAGE, TXT_ERR_MISE_EN_AVANT );
        }
    }

    /**
     * Appel de la fonction pour qu'un cheval ne soit plus mis en avant
     * 
     * @param request
     * @param response
     */
    private void mettreEnArriere( HttpServletRequest request, HttpServletResponse response ) {
        int idCheval = Integer.parseInt( request.getParameter( ATT_ID_CHEVAL ) );
        facadeAdmin.mettreEnArriere( idCheval );
    }
}