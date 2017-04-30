package facade;

import java.util.List;

import javax.ejb.Local;

import dto.DonateurDTO;
import dto.InvestissementDTO;
import dto.NotificationDTO;

@Local
public interface IDonateurFacade {

    /****************/
    // Membres //
    /****************/

    /**
     * Methode d'inscription d'un Donateur
     * 
     * @param Donateur
     */
    void inscription( String nom, String login, String mdp, String email, String image );

    /**
     * Verifier le mdp de l'utilisateur
     * 
     * @param loginDonateur
     * @param mdp
     * @return
     */
    boolean verifierMdpDonateur( String loginDonateur, String mdp );

    /**
     * Modifier le Donateur
     * 
     * @param donateur
     * @return
     */
    void modifierDonateur( DonateurDTO donateur );

    /**
     * Récupérer toutes les notifications d'un Donateur
     * 
     * @param loginDonateur
     * @return
     */
    List<NotificationDTO> recupererNotificationParLoginDonateur( String loginDonateur );

    /**
     * Toutes les notifications du Donateur sont vues
     * 
     * @param loginDonateur
     */
    void viderNotification( String loginDonateur );

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
   // void alimenterPortefeuille( String loginMembre, int montantACrediter );

    /**
     * Un Donateur investie de l'argent sur un cheval
     * 
     * @param monLogin
     *            nom du Donateur
     * @param idChevalAInvestir
     *            id du cheval
     * @param montantAInvestir
     *            somme a investir
     */
    void financerCheval( String monLogin, int idChevalAInvestir, int montantAInvestir );

    /**
     * Recuperer la liste des investissements
     * 
     * @param loginDonateurlogin
     *            du Donateur
     * @return
     */
    List<InvestissementDTO> recupererInvestissementParDonateur(
            String loginDonateur);

}