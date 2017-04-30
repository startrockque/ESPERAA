package facade;

import java.util.List;

import javax.ejb.Local;

import dto.FinanceurPorteurDTO;
import dto.InvestissementDTO;
import dto.NotificationDTO;
import dto.TrancheDTO;

@Local
public interface IFinanceurPorteurFacade {

    /****************/
    // Membres //
    /****************/

    /**
     * Methode d'inscription d'un Financeur Porteur
     * 
     * @param FinanceurPorteur
     */
    void inscription( String nom, String login, String mdp, String email, String image );

    /**
     * Verifier le mdp de l'utilisateur
     * 
     * @param loginFinanceur
     * @param mdp
     * @return
     */
    boolean verifierMdpFinanceur( String loginFinanceur, String mdp );

    /**
     * Modifier le financeur porteur
     * 
     * @param ancienLoginFinanceur
     * @param financeur
     * @return
     */
    void modifierFinanceurPorteur( FinanceurPorteurDTO financeur );

    /**
     * Rï¿½cupï¿½rer toutes les notifications d'un FinanceurPorteur
     * 
     * @param loginFinanceur
     * @return
     */
    List<NotificationDTO> recupererNotificationParLoginFinanceur( String loginFinanceur );

    /**
     * Toutes les notifications du financeur sont vues
     * 
     * @param loginFinanceur
     */
    void viderNotification( String loginFinanceur );

    /****************/
    // Argent //
    /****************/

    /**
     * Le financeur remet de l'argent sur son compte
     * 
     * @param loginMembre
     *            login du financeur
     * @param montantACrediter
     *            montant que le financeur veut ajouter a son compte
     * @return le nouveau solde du compte
     */
    void alimenterPortefeuille( String loginMembre, int montantACrediter );

    /**
     * Un financeur investie de l'argent sur un projet
     * 
     * @param monLogin
     *            nom du financeur
     * @param idProjetAInvestir
     *            id du projet
     * @param montantAInvestir
     *            somme a investir
     */
    void financerProjet( String monLogin, int idProjetAInvestir, int montantAInvestir );

    /**
     * Recuperer la liste des investissements
     * 
     * @param loginFinanceurlogin
     *            du financeur
     * @return
     */
    List<InvestissementDTO> recupererInvesissementParFinanceur(
            String loginFinanceur );

    /****************/
    // Projets //
    /****************/

    /**
     * Permet de savoir si le titre du projet existe dï¿½jï¿½ ou non.
     * 
     * @param titreProjet
     * @return booleen
     */
    boolean verifierTitreProjet( String titreProjet );

    /**
     * Permet de vérifier que le nouveau nom à la modification n'existe pas déjà
     * 
     * @param titre
     * @param idProjet
     * @return
     */
    boolean verifierTitreProjet( String titre, int idProjet );

    /**
     * Ajoute un projet à la BDD
     * 
     * @param titre
     * @param description
     * @param butArgent
     * @param montantDemande
     * @param titreCategorie
     * @param tagString
     * @param dateFin
     * @param loginPorteur
     * @param trancheDtoList
     * @param image
     */
    void ajouterProjet( String titre, String description, String butArgent,
            int montantDemande, String titreCategorie, String tagString,
            String dateFin, String loginPorteur, List<TrancheDTO> trancheDtoList, String image );

    /**
     * Modifier les attributs d'un projet
     * 
     * @param idProjet
     * @param titre
     * @param description
     * @param butArgent
     * @param montantDemande
     * @param titreCategorie
     * @param tagString
     * @param trancheList
     * @param image
     */
    void modifierProjet( int idProjet, String titre, String description,
            String butArgent, int montantDemande, String titreCategorie,
            String tagString, List<TrancheDTO> trancheList, String image );

    /**
     * Terminer un projet
     * 
     * @param idProjet
     */
    void cloturerProjet( int idProjet );

    /**
     * Ne plus aimer un projet
     * 
     * @param idProjet
     * @param loginFinanceur
     */
    void nePlusAimerProjet( int idProjet, String loginFinanceur );

    /**
     * Aimer un projet
     * 
     * @param idProjet
     * @param loginPorteur
     */
    void aimerProjet( int idProjet, String loginPorteur );
}