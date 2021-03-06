package dto;

import entities.Admin;

public class AdminDTO extends AUtilisateurDTO {
    private static final long serialVersionUID = 1L;

    public AdminDTO( Admin admin ) {
        this.login = admin.getLogin();
        this.nom = admin.getNom();
        this.mdp = admin.getMdp();
        this.email = admin.getEmail();
    }
}
