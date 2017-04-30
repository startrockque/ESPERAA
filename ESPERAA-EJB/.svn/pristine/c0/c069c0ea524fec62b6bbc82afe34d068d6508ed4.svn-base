package dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Message;

@Stateless
public class MessageDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Créer un Message dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Message obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer un Message de la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Message obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver un Message par sa clé
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Message findById( int id ) throws DAOException {
        try {
            return em.find( Message.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver tous les Messages de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public List<Message> findAll() throws DAOException {
        try {
            TypedQuery<Message> query = em.createQuery( "SELECT m FROM Message m ORDER BY m.idMessage", Message.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver le nombre de Messages de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public int getSize() throws DAOException {
        try {
            String sqlString = "SELECT COUNT(*) FROM MESSAGE";
            Query query = em.createNativeQuery( sqlString );
            return ( (BigInteger) query.getResultList().get( 0 ) ).intValue();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}