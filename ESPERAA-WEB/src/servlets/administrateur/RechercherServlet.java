package servlets.administrateur;

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
import facade.IDonateurFacade;

/**
 * Servlet implementation class RechercherParTag
 */
@WebServlet( "/Admin/Rechercher" )
public class RechercherServlet extends HttpServlet {
    private static final long       serialVersionUID                  = 1L;

    private static final String     PAGE_ACCUEIL                      = "/WEB-INF/admin/pageAccueil.jsp";

    private static final String     CHAMP_NOM                         = "nomCheval";
    private static final String     CHAMP_CATEGORIE                   = "categorie";
    private static final String     CHAMP_CHECK_BOX_RECHERCHE         = "recherche";

    private static final String     CHECK_BOX_NOM                     = "Nom";
    private static final String     CHECK_BOX_CATEGORIE               = "Categorie";

    private static final String     ATT_LIST_CHEVAUX                  = "listeChevaux";
    private static final String     ATT_AUTO_COMPLETION_CHEVAL        = "autoCompletionCheval";
    private static final String     ATT_LIST_CHEVAL_TAG               = "listeTag";
    private static final String     ATT_LIST_CHEVAL_EN_AVANT          = "listeEnAvant";
    private static final String     ATT_LIST_CHEVAL_CATEGORIE         = "listeCategories";
    private static final String     ATT_TAG_CHEVAL                    = "tagCheval";
    private static final String     ATT_ACTION                        = "action";
    private static final String     ATT_NUMERO_PAGE                   = "numeroPage";
    private static final String     ATT_NB_PAGE                       = "nbPage";
    private static final String     ATT_TAG_POP                       = "tagsPop";
    private static final String     ATT_CATEGORIE_POP                 = "categoriesPop";

    private static final String     ACTION_RECHERCHE_SIMPLE_TAG       = "simpleTag";
    private static final String     ACTION_RECHERCHE_SIMPLE_CATEGORIE = "simpleCat";
    private static final String     ACTION_RECHERCHE_SIMPLE_CHEVAL    = "simpleTitre";
    private static final String     ACTION_CHEVAL_PLUS_FINANCES       = "chevauxPlusFinances";
    private static final String     ACTION_CHEVAL_PRESQUE_FINANCES    = "chevauxPresqueFinances";

    private static final int        CONST_NB_CHEVAL_PAR_PAGE          = 9;

    private int                     numeroPageCourante;

    @EJB
    private IFacadeCommune          facadeCommune;
    @EJB
    private IDonateurFacade facadeMembre;

    public RechercherServlet() {
        super();
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String btn = request.getParameter( ATT_ACTION );
        if ( btn != null ) {
            switch ( btn ) {
            case ACTION_RECHERCHE_SIMPLE_CATEGORIE:
                chercherParCategorie( request, response );
                break;
            case ACTION_RECHERCHE_SIMPLE_TAG:
                chercherParTag( request, response );
                break;
            case ACTION_CHEVAL_PLUS_FINANCES:
                chercherChevalPlusFinances( request, response );
                break;
            case ACTION_CHEVAL_PRESQUE_FINANCES:
                chercherChevalPresqueFinances( request, response );
                break;
            default:
                break;
            }
        }
        dispatchPage( request, response );
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String[] checkedIds = request.getParameterValues( CHAMP_CHECK_BOX_RECHERCHE );

        // Recherche simple
        if ( checkedIds == null ) {
            String btn = request.getParameter( ATT_ACTION );
            if ( btn != null ) {
                switch ( btn ) {
                case ACTION_RECHERCHE_SIMPLE_CHEVAL:
                    chercherParNom( request, response );
                    break;
                case ACTION_RECHERCHE_SIMPLE_CATEGORIE:
                    chercherParCategorie( request, response );
                    break;
                case ACTION_RECHERCHE_SIMPLE_TAG:
                    chercherParTag( request, response );
                    break;
                default:
                    break;
                }
            }
        }
        // Recherche croisée
        else {
            /*
             * Les cas sont : - Nom - Categorie - Nom & Catégorie
             */
            switch ( checkedIds.length ) {
            case 1:
                if ( checkedIds[0].equals( CHECK_BOX_NOM ) ) {
                    chercherParNom( request, response );
                } else if ( checkedIds[0].equals( CHECK_BOX_CATEGORIE ) ) {
                    chercherParCategorie( request, response );
                }
                break;
            case 2:
                if ( checkedIds[0].equals( CHECK_BOX_NOM ) ) {
                    if ( checkedIds[1].equals( CHECK_BOX_CATEGORIE ) ) {
                        chercherParNomCategorie( request, response );
                    }
                } else {
                	chercherParNomCategorie( request, response );
                }
                break;
            default:
            }
        }
        dispatchPage( request, response );
    }

