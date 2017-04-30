package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Categorie;

@Stateless
public class CategorieDao {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager em;

    /**
     * Créer une Categorie en BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Categorie obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer une Cateogorie de la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Categorie obj ) throws DAOException {
        try {
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouve une Categorie par sa clé
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Categorie findById( int id ) throws DAOException {
        try {
            return em.find( Categorie.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver une Catégorie par son titre
     * 
     * @param titre
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public Categorie findByTitre( String titre ) throws DAOException {
        try {
            String ejbql = "SELECT c FROM Categorie c WHERE c.titreCategorie=?1";
            Query query = em.createQuery( ejbql );
            query.setParameter( "1", titre );
            List<Categorie> categorieList = query.getResultList();
            if ( !categorieList.isEmpty() ) {
                return categorieList.get( 0 );
            } else {
                return null;
            }
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver toutes les Categories
     * 
     * @return
     * @throws DAOException
     */
    public List<Categorie> findAll() throws DAOException {
        try {
            TypedQuery<Categorie> query = em.createQuery( "SELECT c FROM Categorie c ORDER BY c.idCategorie",
                    Categorie.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les noms de toutes les Categories
     * 
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<String> findAllNames() throws DAOException {
        try {
            Query query = em.createQuery( "SELECT c.titreCategorie FROM Categorie c ORDER BY c.idCategorie" );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouve les nbCategorie Categories les plus populaires
     * 
     * @param nbCategorie
     *            nombre de Categorie à retrouver
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<String> findPopularCategorie( int nbCategorie ) throws DAOException {
        try {
            String sql = "SELECT TITRECATEGORIE FROM "
                    + "(SELECT TITRECATEGORIE,COUNT(TITRECATEGORIE) as nb FROM"
                    + "(SELECT IDPROJET, CATEGORIE_IDCATEGORIE, TITRECATEGORIE FROM "
                    + "PROJET p "
                    + "JOIN CATEGORIE c ON p.CATEGORIE_IDCATEGORIE = c.IDCATEGORIE "
                    + "GROUP BY p.IDPROJET ) "
                    + "GROUP BY TITRECATEGORIE "
                    + "ORDER BY nb DESC) "
                    + "LIMIT ?1";
            Query query = em.createNativeQuery( sql );
            query.setParameter( 1, nbCategorie );
            List<String> catStrings = query.getResultList();
            if ( !catStrings.isEmpty() ) {
                return catStrings;
            }
            else {
                return new ArrayList<String>();
            }
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouve le nombre de Categories en BDD
     * 
     * @return
     * @throws DAOException
     */
    public int getSize() throws DAOException {
        try {
            String sqlString = "SELECT COUNT(*) FROM CATEGORIE";
            Query query = em.createNativeQuery( sqlString );
            return ( (BigInteger) query.getResultList().get( 0 ) ).intValue();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}
