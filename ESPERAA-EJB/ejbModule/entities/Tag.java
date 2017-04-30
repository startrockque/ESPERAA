package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
    @Id
    private String       tag;

    @ManyToMany
    private List<Cheval> chevauxList;

    public Tag() {
        chevauxList = new ArrayList<Cheval>();
    }

    public Tag( String tag ) {
        this.tag = tag;
        chevauxList = new ArrayList<Cheval>();
    }

    public String getTag() {
        return tag;
    }

    public void setTag( String tag ) {
        this.tag = tag;
    }

    public List<Cheval> getChevauxList() {
        return chevauxList;
    }

    public void setChevalList( List<Cheval> chevaux ) {
        this.chevauxList = chevaux;
    }

    @Override
    public boolean equals( Object obj ) {
        Tag t = (Tag) obj;
        return t.getTag() == this.tag;
    }
}
