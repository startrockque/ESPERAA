package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Conversation {
    @Id
    @GeneratedValue
    private int           idConversation;

    @ManyToOne
    private Cheval        cheval;

    @ManyToOne
    private AUtilisateur  emetteur;

    @OneToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
    private List<Message> messageList;

    public Conversation() {
        messageList = new ArrayList<Message>();
    }

    public void repondreConversation( AUtilisateur emetteur, String message ) {
        messageList.add( new Message( message, emetteur ) );
    }

    public void addMessage( Message message ) {
        messageList.add( message );
    }

    public void removeMessage( Message message ) {
        messageList.remove( message );
    }

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation( int idConversation ) {
        this.idConversation = idConversation;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList( List<Message> messageList ) {
        this.messageList = messageList;
    }

    public Cheval getCheval() {
        return cheval;
    }

    public void setCheval( Cheval cheval ) {
        this.cheval = cheval;
    }

    public AUtilisateur getEmetteur() {
        return emetteur;
    }

    public void setEmetteur( AUtilisateur emetteur ) {
        this.emetteur = emetteur;
    }

    @Override
    public boolean equals( Object obj ) {
        Conversation conversation = (Conversation) obj;
        return conversation.getIdConversation() == this.idConversation;
    }
}
