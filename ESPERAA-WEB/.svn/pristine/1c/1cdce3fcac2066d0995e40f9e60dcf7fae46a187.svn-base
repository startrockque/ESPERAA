package servlets.financeurPorteur.forms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import servlets.utilitaire.ImageUtilitaire;
import dto.TrancheDTO;
import facade.IFinanceurPorteurFacade;

public class ProjetForm {

    private static final String     CHAMP_TITRE            = "titre";
    private static final String     CHAMP_DESCRIPTION      = "description";
    private static final String     CHAMP_BUT              = "butArgent";
    private static final String     CHAMP_MONTANT_DEMANDE  = "montantDemande";
    private static final String     CHAMP_CATEGORIE        = "titreCategorie";
    private static final String     CHAMP_TAG              = "tagText";
    private static final String     CHAMP_DATE_FIN         = "dateFin";
    private static final String     CHAMP_IMAGE            = "imageProjet";

    private static final String     CHAMP_COMPENSATION     = "compensation";
    private static final String     CHAMP_MONTANT_TRANCHE  = "montantTranche";

    private static final String     EXTENSION_IMAGE_PROJET = "projets/";

    private Map<String, String>     erreursProjet;
    private Map<String, String>     erreursTranche;
    private String                  resultatTranche;
    private String                  chemin;
    private String                  imageProjet;

    private List<TrancheDTO>        trancheDtoList;

    private IFinanceurPorteurFacade facadeMembre;

    public ProjetForm( IFinanceurPorteurFacade facadeMembre ) {
        this.facadeMembre = facadeMembre;
        erreursProjet = new HashMap<String, String>();
        erreursTranche = new HashMap<String, String>();
    }

    public void verificationProjet( HttpServletRequest request ) {
        resultatTranche = null;
        erreursProjet = new HashMap<String, String>();
        String titre = getValeurChamp( request, CHAMP_TITRE );
        String description = getValeurChamp( request, CHAMP_DESCRIPTION );
        String butArgent = getValeurChamp( request, CHAMP_BUT );
        String montantDemande = getValeurChamp( request, CHAMP_MONTANT_DEMANDE );
        String titreCategorie = getValeurChamp( request, CHAMP_CATEGORIE );
        String tagString = getValeurChamp( request, CHAMP_TAG );
        String dateFin = getValeurChamp( request, CHAMP_DATE_FIN );

        request.setAttribute( CHAMP_TITRE, titre );
        request.setAttribute( CHAMP_DESCRIPTION, description );
        request.setAttribute( CHAMP_BUT, butArgent );
        request.setAttribute( CHAMP_MONTANT_DEMANDE, montantDemande );
        request.setAttribute( CHAMP_CATEGORIE, titreCategorie );
        request.setAttribute( CHAMP_TAG, tagString );
        request.setAttribute( CHAMP_DATE_FIN, dateFin );

        traiterTitre( titre );
        traiterDescription( description );
        traiterButArgent( butArgent );
        traiterMontantDemande( montantDemande );
        traiterCategorie( titreCategorie );
        traiterDateFin( dateFin );
        traiterImage( request );
        if ( trancheDtoList == null ) {
            resultatTranche = "Le projet doit avoir au moins une tranche";
        }

    }

    public void verificationModifierProjet( HttpServletRequest request, int idProjet ) {
        resultatTranche = null;
        erreursProjet = new HashMap<String, String>();
        String titre = getValeurChamp( request, CHAMP_TITRE );
        String description = getValeurChamp( request, CHAMP_DESCRIPTION );
        String butArgent = getValeurChamp( request, CHAMP_BUT );
        String montantDemande = getValeurChamp( request, CHAMP_MONTANT_DEMANDE );
        String titreCategorie = getValeurChamp( request, CHAMP_CATEGORIE );
        String tagString = getValeurChamp( request, CHAMP_TAG );
        String dateFin = getValeurChamp( request, CHAMP_DATE_FIN );

        request.setAttribute( CHAMP_TITRE, titre );
        request.setAttribute( CHAMP_DESCRIPTION, description );
        request.setAttribute( CHAMP_BUT, butArgent );
        request.setAttribute( CHAMP_MONTANT_DEMANDE, montantDemande );
        request.setAttribute( CHAMP_CATEGORIE, titreCategorie );
        request.setAttribute( CHAMP_TAG, tagString );
        request.setAttribute( CHAMP_DATE_FIN, dateFin );

        traiterTitre( titre, idProjet );
        traiterDescription( description );
        traiterButArgent( butArgent );
        traiterMontantDemande( montantDemande );
        traiterCategorie( titreCategorie );
        traiterDateFin( dateFin );
        traiterImage( request );
        if ( trancheDtoList == null ) {
            resultatTranche = "Le projet doit avoir au moins une tranche";
        }
    }

    public void verificationTranche( HttpServletRequest request ) {
        resultatTranche = null;
        erreursTranche = new HashMap<String, String>();
        String compensationTranche = getValeurChamp( request, CHAMP_COMPENSATION );
        String montantTranche = getValeurChamp( request, CHAMP_MONTANT_TRANCHE );

        request.setAttribute( CHAMP_COMPENSATION, compensationTranche );
        request.setAttribute( CHAMP_MONTANT_TRANCHE, montantTranche );

        traiterCompensation( compensationTranche );
        traiterMontantTranche( montantTranche );
    }

    private void traiterImage( HttpServletRequest request ) {
        imageProjet = null;
        try {
            imageProjet = ImageUtilitaire.validationImage( request, CHAMP_IMAGE, chemin, EXTENSION_IMAGE_PROJET );
        } catch ( FormValidationException e ) {
            setErreurProjet( CHAMP_IMAGE, e.getMessage() );
        }
    }

