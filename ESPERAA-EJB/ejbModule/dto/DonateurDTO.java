package dto;

import java.util.ArrayList;
import java.util.List;

import entities.Donateur;
import entities.Notification;

public class DonateurDTO extends AUtilisateurDTO {
    private static final long     serialVersionUID = 1L;

    private int                   montantAInvestir;
    private List<NotificationDTO> notificationList;


    public DonateurDTO() {
        super();
        notificationList = new ArrayList<NotificationDTO>();
    }

    public DonateurDTO( Donateur fp ) {
        this.login = fp.getLogin();
        this.nom = fp.getNom();
        this.mdp = fp.getMdp();
        this.email = fp.getEmail();
        this.montantAInvestir = fp.getMontantAInvestir();
        notificationList = new ArrayList<NotificationDTO>();
        for ( Notification notification : fp.getNotificationList() ) {
            notificationList.add( new NotificationDTO( notification ) );
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
}