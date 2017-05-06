package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Cheval;
import entities.Tag;

public class TousLesChevauxDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idCheval;
    private String            nomCheval;
    private float             montantDemande;
    private String            butArgent;
    private float             montantInvesti;
    private boolean           enAvant;
    private String            categorie;
    private List<String>      tagList;
    private String            image;

    public TousLesChevauxDTO( int idCheval, String nomCheval,
            float montantDemande, String butArgent, float montantInvesti, boolean enAvant,
            String categorie, List<String> tagList ) {
        super();
        this.idCheval = idCheval;
        this.nomCheval = nomCheval;
        this.montantDemande = montantDemande;
        this.butArgent = butArgent;
        this.montantInvesti = montantInvesti;
        this.enAvant = enAvant;
        this.categorie = categorie;
        this.tagList = tagList;

    }

    public TousLesChevauxDTO( Cheval cheval ) {
        this.idCheval = cheval.getIdCheval();
        this.nomCheval = cheval.getNomCheval();
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

    public int getIdCheval() {
        return idCheval;
    }

    public void setIdCheval( int idCheval ) {
        this.idCheval = idCheval;
    }

    public String getNomCheval() {
        return nomCheval;
    }

    public void setNomCheval( String nomCheval ) {
        this.nomCheval = nomCheval;
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