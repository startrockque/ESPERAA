package facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.CategorieDao;
import dao.ChevalDAO;
import dao.DAOException;
import dao.DonateurDAO;
import dao.InvestissementDAO;
import dao.NotificationDAO;
import dao.TagDAO;
import dto.DonateurDTO;
import dto.InvestissementDTO;
import dto.NotificationDTO;
import entities.Cheval;
import entities.Donateur;
import entities.Investissement;
import entities.Notification;

@Stateless
public class DonateurFacade implements IDonateurFacade {

    // private static final String NOTIFICATION_PROJET_FINANCE     = " a investi dans le projet la somme de ";
    private static final String NOTIFICATION_CHEVAL_FINANCE_100 = "Le cheval a été entierement financé : ";

    @EJB
    private ChevalDAO           chevalDao;
    @EJB
    private CategorieDao        categorieDao;
    @EJB
    private DonateurDAO         donateurDao;
    @EJB
    private InvestissementDAO   investissementDao;
    @EJB
    private TagDAO              tagDao;
    @EJB
    private NotificationDAO     notificationDAO;

    /****************/
    // Membres //
    /****************/

    @Override
    public void inscription( String nom, String login, String mdp, String email, String image ) {
        try {
            Donateur utilisateurTemp = donateurDao.findByLogin( login );
            if ( utilisateurTemp == null ) {
                Donateur financeurAAjouter = new Donateur( nom, login, mdp, email, image );
                donateurDao.create( financeurAAjouter );
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifierMdpDonateur( String loginDonateur, String mdp ) {
        try {
            Donateur fPorteur = donateurDao.findByLogin( loginDonateur );
            return fPorteur.getMdp().equals( mdp );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void modifierDonateur( DonateurDTO membreDto ) {
        try {
            Donateur fp = donateurDao.findByLogin( membreDto.getLogin() );
            fp.setEmail( membreDto.getEmail() );
            fp.setMdp( membreDto.getMdp() );
            fp.setNom( membreDto.getNom() );
            fp.setImage( membreDto.getImage() );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NotificationDTO> recupererNotificationParLoginDonateur( String loginDonateur ) {
        List<NotificationDTO> listNotificationDTO = new ArrayList<NotificationDTO>();
        try {
            List<Notification> list = notificationDAO.findByDonateurLogin( loginDonateur );
            for ( Notification notification : list ) {
                listNotificationDTO.add( new NotificationDTO( notification ) );
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return listNotificationDTO;
    }

    @Override
    public void viderNotification( String loginDonateur ) {
        try {
            Donateur membre = donateurDao.findByLogin( loginDonateur );
            membre.viderNotifications();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    /****************/
    // Argent //
    /****************/

//    @Override
//    public void alimenterPortefeuille( String loginMembre, int montantACrediter ) {
//        try {
//            Donateur membre = donateurDao.findByLogin( loginMembre );
//            membre.alimenterPortefeuille( montantACrediter );
//        } catch ( DAOException e ) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void financerCheval( String loginDonateur, int idCheval, int sommeInvestie ) {
        try {
            Cheval cheval = chevalDao.findById( idCheval );
            boolean finance = cheval.getMontantDemande() <= cheval.getMontantInvesti();

            Donateur financeur = donateurDao.findByLogin( loginDonateur );
            cheval.investir( sommeInvestie, financeur );

            //String notificationInvestissement = financeur.getLogin()
                    //+ NOTIFICATION_PROJET_FINANCE
                    //+ sommeInvestie
                    //+ " (dans  " + cheval.getNomCheval() + ")";

            //TODO prévenir l'admin
            //cheval.getPorteur().addNotification( notificationInvestissement );

            // si le projet n'était pas financé à 100% avant l'investissement et qu'il l'est maintenant
            if ( !finance && cheval.getMontantDemande() <= cheval.getMontantInvesti() ) {
                String notificationProjetFinance = NOTIFICATION_CHEVAL_FINANCE_100
                        + cheval.getNomCheval();
                for ( Donateur investisseur : cheval.getDonateurList() ) {
                    investisseur.addNotification( notificationProjetFinance );
                }
                //TODO prévenir l'admin
                //cheval.getPorteur().addNotification( notificationProjetFinance );
            }

        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InvestissementDTO> recupererInvestissementParDonateur( String loginDonateur ) {
        try {
            List<InvestissementDTO> investissementDtoList = new ArrayList<InvestissementDTO>();
            List<Investissement> investissementList = investissementDao.findByDonateur( loginDonateur );
            for ( Investissement investissement : investissementList ) {
                investissementDtoList.add( new InvestissementDTO( investissement ) );
            }
            return investissementDtoList;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }
}