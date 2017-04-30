package facade;

import java.util.List;

import dao.DAOException;
import dto.DonateurMinDTO;
import dto.TrancheDTO;

public interface IAdministrateurFacade {

    /****************/
    // Catégorie //
    /****************/

    /**
     * Creation d'une categorie
     * 
     * @param titreCategorie
     *            le titre de la catÃ©gorie
     */
    void creerCategorie( String titreCategorie );

    /**
     * Recuperer une categorie par son nom
     * 
     * @param nomCategorie
     * @return
     */
    boolean verifierCategorie( String nomCategorie );

    /**
     * Récupère le nombre de catégories
     * 
     * @return le nombre de catégories
     */
    int recupererNbCategories();

    /****************/
    // Cheval //
    /****************/

    /**
     * Mise en avant d'un cheval
     * 
     * @param idCheval
     */
    void mettreEnAvant( int idCheval );

    /**
     * Mise en arrière d'un cheval
     * 
     * @param idCheval
     */
    void mettreEnArriere( int idCheval );

    /**
     * Récupère le nombre de chevaux
     * 
     * @return le nombre de chevaux
     */
    int recupererNbChevaux();
    
    /**
     * Ajoute un cheval à la BDD
     * 
     * @param nom
     * @param description
     * @param butArgent
     * @param montantDemande
     * @param titreCategorie
     * @param tagString
     * @param trancheDtoList
     * @param image
     */
    void ajouterCheval( String nom, String description, String butArgent,
            int montantDemande, String titreCategorie, String tagString,
            List<TrancheDTO> trancheDTOList, String image );

    /**
     * Modifier les attributs d'un cheval
     * 
     * @param idCheval
     * @param nom
     * @param description
     * @param butArgent
     * @param montantDemande
     * @param titreCategorie
     * @param tagString
     * @param trancheDTOList
     * @param image
     */
    void modifierCheval( int idCheval, String nom, String description,
            String butArgent, int montantDemande, String titreCategorie,
            String tagString, List<TrancheDTO> trancheDTOList, String image );

    /**
     * Retirer un cheval
     * 
     * @param idCheval
     */
    void supprimerCheval( int idCheval );

    /****************/
    // Membres //
    /****************/

    /**
     * Récupère la liste de tous les membres
     * 
     * @return
     * @throws DAOException
     */
    List<DonateurMinDTO> recupererTousLesMembres();

    /****************/
    // Compteurs //
    /****************/

    /**
     * Récupère le nombre de membres
     * 
     * @return le nombre de membres
     */
    int recupererNbMembres();

    /**
     * Récupère le nombre de messages
     * 
     * @return le nombre de messages
     */
    int recupererNbMessages();

    /**
     * Récupère le total d'investissements
     * 
     * @return le total de investissements
     */
    int recupererTotalInvestissements();

    /**
     * Récupère le nombre de tags
     * 
     * @return le nombre de tags
     */
    int recupererNbTags();
}
