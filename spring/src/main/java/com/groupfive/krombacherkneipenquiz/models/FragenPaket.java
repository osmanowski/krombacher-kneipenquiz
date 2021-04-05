package com.groupfive.krombacherkneipenquiz.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Fragenpaket")
public class FragenPaket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paket_id", nullable = false)
    private long paket_id;

    public String getPaketname() {
        return Paketname;
    }

    public void setPaketname(String paketname) {
        Paketname = paketname;
    }

    @Column (name = "Paketname", nullable = false)
    private String Paketname;
    //private ArrayList<Frage> fragen;

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Fragenpaket_Frage",
            joinColumns = {@JoinColumn(name = "paket_id")},
            inverseJoinColumns = { @JoinColumn (name = "frage_id")}
    )
    Set<Frage> fragen = new HashSet<>();



    public void setKneipen(List<Kneipe> kneipen) {
        this.kneipen = kneipen;
    }

    @ManyToMany (mappedBy = "fragenpakete")
    List<Kneipe> kneipen;


    public long getId() {
        return paket_id;
    }

    public void setId(long id) {
        this.paket_id = id;
    }


    public void addFrage(Frage frage)
    {
        fragen.add(frage);
    }

    public List<Frage> getRandomFragen(int anzahl)
    {
        List<Frage> result = new ArrayList<>();

        int size = fragen.size();
        if (anzahl > size)
        {
            anzahl = size;
        }
        int item = 0;

        List<Integer> picked = new ArrayList<>();

        for (int x = 1; x <= anzahl; x++)
        {

            do {
                item = new Random().nextInt(size);

            }
            while (picked.contains(item));
            int i = 0;

            for (Frage f : fragen)
            {
                if (i == item)
                {
                    result.add(f);
                    picked.add(item);
                }
                i++;
            }
        }



        return result;
    }
    /*
    public ArrayList<Frage> getFragen() {
        return fragen;
    }



    public void setFragen(ArrayList fragen) {
        this.fragen = fragen;
    }
    */

}
