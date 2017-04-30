package facade;

import java.util.List;

import javax.ejb.Local;

import dto.AUtilisateurDTO;
import dto.AdminDTO;
import dto.CategorieDTO;
import dto.FinanceurPorteurDTO;
import dto.ProjetDTO;
import dto.TousLesProjetsDTO;

@Local
public interface IFacadeCommune {

    /****************/
    // Recherche //
    /****************/

    /**
     * R���cup���re le projet ��� partir de son id
     * 
     * @param idProjet
     *            l'id du projet
     * @return le projet ayant un id ���gal ��� idprojet
     */
    ProjetDTO findProjetById( int idProjet );

    /**
     * M���thode de recherche d'un projet par son titre
     * 
     * @param titreProjet
     * @return la liste des projets correspondants au titre
     *         renseign������
     */
    List<TousLesProjetsDTO> rechercherParTitre( String titreProjet );

    /**
     * M���������thode de recherche de projets par
     * cat���������gorie
     * 
     * @param categorie
     * @return la liste des projets correspondants ���������
     *         la cat���������gorie
     *         renseign���������e
     */
    List<TousLesProjetsDTO> rechercherParCatgeorie( String categorie );

    /**
     * Rechercher des projets par un ou plusieurs tag
     * 
     * @param tagString
     *            les tags
     * @return liste de projets
     */
    List<TousLesProjetsDTO> rechercherParTag( String tagString );

    /**
     * M���thode de recherche de projets par porteur
     * 
     * @param nomPorteur
     * @returnla liste des projets correspondants au nom de porteur
     *           renseign���
     */
    List<TousLesProjetsDTO> rechercherParPorteur( String nomPorteur );

    /**
     * M���thode de recherche de projet par titre et cat���gorie
     * 
     * @param titreProjet
     * @param categorie
     * @return liste des projets correspondants aux infos renseign���es
     */
    List<TousLesProjetsDTO> rechercherParTitreCatgeorie( String titreProjet, String categorie );

    /**
     * M���thode de recherche de projet par titre et nom
     * 
     * @param titreProjet
     * @param nomPorteur
     * @return liste des projets correspondants aux infos renseign���es
     */
    List<TousLesProjetsDTO> rechercherParTitreNom( String titreProjet, String nomPorteur );

    /**
     * M���thode de recherche de projet par categorie et porteur
     * 
     * @param categorie
     * @param nomPorteur
     * @return liste des projets correspondants aux infos renseign���es
     */
    List<TousLesProjetsDTO> rechercherParCatgeorieNom( String categorie, String nomPorteur );

    /**
     * M���thode de recherche de projet par titre, cat���gorie et
     * porteur
     * 
     * @param titreProjet
     * @param categorie
     * @param nomPorteur
     * @return liste des projets correspondants aux infos renseign���es
     */
    List<TousLesProjetsDTO> rechercherParTitreCatgeorieNom( String titreProjet, String categorie, String nomPorteur );

    /****************/
    // Categories //
    /****************/

    /**
     * M���thode de chargement du nom des cat���gories
     * 
     * @return la liste des noms des cat���gories
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
    // Projets //
    /****************/

    /**
     * Recuperer une liste contenant tous les projets de la BDD
     * 
     * @return ArrayList de tous les projets
     */
    List<TousLesProjetsDTO> recupererTousLesProjets();

    /**
     * R���cup���rer une liste contenant les titres de tous les
     * projets
     * 
     * @return ArrayList de tous les projets
     */
    List<String> recupererTousLesTitresDesProjets();

    /**
     * Recuperer une liste contenant tous les projets en avant de la BDD
     * 
     * @return ArrayList de tous les projets en avant
     */
    List<TousLesProjetsDTO> recupererProjetsEnAvant();

    /**
     * Trouver un projet par son id
     * 
     * @param idProjet
     * @return
     */
    ProjetDTO findProjetDTOById( int idProjet );

    /**
     * Fonction d'abandon d'un projet, rembourse tous les financeurs
     * 
     * @param idProjet
     *            projet ��� cloturer
     * @param admin
     *            l'utilisateur est il administrateur?
     */
    void cloturerProjet( int idProjet, boolean admin );

    /**
     * Trouver les projets les plus r�cents
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsRecents( int nbProjet );

    /**
     * Trouver les projets presque financ�s
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsPresqueFinances( int nbProjet );

    /**
     * Trouver les projets les plus financ�s
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsPlusFinances( int nbProjet );

    /**
     * Trouver les projets les plus aim�s
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsPlusAimes( int nbProjet );

    /**
     * R�cup�re tous les projets de la BDD dont la date de fin est sup�rieure �
     * la date actuelle
     * 
     * @return
     */
    List<TousLesProjetsDTO> recupererTousLesProjetsEnCours();

    /**
     * Trouver les nombre de likes
     * 
     * @param idProjet
     * @return
     */
    int nombreAimeProjet( int idProjet );

    /****************/
    // Membres //
    /****************/

    /**
     * Connexion, renvoie null si l'utilisateur n'existe pas Renvoie un
     * Administrateur ou un FinanceurPorteur selon l'utilisateur
     * 
     * @return AUtilisateur Administrateur ou FinanceurPorteur
     */
    AUtilisateurDTO connexion( String login, String mdp );

    /**
     * Recuperer tous les noms des financeurs porteurs dans une liste
     * 
     * @return une array liste contenant tous les noms de financeurs porteurs
     */
    List<String> recupererTousLesNomsDeFinanceurs();

    /**
     * Recuperer la liste des projets finances par un porteur
     * 
     * @param loginFinanceur
     *            du porteur
     * @return la liste des projets du porteur
     */
    List<TousLesProjetsDTO> recupererMesProjets( String loginFinanceur );

    /**
     * Trouver un financeur par son login
     * 
     * @param login
     * @return
     */
    FinanceurPorteurDTO findFinanceurDTOByLogin( String login );

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
     * Un utilisateur envoie un messge (commentaire) sur un projet
     * 
     * @param nomEmetteur
     *            Nom de l'utilisateur
     * @param idProjet
     *            id du projet
     * @param contenuMessage
     *            contenu du message
     */
    void envoyerMessage( String nomEmetteur, int idProjet, String contenuMessage );

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
     * @param idProjet
     * @param admin
     */
    void supprimerMessage( int idMessage, int idConversation, int idProjet, boolean admin );

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