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
     * Rï¿½ï¿½ï¿½cupï¿½ï¿½ï¿½re le projet ï¿½ï¿½ï¿½ partir de son id
     * 
     * @param idProjet
     *            l'id du projet
     * @return le projet ayant un id ï¿½ï¿½ï¿½gal ï¿½ï¿½ï¿½ idprojet
     */
    ProjetDTO findProjetById( int idProjet );

    /**
     * Mï¿½ï¿½ï¿½thode de recherche d'un projet par son titre
     * 
     * @param titreProjet
     * @return la liste des projets correspondants au titre
     *         renseignï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     */
    List<TousLesProjetsDTO> rechercherParTitre( String titreProjet );

    /**
     * Mï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½thode de recherche de projets par
     * catï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½gorie
     * 
     * @param categorie
     * @return la liste des projets correspondants ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
     *         la catï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½gorie
     *         renseignï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½e
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
     * Mï¿½ï¿½ï¿½thode de recherche de projets par porteur
     * 
     * @param nomPorteur
     * @returnla liste des projets correspondants au nom de porteur
     *           renseignï¿½ï¿½ï¿½
     */
    List<TousLesProjetsDTO> rechercherParPorteur( String nomPorteur );

    /**
     * Mï¿½ï¿½ï¿½thode de recherche de projet par titre et catï¿½ï¿½ï¿½gorie
     * 
     * @param titreProjet
     * @param categorie
     * @return liste des projets correspondants aux infos renseignï¿½ï¿½ï¿½es
     */
    List<TousLesProjetsDTO> rechercherParTitreCatgeorie( String titreProjet, String categorie );

    /**
     * Mï¿½ï¿½ï¿½thode de recherche de projet par titre et nom
     * 
     * @param titreProjet
     * @param nomPorteur
     * @return liste des projets correspondants aux infos renseignï¿½ï¿½ï¿½es
     */
    List<TousLesProjetsDTO> rechercherParTitreNom( String titreProjet, String nomPorteur );

    /**
     * Mï¿½ï¿½ï¿½thode de recherche de projet par categorie et porteur
     * 
     * @param categorie
     * @param nomPorteur
     * @return liste des projets correspondants aux infos renseignï¿½ï¿½ï¿½es
     */
    List<TousLesProjetsDTO> rechercherParCatgeorieNom( String categorie, String nomPorteur );

    /**
     * Mï¿½ï¿½ï¿½thode de recherche de projet par titre, catï¿½ï¿½ï¿½gorie et
     * porteur
     * 
     * @param titreProjet
     * @param categorie
     * @param nomPorteur
     * @return liste des projets correspondants aux infos renseignï¿½ï¿½ï¿½es
     */
    List<TousLesProjetsDTO> rechercherParTitreCatgeorieNom( String titreProjet, String categorie, String nomPorteur );

    /****************/
    // Categories //
    /****************/

    /**
     * Mï¿½ï¿½ï¿½thode de chargement du nom des catï¿½ï¿½ï¿½gories
     * 
     * @return la liste des noms des catï¿½ï¿½ï¿½gories
     */
    List<String> getAllCategoriesNames();

    /**
     * Trouver toutes les Categories
     * 
     * @return
     */
    List<CategorieDTO> findAllCategorieDto();

    /**
     * Trouver les catégories les plus populaires
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
     * Rï¿½ï¿½ï¿½cupï¿½ï¿½ï¿½rer une liste contenant les titres de tous les
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
     *            projet ï¿½ï¿½ï¿½ cloturer
     * @param admin
     *            l'utilisateur est il administrateur?
     */
    void cloturerProjet( int idProjet, boolean admin );

    /**
     * Trouver les projets les plus récents
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsRecents( int nbProjet );

    /**
     * Trouver les projets presque financés
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsPresqueFinances( int nbProjet );

    /**
     * Trouver les projets les plus financés
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsPlusFinances( int nbProjet );

    /**
     * Trouver les projets les plus aimés
     * 
     * @param nbProjet
     * @return
     */
    List<TousLesProjetsDTO> recupererProjetsPlusAimes( int nbProjet );

    /**
     * Récupère tous les projets de la BDD dont la date de fin est supérieure à
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
     * Trouver les tags les plus utilisés
     * 
     * @param nbTag
     * @return
     */
    List<String> listeTagPopulaire( int nbTag );
}