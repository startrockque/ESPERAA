package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Tranche;

/**
 * Class inspirï¿½ d'open class room
 */
@Stateless
public class TrancheDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Créer une Tranche dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Tranche obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer une Tranche de la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Tranche obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver une Tranche par sa clé
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Tranche findById( int id ) throws DAOException {
        try {
            return em.find( Tranche.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver toutes les Tranches de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public List<Tranche> findAll() throws DAOException {
        try {
            TypedQuery<Tranche> query = em.createQuery( "SELECT t FROM Tranche t ORDER BY t.idTranche", Tranche.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}
