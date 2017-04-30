package entities;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message implements Comparable<Message> {
    @Id
    @GeneratedValue
    private int          idMessage;
    private Calendar     dateMessage;
    @Column( columnDefinition = "TEXT" )
    private String       message;

    @ManyToOne
    private AUtilisateur emetteur;

    /* ************* *
     * Constructeur * *************
     */

    public Message() {
        dateMessage = Calendar.getInstance();
    }

    public Message( String message, AUtilisateur emetteur ) {
        this.dateMessage = Calendar.getInstance();
        ;
        this.message = message;
        this.emetteur = emetteur;
    }

    /* ***************** *
     * Getter et Setter * *****************
     */

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage( int idMessage ) {
        this.idMessage = idMessage;
    }

    public Calendar getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage( Calendar date ) {
        this.dateMessage = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public AUtilisateur getEmetteur() {
        return emetteur;
    }

    public void setEmetteur( FinanceurPorteur emetteur ) {
        this.emetteur = emetteur;
    }

    public String getFrenchDate() {
        SimpleDateFormat formater = new SimpleDateFormat( "dd-MM-yyyy" );
        return formater.format( dateMessage.getTime() );
    }

    @Override
    public int compareTo( Message o ) {
        return this.dateMessage.compareTo( o.getDateMessage() );
    }

    @Override
    public boolean equals( Object obj ) {
        Message message = (Message) obj;
        return message.getIdMessage() == this.idMessage;
    }
}