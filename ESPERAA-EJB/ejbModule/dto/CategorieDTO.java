package dto;

import java.io.Serializable;

import entities.Categorie;

public class CategorieDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idCategorie;
    private String            titreCategorie;
    private int               nbChevaux;

    public CategorieDTO( Categorie categorie ) {
        this.idCategorie = categorie.getIdCategorie();
        this.titreCategorie = categorie.getTitreCategorie();
        this.nbChevaux = categorie.getChevauxList().size();
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie( int idCategorie ) {
        this.idCategorie = idCategorie;
    }

    public String getTitreCategorie() {
        return titreCategorie;
    }

    public void setTitreCategorie( String titreCategorie ) {
        this.titreCategorie = titreCategorie;
    }

    public int getNbChevaux() {
        return nbChevaux;
    }

    public void setNbChevaux( int nbChevaux ) {
        this.nbChevaux = nbChevaux;
    }
}
