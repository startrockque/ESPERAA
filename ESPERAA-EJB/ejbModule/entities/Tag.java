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
    private List<Projet> projetList;

    public Tag() {
        projetList = new ArrayList<Projet>();
    }

    public Tag( String tag ) {
        this.tag = tag;
        projetList = new ArrayList<Projet>();
    }

    public String getTag() {
        return tag;
    }

    public void setTag( String tag ) {
        this.tag = tag;
    }

    public List<Projet> getProjeList() {
        return projetList;
    }

    public void setProjetList( List<Projet> projet ) {
        this.projetList = projet;
    }

    @Override
    public boolean equals( Object obj ) {
        Tag t = (Tag) obj;
        return t.getTag() == this.tag;
    }
}
