package dto;

import entities.FinanceurPorteur;

public class FinanceurPorteurMinDTO extends AUtilisateurDTO {
    private static final long serialVersionUID = 1L;

    private int               nbAime;
    private int               nbProjets;

    public FinanceurPorteurMinDTO() {
        super();
    }

    public FinanceurPorteurMinDTO( FinanceurPorteur fp ) {
        this.login = fp.getLogin();
        this.nom = fp.getNom();
        this.mdp = fp.getMdp();
        this.email = fp.getEmail();
        this.nbAime = fp.getAimeList().size();
        this.image = fp.getImage();
    }

    public int getNbAime() {
        return nbAime;
    }

    public void setNbAime( int nbAime ) {
        this.nbAime = nbAime;
    }

    public int getNbProjets() {
        return nbProjets;
    }

    public void setNbProjets( int nbProjets ) {
        this.nbProjets = nbProjets;
    }
}