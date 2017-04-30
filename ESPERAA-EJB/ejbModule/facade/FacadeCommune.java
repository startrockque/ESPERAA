package facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.AdminDAO;
import dao.CategorieDao;
import dao.ChevalDAO;
import dao.ConversationDAO;
import dao.DAOException;
import dao.DonateurDAO;
import dao.MessageDAO;
import dao.TagDAO;
import dto.AUtilisateurDTO;
import dto.AdminDTO;
import dto.CategorieDTO;
import dto.ChevalDTO;
import dto.DonateurDTO;
import dto.TousLesChevauxDTO;
import entities.AUtilisateur;
import entities.Admin;
import entities.Categorie;
import entities.Cheval;
import entities.Conversation;
import entities.Donateur;
import entities.Message;
import entities.Tag;

@Stateless
public class FacadeCommune implements IFacadeCommune {

    private static final String NOTIFICATION_MESSAGE_SUPPRIME         = "Un administrateur a supprimé votre commentaire ";

    @EJB
    private ChevalDAO           chevalDao;
    @EJB
    private CategorieDao        categorieDao;
    @EJB
    private AdminDAO            adminDao;
    @EJB
    private DonateurDAO         donateurDao;
    @EJB
    private TagDAO              tagDao;
    @EJB
    private ConversationDAO     conversationDao;
    @EJB
    private MessageDAO          messageDao;

    /****************/
    // Recherche //
    /****************/

