package com.groupfive.krombacherkneipenquiz.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kneipe")
@EntityListeners(AuditingEntityListener.class)
public class Kneipe
{
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name,adresse;
    private long benutzerid;

    public List<FragenPaket> getFragenpakete() {
        return fragenpakete;
    }

    public void setFragenpakete(List<FragenPaket> fragenpakete) {
        this.fragenpakete = fragenpakete;
    }

    @ManyToMany
    List<FragenPaket> fragenpakete;


   public Kneipe(String name, String adresse, long benutzer_id)
    {
        this.name = name;
        this.adresse = adresse;
        this.benutzerid = benutzer_id;
    }

    public Kneipe() {
    }

    public String getName() {
        return name;
    }                   // getter und setter

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public long getBenutzer_id() {
        return benutzerid;
    }

    public void setBenutzer_id(long benutzer_id) {
        this.benutzerid = benutzer_id;
    }

}
