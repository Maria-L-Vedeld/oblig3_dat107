package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("AnsattPU");

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EntityManager em = emf.createEntityManager();
		AnsattDAO ansattDAO = new AnsattDAO(em);

		while (true) {
			System.out.println("\n--- HOVEDMENY ---");
			System.out.println("1. Finn ansatt med ID");
			System.out.println("2. Finn ansatt med brukernavn");
			System.out.println("3. List ut alle ansatte");
			System.out.println("4. Oppdater ansatt sin stilling/lønn");
			System.out.println("5. Legg til ny ansatt");
			System.out.println("6. Søk etter avdeling");
			System.out.println("7. Ansatte i avdeling");
			System.out.println("8. Legg til avdeling");
			System.out.println("8. Legg til nytt prosjekt");
			System.out.println("0. Avslutt");
			System.out.print("Velg et alternativ: ");

			int valg = scanner.nextInt();
			scanner.nextLine(); // Fjerner newline

			switch (valg) {
			case 1:
				System.out.print("Skriv inn ansatt-ID: ");
				int id = scanner.nextInt();
				scanner.nextLine();
				Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
				if (ansatt != null)
					ansatt.skrivUt();
				else
					System.out.println("Ansatt ikke funnet.");
				break;

			case 2:
				System.out.print("Skriv inn brukernavn: ");
				String brukernavn = scanner.nextLine();
				ansatt = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
				if (ansatt != null)
					ansatt.skrivUt();
				else
					System.out.println("Ansatt ikke funnet.");
				break;

			case 3:
				List<Ansatt> ansatte = ansattDAO.hentAlleAnsatte();
				if (ansatte.isEmpty()) {
					System.out.println("Ingen ansatte registrert.");
				} else {
					ansatte.forEach(Ansatt::skrivUt);
				}
				break;

			case 4:
				System.out.print("Skriv inn ansatt-ID: ");
				id = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Ny stilling (eller trykk ENTER for å beholde): ");
				String nyStilling = scanner.nextLine();
				System.out.print("Ny månedslønn (eller -1 for å beholde): ");
				double nyLonn = scanner.nextDouble();
				scanner.nextLine();

				ansattDAO.oppdaterAnsatt(id, nyStilling.isEmpty() ? null : nyStilling, nyLonn == -1 ? null : nyLonn);
				System.out.println("Ansatt oppdatert.");
				break;

			case 5:
				System.out.print("Brukernavn (3-4 bokstaver): ");
				String nyttBrukernavn = scanner.nextLine();
				System.out.print("Fornavn: ");
				String fornavn = scanner.nextLine();
				System.out.print("Etternavn: ");
				String etternavn = scanner.nextLine();
				System.out.print("Dato for ansettelse (YYYY-MM-DD): ");
				LocalDate datoAnsatt = LocalDate.parse(scanner.nextLine());
				System.out.print("Stilling: ");
				String stilling = scanner.nextLine();
				System.out.print("Månedslønn: ");
				double manedslonn = scanner.nextDouble();
				scanner.nextLine();

				Ansatt nyAnsatt = new Ansatt(nyttBrukernavn, fornavn, etternavn, datoAnsatt, stilling, manedslonn);
				ansattDAO.leggTilAnsatt(nyAnsatt);
				System.out.println("Ny ansatt registrert!");
				break;

			case 0:
				System.out.println("Avslutter programmet.");
				em.close();
				emf.close();
				scanner.close();
				return;

			default:
				System.out.println("Ugyldig valg, prøv igjen.");
			case 6:
				System.out.print("Skriv inn avdeling-ID: ");
				int avdelingId = scanner.nextInt();
				Avdeling avdeling = AvdelingDAO.finnAvdelingMedId(avdelingId);
				if (avdeling != null) {
					System.out.println("Avdeling: " + avdeling.getNavn());
				} else {
					System.out.println("Avdeling ikke funnet.");
				}
				break;

			case 7:
				System.out.print("Skriv inn ansatt-ID: ");
				int ansattId = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Ny avdeling-ID: ");
				int nyAvdelingId = scanner.nextInt();
				scanner.nextLine();

				Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);
				Avdeling nyAvdeling = AvdelingDAO.finnAvdelingMedId(nyAvdelingId);

				if (ansatt != null && nyAvdeling != null) {
					ansatt.setAvdeling(nyAvdeling);
					ansattDAO.oppdaterAnsatt(ansattId, null, null);
					System.out.println("Ansatt flyttet til ny avdeling.");
				} else {
					System.out.println("Feil ved oppdatering av avdeling.");
				}
				break;
			case 8:
				System.out.print("Navn på ny avdeling: ");
				String avdNavn = scanner.nextLine();
				System.out.print("Sjefens ansatt-ID: ");
				int sjefId = scanner.nextInt();
				scanner.nextLine();

				Ansatt sjef = ansattDAO.finnAnsattMedId(sjefId);
				if (sjef != null) {
					Avdeling nyAvdeling = new Avdeling(avdNavn, sjef);
					AvdelingDAO.leggTilAvdeling(nyAvdeling);
					System.out.println("Ny avdeling registrert.");
				} else {
					System.out.println("Fant ikke sjef.");
				}
				break;
			case 9:
				System.out.print("Navn på prosjekt: ");
				String prosjektNavn = scanner.nextLine();
				System.out.print("Beskrivelse av prosjekt: ");
				String prosjektBeskrivelse = scanner.nextLine();

				Prosjekt nyttProsjekt = new Prosjekt(prosjektNavn, prosjektBeskrivelse);
				ProsjektDAO.leggTilProsjekt(nyttProsjekt);
				System.out.println("Prosjekt lagt til!");
				break;
			}
		}
	}
}