    @Override
    public ChevalDTO findChevalById( int idCheval ) {
        try {
            return new ChevalDTO( chevalDao.findById( idCheval ) );
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public List<TousLesChevauxDTO> rechercherParNom( String nomCheval ) {
        try {
            List<TousLesChevauxDTO> chevalDtoList = new ArrayList<>();
            for ( Cheval cheval : chevalDao.findAllByName( nomCheval ) ) {
                chevalDtoList.add( new TousLesChevauxDTO( cheval ) );
            }
            return chevalDtoList;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TousLesChevauxDTO> rechercherParCategorie( String categorie ) {
        try {
            List<TousLesChevauxDTO> chevalDtoList = new ArrayList<TousLesChevauxDTO>();
            for ( Cheval cheval : chevalDao.findByCategorie( categorie ) ) {
                chevalDtoList.add( new TousLesChevauxDTO( cheval ) );
            }
            return chevalDtoList;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<TousLesChevauxDTO> rechercherParTag( String tagString ) {
        try {
            if ( tagString != null ) {
                String[] tagNameTab = tagString.split( " " );
                List<TousLesChevauxDTO> chevalDTOList = new ArrayList<TousLesChevauxDTO>();
                List<Cheval> chevalList = chevalDao.findByTag( tagNameTab );
                for ( Cheval cheval : chevalList ) {
                    chevalDTOList.add( new TousLesChevauxDTO( cheval ) );
                }
                return chevalDTOList;
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return new ArrayList<TousLesChevauxDTO>();
    }

    @Override
    public List<TousLesChevauxDTO> rechercherParNomCategorie( String nomCheval, String categorie ) {
        try {
            List<TousLesChevauxDTO> chevalDtoList = new ArrayList<TousLesChevauxDTO>();
            for ( Cheval cheval : chevalDao.findByNomCategorie( nomCheval, categorie ) ) {
                chevalDtoList.add( new TousLesChevauxDTO( cheval ) );
            }
            return chevalDtoList;
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }


    /****************/
    // Categories //
    /****************/

    @Override
    public List<String> getAllCategoriesNames() {
        try {
            return categorieDao.findAllNames();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategorieDTO> findAllCategorieDto() {
        try {
            List<CategorieDTO> categorieDtoList = new ArrayList<CategorieDTO>();
            for ( Categorie categorie : categorieDao.findAll() ) {
                categorieDtoList.add( new CategorieDTO( categorie ) );
            }
            return categorieDtoList;
        } catch ( DAOException e ) {
            return null;
        }
    }

    @Override
    public List<String> listeCategoriePopulaire( int nbCat ) {
        try {
            return categorieDao.findPopularCategorie( nbCat );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

    /****************/
    // Chevaux //
    /****************/

    @Override
    public List<TousLesChevauxDTO> recupererTousLesChevaux() {
        try {
            List<TousLesChevauxDTO> chevalDtoList = new ArrayList<TousLesChevauxDTO>();
            for ( Cheval cheval : chevalDao.findAll() ) {
                chevalDtoList.add( new TousLesChevauxDTO( cheval ) );
            }
            return chevalDtoList;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> recupererTousLesNomsDesChevaux() {
        try {
            return chevalDao.findAllName();
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TousLesChevauxDTO> recupererChevauxEnAvant() {
        try {
            List<TousLesChevauxDTO> chevalDtoList = new ArrayList<TousLesChevauxDTO>();
            for ( Cheval cheval : chevalDao.findEnAvant() ) {
                chevalDtoList.add( new TousLesChevauxDTO( cheval ) );
            }
            return chevalDtoList;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ChevalDTO findChevalDTOById( int idCheval ) {
        try {
            Cheval cheval = chevalDao.findById( idCheval );
            for ( Conversation conversation : cheval.getConversationList() )
                Collections.sort( conversation.getMessageList() );
            ChevalDTO chevalDto = new ChevalDTO( cheval );
            return chevalDto;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TousLesChevauxDTO> recupererChevauxRecents( int nbCheval ) {
        try {
            List<TousLesChevauxDTO> chevalList = new ArrayList<TousLesChevauxDTO>();
            for ( Cheval cheval : chevalDao.findLastHorses( nbCheval ) ) {
                chevalList.add( new TousLesChevauxDTO( cheval ) );
            }
            return chevalList;
        } catch ( DAOException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<TousLesChevauxDTO> recupererChevauxPresqueFinances( int nbCheval ) {
        List<TousLesChevauxDTO> chevalList = new ArrayList<TousLesChevauxDTO>();
        try {
            for ( Cheval cheval : chevalDao.findAlmostFinancedHorses( nbCheval ) ) {
                chevalList.add( new TousLesChevauxDTO( cheval ) );
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return chevalList;
    }

    @Override
    public List<TousLesChevauxDTO> recupererChevauxPlusFinances( int nbCheval ) {
        List<TousLesChevauxDTO> chevalList = new ArrayList<TousLesChevauxDTO>();
        try {
            for ( Cheval cheval : chevalDao.findMostFinancedHorses( nbCheval ) ) {
                chevalList.add( new TousLesChevauxDTO( cheval ) );
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return chevalList;
    }


    /****************/
    // Membres //
    /****************/

    @Override
    public AUtilisateurDTO connexion( String login, String mdp ) {
        try {
            AUtilisateur utilisateur = adminDao.findByLogin( login );
            AUtilisateurDTO utilisateurDto = null;
            if ( utilisateur == null ) {
                utilisateur = donateurDao.findByLogin( login );
                if ( utilisateur == null ) {
                    return null;
                } else {
                    utilisateurDto = new DonateurDTO( (Donateur) utilisateur );
                }
            } else {
                utilisateurDto = new AdminDTO( (Admin) utilisateur );
            }
            if ( utilisateur.getMdp().equals( mdp ) ) {
                return utilisateurDto;
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> recupererTousLesNomsDeDonateurs() {
        try {
            return donateurDao.findAllLogins();
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DonateurDTO findDonateurDTOByLogin( String login ) {
        try {
            Donateur membre = donateurDao.findByLogin( login );
            if ( membre == null ) {
                return null;
            } else {
                return new DonateurDTO( membre );
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AdminDTO findAdminDTOByLogin( String login ) {
        try {
            return new AdminDTO( adminDao.findByLogin( login ) );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return null;
    }

    /****************/
    // Messages //
    /****************/

    @Override
    public void envoyerMessage( String loginEmetteur, int idCheval, String contenuMessage ) {
        try {
            AUtilisateur emetteur = donateurDao.findByLogin( loginEmetteur );
            if ( emetteur == null ) {
                emetteur = adminDao.findByLogin( loginEmetteur );
            }
            Cheval cheval = chevalDao.findById( idCheval );
            cheval.creerConversation( emetteur, contenuMessage );
            //TODO avertir admin
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void repondreMessage( String loginEmetteur, int idConversation, String message ) {
        try {
            Conversation conversation = conversationDao.findById( idConversation );
            AUtilisateur emetteur = donateurDao.findByLogin( loginEmetteur );
            if ( emetteur == null ) {
                emetteur = adminDao.findByLogin( loginEmetteur );
            }
            conversation.repondreConversation( emetteur, message );
            //TODO avertir admin
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerMessage( int idMessage, int idConversation, int idCheval, boolean admin ) {
        try {
            Message message = messageDao.findById( idMessage );
            Conversation conversation = conversationDao.findById( idConversation );
            Cheval cheval = chevalDao.findById( idCheval );
            Collections.sort( conversation.getMessageList() );
            if ( admin ) {
                String notificationSuppression = NOTIFICATION_MESSAGE_SUPPRIME
                        + " (dans  " + cheval.getNomCheval() + ")";
                if ( conversation.getMessageList().get( 0 ).equals( message ) ) {
                    for ( Message mess : conversation.getMessageList() ) {
                        mess.getEmetteur().addNotification( notificationSuppression );
                    }
                    cheval.removeConversation( conversation );
                    conversationDao.delete( conversation );
                }
                else {
                    message.getEmetteur().addNotification( notificationSuppression );
                    conversation.removeMessage( message );
                    conversationDao.delete( conversation );
                }
            } else {
                if ( conversation.getMessageList().get( 0 ).equals( message ) ) {
                    cheval.removeConversation( conversation );
                    conversationDao.delete( conversation );
                }
                else {
                    conversation.removeMessage( message );
                    messageDao.delete( message );
                }
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
    }

    /****************/
    // Tags //
    /****************/

    @Override
    public List<String> recupererTousLesTagName() {
        List<String> tagList = new ArrayList<String>();
        try {
            for ( Tag tag : tagDao.findAll() ) {
                tagList.add( tag.getTag() );
            }
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return tagList;
    }

    @Override
    public List<String> listeTagPopulaire( int nbTag ) {
        try {
            return tagDao.findPopularTag( nbTag );
        } catch ( DAOException e ) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }
}