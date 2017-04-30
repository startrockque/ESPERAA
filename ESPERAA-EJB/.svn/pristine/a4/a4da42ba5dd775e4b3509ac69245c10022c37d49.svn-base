package dto;

import java.io.Serializable;
import java.util.Calendar;

import entities.Notification;

public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idNotification;
    private Calendar          dateNotification;
    private String            notification;
    private String            emetteur;
    private boolean           notificationVue;

    public NotificationDTO() {

    }

    public NotificationDTO( Notification notification ) {
        this.idNotification = notification.getIdNotification();
        this.dateNotification = notification.getDateNotification();
        this.notification = notification.getNotification();
        this.notificationVue = notification.isNotificationVue();
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification( int idNotification ) {
        this.idNotification = idNotification;
    }

    public Calendar getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification( Calendar dateNotification ) {
        this.dateNotification = dateNotification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification( String notification ) {
        this.notification = notification;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setEmetteur( String emetteur ) {
        this.emetteur = emetteur;
    }

    public boolean isNotificationVue() {
        return notificationVue;
    }

    public void setNotificationVue( boolean notificationVue ) {
        this.notificationVue = notificationVue;
    }
}