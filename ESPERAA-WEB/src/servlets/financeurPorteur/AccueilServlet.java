package servlets.financeurPorteur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.TousLesChevauxDTO;
import facade.IFacadeCommune;

/**
 * Servlet implementation class PageAccueilEtRecherche
 */
@WebServlet( "/Membre/Accueil" )
public class AccueilServlet extends HttpServlet {
    private static final long   serialVersionUID         = 1L;

    private static final String PAGE_ACCUEIL             = "/WEB-INF/financeurPorteur/pageAccueil.jsp";

    private static final String ATT_LIST_CHEVAUX          = "listeChevaux";
    private static final String ATT_LIST_CHEVAUX_EN_AVANT = "listeEnAvant";

    private static final String ATT_TAG_POP              = "tagsPop";
    private static final String ATT_CATEGORIE_POP        = "categoriesPop";
    private static final String ATT_NUMERO_PAGE          = "numeroPage";
    private static final String ATT_NB_PAGE              = "nbPage";

    private static final int    CONST_NB_CHEVAUX_PAR_PAGE = 9;

    private int                 numeroPageCourante;

    @EJB
    private IFacadeCommune      facadeCommune;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccueilServlet() {
        super();
        numeroPageCourante = 1;
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        numeroPageCourante = 1;
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        List<TousLesChevauxDTO> chevalAllList = facadeCommune.recupererTousLesChevaux();
        if ( numeroPageString == null ) {
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( chevalAllList, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( chevalAllList, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
        dispatchPage( request, response );
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        dispatchPage( request, response );
    }

    /**
     * Affichage de la jsp avec chargement des donn�es
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void dispatchPage( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        request.setAttribute( ATT_TAG_POP, facadeCommune.listeTagPopulaire( 3 ) );
        request.setAttribute( ATT_CATEGORIE_POP, facadeCommune.listeCategoriePopulaire( 3 ) );
        request.setAttribute( ATT_LIST_CHEVAUX_EN_AVANT, facadeCommune.recupererChevauxEnAvant() );
        request.getRequestDispatcher( PAGE_ACCUEIL ).forward( request, response );
    }

    private List<TousLesChevauxDTO> recupererChevalPage( List<TousLesChevauxDTO> chevalAllList, int numeroPage,
            HttpServletRequest request ) {
        int indexFin = 0;
        int indexDeb = ( numeroPage - 1 ) * CONST_NB_CHEVAUX_PAR_PAGE;

        List<TousLesChevauxDTO> chevalPageList = new ArrayList<TousLesChevauxDTO>();
        if ( indexDeb + CONST_NB_CHEVAUX_PAR_PAGE > chevalAllList.size() ) {
            indexFin = chevalAllList.size();
        } else {
            indexFin = indexDeb + CONST_NB_CHEVAUX_PAR_PAGE;
        }
        int nbPage = 0;
        for ( int i = 0; i < chevalAllList.size(); i = i + CONST_NB_CHEVAUX_PAR_PAGE ) {
            nbPage++;
        }

        request.setAttribute( ATT_NB_PAGE, nbPage );
        for ( int i = indexDeb; i < indexFin; i++ ) {
            chevalPageList.add( chevalAllList.get( i ) );
        }

        return chevalPageList;
    }
}