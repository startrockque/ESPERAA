package dto;

import java.io.Serializable;

public abstract class AUtilisateurDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String          login;
    protected String          nom;
    protected String          mdp;
    protected String          email;
    protected String          image;

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp( String mdp ) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage( String image ) {
        this.image = image;
    }
}
