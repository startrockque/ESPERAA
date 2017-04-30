package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cheval {

	@Id
    @GeneratedValue
    private int                idCheval;
    private String             nomCheval;
    @Column( columnDefinition = "TEXT" )
    private String             description;
    @Column( columnDefinition = "TEXT" )
    private String             butArgent;
    private Calendar           dateCreation;
    private int                montantInvesti;
    private int                montantDemande;
    private boolean            enAvant;
    private String             image;

    @OneToMany( mappedBy = "cheval", cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    private List<Tranche>      trancheList;

    @OneToMany( mappedBy = "cheval", cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    private List<Conversation> conversationList;

    @ManyToOne
    private Categorie          categorie;

    @ManyToMany( mappedBy = "chevauxList", cascade = { CascadeType.PERSIST } )
    private List<Tag>          tagList;

    /* ************* 
     * Constructeur 
     * *************
     */

    public Cheval() {
        conversationList = new ArrayList<Conversation>();
        trancheList = new ArrayList<Tranche>();
        tagList = new ArrayList<Tag>();
    }

    /*
     * FONCTIONS
     */
    public void creerConversation( AUtilisateur emetteur, String message ) {
        Conversation conversation = new Conversation();
        conversation.addMessage( new Message( message, emetteur ) );
        conversationList.add( conversation );
        conversation.setCheval( this );
        conversation.setEmetteur( emetteur );
    }

    public void removeConversation( Conversation conversation ) {
        conversationList.remove( conversation );
        conversation.setCheval( null );
    }

    public void investir( int sommeInvestie, Donateur donateur ) {
        Investissement investissement = new Investissement( sommeInvestie, donateur );
        Collections.sort( trancheList );
        Integer sommeInteger = this.montantInvesti + sommeInvestie;
        if ( sommeInteger < 0 )
            sommeInteger = Integer.MAX_VALUE;
        this.montantInvesti = sommeInteger;
        if ( sommeInvestie >= trancheList.get( trancheList.size() - 1 ).getMontantTranche() ) {
            trancheList.get( trancheList.size() - 1 ).addInvestissement( investissement );
        } else {
            for ( int i = 1; i < trancheList.size(); i++ ) {
                if ( sommeInvestie < trancheList.get( i ).getMontantTranche() ) {
                    trancheList.get( i - 1 ).addInvestissement( investissement );
                    break;
                }
            }
        }
        donateur.setMontantAInvestir( donateur.getMontantAInvestir() - sommeInvestie );
    }

    public void addTranche( Tranche tranche ) {
        this.trancheList.add( tranche );
        tranche.setCheval( this );
    }

    public void removeTranche( Tranche tranche ) {
        this.trancheList.remove( tranche );
        tranche.setCheval( null );
    }

    public void addTag( Tag tag ) {
        this.tagList.add( tag );
        tag.getChevauxList().add( this );
    }

    public void removeTag( Tag tag ) {
        this.tagList.remove( tag );
        tag.getChevauxList().remove( this );
    }

    public void removeAllTag() {
        for ( Tag tag : tagList ) {
            tag.getChevauxList().remove( this );
        }
        tagList = new ArrayList<Tag>();
    }
    
    public List<Donateur> getDonateurList() {
        List<Donateur> donateurList = new ArrayList<Donateur>();
        for ( Tranche tranche : trancheList ) {
            for ( Investissement investissement : tranche.getInvestissementList() ) {
                if ( !donateurList.contains( investissement.getDonateur() ) ) {
                    donateurList.add( investissement.getDonateur() );
                }
            }
        }
        return donateurList;
    }
    
    /*
     * GETTER ET SETTER
     */

	public int getIdCheval() {
		return idCheval;
	}

	public void setIdCheval(int idCheval) {
		this.idCheval = idCheval;
	}

	public String getNomCheval() {
		return nomCheval;
	}

	public void setNomCheval(String nomCheval) {
		this.nomCheval = nomCheval;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getButArgent() {
		return butArgent;
	}

	public void setButArgent(String butArgent) {
		this.butArgent = butArgent;
	}

	
	
	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getMontantInvesti() {
		return montantInvesti;
	}

	public void setMontantInvesti(int montantInvesti) {
		this.montantInvesti = montantInvesti;
	}
	
	public int getMontantDemande() {
		return montantDemande;
	}

	public void setMontantDemande(int montantDemande) {
		this.montantDemande = montantDemande;
	}

	public boolean isEnAvant() {
		return enAvant;
	}

	public void setEnAvant(boolean enAvant) {
		this.enAvant = enAvant;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Tranche> getTrancheList() {
		return trancheList;
	}

	public void setTrancheList(List<Tranche> trancheList) {
		this.trancheList = trancheList;
	}

	public List<Conversation> getConversationList() {
		return conversationList;
	}

	public void setConversationList(List<Conversation> conversationList) {
		this.conversationList = conversationList;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}
    
    @Override
    public String toString() {
        return "Cheval [nomCheval=" + nomCheval + ", montant demandé=" + montantDemande +", categorie=" + categorie + "]";
    }

    @Override
    public boolean equals( Object obj ) {
        Cheval cheval = (Cheval) obj;
        return cheval.getIdCheval() == this.idCheval;
    }
}
