package facade;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.AdminDAO;
import dao.AimeDAO;
import dao.CategorieDao;
import dao.DAOException;
import dao.FinanceurDAO;
import dao.InvestissementDAO;
import dao.MessageDAO;
import dao.ProjetDAO;
import dao.TagDAO;
import dto.FinanceurPorteurMinDTO;
import entities.Categorie;
import entities.FinanceurPorteur;
import entities.Investissement;
import entities.Projet;
import entities.Tranche;

@Stateless
public class AdministrateurFacade implements IAdministrateurFacade {

    private static final String NOTIFICATION_PROJET_CLOTURE         = "Un administrateur a cloturé le projet : ";
    private static final String NOTIFICATION_PROJET_CLOTURE_PORTEUR = "Un administrateur a cloturé votre projet : ";

    @EJB
    private CategorieDao        categorieDao;
    @EJB
    private AdminDAO            adminDao;
    @EJB
    private ProjetDAO           projetDao;
    @EJB
    private FinanceurDAO        financeurDao;
    @EJB
    private MessageDAO          messageDao;
    @EJB
    private AimeDAO             aimeDao;
    @EJB
    private TagDAO              tagDao;
    @EJB
    private InvestissementDAO   investissementDao;

    /****************/
    // Catégorie //
    /****************/

    @Override
    public void creerCategorie( String titreCategorie ) {
        Categorie categorie = new Categorie();
        categorie.setTitreCategorie( titreCategorie );
        categorie.setProjetList( new ArrayList<Projet>() );
        try {
            categorieDao.create( categorie );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifierCategorie( String nomCategorie ) {
        try {
            return categorieDao.findByTitre( nomCategorie ) != null;
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return false;
    }

    /****************/
    // Projet //
    /****************/

    @Override
    public void mettreEnAvant( int idProjet ) {
        try {
            projetDao.mettreEnAvant( idProjet );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void mettreEnArriere( int idProjet ) {
        try {
            projetDao.mettreEnArriere( idProjet );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void cloturerProjet( int idProjet ) {
        try {
            Projet projet = projetDao.findById( idProjet );
            int montantARembourser;
            int montantAInvestir;
            for ( Tranche tranche : projet.getTrancheList() ) {
                for ( Investissement investissement : tranche.getInvestissementList() ) {
                    montantARembourser = investissement.getSommeInvestie();
                    montantAInvestir = investissement.getFinanceur().getMontantAInvestir();
                    investissement.getFinanceur().setMontantAInvestir( montantARembourser + montantAInvestir );
                    String notificationText = null;

                    notificationText = NOTIFICATION_PROJET_CLOTURE
                            + projet.getTitreProjet();
                    investissement.getFinanceur().addNotification( notificationText );
                }
            }
            String notificationText = NOTIFICATION_PROJET_CLOTURE_PORTEUR
                    + projet.getTitreProjet();
            projet.getPorteur().addNotification( notificationText );

            projetDao.delete( projet );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    /****************/
    // Membres //
    /****************/

    @Override
    public List<FinanceurPorteurMinDTO> recupererTousLesMembres() {
        List<FinanceurPorteur> listAll;
        try {
            listAll = financeurDao.findAll();
            List<FinanceurPorteurMinDTO> listAllDTO = new ArrayList<FinanceurPorteurMinDTO>();
            for ( FinanceurPorteur financeur : listAll ) {
                FinanceurPorteurMinDTO financeurDTO = new FinanceurPorteurMinDTO( financeur );
                listAllDTO.add( financeurDTO );
            }
            return listAllDTO;
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /****************/
    // Compteurs //
    /****************/

    @Override
    public int recupererNbProjets() {
        try {
            return projetDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int recupererNbCategories() {
        try {
            return categorieDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int recupererNbMembres() {
        try {
            return financeurDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int recupererNbAimes() {
        try {
            return aimeDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int recupererNbMessages() {
        try {
            return messageDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int recupererNbInvestissements() {
        try {
            return investissementDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int recupererNbTags() {
        try {
            return tagDao.getSize();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return 0;
    }
}