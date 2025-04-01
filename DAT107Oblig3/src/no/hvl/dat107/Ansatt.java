package no.hvl.dat107;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ansattId;

    @Column(unique = true, nullable = false)
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate datoAnsatt;
    private String stilling;
    private double manedslonn;

    @ManyToOne
    @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    public Ansatt() {}

    public Ansatt(String brukernavn, String fornavn, String etternavn, LocalDate datoAnsatt, String stilling, double manedslonn) {
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.datoAnsatt = datoAnsatt;
        this.stilling = stilling;
        this.manedslonn = manedslonn;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    public Avdeling getAvdeling() {
        return avdeling;
    }

    public void skrivUt() {
        System.out.println("Ansatt-ID: " + ansattId + ", Navn: " + fornavn + " " + etternavn +
                ", Stilling: " + stilling + ", Lønn: " + manedslonn +
                ", Avdeling: " + (avdeling != null ? avdeling.getNavn() : "Ingen"));
    }
}
