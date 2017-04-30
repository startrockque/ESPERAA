package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Notification;

@Stateless
public class NotificationDAO {
    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    @EJB
    private DonateurDAO  donateurDAO;

    /**
     * Créer une nouvelle Notification dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Notification obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer une Notification dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Notification obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver une Notification par sa clé
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Notification findById( int id ) throws DAOException {
        try {
            return em.find( Notification.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver toutes les Notifications de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public List<Notification> findAll() throws DAOException {
        try {
            TypedQuery<Notification> query = em.createQuery( "SELECT m FROM Notification m ORDER BY m.idNotification",
                    Notification.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Recuperer les notifications dont loginDonateur est le destinataire, et
     * qui n'ont pas encore été consultées
     * 
     * @param loginDonateur
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Notification> findByDonateurLogin( String loginDonateur ) throws DAOException {
        try {
            String ejbql = "SELECT n FROM Notification n WHERE n.destinataire=?1 AND n.notificationVue=?2";
            Query query = em.createQuery( ejbql );
            query.setParameter( "1", donateurDAO.findByLogin( loginDonateur ) );
            query.setParameter( "2", false );
            List<Notification> notifList = query.getResultList();
            if ( notifList.isEmpty() )
                return new ArrayList<Notification>();
            else
                return notifList;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}