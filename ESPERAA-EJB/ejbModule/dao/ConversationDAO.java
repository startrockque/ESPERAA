package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Conversation;

@Stateless
public class ConversationDAO {
    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Cr�er une Conversation en BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Conversation obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer une Conversation en BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Conversation obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver une Conversation par id
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Conversation findById( int id ) throws DAOException {
        try {
            return em.find( Conversation.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver toutes les Conversations
     * 
     * @return
     * @throws DAOException
     */
    public List<Conversation> findAll() throws DAOException {
        try {
            TypedQuery<Conversation> query = em.createQuery( "SELECT c FROM Conversation c ORDER BY c.idConversation",
                    Conversation.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}
