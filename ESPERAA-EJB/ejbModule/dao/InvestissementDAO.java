package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Investissement;

@Stateless
public class InvestissementDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    @EJB
    private DonateurDAO  donateurDAO;

    /**
     * Créer un Investissement en BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Investissement obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer un Investissement de la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Investissement obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver un Investissement par sa clé
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Investissement findById( int id ) throws DAOException {
        try {
            return em.find( Investissement.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver tous les Investissements de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public List<Investissement> findAll() throws DAOException {
        try {
            TypedQuery<Investissement> query = em.createQuery(
                    "SELECT i FROM Investissement i ORDER BY i.idInvestissement", Investissement.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver tous les Investissements d'un Donateur
     * 
     * @param loginDonateur
     *            login du donateur
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Investissement> findByDonateur( String loginDonateur ) throws DAOException {
        try {
            String ejbql = "SELECT i FROM Investissement i WHERE i.donateur=?1";
            Query query = em.createQuery( ejbql );
            query.setParameter( "1", donateurDAO.findByLogin( loginDonateur ) );
            List<Investissement> invList = query.getResultList();
            if ( invList.isEmpty() )
                return new ArrayList<Investissement>();
            else
                return invList;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver le montant total d'Investissements en BDD
     * 
     * @return
     * @throws DAOException
     */
    public int getTotal() throws DAOException {
        try {
            String sqlString = "SELECT SUM(SOMMEINVESTIE) FROM INVESTISSEMENT";
            Query query = em.createNativeQuery( sqlString );
            Object montant = query.getResultList().get( 0 );
            if ( montant == null )
                montant = 0;
            return Integer.parseInt( montant.toString() );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}
