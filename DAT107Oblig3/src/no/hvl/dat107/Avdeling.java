package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingId;

    @Column(nullable = false, unique = true)
    private String navn;

    @OneToOne
    @JoinColumn(name = "sjef_id", unique = true)
    private Ansatt sjef;

    @OneToMany(mappedBy = "avdeling")
    private List<Ansatt> ansatte;

    public Avdeling() {}

    public Avdeling(String navn, Ansatt sjef) {
        this.navn = navn;
        this.sjef = sjef;
    }

    public String getNavn() {
        return navn;
    }

    public void setSjef(Ansatt sjef) {
        this.sjef = sjef;
    }
}
