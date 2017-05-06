package dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Cheval;
import entities.Tag;

/**
 * Class inspirée d'open class room
 */
@Stateless
public class ChevalDAO {

    @PersistenceContext( unitName = "monUnite" )
    private EntityManager     em;

    @EJB
    private TagDAO            tagDao;
    @EJB
    private TrancheDAO        trancheDao;
    @EJB
    private InvestissementDAO investissementDao;

    /**
     * Créer un Cheval dans la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void create( Cheval obj ) throws DAOException {
        try {
            em.persist( obj );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Supprimer un cheval de la BDD
     * 
     * @param obj
     * @throws DAOException
     */
    public void delete( Cheval obj ) throws DAOException {
        try {
            for ( Tag tag : obj.getTagList() ) {
                tagDao.findByName( tag.getTag() ).getChevauxList().remove( obj );
            }
            em.remove( em.merge( obj ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver un cheval par sa clé
     * 
     * @param id
     * @return
     * @throws DAOException
     */
    public Cheval findById( int id ) throws DAOException {
        try {
            return em.find( Cheval.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver tous les chevaux de la BDD
     * 
     * @return
     * @throws DAOException
     */
    public List<Cheval> findAll() throws DAOException {
        try {
            TypedQuery<Cheval> query = em.createQuery( "SELECT p FROM Cheval p ORDER BY p.idCheval", Cheval.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les noms de tous les chevaux de la BDD
     * 
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public List<String> findAllName() throws DAOException {
        try {
            return em.createQuery( "SELECT p.nomCheval FROM Cheval p ORDER BY p.nomCheval" ).getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les chevaux mis en avant par l'admin
     * 
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findEnAvant() throws DAOException {
        try {
            return em.createQuery( "SELECT p FROM Cheval p WHERE p.enAvant=true ORDER BY p.idCheval" ).getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /************************/
    // RECHERCHE //
    /************************/

    /**
     * Trouver tous les chevaux dont le nom contient la chaine de caractères
     * passée en paramètre
     * 
     * @param nom
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findAllByName( String name ) throws DAOException {
        try {
            String ejbql = "SELECT p FROM Cheval p WHERE p.nomCheval LIKE :pattern ORDER BY p.idCheval";
            Query query = em.createQuery( ejbql );
            query.setParameter( "pattern", "%" + name + "%" );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver par nom exact
     * 
     * @param nomCheval
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public Cheval findByName( String nomCheval ) throws DAOException {
        try {
            Query query = em.createQuery( "SELECT p FROM Cheval p WHERE p.nomCheval=?1" );
            query.setParameter( "1", "%" + nomCheval + "%" );
            List<Cheval> chevalList = query.getResultList();
            if ( !chevalList.isEmpty() ) {
                return chevalList.get( 0 );
            } else {
                return null;
            }
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les chevaux d'une catégorie
     * 
     * @param categorie
     * @return
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findByCategorie( String categorie ) throws DAOException {
        try {
            Query query = em
                    .createQuery( "SELECT p FROM Cheval p WHERE p.categorie.titreCategorie=?1 ORDER BY p.idCheval" );
            query.setParameter( "1", categorie );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver tous les chevaux par nom et Categorie
     * 
     * @param nomCheval
     * @param categorie
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findByNomCategorie( String nomCheval, String categorie ) throws DAOException {
        try {
            String ejbql = "SELECT p FROM Cheval p WHERE p.nomCheval LIKE :pattern AND p.categorie.titreCategorie=?1 ORDER BY p.idCheval";
            Query query = em.createQuery( ejbql );
            query.setParameter( "pattern", "%" + nomCheval + "%" );
            query.setParameter( "1", categorie );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les chevaux par un Tag
     * 
     * @param stringTab
     * @return
     */
    public List<Cheval> findByTag( String[] stringTab ) throws DAOException {
        List<Cheval> chevalTag = new ArrayList<Cheval>();
        try {
            List<Tag> tagsList = new ArrayList<Tag>();
            Tag tag;
            for ( String nomTag : stringTab ) {
                tag = tagDao.findByName( nomTag );
                if ( tag != null ) {
                    tagsList.add( tag );
                }
            }
            if ( !tagsList.isEmpty() ) {
                List<Cheval> allChevals = findAll();
                for ( Cheval p : allChevals ) {
                    chevalTag.add( p );
                    for ( Tag t : tagsList ) {
                        if ( p.getTagList() == null || ( !p.getTagList().contains( t ) ) ) {
                            chevalTag.remove( p );
                        }
                    }
                }
            }
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
        return chevalTag;
    }


    /************************/
    // Listes dynamiques //
    /************************/

    /**
     * Trouve les nbCheval derniers chevaux créés
     * 
     * @param nbCheval
     *            nombre de chevaux à retourner
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findLastHorses( int nbCheval ) throws DAOException {
        try {
            String ejbql = "SELECT p "
                    + "FROM Cheval p "
                    + "ORDER BY p.dateCreation DESC ";
            Query query = em.createQuery( ejbql );
            query.setMaxResults( nbCheval );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les nbChevals les plus proches d'être financés
     * 
     * @param nbCheval
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findAlmostFinancedHorses( int nbCheval ) throws DAOException {
        try {
            String sql =
                    "SELECT IDCHEVAL FROM "
                            + "(SELECT IDCHEVAL , MONTANTINVESTI  , (p.MONTANTINVESTI *10000 /p.MONTANTDEMANDE) as nb "
                            + "FROM CHEVAL p "
                            + "WHERE p.MONTANTINVESTI *10000 /p.MONTANTDEMANDE < 10000 "
                            + "ORDER BY (nb) "
                            + "DESC LIMIT ?1)";
            Query query = em.createNativeQuery( sql );
            query.setParameter( 1, nbCheval );
            List<Integer> intList = query.getResultList();
            return recupererChevalDepuisListId( intList );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver les chevaux les plus financés
     * 
     * @param nbCheval
     * @return
     * @throws DAOException
     */
    @SuppressWarnings( "unchecked" )
    public List<Cheval> findMostFinancedHorses( int nbCheval ) throws DAOException {
        try {
            String sql =
                    "SELECT IDCHEVAL FROM "
                            + "(SELECT IDCHEVAL , MONTANTINVESTI  , (p.MONTANTINVESTI *10000 /p.MONTANTDEMANDE) as nb "
                            + "FROM CHEVAL p "
                            + "ORDER BY (nb) "
                            + "DESC LIMIT ?1)";
            Query query = em.createNativeQuery( sql );
            query.setParameter( 1, nbCheval );
            List<Integer> intList = query.getResultList();
            return recupererChevalDepuisListId( intList );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Trouver des chevaux depuis une liste de clés
     * 
     * @param ids
     * @return
     * @throws DAOException
     */
    public List<Cheval> recupererChevalDepuisListId( List<Integer> ids ) throws DAOException {
        try {
            List<Cheval> listChevals = new ArrayList<Cheval>();
            for ( int i : ids ) {
                listChevals.add( findById( i ) );
            }
            return listChevals;
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /************************/
    // Administration 		 /
    /************************/

    /**
     * Met un cheval en avant
     * 
     * @param idCheval
     * @throws DAOException
     */
    public void mettreEnAvant( int idCheval ) throws DAOException {
        try {
            Query query = em.createQuery( "UPDATE Cheval p SET p.enAvant=true WHERE p.idCheval=?1" ).setParameter( 1,
                    idCheval );
            query.executeUpdate();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    /**
     * Met un cheval en arrière s'il était en avant
     * 
     * @param idCheval
     * @throws DAOException
     */
    public void mettreEnArriere( int idCheval ) throws DAOException {
        try {
            Query query = em.createQuery( "UPDATE Cheval p SET p.enAvant=false WHERE p.idCheval=?1" ).setParameter( 1,
                    idCheval );
            query.executeUpdate();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }


    /**
     * Trouver le nombre de chevaux dans la BDD
     * 
     * @return
     * @throws DAOException
     */
    public int getSize() throws DAOException {
        try {
            String sqlString = "SELECT COUNT(*) FROM CHEVAL";
            Query query = em.createNativeQuery( sqlString );
            return ( (BigInteger) query.getResultList().get( 0 ) ).intValue();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}