    private void traiterTitre( String titre, int idProjet ) {
        if ( titre != null && !titre.isEmpty() ) {
            if ( !facadeMembre.verifierTitreProjet( titre, idProjet ) ) {
                setErreurProjet( CHAMP_TITRE, "Ce titre est déjà utilisé pour un autre projet" );
            }
        } else {
            setErreurProjet( CHAMP_TITRE, "Le champ titre est obligatoire" );
        }
    }

    private void traiterTitre( String titre ) {
        if ( titre != null && !titre.isEmpty() ) {
            if ( !facadeMembre.verifierTitreProjet( titre ) ) {
                setErreurProjet( CHAMP_TITRE, "Ce titre est déjà utilisé pour un autre projet" );
            }
        } else {
            setErreurProjet( CHAMP_TITRE, "Le champ titre est obligatoire" );
        }
    }

    private void traiterDescription( String description ) {
        if ( description != null && !description.isEmpty() ) {
            if ( description.length() < 10 ) {
                setErreurProjet( CHAMP_DESCRIPTION, "La description du projet doit faire au moins 10 caractères" );
            }
        } else {
            setErreurProjet( CHAMP_DESCRIPTION, "Le champ description est obligatoire" );
        }
    }

    private void traiterButArgent( String butArgent ) {
        if ( butArgent != null && !butArgent.isEmpty() ) {
            if ( butArgent.length() < 10 ) {
                setErreurProjet( CHAMP_BUT, "Le but du projet doit faire au moins 10 caractères" );
            }
        } else {
            setErreurProjet( CHAMP_BUT, "Le champ but argent est obligatoire" );
        }

    }

    private void traiterMontantDemande( String montantDemande ) {
        if ( montantDemande != null && !montantDemande.isEmpty() ) {
            try {
                Integer.parseInt( montantDemande );
            } catch ( Exception e ) {
                setErreurProjet( CHAMP_MONTANT_DEMANDE, "Le champ ne doit pas comporté de lettre" );
            }
        } else {
            setErreurProjet( CHAMP_MONTANT_DEMANDE, "Le champ montant demande est obligatoire" );
        }
    }

    private void traiterCategorie( String titreCategorie ) {
        if ( titreCategorie == null ) {
            erreursProjet.put( CHAMP_CATEGORIE, "Choississez une categorie pour votre projet" );
        } else if ( titreCategorie.isEmpty() ) {
            erreursProjet.put( CHAMP_CATEGORIE, "Choississez une categorie pour votre projet" );
        }
    }

    private void traiterDateFin( String dateFin ) {
        if ( dateFin != null ) {
            Calendar aujourdhui = Calendar.getInstance();
            Calendar dateMax = Calendar.getInstance();
            dateMax.add( Calendar.MONTH, 2 );

            SimpleDateFormat formater = new SimpleDateFormat( "yyyy-MM-dd" );
            Calendar currentDate = Calendar.getInstance();
            try {
                currentDate.setTime( formater.parse( dateFin ) );
                if ( currentDate.before( aujourdhui ) || currentDate.after( dateMax ) ) {
                    setErreurProjet( CHAMP_DATE_FIN, "La date de fin ne peut pas dépasser les 2 mois" );
                }
            } catch ( ParseException pe ) {
                setErreurProjet( CHAMP_DATE_FIN, "Un problème est survenu lors de la saisie de la date" );
            }
        }
    }

    private void traiterCompensation( String compensationTranche ) {
        if ( compensationTranche == null ) {
            setErreurTranche( CHAMP_COMPENSATION, "Le champ est obligatoire" );
        } else if ( compensationTranche.isEmpty() ) {
            setErreurTranche( CHAMP_COMPENSATION, "Le champ est obligatoire" );
        }
    }

    private void traiterMontantTranche( String montantTranche ) {
        if ( montantTranche != null && !montantTranche.isEmpty() ) {
            try {
                float montant = Float.parseFloat( montantTranche );
                if ( !verifierMontantTranche( montant ) ) {
                    setErreurTranche( CHAMP_MONTANT_TRANCHE, "Ce montant est déjà associé à une compensation" );
                }
            } catch ( Exception e ) {
                setErreurTranche( CHAMP_MONTANT_TRANCHE, "Le champ ne doit pas comporté de lettre" );
            }
        } else {
            setErreurTranche( CHAMP_MONTANT_TRANCHE, "Le champ est obligatoire" );
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreurProjet( String champ, String message ) {
        erreursProjet.put( champ, message );
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreurTranche( String champ, String message ) {
        erreursTranche.put( champ, message );
    }

    public void ajouterTranche( String compensation, int montant ) {
        if ( trancheDtoList == null ) {
            trancheDtoList = new ArrayList<TrancheDTO>();
            trancheDtoList.add( new TrancheDTO( compensation, montant ) );
        } else {
            trancheDtoList.add( new TrancheDTO( compensation, montant ) );
        }
    }

    private boolean verifierMontantTranche( float montantTranche ) {
        if ( trancheDtoList != null ) {
            for ( TrancheDTO trancheDTO : trancheDtoList ) {
                if ( trancheDTO.getMontantTranche() == montantTranche ) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin( String chemin ) {
        this.chemin = chemin;
    }

    public String getImage() {
        return imageProjet;
    }

    public List<TrancheDTO> getTrancheList() {
        return trancheDtoList;
    }

    public String getResultatTranche() {
        return resultatTranche;
    }

    public Map<String, String> getErreursProjet() {
        return erreursProjet;
    }

    public Map<String, String> getErreursTranche() {
        return erreursTranche;
    }

    public void setTrancheDtoList( List<TrancheDTO> trancheDtoList ) {
        this.trancheDtoList = trancheDtoList;
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }
}