    /**
     * Appel de la recherche par titre
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void chercherParNom( HttpServletRequest request, HttpServletResponse response ) throws ServletException,
            IOException {
        String nomCheval = request.getParameter( CHAMP_NOM );
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        List<TousLesChevauxDTO> listChevaux = facadeCommune.rechercherParNom( nomCheval );
        if ( numeroPageString == null ) {
            numeroPageCourante = 1;
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
    }

    /**
     * Appel de la recherche pas catégorie
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void chercherParCategorie( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        String categorie = request.getParameter( CHAMP_CATEGORIE );
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        List<TousLesChevauxDTO> listChevaux = facadeCommune.rechercherParCategorie( categorie );
        if ( numeroPageString == null ) {
            numeroPageCourante = 1;
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
    }


    /**
     * Appel de la recherche par tag
     * 
     * @param request
     * @param response
     */
    private void chercherParTag( HttpServletRequest request, HttpServletResponse response ) {
        String tags = request.getParameter( ATT_TAG_CHEVAL );
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        List<TousLesChevauxDTO> listChevaux = facadeCommune.rechercherParTag( tags );
        if ( numeroPageString == null ) {
            numeroPageCourante = 1;
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
    }

    private void chercherChevalPlusFinances( HttpServletRequest request, HttpServletResponse response ) {
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        List<TousLesChevauxDTO> listChevaux = facadeCommune.recupererChevauxPlusFinances( 9 );
        if ( numeroPageString == null ) {
            numeroPageCourante = 1;
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
    }

    private void chercherChevalPresqueFinances( HttpServletRequest request, HttpServletResponse response ) {
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        List<TousLesChevauxDTO> listChevaux = facadeCommune.recupererChevauxPresqueFinances( 9 );
        if ( numeroPageString == null ) {
            numeroPageCourante = 1;
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
    }

    /**
     * Appel de la fonction de recherche par nom et catégorie
     * 
     * @param request
     * @param response
     */
    private void chercherParNomCategorie( HttpServletRequest request, HttpServletResponse response ) {
        String nomCheval = request.getParameter( CHAMP_NOM );
        String categorie = request.getParameter( CHAMP_CATEGORIE );
        List<TousLesChevauxDTO> listChevaux = facadeCommune.rechercherParNomCategorie( nomCheval, categorie );
        String numeroPageString = request.getParameter( ATT_NUMERO_PAGE );
        if ( numeroPageString == null ) {
            numeroPageCourante = 1;
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        } else {
            numeroPageCourante = Integer.parseInt( numeroPageString );
            request.setAttribute( ATT_LIST_CHEVAUX, recupererChevalPage( listChevaux, numeroPageCourante, request ) );
        }
        request.setAttribute( ATT_NUMERO_PAGE, numeroPageCourante );
    }

    /**
     * Affichage de la jsp avec chargement des données
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
        request.setAttribute( ATT_AUTO_COMPLETION_CHEVAL, facadeCommune.recupererTousLesNomsDesChevaux() );
        request.setAttribute( ATT_LIST_CHEVAL_TAG, facadeCommune.recupererTousLesTagName() );
        request.setAttribute( ATT_LIST_CHEVAL_EN_AVANT, facadeCommune.recupererChevauxEnAvant() );
        request.setAttribute( ATT_LIST_CHEVAL_CATEGORIE, facadeCommune.getAllCategoriesNames() );
        request.getRequestDispatcher( PAGE_ACCUEIL ).forward( request, response );
    }

    private List<TousLesChevauxDTO> recupererChevalPage( List<TousLesChevauxDTO> chevalAllList, int numeroPage,
            HttpServletRequest request ) {
        int indexFin = 0;
        int indexDeb = ( numeroPage - 1 ) * CONST_NB_CHEVAL_PAR_PAGE;

        List<TousLesChevauxDTO> chevalPageList = new ArrayList<TousLesChevauxDTO>();
        if ( indexDeb + CONST_NB_CHEVAL_PAR_PAGE > chevalAllList.size() ) {
            indexFin = chevalAllList.size();
        } else {
            indexFin = indexDeb + CONST_NB_CHEVAL_PAR_PAGE;
        }
        int nbPage = 0;
        for ( int i = 0; i < chevalAllList.size(); i = i + CONST_NB_CHEVAL_PAR_PAGE ) {
            nbPage++;
        }

        request.setAttribute( ATT_NB_PAGE, nbPage );
        for ( int i = indexDeb; i < indexFin; i++ ) {
            chevalPageList.add( chevalAllList.get( i ) );
        }

        return chevalPageList;
    }
}
