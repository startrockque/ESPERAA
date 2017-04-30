package dto;

import java.io.Serializable;

import entities.Investissement;

public class InvestissementDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idInvestissement;
    private int               idProjet;
    private int               sommeInvestie;
    private String            financeur;
    private String            titreProjet;
    private String            compensation;

    public InvestissementDTO( Investissement investissement ) {
        this.idInvestissement = investissement.getIdInvestissement();
        this.idProjet = investissement.getTranche().getProjet().getIdProjet();
        this.sommeInvestie = investissement.getSommeInvestie();
        this.financeur = investissement.getFinanceur().getLogin();
        this.titreProjet = investissement.getTranche().getProjet().getTitreProjet();
        this.compensation = investissement.getTranche().getCompensation();
    }

    public int getIdInvestissement() {
        return idInvestissement;
    }

    public void setIdInvestissement( int idInvestissement ) {
        this.idInvestissement = idInvestissement;
    }

    public int getSommeInvestie() {
        return sommeInvestie;
    }

    public void setSommeInvestie( int sommeInvestie ) {
        this.sommeInvestie = sommeInvestie;
    }

    public String getFinanceur() {
        return financeur;
    }

    public void setFinanceur( String financeur ) {
        this.financeur = financeur;
    }

    public String getTitreProjet() {
        return titreProjet;
    }

    public void setTitreProjet( String titreProjet ) {
        this.titreProjet = titreProjet;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation( String compensation ) {
        this.compensation = compensation;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet( int idProjet ) {
        this.idProjet = idProjet;
    }
}
