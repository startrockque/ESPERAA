package dto;

import java.io.Serializable;

import entities.Tranche;

public class TrancheDTO implements Comparable<TrancheDTO>, Serializable {
    private static final long serialVersionUID = 1L;

    private int               idTranche;
    private int               montantTranche;
    private String            compensation;

    public TrancheDTO() {
        super();
    }

    public TrancheDTO( Tranche t ) {
        this.idTranche = t.getIdTranche();
        this.compensation = t.getCompensation();
        this.montantTranche = t.getMontantTranche();
    }

    public TrancheDTO( String compensation, int montant ) {
        this.compensation = compensation;
        this.montantTranche = montant;
    }

    public int getIdTranche() {
        return idTranche;
    }

    public void setIdTranche( int idTranche ) {
        this.idTranche = idTranche;
    }

    public int getMontantTranche() {
        return montantTranche;
    }

    public void setMontantTranche( int montantTranche ) {
        this.montantTranche = montantTranche;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation( String compensation ) {
        this.compensation = compensation;
    }

    @Override
    public int compareTo( TrancheDTO o ) {
        TrancheDTO t = (TrancheDTO) o;
        return (int) ( this.montantTranche - t.getMontantTranche() );
    }
}
