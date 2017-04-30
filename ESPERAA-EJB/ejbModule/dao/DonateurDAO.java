package dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Donateur;

@Stateless
public class DonateurDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Creer un Donateur en BDD
     * 
     * @param obj
     *            Donateur entity
     * @throws DAOException
     */
    public void create( Donateur obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer un Donateur en BDD
     * 
     * @param obj
     *            Donateur entity
     * @throws DAOException
     */
    public void delete( Donateur obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver un Donateur par son nom
     * 
     * @param nom
     *            nom du Donateur
     * @return le Donateur
     * @throws DAOException
     */
    public Donateur findByLogin( String nom ) throws DAOException {
        try {
            return em.find( Donateur.class, nom );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Recuperer tous les Donateur de la BDD
     * 
     * @return une liste contenant tous les Donateur
     * @throws DAOException
     */
    public List<Donateur> findAll() throws DAOException {
        try {
            TypedQuery<Donateur> query = em.createQuery( "SELECT c FROM Donateur c ORDER BY c.login",
                    Donateur.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les noms de tous les membres
     * 
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<String> findAllLogins() throws DAOException {
        try {
            Query query = em.createQuery( "Select f.login FROM Donateur f ORDER BY f.login" );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver le nombre de Membres dans la BDD
     * 
     * @return
     * @throws DAOException
     */
    public int getSize() throws DAOException {
        try {
            String sqlString = "SELECT COUNT(*) FROM Donateur ";
            Query query = em.createNativeQuery( sqlString );
            return ( (BigInteger) query.getResultList().get( 0 ) ).intValue();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}