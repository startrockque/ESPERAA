package facade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.AdminDAO;
import dao.CategorieDao;
import dao.DAOException;
import dao.DonateurDAO;
import dao.InvestissementDAO;
import dao.MessageDAO;
import dao.ChevalDAO;
import dao.TagDAO;
import dto.DonateurMinDTO;
import dto.TrancheDTO;
import entities.Categorie;
import entities.Cheval;
import entities.Donateur;
import entities.Investissement;
import entities.Tag;
import entities.Tranche;

@Stateless
public class AdministrateurFacade implements IAdministrateurFacade {

    @EJB
    private CategorieDao        categorieDao;
    @EJB
    private AdminDAO            adminDao;
    @EJB
    private ChevalDAO           chevalDao;
    @EJB
    private DonateurDAO         donateurDao;
    @EJB
    private MessageDAO          messageDao;
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
        categorie.setChevauxList( new ArrayList<Cheval>() );
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
    // Cheval //
    /****************/

    @Override
    public void mettreEnAvant( int idCheval ) {
        try {
            chevalDao.mettreEnAvant( idCheval );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void mettreEnArriere( int idCheval ) {
        try {
        	chevalDao.mettreEnArriere( idCheval );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    /****************/
    // Membres //
    /****************/

    @Override
    public List<DonateurMinDTO> recupererTousLesMembres() {
        List<Donateur> listAll;
        try {
            listAll = donateurDao.findAll();
            List<DonateurMinDTO> listAllDTO = new ArrayList<DonateurMinDTO>();
            for ( Donateur donateur : listAll ) {
            	DonateurMinDTO donateurDTO = new DonateurMinDTO( donateur );
                listAllDTO.add( donateurDTO );
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
    public int recupererNbChevaux() {
        try {
            return chevalDao.getSize();
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
            return donateurDao.getSize();
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
    public int recupererTotalInvestissements() {
        try {
            return investissementDao.getTotal();
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
    
    /****************/
    // Chevaux //
    /****************/


    @Override
    public void ajouterCheval( String nom, String description, String butArgent,
            int montantDemande, String titreCategorie, String tagString,
            List<TrancheDTO> trancheDTOList, String image ) {
        try {
            Categorie categorie = categorieDao.findByTitre( titreCategorie );

            Cheval cheval = new Cheval();
            cheval.setNomCheval( nom );
            cheval.setDescription( description );
            cheval.setButArgent( butArgent );
            cheval.setMontantDemande( montantDemande );
            cheval.setDateCreation( Calendar.getInstance() );
            cheval.setCategorie( categorie );

            if ( image != null ) {
                cheval.setImage( image );
            }

            if ( tagString != null ) {
                String[] tagTab = tagString.split( " " );
                for ( String tagName : tagTab ) {
                    if ( !tagName.equals( "" ) ) {
                        Tag tag = tagDao.findByName( tagName );
                        if ( tag == null ) {
                            cheval.addTag( new Tag( tagName ) );
                        } else if ( !cheval.getTagList().contains( tag ) ) {
                            cheval.addTag( tag );
                        }
                    }
                }
            }
            cheval.addTranche( new Tranche( "aucune compensation", 0 ) );
            for ( TrancheDTO trancheDTO : trancheDTOList ) {
                cheval.addTranche( new Tranche( trancheDTO.getCompensation(), trancheDTO.getMontantTranche() ) );
            }

            chevalDao.create( cheval );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierCheval( int idCheval, String nom, String description,
            String butArgent, int montantDemande, String titreCategorie,
            String tagString, List<TrancheDTO> trancheDTOList, String image ) {
        try {
            Cheval cheval = chevalDao.findById( idCheval );
            Categorie categorie = categorieDao.findByTitre( titreCategorie );
            cheval.setNomCheval( nom );
            cheval.setDescription( description );
            cheval.setButArgent( butArgent );
            cheval.setMontantDemande( montantDemande );
            cheval.setCategorie( categorie );
            if ( image != null ) {
                cheval.setImage( image );
            }

            cheval.removeAllTag();
            if ( tagString != null ) {
                String[] tagTab = tagString.split( " " );
                for ( String tagName : tagTab ) {
                    if ( !tagName.equals( "" ) ) {
                        Tag tag = tagDao.findByName( tagName );
                        if ( tag == null ) {
                            cheval.addTag( new Tag( tagName ) );
                        } else if ( !cheval.getTagList().contains( tag ) ) {
                            cheval.addTag( tag );
                        }
                    }
                }
            }
            for ( TrancheDTO trancheDto : trancheDTOList ) {
                boolean existeDeja = false;
                for ( Tranche tranche : cheval.getTrancheList() ) {
                    if ( tranche.getMontantTranche() == trancheDto.getMontantTranche() ) {
                        existeDeja = true;
                    }
                }
                if ( !existeDeja ) {
                    cheval.addTranche( new Tranche( trancheDto.getCompensation(), trancheDto.getMontantTranche() ) );
                }
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerCheval( int idCheval ) {
        try {
            Cheval cheval = chevalDao.findById( idCheval );
            chevalDao.delete( cheval );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }
}