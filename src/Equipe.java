import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

public class Equipe {
	private String nom;
	private Joueur[] joueurs;
	private int nbJoueurs = 0;
	private int[] Stats = {0,0,0,0,0};
	private Match[] matchs;
	
	public Equipe(String nom) {
		this.nom = nom;
		joueurs = new Joueur[100];
		matchs = new Match[1000];
	}
	public String getNom() {
		return nom;
	}
	public Match[] getMatchs() {
		return matchs;
	}
	
	public void ajouterJoueur() {
		String nomTemp = inputOutput("Quel est le nom du joueur?");
		int ageTemp = Integer.parseInt(inputOutput("Quel est l'âge du joueur?"));
		String nationalitéTemp = inputOutput("Quel est la nationalité du joueur?");
		int tailleTemp = Integer.parseInt(inputOutput("Quel est la taille du joueur?"));
		String posteTemp = inputOutput("Quel est le poste du joueur?");
		String équipeTemp = inputOutput("Dans quelle équipe joue le joueur?");
		Joueur JoueurTemp = new Joueur(nomTemp, ageTemp, nationalitéTemp, tailleTemp, posteTemp, équipeTemp);
		joueurs[nbJoueurs] = JoueurTemp;
		nbJoueurs ++;
	}
	// il doit y avoir au moins un joueur pour pouvoir ajouter un événement
	public void ajouterEvenement() {
		if (nbJoueurs > 0) {
			String équipe = inputOutput("Contre quelle équipe?");
			String joueurTemp = inputOutput("Quel est le joueur concerné?");
			Joueur JoueurConcerné = findJoueur(joueurTemp);
			int minute = Integer.parseInt(inputOutput("Quelle est la minute de l'événement?"));
			System.out.println("1. But");
			System.out.println("2. Passe décisive");
			System.out.println("3. Faute");
			System.out.println("4. Carton Jaune");
			System.out.println("5. Carton Rouge");
			try {
	        	int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));
	        	if (userInput >= 0 && userInput <=2) {
	        		switch(userInput) {
	        		case 0:
	        			System.exit(0);
	        			break;
	        		case 1: 
	        			JoueurConcerné.ajouterBut(minute, équipe);
	        			currentMatch().addEvenement("But", JoueurConcerné, minute, équipe);
	        			break;
	        		case 2:
	        			JoueurConcerné.ajouterPD(minute, équipe);
	        			currentMatch().addEvenement("Passe décisive", JoueurConcerné, minute, équipe);
	        			break;
	        		case 3:
	        			JoueurConcerné.ajouterFaute(minute, équipe);
	        			currentMatch().addEvenement("Faute", JoueurConcerné, minute, équipe);
	        			break;
	        		case 4:
	        			JoueurConcerné.ajouterCJ(minute, équipe);
	        			currentMatch().addEvenement("Carton jaune", JoueurConcerné, minute, équipe);
	        			break;
	        		case 5:
	        			JoueurConcerné.ajouterCR(minute, équipe);
	        			currentMatch().addEvenement("Carton rouge", JoueurConcerné, minute, équipe);
	        			break;
	        		}
	        	} else {
	        		System.out.println("Please enter a number from 0 - 5");
	        	}
		     }
    		catch (NumberFormatException e) {
	        	System.out.println("Please enter a number from 0 - 5");
	        }
		}
		else {
			System.out.println("Il faut avoir ajouté au moins un joueur pour ajouter un événement.");
		}
	}
	public Joueur findJoueur(String joueurTemp) {
		int i = 0;
		while (true) {
			if (i < nbJoueurs) {
				if ((joueurs[i].getNom()).equals(joueurTemp)) {
					return joueurs[i];
				}
				else {
					i++;
				}
			}
			else {
				System.out.println("Le joueur n'a pas été trouvé. La donnée va être attribuée par défaut à "+ joueurs[0].getNom());
				return joueurs[0];
			}
		}
	}
	private String inputOutput(String message) {
	    System.out.println(message);
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String returnString = "";
	    try {
	        returnString = br.readLine();
	    }
	    catch (IOException e){
	        System.out.println("Error reading in value");
	    }
	    return returnString;
	}
	public void modifierNom() {
		System.out.println("Nom actuel : "+ nom);
		nom = inputOutput("Quel nom voulez-vous donner à votre équipe?");
	}
	public void checkStats() {
		actualiserStats();
		System.out.println("Nombre de buts : "+ Stats[0]);
		System.out.println("Nombre de fautes : "+ Stats[2]);
		System.out.println("Nombre de cartons jaunes : "+ Stats[3]);
		System.out.println("Nombre de cartons rouges : "+ Stats[4]);
	}
	public void actualiserStats() {
		for (int i=0; i<nbJoueurs; i++) {
			Stats[0] += joueurs[i].getStats()[0];
			Stats[1] += joueurs[i].getStats()[1];
			Stats[2] += joueurs[i].getStats()[2];
			Stats[3] += joueurs[i].getStats()[3];
			Stats[4] += joueurs[i].getStats()[4];
		}
	}
	public void checkIndStats() {
		if (nbJoueurs > 0) {
			String joueurTemp = inputOutput("Quel est le joueur dont vous souhaitez voir les statistiques?");
			Joueur JoueurConcerné = findJoueur(joueurTemp);
			System.out.println("Nombre de buts : "+ JoueurConcerné.getStats()[0]);
			System.out.println("Nombre de passes décisives : "+ JoueurConcerné.getStats()[1]);
			System.out.println("Nombre de fautes : "+ JoueurConcerné.getStats()[2]);
			System.out.println("Nombre de cartons jaunes : "+ JoueurConcerné.getStats()[3]);
			System.out.println("Nombre de cartons rouges : "+ JoueurConcerné.getStats()[4]);
		}
		else {
			System.out.println("Vous devez d'abord créer un joueur.");
		}
	}
	public void avantMatch() throws Exception {
		String équipeTemp = inputOutput("Quelle est l'équipe à affronter?");
		String homeDirectory = System.getProperty("user.home");
		Process process;
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		if (isWindows) {
		    process = Runtime.getRuntime().exec(String.format("cmd.exe /c dir %s", homeDirectory));
		} else {
		    process = Runtime.getRuntime().exec(String.format("/bin/sh -c ls %s", homeDirectory));
		}
		StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
		Future future = Executors.newSingleThreadExecutor().submit(streamGobbler);

		int exitCode = process.waitFor();
		assert exitCode == 0;

		future.get();
		// Passer en OCaml 
	}
	public int ActualLength() {
		int i = 0;
		boolean NullFound = false;
		while ((NullFound == false) && (i<1000)){
			if (matchs[i]!=null) {
				i = i+1;
			}
			else {
				NullFound = true;
			}
		}
		return i;
	}
	public void changeMatch(Equipe équipe) {
		matchs[ActualLength()] = new Match(équipe);
	}
	public Match currentMatch() {
		
		return matchs[ActualLength()-1];
	}
}
