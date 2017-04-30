package entities;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class Donateur extends AUtilisateur {

    private int        montantAInvestir;


    /* ************ *
     * Constructeur *
     * ************ *
     */

    public Donateur() {
        super();
        montantAInvestir = 0;
        notificationList = new ArrayList<Notification>();
    }

    public Donateur( String nom, String login, String mdp, String email, String image ) {
        super( nom, login, mdp, email, image );
        montantAInvestir = 0;
        notificationList = new ArrayList<Notification>();
    }

    public void alimenterPortefeuille( int montantACrediter ) {
        Integer sommeInteger = this.montantAInvestir + montantACrediter;
        if ( sommeInteger < 0 )
            sommeInteger = Integer.MAX_VALUE;
        this.montantAInvestir = sommeInteger;
    }


    /* ***************** *
     * Getter et Setter * *****************
     */

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public int getMontantAInvestir() {
        return montantAInvestir;
    }

    public void setMontantAInvestir( int montantAInvestir ) {
        this.montantAInvestir = montantAInvestir;
    }
}