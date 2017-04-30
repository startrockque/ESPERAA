package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Investissement {

    @Id
    @GeneratedValue
    private int              idInvestissement;
    private int              sommeInvestie;
    @ManyToOne
    private Donateur donateur;
    @ManyToOne
    private Tranche          tranche;

    /* ************* *
     * Constructeur * *************
     */

    public Investissement() {

    }

    public Investissement( int sommeInvestie, Donateur financeur ) {
        this.sommeInvestie = sommeInvestie;
        this.donateur = financeur;
    }

    /* **************** *
     * Getter et Setter *
     * **************** *
     */

    public int getIdInvestissement() {
        return idInvestissement;
    }

    public void setIdInvestissement( int idInvestissement ) {
        this.idInvestissement = idInvestissement;
    }

    public int getSommeInvestie() {
        return sommeInvestie;
    }

    public void setSommeInvestie( int sommeInvestie ) {
        this.sommeInvestie = sommeInvestie;
    }

    public Donateur getDonateur() {
        return donateur;
    }

    public void setDonateur( Donateur donateur ) {
        this.donateur = donateur;
    }

    public Tranche getTranche() {
        return tranche;
    }

    public void setTranche( Tranche tranche ) {
        this.tranche = tranche;
    }
}
