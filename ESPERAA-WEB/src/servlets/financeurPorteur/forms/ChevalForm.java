package servlets.financeurPorteur.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import servlets.utilitaire.ImageUtilitaire;
import dto.TrancheDTO;

public class ChevalForm {

    private static final String     CHAMP_NOM              = "nom";
    private static final String     CHAMP_DESCRIPTION      = "description";
    private static final String     CHAMP_BUT              = "butArgent";
    private static final String     CHAMP_MONTANT_DEMANDE  = "montantDemande";
    private static final String     CHAMP_CATEGORIE        = "titreCategorie";
    private static final String     CHAMP_TAG              = "tagText";
    private static final String     CHAMP_IMAGE            = "imageCheval";

    private static final String     CHAMP_COMPENSATION     = "compensation";
    private static final String     CHAMP_MONTANT_TRANCHE  = "montantTranche";

    private static final String     EXTENSION_IMAGE_CHEVAL = "chevaux/";

    private Map<String, String>     erreursCheval;
    private Map<String, String>     erreursTranche;
    private String                  resultatTranche;
    private String                  chemin;
    private String                  imageCheval;

    private List<TrancheDTO>        trancheDtoList;

    public ChevalForm() {
        erreursCheval = new HashMap<String, String>();
        erreursTranche = new HashMap<String, String>();
    }

    public void verificationCheval( HttpServletRequest request ) {
        resultatTranche = null;
        erreursCheval = new HashMap<String, String>();
        String nom = getValeurChamp( request, CHAMP_NOM );
        String description = getValeurChamp( request, CHAMP_DESCRIPTION );
        String butArgent = getValeurChamp( request, CHAMP_BUT );
        String montantDemande = getValeurChamp( request, CHAMP_MONTANT_DEMANDE );
        String titreCategorie = getValeurChamp( request, CHAMP_CATEGORIE );
        String tagString = getValeurChamp( request, CHAMP_TAG );

        request.setAttribute( CHAMP_NOM, nom );
        request.setAttribute( CHAMP_DESCRIPTION, description );
        request.setAttribute( CHAMP_BUT, butArgent );
        request.setAttribute( CHAMP_MONTANT_DEMANDE, montantDemande );
        request.setAttribute( CHAMP_CATEGORIE, titreCategorie );
        request.setAttribute( CHAMP_TAG, tagString );

        traiterNom( nom );
        traiterDescription( description );
        traiterButArgent( butArgent );
        traiterMontantDemande( montantDemande );
        traiterCategorie( titreCategorie );
        traiterImage( request );
        if ( trancheDtoList == null ) {
            resultatTranche = "Le cheval doit avoir au moins un objectif";
        }

    }

    public void verificationModifierCheval( HttpServletRequest request, int idCheval ) {
        resultatTranche = null;
        erreursCheval = new HashMap<String, String>();
        String titre = getValeurChamp( request, CHAMP_NOM );
        String description = getValeurChamp( request, CHAMP_DESCRIPTION );
        String butArgent = getValeurChamp( request, CHAMP_BUT );
        String montantDemande = getValeurChamp( request, CHAMP_MONTANT_DEMANDE );
        String titreCategorie = getValeurChamp( request, CHAMP_CATEGORIE );
        String tagString = getValeurChamp( request, CHAMP_TAG );

        request.setAttribute( CHAMP_NOM, titre );
        request.setAttribute( CHAMP_DESCRIPTION, description );
        request.setAttribute( CHAMP_BUT, butArgent );
        request.setAttribute( CHAMP_MONTANT_DEMANDE, montantDemande );
        request.setAttribute( CHAMP_CATEGORIE, titreCategorie );
        request.setAttribute( CHAMP_TAG, tagString );

        traiterNom( titre, idCheval );
        traiterDescription( description );
        traiterButArgent( butArgent );
        traiterMontantDemande( montantDemande );
        traiterCategorie( titreCategorie );
        traiterImage( request );
        if ( trancheDtoList == null ) {
            resultatTranche = "Le cheval doit avoir au moins un objectif";
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
        imageCheval = null;
        try {
            imageCheval = ImageUtilitaire.validationImage( request, CHAMP_IMAGE, chemin, EXTENSION_IMAGE_CHEVAL );
        } catch ( FormValidationException e ) {
            setErreurCheval( CHAMP_IMAGE, e.getMessage() );
        }
    }

    private void traiterNom( String titre, int idCheval ) {
        if ( titre != null && !titre.isEmpty() ) {
        } else {
            setErreurCheval( CHAMP_NOM, "Le champ nom est obligatoire" );
        }
    }

    private void traiterNom( String titre ) {
        if ( titre != null && !titre.isEmpty() ) {
        } else {
            setErreurCheval( CHAMP_NOM, "Le champ nom est obligatoire" );
        }
    }

    private void traiterDescription( String description ) {
        if ( description != null && !description.isEmpty() ) {
            if ( description.length() < 10 ) {
                setErreurCheval( CHAMP_DESCRIPTION, "La description du cheval doit faire au moins 10 caractères" );
            }
        } else {
            setErreurCheval( CHAMP_DESCRIPTION, "Le champ description est obligatoire" );
        }
    }

    private void traiterButArgent( String butArgent ) {
        if ( butArgent != null && !butArgent.isEmpty() ) {
            if ( butArgent.length() < 10 ) {
                setErreurCheval( CHAMP_BUT, "Le but du cheval doit faire au moins 10 caractères" );
            }
        } else {
            setErreurCheval( CHAMP_BUT, "Le champ but argent est obligatoire" );
        }

    }

    private void traiterMontantDemande( String montantDemande ) {
        if ( montantDemande != null && !montantDemande.isEmpty() ) {
            try {
                Integer.parseInt( montantDemande );
            } catch ( Exception e ) {
                setErreurCheval( CHAMP_MONTANT_DEMANDE, "Le champ ne doit pas comporté de lettre" );
            }
        } else {
            setErreurCheval( CHAMP_MONTANT_DEMANDE, "Le champ montant demande est obligatoire" );
        }
    }

    private void traiterCategorie( String titreCategorie ) {
        if ( titreCategorie == null ) {
            erreursCheval.put( CHAMP_CATEGORIE, "Choississez une categorie pour le cheval" );
        } else if ( titreCategorie.isEmpty() ) {
            erreursCheval.put( CHAMP_CATEGORIE, "Choississez une categorie pour le cheval" );
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
    private void setErreurCheval( String champ, String message ) {
        erreursCheval.put( champ, message );
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
        return imageCheval;
    }

    public List<TrancheDTO> getTrancheList() {
        return trancheDtoList;
    }

    public String getResultatTranche() {
        return resultatTranche;
    }

    public Map<String, String> getErreursCheval() {
        return erreursCheval;
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
