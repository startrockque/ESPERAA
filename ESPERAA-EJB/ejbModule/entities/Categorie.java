package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {

    @Id
    @GeneratedValue
    private int          idCategorie;
    private String       titreCategorie;

    @OneToMany( mappedBy = "categorie" )
    private List<Cheval> chevauxList;

    /* ************ *
     * Constructeur *
     * ************ *
     */

    public Categorie() {

    }

    public Categorie( int idCategorie, String titreCategorie, List<Cheval> chevauxList ) {
        this.idCategorie = idCategorie;
        this.titreCategorie = titreCategorie;
        this.chevauxList = chevauxList;
    }

    /* **************** *
     * Getter et Setter * 
     * **************** *
     */

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

    public List<Cheval> getChevauxList() {
        return chevauxList;
    }

    public void setChevauxList( List<Cheval> chevauxList ) {
        this.chevauxList = chevauxList;
    }

    @Override
    public String toString() {
        return "Categorie [titreCategorie=" + titreCategorie + "]";
    }
}
