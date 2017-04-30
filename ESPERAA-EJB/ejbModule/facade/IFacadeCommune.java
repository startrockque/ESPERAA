package facade;

import java.util.List;

import javax.ejb.Local;

import dto.AUtilisateurDTO;
import dto.AdminDTO;
import dto.CategorieDTO;
import dto.DonateurDTO;
import dto.ChevalDTO;
import dto.TousLesChevauxDTO;

@Local
public interface IFacadeCommune {

    /****************/
    // Recherche //
    /****************/

    /**
     * R�cup�re le cheval � partir de son id
     * 
     * @param idCheval
     *            l'id du cheval
     * @return le cheval ayant un id �gal � idcheval
     */
    ChevalDTO findChevalById( int idCheval );

    /**
     * M�thode de recherche d'un cheval par son nom
     * 
     * @param nomCheval
     * @return la liste des chevaux correspondants au nom
     *         renseign�
     */
    List<TousLesChevauxDTO> rechercherParNom( String nomCheval );

    /**
     * M�thode de recherche de chevaux par par
     * cat�gorie
     * 
     * @param categorie
     * @return la liste des chevaux correspondants � la cat�gorie renseign�e
     */
    List<TousLesChevauxDTO> rechercherParCategorie( String categorie );

    /**
     * Rechercher des chevaux par un ou plusieurs tag
     * 
     * @param tagString
     *            les tags
     * @return liste de chevaux
     */
    List<TousLesChevauxDTO> rechercherParTag( String tagString );


    /**
     * M���thode de recherche de cheval par nom et cat�gorie
     * 
     * @param nomCheval
     * @param categorie
     * @return liste des chevaux correspondants aux infos renseign�es
     */
    List<TousLesChevauxDTO> rechercherParNomCategorie( String nomCheval, String categorie );

    /****************/
    // Categories //
    /****************/

    /**
     * M�thode de chargement du nom des cat�gories
     * 
     * @return la liste des noms des cat�gories
     */
    List<String> getAllCategoriesNames();

    /**
     * Trouver toutes les Categories
     * 
     * @return
     */
    List<CategorieDTO> findAllCategorieDto();

    /**
     * Trouver les cat�gories les plus populaires
     * 
     * @param nbCat
     * @return
     */
    List<String> listeCategoriePopulaire( int nbCat );

    /****************/
    // Chevaux //
    /****************/

    /**
     * Recuperer une liste contenant tous les chevaux de la BDD
     * 
     * @return ArrayList de tous les chevaux
     */
    List<TousLesChevauxDTO> recupererTousLesChevaux();

    /**
     * R�cup�rer une liste contenant les noms de tous les chevaux
     * 
     * @return ArrayList de tous les chevaux
     */
    List<String> recupererTousLesNomsDesChevaux();

    /**
     * Recuperer une liste contenant tous les chevaux en avant de la BDD
     * 
     * @return ArrayList de tous les chevaux en avant
     */
    List<TousLesChevauxDTO> recupererChevauxEnAvant();

    /**
     * Trouver un cheval par son id
     * 
     * @param idCheval
     * @return
     */
    ChevalDTO findChevalDTOById( int idCheval );

    /**
     * Trouver les chevaux les plus r�cents
     * 
     * @param nbCheval
     * @return
     */
    List<TousLesChevauxDTO> recupererChevauxRecents( int nbCheval );

    /**
     * Trouver les chevaux presque financ�s
     * 
     * @param nbCheval
     * @return
     */
    List<TousLesChevauxDTO> recupererChevauxPresqueFinances( int nbCheval );

    /**
     * Trouver les chevaux les plus financ�s
     * 
     * @param nbCheval
     * @return
     */
    List<TousLesChevauxDTO> recupererChevauxPlusFinances( int nbCheval );

    /****************/
    // Membres //
    /****************/

    /**
     * Connexion, renvoie null si l'utilisateur n'existe pas Renvoie un
     * Administrateur ou un Donateur selon l'utilisateur
     * 
     * @return AUtilisateur Administrateur ou Donateur
     */
    AUtilisateurDTO connexion( String login, String mdp );

    /**
     * Recuperer tous les noms des Donateur dans une liste
     * 
     * @return une array liste contenant tous les noms des Donateur
     */
    List<String> recupererTousLesNomsDeDonateurs();

    /**
     * Trouver un Donateur par son login
     * 
     * @param login
     * @return
     */
    DonateurDTO findDonateurDTOByLogin( String login );

    /**
     * Trouver un admin par son login
     * 
     * @param login
     * @return
     */
    AdminDTO findAdminDTOByLogin( String login );

    /****************/
    // Messages //
    /****************/

    /**
     * Un utilisateur envoie un messge (commentaire) sur un cheval
     * 
     * @param nomEmetteur
     *            Nom de l'utilisateur
     * @param idCheval
     *            id du cheval
     * @param contenuMessage
     *            contenu du message
     */
    void envoyerMessage( String nomEmetteur, int idCheval, String contenuMessage );

    /**
     * Un utilisateur repond a un message (commentaire) deja poste par un autre
     * utilisateur (ou lui meme)
     * 
     * @param nomEmetteur
     *            nom de l'utilisateur
     * @param idConversation
     *            id de la conversation
     */
    void repondreMessage( String loginEmetteur, int idConversation, String contenuReponse );

    /**
     * Un utilisateur supprime un de ses messages ou un administrateur supprime
     * un message
     * 
     * @param idMessage
     * @param idConversation
     * @param idCheval
     * @param admin
     */
    void supprimerMessage( int idMessage, int idConversation, int idCheval, boolean admin );

    /****************/
    // Tags //
    /****************/

    /**
     * Recuperer la liste de tous les tags
     * 
     * @return
     */
    List<String> recupererTousLesTagName();

    /**
     * Trouver les tags les plus utilis�s
     * 
     * @param nbTag
     * @return
     */
    List<String> listeTagPopulaire( int nbTag );
}