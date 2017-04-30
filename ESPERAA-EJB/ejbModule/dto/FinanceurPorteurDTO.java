package dto;

import java.util.ArrayList;
import java.util.List;

import entities.Aime;
import entities.FinanceurPorteur;
import entities.Notification;

public class FinanceurPorteurDTO extends AUtilisateurDTO {
    private static final long     serialVersionUID = 1L;

    private int                   montantAInvestir;
    private List<NotificationDTO> notificationList;

    private List<AimeDTO>         aimeList;

    public FinanceurPorteurDTO() {
        super();
        notificationList = new ArrayList<NotificationDTO>();
        aimeList = new ArrayList<AimeDTO>();
    }

    public FinanceurPorteurDTO( FinanceurPorteur fp ) {
        this.login = fp.getLogin();
        this.nom = fp.getNom();
        this.mdp = fp.getMdp();
        this.email = fp.getEmail();
        this.montantAInvestir = fp.getMontantAInvestir();
        notificationList = new ArrayList<NotificationDTO>();
        for ( Notification notification : fp.getNotificationList() ) {
            notificationList.add( new NotificationDTO( notification ) );
        }
        this.aimeList = new ArrayList<AimeDTO>();
        for ( Aime aime : fp.getAimeList() ) {
            this.aimeList.add( new AimeDTO( aime ) );
        }
        this.image = fp.getImage();
    }

    public int getMontantAInvestir() {
        return montantAInvestir;
    }

    public void setMontantAInvestir( int montantAInvestir ) {
        this.montantAInvestir = montantAInvestir;
    }

    public List<NotificationDTO> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList( List<NotificationDTO> notificationList ) {
        this.notificationList = notificationList;
    }

    public List<AimeDTO> getAimeList() {
        return aimeList;
    }

    public void setAimeList( List<AimeDTO> aimeList ) {
        this.aimeList = aimeList;
    }

    /**
     * Donne le nombre de like du membre
     * 
     * @return
     */
    public int getNbAime() {
        return aimeList.size();
    }

    public boolean aimeProjet( TousLesProjetsDTO projet ) {
        for ( AimeDTO aime : this.aimeList ) {
            if ( aime.getIdProjet() == projet.getIdProjet() )
                return true;
        }
        return false;
    }

}
