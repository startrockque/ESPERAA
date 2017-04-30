package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Cheval;
import entities.Tag;

public class TousLesChevauxDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idProjet;
    private String            titreProjet;
    private float             montantDemande;
    private String            butArgent;
    private float             montantInvesti;
    private boolean           enAvant;
    private String            categorie;
    private List<String>      tagList;
    private String            image;

    public TousLesChevauxDTO( int idProjet, String titreProjet,
            float montantDemande, String butArgent, float montantInvesti, boolean enAvant,
            String categorie, List<String> tagList ) {
        super();
        this.idProjet = idProjet;
        this.titreProjet = titreProjet;
        this.montantDemande = montantDemande;
        this.butArgent = butArgent;
        this.montantInvesti = montantInvesti;
        this.enAvant = enAvant;
        this.categorie = categorie;
        this.tagList = tagList;

    }

    public TousLesChevauxDTO( Cheval cheval ) {
        this.idProjet = cheval.getIdCheval();
        this.titreProjet = cheval.getNomCheval();
        this.montantDemande = cheval.getMontantDemande();
        this.butArgent = cheval.getButArgent();
        this.montantInvesti = cheval.getMontantInvesti();
        this.enAvant = cheval.isEnAvant();
        this.categorie = cheval.getCategorie().getTitreCategorie();
        this.image = cheval.getImage();
        List<String> tagNameList = new ArrayList<>();
        for ( Tag tag : cheval.getTagList() ) {
            tagNameList.add( tag.getTag() );
        }
        this.tagList = tagNameList;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet( int idProjet ) {
        this.idProjet = idProjet;
    }

    public String getTitreProjet() {
        return titreProjet;
    }

    public void setTitreProjet( String titreProjet ) {
        this.titreProjet = titreProjet;
    }

    public float getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande( float montantDemande ) {
        this.montantDemande = montantDemande;
    }

    public String getButArgent() {
        return butArgent;
    }

    public void setButArgent( String butArgent ) {
        this.butArgent = butArgent;
    }

    public float getMontantInvesti() {
        return montantInvesti;
    }

    public void setMontantInvesti( float montantInvesti ) {
        this.montantInvesti = montantInvesti;
    }

    public boolean isEnAvant() {
        return enAvant;
    }

    public void setEnAvant( boolean enAvant ) {
        this.enAvant = enAvant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie( String categorie ) {
        this.categorie = categorie;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList( List<String> tagList ) {
        this.tagList = tagList;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }
}