package Main;
import java.io.BufferedReader;
import orientééquipe.Equipe;
import orientééquipe.Joueur;
import orientématch.Match;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private Equipe[] équipes;
	private final static String ATTENTION = "ATTENTION! L'équipe n'a pas été trouvée et la donnée va donc être assignée à l'équipe par défaut!!";
	private final static String ENTREZ06 = "Entrez un nombre entre 0 et 6.";
	public Main(){
		équipes = new Equipe[1000];
		Equipe TFC = new Equipe("TFC");
		équipes[0] = TFC;
	}
	public Equipe[] getEquipes() {
		return équipes;
	}
	public void setEquipe(Equipe équipe) {
		équipes[TailleEffective()] = équipe;
	}
	public Equipe getEquipe() {
		return équipes[0];
	}
	public static void main(String[] args) {
		Main main = new Main();
		for (int k=0; k<main.TailleEffective();k++) {
		System.out.println((main.équipes[k]).getNom());
		}
		main.MenuPrincipal();
	}
	public void ajouterJoueur(Joueur joueur) {
		équipes[0].ajouterJoueur(joueur);
	}
	public void setStats(int int1, int int2, int int3, int int4) {
		équipes[0].setStats(int1, int2, int3, int4);
	}
	public void MenuPrincipal() {
		while (true) {
	        System.out.println("1. Regarder les statistiques globales de l'équipe.");
	        System.out.println("2. Regarder les statistiques individuelles d'un joueur.");
	        System.out.println("3. Avant-match.");
	        System.out.println("4. Meilleurs joueurs.");
	        System.out.println("5. Ajouter des données de match.");
	        System.out.println("6. Sauvegarder les données.");
	        System.out.println("7. Télécharger les données.");
	        System.out.println("8. Ajouter une nouvelle équipe.");
	        System.out.println("0. Exit\n");
	        try {
	        	int userInput = Integer.parseInt(inputOutput("Sélectionnez le numéro associé à la fonctionnalité que vous souhaitez utiliser."));
	        	
	        	if (userInput >= 0 && userInput <=9) {
	        		switch(userInput) {
	        		case 8:
	        			String équipeTemp = inputOutput("Quel est le nom de l'équipe?");
	        			if (TailleEffective() < 1000) 
	        			{
	        				équipes[TailleEffective()] = new Equipe(équipeTemp);
	        			}
	        			else {
	        				System.out.println("Il y a déjà trop d'équipes, impossible d'en créer une nouvelle.");
	        			}
	        			break;
	        			case 1:
	        				équipes[0].checkStats();
	        				break;
	        			case 2:
	        				équipes[0].checkIndStats();
	        				break;
	        			case 6:
	        				WriteToCsvJava.SaveFile(équipes[0], this);
	        				break;
	        			case 7:
	        				LoadDataFromCSV.LoadFile1(this);
	        				LoadDataFromCSV.LoadFile2(this);
	        				LoadDataFromCSV.LoadFile3(this);
	        				break;
	        			case 3:
	        				équipes[0].avantMatch();
	        				break;
	        			case 4:
	        				équipes[0].meilleursJoueurs();
	        				break;
	        			case 5: 
	        				System.out.println("1. Ajouter un joueur.");
	        		        System.out.println("2. Ajouter un événement.");
	        		        System.out.println("3. Ajouter un match.");
	        		        try {
	        		        	int userInput2 = Integer.parseInt(inputOutput("Sélectionnez le numéro associé à la fonctionnalité que vous souhaitez utiliser."));
	        		        	
	        		        	if (userInput2 >= 0 && userInput2 <=3) {
	        		        		switch(userInput2) {
	        		        		case 0:
	        		        			System.exit(0);
	        		        			break;
	        		        		case 1: 
	        		        			if (TailleEffective()>0) {
	        		        			String équipeTemp4 = inputOutput("Quelle est l'équipe du joueur?");
	        		        			Equipe équipeConcernée = trouverEquipe(équipeTemp4);
	        		        			équipeConcernée.ajouterJoueur();
	        		        			}
	        		        			else {
	        		        				System.out.println("Aucune équipe n'est disponible.");
	        		        			}
	        		        			break;
	        		        		case 2:
	        		        			if ((équipes[0].getMatchs())[0]!=null){
	        		        				System.out.println("Match actuel : "+ équipes[0].getNom() + " - " +  ((équipes[0].matchActuel()).getEquipe2()).getNom());
	        		        				String équipeTemp2 = inputOutput("Quelle est l'équipe du joueur concerné?");
		        		        			Equipe équipeConcernée = trouverEquipeEff(équipeTemp2);
		        		        			équipeConcernée.ajouterEvenement();
	        		        			}
	        		        			else {
	        		        				System.out.println("Aucun match n'a été créé.");
	        		        			}
	        		        			break;
	        		        		case 3: 
	        		        			String équipeTemp3 = inputOutput("Contre quelle équipe?");
	        		        			Equipe équipeConcernée = trouverEquipe(équipeTemp3);
	        		 // convertir les variables en attributs du main
	        		        			Joueur[] équipe1_joueurs = new Joueur[11];
	        		        			for (int i=0;i<11;i++) {
	        		        				String temp = inputOutput("Quel est le nom du joueur " + i + " de l'équipe " + équipes[0].getNom()+ " ?");
	        		        				équipe1_joueurs[i] = équipes[0].trouverJoueur(temp);
	        		        			}
	        		        			Match match = new Match(équipes[0], équipeConcernée, this, équipe1_joueurs);
	        		        			équipes[0].changeMatch(match);
	        		        			équipeConcernée.changeMatch(match);
	        		        			break;
	        		        		}
	        		        	} else {
	        		        		System.out.println("Entrez un nombre entre 0 et 3.");
	        		        	}
	        		        }
	        		catch (NumberFormatException e) {
	    	        	System.out.println(ENTREZ06);
	    	        }
	        		        	
	        				break;
	        			case 0:
	        				System.exit(0);
	        				break;
	        		}
	        			
	        	} else {
	        		System.out.println(ENTREZ06);
	        	}
	        } catch (NumberFormatException e) {
	        	System.out.println(ENTREZ06);
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
        MenuPrincipal();
    }
    return returnString;
}
public Equipe trouverEquipeEff(String équipeTemp) {
			if ((équipes[0].getNom()).equals(équipeTemp)) {
				return équipes[0];
			}
			else if (équipes[0].getMatchs()[0]!=null) {
				if ((((équipes[0].matchActuel()).getEquipe2()).getNom()).equals(équipeTemp)){
				return ((équipes[0].matchActuel()).getEquipe2());
			}
				else {
					System.out.println(ATTENTION);
					return équipes[0];
				}
			}
		else {
			System.out.println(ATTENTION);
			return équipes[0];
		}
	}

public Equipe trouverEquipe(String équipeTemp) {
	int i = 0;
	System.out.println(équipeTemp);
	while (i<TailleEffective())
	{ if ((équipes[i].getNom()).equals(équipeTemp)) {
		return équipes[i];
	}
	System.out.println(équipes[i].getNom());
	i =i+1;
	}
	System.out.println("Taille : " + TailleEffective());
	System.out.println(ATTENTION);
	return équipes[0];
}

public int TailleEffective() {
	int i = 0;
	boolean nullFound = false;
	while (!(nullFound) && (i<1000)){
		if (équipes[i]!=null) {
			i = i+1;
		}
		else {
			nullFound = true;
		}
	}
	return i;
}
}
