package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Tag;

@Stateless
public class TagDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Créer un Tag dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Tag obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer un Tag dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Tag obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver un Tag par son nom
     * 
     * @param name
     * @return
     * @throws DAOException
     */
    public Tag findByName( String name ) throws DAOException {
        try {
            return em.find( Tag.class, name );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver tous les Tags de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public List<Tag> findAll() throws DAOException {
        try {
            TypedQuery<Tag> query = em.createQuery( "SELECT t FROM Tag t ORDER BY t.tag", Tag.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les noms de tous les Tags de le BDD
     * 
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<String> findAllTags() throws DAOException {
        try {
            Query query = em.createQuery( "SELECT t.tag FROM Tag t ORDER BY t.tag" );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les nbTag les plus populaires
     * 
     * @param nbTag
     *            le nombre de tags à retourner
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<String> findPopularTag( int nbTag ) throws DAOException {
        try {
            String sqlString =
                    "SELECT TAGLIST_TAG  FROM "
                            + "(SELECT TAGLIST_TAG, COUNT(*) as nb "
                            + "FROM TAG_PROJET  t "
                            + "GROUP BY t.TAGLIST_TAG "
                            + "ORDER BY nb DESC "
                            + "LIMIT ?1 )";
            Query query = em.createNativeQuery( sqlString );
            query.setParameter( 1, nbTag );
            List<String> tagStrings = query.getResultList();
            if ( !tagStrings.isEmpty() ) {
                return tagStrings;
            }
            else {
                return new ArrayList<String>();
            }
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver le nombre de Tag en BDD
     * 
     * @return
     * @throws DAOException
     */
    public int getSize() throws DAOException {
        try {
            String sqlString = "SELECT COUNT(*) FROM TAG";
            Query query = em.createNativeQuery( sqlString );
            return ( (BigInteger) query.getResultList().get( 0 ) ).intValue();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}