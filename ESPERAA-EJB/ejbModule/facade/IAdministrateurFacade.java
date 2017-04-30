package facade;

import java.util.List;

import dao.DAOException;
import dto.DonateurMinDTO;
import dto.TrancheDTO;

public interface IAdministrateurFacade {

    /****************/
    // Cat�gorie //
    /****************/

    /**
     * Creation d'une categorie
     * 
     * @param titreCategorie
     *            le titre de la catégorie
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
     * R�cup�re le nombre de cat�gories
     * 
     * @return le nombre de cat�gories
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
     * Mise en arri�re d'un cheval
     * 
     * @param idCheval
     */
    void mettreEnArriere( int idCheval );

    /**
     * R�cup�re le nombre de chevaux
     * 
     * @return le nombre de chevaux
     */
    int recupererNbChevaux();
    
    /**
     * Ajoute un cheval � la BDD
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
     * R�cup�re la liste de tous les membres
     * 
     * @return
     * @throws DAOException
     */
    List<DonateurMinDTO> recupererTousLesMembres();

    /****************/
    // Compteurs //
    /****************/

    /**
     * R�cup�re le nombre de membres
     * 
     * @return le nombre de membres
     */
    int recupererNbMembres();

    /**
     * R�cup�re le nombre de messages
     * 
     * @return le nombre de messages
     */
    int recupererNbMessages();

    /**
     * R�cup�re le total d'investissements
     * 
     * @return le total de investissements
     */
    int recupererTotalInvestissements();

    /**
     * R�cup�re le nombre de tags
     * 
     * @return le nombre de tags
     */
    int recupererNbTags();
}
