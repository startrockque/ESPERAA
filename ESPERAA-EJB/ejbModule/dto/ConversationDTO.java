package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.Conversation;
import entities.Message;

public class ConversationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idConversation;
    private String            emetteur;
    private List<MessageDTO>  messageList;

    public ConversationDTO( Conversation conversation ) {
        this.idConversation = conversation.getIdConversation();
        this.emetteur = conversation.getEmetteur().getLogin();
        this.messageList = new ArrayList<MessageDTO>();
        for ( Message m : conversation.getMessageList() ) {
            this.messageList.add( new MessageDTO( m ) );
        }
        Collections.sort( this.messageList );
    }

    public int getIdConversation() {
        return idConversation;
    }

    public void setIdConversation( int idConversation ) {
        this.idConversation = idConversation;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public void setEmetteur( String emetteur ) {
        this.emetteur = emetteur;
    }

    public List<MessageDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList( List<MessageDTO> messageList ) {
        this.messageList = messageList;
    }
}
