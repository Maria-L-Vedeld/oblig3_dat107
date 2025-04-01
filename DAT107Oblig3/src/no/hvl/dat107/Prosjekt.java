package no.hvl.dat107;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Prosjekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjektId;

    @Column(nullable = false, unique = true)
    private String navn;
    
    private String beskrivelse;

    @ManyToMany
    @JoinTable(
        name = "Prosjektdeltagelse",
        joinColumns = @JoinColumn(name = "prosjekt_id"),
        inverseJoinColumns = @JoinColumn(name = "ansatt_id")
    )
    private List<Ansatt> ansatte;

    public Prosjekt() {}

    public Prosjekt(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public String getNavn() {
        return navn;
    }
}
