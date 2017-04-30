package dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import entities.Aime;
import entities.Conversation;
import entities.Projet;
import entities.Tag;
import entities.Tranche;

public class ProjetDTO implements Serializable {
    private static final long     serialVersionUID = 1L;

    private int                   idProjet;
    private String                titreProjet;
    private String                description;
    private int                   montantDemande;
    private String                butArgent;
    private Calendar              dateCreation;
    private Calendar              dateFin;
    private int                   montantInvesti;
    private List<TrancheDTO>      trancheList;
    private List<ConversationDTO> conversationList;
    private FinanceurPorteurDTO   porteur;
    private String                categorie;
    private List<String>          tagList;
    private List<AimeDTO>         aimeList;
    private String                image;
    private boolean               enAvant;

    public ProjetDTO( Projet projet ) {
        this.idProjet = projet.getIdProjet();
        this.titreProjet = projet.getTitreProjet();
        this.description = projet.getDescription();
        this.montantDemande = projet.getMontantDemande();
        this.butArgent = projet.getButArgent();
        this.dateCreation = projet.getDateCreation();
        this.dateFin = projet.getDateFin();
        this.montantInvesti = projet.getMontantInvesti();
        this.image = projet.getImage();
        this.trancheList = new ArrayList<>();
        for ( Tranche tranche : projet.getTrancheList() ) {
            this.trancheList.add( new TrancheDTO( tranche ) );
        }
        Collections.sort( this.trancheList );
        this.conversationList = new ArrayList<ConversationDTO>();
        for ( Conversation conv : projet.getConversationList() ) {
            conversationList.add( new ConversationDTO( conv ) );
        }
        this.aimeList = new ArrayList<AimeDTO>();
        for ( Aime aime : projet.getAimeList() ) {
            aimeList.add( new AimeDTO( aime ) );
        }
        this.porteur = new FinanceurPorteurDTO( projet.getPorteur() );
        this.categorie = projet.getCategorie().getTitreCategorie();
        this.tagList = new ArrayList<String>();
        for ( Tag t : projet.getTagList() ) {
            this.tagList.add( t.getTag() );
        }
        this.enAvant = projet.isEnAvant();
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

    public Calendar getDateFin() {
        return dateFin;
    }

    public void setDateFin( Calendar dateFin ) {
        this.dateFin = dateFin;
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

    public FinanceurPorteurDTO getPorteur() {
        return porteur;
    }

    public void setPorteur( FinanceurPorteurDTO porteur ) {
        this.porteur = porteur;
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

    public List<AimeDTO> getAimeList() {
        return aimeList;
    }

    public void setAimeList( List<AimeDTO> aimeList ) {
        this.aimeList = aimeList;
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

    /**
     * Formate la date de fin du projet en un format compréhensible
     * 
     * @return
     */
    public String getFrenchDate() {
        SimpleDateFormat formater = new SimpleDateFormat( "dd-MM-yyyy" );
        return formater.format( dateFin.getTime() );
    }

    public boolean isEnCours() {
        return dateFin.after( Calendar.getInstance() );
    }

    @Override
    public boolean equals( Object obj ) {
        ProjetDTO p = (ProjetDTO) obj;
        return p.getIdProjet() == this.idProjet;
    }
}
