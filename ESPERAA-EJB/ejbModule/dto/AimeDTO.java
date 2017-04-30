package dto;

import java.io.Serializable;

import entities.Aime;

public class AimeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idAime;
    private String            loginFinanceur;
    private int               idProjet;

    public AimeDTO( Aime aime ) {
        this.idAime = aime.getIdAime();
        this.loginFinanceur = aime.getFinanceur().getLogin();
        this.idProjet = aime.getProjet().getIdProjet();
    }

    public int getIdAime() {
        return idAime;
    }

    public void setIdAime( int idAime ) {
        this.idAime = idAime;
    }

    public String getLoginFinanceur() {
        return loginFinanceur;
    }

    public void setLoginFinanceur( String loginFinanceur ) {
        this.loginFinanceur = loginFinanceur;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet( int idProjet ) {
        this.idProjet = idProjet;
    }
}
