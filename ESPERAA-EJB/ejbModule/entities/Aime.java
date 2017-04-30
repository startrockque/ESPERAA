package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Aime {

    @Id
    @GeneratedValue
    private int              idAime;

    @ManyToOne
    private FinanceurPorteur financeur;

    @ManyToOne
    private Projet           projet;

    public Aime() {
        super();
    }

    public Aime( FinanceurPorteur financeur, Projet projet ) {
        super();
        this.financeur = financeur;
        this.projet = projet;
    }

    public FinanceurPorteur getFinanceur() {
        return financeur;
    }

    public void setFinanceur( FinanceurPorteur financeur ) {
        this.financeur = financeur;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet( Projet projet ) {
        this.projet = projet;
    }

    public int getIdAime() {
        return idAime;
    }

    public void setIdAime( int idAime ) {
        this.idAime = idAime;
    }
}
