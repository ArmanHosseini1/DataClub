import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private Equipe[] équipes;
	public Main(String équipe){
		équipes = new Equipe[1000];
		Equipe Team = new Equipe(équipe);
		équipes[0] = Team;
	}
	public static void main(String[] args) {
		Main main = new Main("TFC");
		System.out.println((main.équipes[0]).getNom());
		main.MainMenu();
	}
	public void MainMenu() {
		while (true) {
	        System.out.println("1. Edit your team.");
	        System.out.println("2. Check your team's global stats.");
	        System.out.println("3. Check individual stats.");
	        System.out.println("4. Avant-match.");
	        System.out.println("5. Interesting combinations.");
	        System.out.println("6. Add match data.");
	        System.out.println("7. Save data.");
	        System.out.println("8. Load data.");
	        System.out.println("9. Add new team.");
	        System.out.println("0. Exit\n");
	        try {
	        	int userInput = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));
	        	
	        	if (userInput >= 0 && userInput <=9) {
	        		switch(userInput) {
	        		case 9:
	        			String équipeTemp = inputOutput("Quel est le nom de l'équipe?");
	        			if (ActualLength() < 1000) 
	        			{
	        				équipes[ActualLength()] = new Equipe(équipeTemp);
	        			}
	        			else {
	        				System.out.println("Il y a déjà trop d'équipes, impossible d'en créer une nouvelle.");
	        			}
	        			break;
	        			case 1:
	        				équipes[0].modifierNom();
	        				break;
	        			case 2:
	        				équipes[0].checkStats();
	        				break;
	        			case 3:
	        				équipes[0].checkIndStats();
	        				break;
	        			case 4:
	        				équipes[0].avantMatch();
	        				break;
	        			case 5:
	        				break;
	        			case 6: 
	        				System.out.println("1. Add a player.");
	        		        System.out.println("2. Add an event.");
	        		        System.out.println("3. Add a match.");
	        		        try {
	        		        	int userInput2 = Integer.parseInt(inputOutput("Please press the number that corresponds to what you would like the coffee maker to do."));
	        		        	
	        		        	if (userInput2 >= 0 && userInput2 <=3) {
	        		        		switch(userInput2) {
	        		        		case 0:
	        		        			System.exit(0);
	        		        			break;
	        		        		case 1: 
	        		        			if (ActualLength()>0) {
	        		        			String équipeTemp4 = inputOutput("Quelle est l'équipe du joueur?");
	        		        			Equipe équipeConcernée = findEquipe(équipeTemp4);
	        		        			équipeConcernée.ajouterJoueur();
	        		        			}
	        		        			else {
	        		        				System.out.println("Aucune équipe n'est disponible.");
	        		        			}
	        		        			break;
	        		        		case 2:
	        		        			if (équipes[0].getMatchs()!=null){
	        		        				System.out.println("Match actuel : "+ équipes[0].getNom() + " - " +  ((équipes[0].currentMatch()).getEquipe()).getNom());
	        		        				String équipeTemp2 = inputOutput("Quelle est l'équipe du joueur concerné?");
		        		        			Equipe équipeConcernée = findEquipeEff(équipeTemp2);
		        		        			équipeConcernée.ajouterEvenement();
	        		        			}
	        		        			else {
	        		        				System.out.println("Aucune match n'a été créé.");
	        		        			}
	        		        			break;
	        		        		case 3: 
	        		        			String équipeTemp3 = inputOutput("Contre quelle équipe?");
	        		        			Equipe équipeConcernée = findEquipe(équipeTemp3);
	        		 // convertir les variables en attributs du main
	        		        			équipes[0].changeMatch(équipeConcernée);
	        		        		}
	        		        	} else {
	        		        		System.out.println("Please enter a number from 0 - 3");
	        		        	}
	        		        }
	        		catch (NumberFormatException e) {
	    	        	System.out.println("Please enter a number from 0 - 6");
	    	        }
	        		        	
	        				break;
	        			case 0:
	        				System.exit(0);
	        				break;
	        		}
	        			
	        	} else {
	        		System.out.println("Please enter a number from 0 - 6");
	        	}
	        } catch (NumberFormatException e) {
	        	System.out.println("Please enter a number from 0 - 6");
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
        MainMenu();
    }
    return returnString;
}
public Equipe findEquipeEff(String équipeTemp) {
			if ((équipes[0].getNom()).equals(équipeTemp)) {
				return équipes[0];
			}
			else if (équipes[0].getMatchs()[0]!=null) {
				if ((((équipes[0].currentMatch()).getEquipe()).getNom()).equals(équipeTemp)){
				return ((équipes[0].currentMatch()).getEquipe());
			}
				else {
					System.out.println("ATTENTION! L'équipe n'a pas été trouvée et la donnée va donc être assignée à l'équipe par défaut!!");
					return équipes[0];
				}
			}
		else {
			System.out.println("ATTENTION! L'équipe n'a pas été trouvée et la donnée va donc être assignée à l'équipe par défaut!!");
			équipes[0].changeMatch(équipes[0]);
			return équipes[0];
		}
	}

public Equipe findEquipe(String équipeTemp) {
	int i = 0;
	System.out.println(équipeTemp);
	while (i<ActualLength())
	{ if ((équipes[i].getNom()).equals(équipeTemp)) {
		return équipes[i];
	}
	System.out.println(équipes[i].getNom());
	i =i+1;
	}
	System.out.println("Taille : " + ActualLength());
	System.out.println("ATTENTION! L'équipe n'a pas été trouvée et la donnée va donc être assignée à l'équipe par défaut!!");
	return équipes[0];
}

public int ActualLength() {
	int i = 0;
	boolean NullFound = false;
	while ((NullFound == false) && (i<1000)){
		if (équipes[i]!=null) {
			i = i+1;
		}
		else {
			NullFound = true;
		}
	}
	return i;
}
}
