package dto;

import entities.Donateur;

public class DonateurMinDTO extends AUtilisateurDTO {
    private static final long serialVersionUID = 1L;

    public DonateurMinDTO() {
        super();
    }

    public DonateurMinDTO( Donateur fp ) {
        this.login = fp.getLogin();
        this.nom = fp.getNom();
        this.mdp = fp.getMdp();
        this.email = fp.getEmail();
        this.image = fp.getImage();
    }
}