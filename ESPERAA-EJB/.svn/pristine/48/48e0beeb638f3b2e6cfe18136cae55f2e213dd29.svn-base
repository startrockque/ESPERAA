package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Admin;

@Stateless
public class AdminDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Creer un Admin en BDD
     * 
     * @param obj
     *            Admin entity
     * @throws DAOException
     */
    public void create( Admin obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer un Admin en BDD
     * 
     * @param obj
     *            Admin entity
     * @throws DAOException
     */
    public void delete( Admin obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver un Admin par son nom
     * 
     * @param nom
     *            nom de l Admin
     * @return l Admin
     * @throws DAOException
     */
    public Admin findByLogin( String nom ) throws DAOException {
        try {
            return em.find( Admin.class, nom );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Recuperer tous les Admin de la BDD
     * 
     * @return une liste contenant tous les Admin
     * @throws DAOException
     */
    public List<Admin> findAll() throws DAOException {
        try {
            TypedQuery<Admin> query = em.createQuery( "SELECT c FROM Admin c ORDER BY c.nom", Admin.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}
