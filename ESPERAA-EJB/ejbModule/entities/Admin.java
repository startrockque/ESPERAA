package entities;

import javax.persistence.Entity;

/**
 * Administrateur du site
 * 
 * @author Fabien
 * @see AUtilisateur
 */
@Entity
public class Admin extends AUtilisateur {

    /* ************* *
     * Constructeur *************
     */

    public Admin() {
        super();
    }

    public Admin( String nom, String login, String mdp, String email ) {
        super( nom, login, mdp, email );
    }
}