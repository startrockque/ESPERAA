package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Notification {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private int          idNotification;

    @ManyToOne
    private AUtilisateur destinataire;
    @Column( columnDefinition = "TEXT" )
    private String       notification;
    private Calendar     dateNotification;
    private boolean      notificationVue;

    public Notification( String notification, AUtilisateur destinataire ) {
        this.destinataire = destinataire;
        this.notification = notification;
        this.dateNotification = Calendar.getInstance();
        this.notificationVue = false;
    }

    public Notification( String notification ) {
        this.notification = notification;
        this.dateNotification = Calendar.getInstance();
        this.notificationVue = false;
    }

    public Notification() {
        this.dateNotification = Calendar.getInstance();
        this.notificationVue = false;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification( int idNotification ) {
        this.idNotification = idNotification;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification( String notification ) {
        this.notification = notification;
    }

    public Calendar getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification( Calendar dateNotification ) {
        this.dateNotification = dateNotification;
    }

    public String getFrenchDate() {
        SimpleDateFormat formater = new SimpleDateFormat( "dd-MM-yyyy" );
        return formater.format( dateNotification.getTime() );
    }

    public boolean isNotificationVue() {
        return notificationVue;
    }

    public void setNotificationVue( boolean notificationVue ) {
        this.notificationVue = notificationVue;
    }

    public AUtilisateur getDestinataire() {
        return destinataire;
    }

    public void setDestinataire( Donateur destinataire ) {
        this.destinataire = destinataire;
    }
}