package dto;

import java.io.Serializable;

import entities.Investissement;

public class InvestissementDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int               idInvestissement;
    private int               idCheval;
    private int               sommeInvestie;
    private String            donateur;
    private String            nomCheval;
    private String            compensation;

    public InvestissementDTO( Investissement investissement ) {
        this.idInvestissement = investissement.getIdInvestissement();
        this.idCheval = investissement.getTranche().getCheval().getIdCheval();
        this.sommeInvestie = investissement.getSommeInvestie();
        this.donateur = investissement.getDonateur().getLogin();
        this.nomCheval = investissement.getTranche().getCheval().getNomCheval();
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
        return donateur;
    }

    public void setFinanceur( String financeur ) {
        this.donateur = financeur;
    }

    public String getTitreCheval() {
        return nomCheval;
    }

    public void setTitreCheval( String titreCheval ) {
        this.nomCheval = titreCheval;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation( String compensation ) {
        this.compensation = compensation;
    }

    public int getIdCheval() {
        return idCheval;
    }

    public void setIdCheval( int idCheval ) {
        this.idCheval = idCheval;
    }
}
