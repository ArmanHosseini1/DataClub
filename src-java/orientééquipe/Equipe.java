package orientééquipe;
import orientématch.Match;
import orientématch.But;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Equipe {
	private String nom;
	private Joueur[] joueurs;
	private int nbJoueurs;
	private int[] stats_equipe = {0,0,0,0};
	private Match[] matchs;
	public void ajouterJoueur(Joueur joueur) {
		joueurs[nbJoueurs] = joueur;
		nbJoueurs ++;
	}
	public void setStats(int int1, int int2, int int3, int int4) {
		if (nbJoueurs>0) {
			joueurs[nbJoueurs-1].setStats(int1, int2, int3, int4);
		}
	}
	public Joueur[] getJoueurs() {
		return joueurs;
	}
	public int getnbJoueurs() {
		return nbJoueurs;
	}
	public Equipe(String nom) {
		this.nom = nom;
		joueurs = new Joueur[100];
		matchs = new Match[1000];
		if (nom!="TFC") {
			Joueur opposant = new Joueur("Opposant", 25, "Default", "Default", nom);
			joueurs[0] = opposant;
			nbJoueurs = 1;
		}
		else {
			nbJoueurs = 0;
		}
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
		String posteTemp = inputOutput("Quel est le poste du joueur?");
		String équipeTemp = inputOutput("Dans quelle équipe joue le joueur?");
		Joueur joueurTemp = new Joueur(nomTemp, ageTemp, nationalitéTemp, posteTemp, équipeTemp);
		joueurs[nbJoueurs] = joueurTemp;
		nbJoueurs ++;
	}
	// il doit y avoir au moins un joueur pour pouvoir ajouter un événement
	public void ajouterEvenement() {
		if (nbJoueurs > 0) {
			String joueurTemp = inputOutput("Quel est le joueur concerné?");
			Joueur joueurConcerné = trouverJoueur(joueurTemp);
			int minute = Integer.parseInt(inputOutput("Quelle est la minute de l'événement?"));
			System.out.println("1. But");
			System.out.println("2. Passe décisive");
			System.out.println("3. Carton Jaune");
			System.out.println("4. Carton Rouge");
			try {
	        	int userInput = Integer.parseInt(inputOutput("Sélectionnez le numéro associé à l'événement que vous souhaitez ajouter."));
	        	if (userInput >= 0 && userInput <=4) {
	        		switch(userInput) {
	        		case 0:
	        			System.exit(0);
	        			break;
	        		case 1: 
	        			joueurConcerné.ajouterBut(minute, getNom());
	        			matchActuel().ajouterEvenement("But", joueurConcerné, minute, getNom());
	        			break;
	        		case 2:
	        			joueurConcerné.ajouterPD(minute, getNom());
	        			matchActuel().ajouterEvenement("PasseDecisive", joueurConcerné, minute, getNom());
	        			break;
	        		case 3:
	        			joueurConcerné.ajouterCJ(minute, getNom());
	        			matchActuel().ajouterEvenement("CartonJaune", joueurConcerné, minute, getNom());
	        			break;
	        		case 4:
	        			joueurConcerné.ajouterCR(minute, getNom());
	        			matchActuel().ajouterEvenement("CartonRouge", joueurConcerné, minute, getNom());
	        			break;
	        		}
	        	} else {
	        		System.out.println("Entrez un nombre entre 0 et 4.");
	        	}
		     }
    		catch (NumberFormatException e) {
	        	System.out.println("Entrez un nombre entre 0 et 4.");
	        }
		}
		else {
			System.out.println("Il faut avoir ajouté au moins un joueur pour ajouter un événement.");
		}
	}
	public Joueur trouverJoueur(String joueurTemp) {
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
	        System.out.println("Erreur de lecture.");
	    }
	    return returnString;
	}
	public void checkStats() {
		actualiserStats();
		System.out.println("Nombre de buts : "+ stats_equipe[0]);
		System.out.println("Nombre de fautes : "+ stats_equipe[2]);
		System.out.println("Nombre de cartons jaunes : "+ stats_equipe[3]);
		System.out.println("Nombre de cartons rouges : "+ stats_equipe[4]);
	}
	public void actualiserStats() {
		for (int i=0; i<nbJoueurs; i++) {
			stats_equipe[0] += joueurs[i].getStats()[0];
			stats_equipe[1] += joueurs[i].getStats()[1];
			stats_equipe[2] += joueurs[i].getStats()[2];
			stats_equipe[3] += joueurs[i].getStats()[3];
		}
	}
	public void checkIndStats() {
		if (nbJoueurs > 0) {
			String joueurTemp = inputOutput("Quel est le joueur dont vous souhaitez voir les statistiques?");
			Joueur joueurConcerné = trouverJoueur(joueurTemp);
			System.out.println("Nombre de buts : "+ joueurConcerné.getStats()[0]);
			System.out.println("Nombre de passes décisives : "+ joueurConcerné.getStats()[1]);
			System.out.println("Nombre de cartons jaunes : "+ joueurConcerné.getStats()[2]);
			System.out.println("Nombre de cartons rouges : "+ joueurConcerné.getStats()[3]);
		}
		else {
			System.out.println("Vous devez d'abord créer un joueur.");
		}
	}
	public void avantMatch() {
		String équipeTemp = inputOutput("Quelle est l'équipe à affronter?");
		System.out.println(TailleEffective());
		System.out.println(matchs[2].getEquipe2().getNom());
		//String homeDirectory = System.getProperty("user.home");
		//Process process;
		//boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		//if (isWindows) {
		    //process = Runtime.getRuntime().exec(String.format("cmd.exe /c dir %s", homeDirectory));
		//} else {
		   // process = Runtime.getRuntime().exec(String.format("/bin/sh -c ls %s", homeDirectory));
		//}
		//StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
		//Future future = Executors.newSingleThreadExecutor().submit(streamGobbler);

		//int exitCode = process.waitFor();
		//assert exitCode == 0;

		//future.get();
		// Passer en OCaml 
		for (int i=0; i<TailleEffective();i++) {
			if ((matchs[i].getEquipe2().getNom()).equals(équipeTemp)) {
				int goals_1 = 0;
				int goals_2 = 0;
				System.out.println(matchs[i].getnbEvenements());
				for (int j=0; j<matchs[i].getnbEvenements(); j++) {
					if ((matchs[i].getEvenements())[j] instanceof But) {
						if ((matchs[i].getEvenements()[j].getEquipe()).equals(nom)) {
							goals_1 ++;
						}
						else {
							goals_2++;
						}
					}
				}
				System.out.println(nom + " " + goals_1 + " - "+ goals_2 + équipeTemp);
			}
		}
	}
	public int TailleEffective() {
		int i = 0;
		boolean nullFound = false;
		while (!(nullFound) && (i<1000)){
			if (matchs[i]!=null) {
				i = i+1;
			}
			else {
				nullFound = true;
			}
		}
		return i;
	}
	public void changeMatch(Match match) {
		matchs[TailleEffective()] = match;
	}
	public Match matchActuel() {
		return matchs[TailleEffective()-1];
	}
	public void meilleursJoueurs() {
		int[] stats = new int[100];
		for (int i=0; i<nbJoueurs;i++) {
			stats[i] = 0;
		}
		for (int j=0; j<TailleEffective() ; j++) {
			for (int i=0; i<nbJoueurs;i++) {
				if ((matchs[j].gagné()) && (matchs[j].présence(joueurs[i]))){
					stats[i]++;
				}
			}
		}
		System.out.println("Meilleurs joueurs :");
		for (int i=0; i<nbJoueurs ; i++) {
			if (stats[i]>(TailleEffective()*0.4)) {
				System.out.println(joueurs[i].getNom() + " : " + ((stats[i]/TailleEffective())*100) + " % ");
			}
		}
		
	}
	public void setMatch(Match match) {
		if (TailleEffective()<1000) {
			matchs[TailleEffective()] = match;
		}
		else {
			System.out.println("L'équipe a déjà atteint les 1000 matchs.");
		}
	}
}
