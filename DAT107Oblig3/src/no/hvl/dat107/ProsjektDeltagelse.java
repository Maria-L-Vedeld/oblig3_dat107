package no.hvl.dat107;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProsjektDeltagelse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjekt_id")
    private Prosjekt prosjekt;

    private String rolle;
    private int timer;

    public ProsjektDeltakelse() {}

    public ProsjektDeltakelse(Ansatt ansatt, Prosjekt prosjekt, String rolle, int timer) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.rolle = rolle;
        this.timer = timer;
    }

    @Override
    public String toString() {
        return ansatt.getFornavn() + " (" + rolle + ", " + timer + " timer)";
    }
}
