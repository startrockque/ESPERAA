package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import entities.Conversation;
import entities.Cheval;
import entities.Tag;
import entities.Tranche;

public class ChevalDTO implements Serializable {
    private static final long     serialVersionUID = 1L;

    private int                   idCheval;
    private String                nomCheval;
    private String                description;
    private int                   montantDemande;
    private String                butArgent;
    private Calendar              dateCreation;
    private int                   montantInvesti;
    private List<TrancheDTO>      trancheList;
    private List<ConversationDTO> conversationList;
    private String                categorie;
    private List<String>          tagList;
    private String                image;
    private boolean               enAvant;

    public ChevalDTO( Cheval cheval ) {
        this.idCheval = cheval.getIdCheval();
        this.nomCheval = cheval.getNomCheval();
        this.description = cheval.getDescription();
        this.montantDemande = cheval.getMontantDemande();
        this.butArgent = cheval.getButArgent();
        this.dateCreation = cheval.getDateCreation();
        this.montantInvesti = cheval.getMontantInvesti();
        this.image = cheval.getImage();
        this.trancheList = new ArrayList<>();
        for ( Tranche tranche : cheval.getTrancheList() ) {
            this.trancheList.add( new TrancheDTO( tranche ) );
        }
        Collections.sort( this.trancheList );
        this.conversationList = new ArrayList<ConversationDTO>();
        for ( Conversation conv : cheval.getConversationList() ) {
            conversationList.add( new ConversationDTO( conv ) );
        }
        this.categorie = cheval.getCategorie().getTitreCategorie();
        this.tagList = new ArrayList<String>();
        for ( Tag t : cheval.getTagList() ) {
            this.tagList.add( t.getTag() );
        }
        this.enAvant = cheval.isEnAvant();
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

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public int getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande( int montantDemande ) {
        this.montantDemande = montantDemande;
    }

    public String getButArgent() {
        return butArgent;
    }

    public void setButArgent( String butArgent ) {
        this.butArgent = butArgent;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation( Calendar dateCreation ) {
        this.dateCreation = dateCreation;
    }

    public int getMontantInvesti() {
        return montantInvesti;
    }

    public void setMontantInvesti( int montantInvesti ) {
        this.montantInvesti = montantInvesti;
    }

    public List<TrancheDTO> getTrancheList() {
        return trancheList;
    }

    public void setTrancheList( List<TrancheDTO> trancheList ) {
        this.trancheList = trancheList;
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

    public List<ConversationDTO> getConversationList() {
        return conversationList;
    }

    public void setConversationList( List<ConversationDTO> conversationList ) {
        this.conversationList = conversationList;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }

    public boolean isEnAvant() {
        return enAvant;
    }

    public void setEnAvant( boolean enAvant ) {
        this.enAvant = enAvant;
    }

    @Override
    public boolean equals( Object obj ) {
        ChevalDTO p = (ChevalDTO) obj;
        return p.getIdCheval() == this.idCheval;
    }
}
