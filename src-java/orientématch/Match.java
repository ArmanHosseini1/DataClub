package orientématch;
import orientééquipe.Joueur;
import orientééquipe.Equipe;
import Main.Main;

public class Match {
	private Evenement[] événements = new Evenement[1000];
	private Joueur[] équipe1_joueurs;
	private Equipe équipe1;
	private Equipe équipe2;
	private int nbEvenements;
	private Main main;
	public Match(Equipe équipe1, Equipe équipe2, Main main, Joueur[] équipe1_joueurs) {
		nbEvenements = 0;
		this.équipe1 = équipe1;
		this.main = main;
		this.équipe2 = équipe2;
		this.équipe1_joueurs = équipe1_joueurs;
	}
	public Equipe getEquipe1() {
		return équipe1;
	}
	public Equipe getEquipe2() {
		return équipe2;
	}
	public Joueur[] getEquipe1_joueurs() {
		return équipe1_joueurs;
	}
	public int getnbEvenements() {
		return nbEvenements;
	}
	public Evenement[] getEvenements() {
		return événements;
	}
	//code à factoriser
	public void ajouterEvenement(String type, Joueur joueur, int minute, String équipe) {
		if (type.equals("But")) {
			But but = new But(joueur, minute, équipe);
			événements[nbEvenements] = but;
			nbEvenements ++;
		}
		else if (type.equals("PasseDecisive")) {
			PasseDecisive passedecisive = new PasseDecisive(joueur, minute, équipe);
			événements[nbEvenements] = passedecisive;
			nbEvenements ++;
		}
		else if (type.equals("CartonJaune")) {
			CartonJaune cartonjaune = new CartonJaune(joueur, minute, équipe);
			événements[nbEvenements] = cartonjaune;
			nbEvenements ++;
		}
		else if (type.equals("CartonRouge")) {
			CartonRouge cartonrouge = new CartonRouge(joueur, minute, équipe);
			événements[nbEvenements] = cartonrouge;
			nbEvenements ++;
		}
	}
	public boolean gagné() {
		int goals_1 = 0;
		int goals_2 = 0;
		for (int j=0; j<nbEvenements; j++) {
			if (événements[j] instanceof But) {
				if (événements[j].getEquipe().equals(équipe2.getNom())) {
					goals_2 ++;
				}
				else {
					goals_1++;
				}
			}
		}
		return ((goals_1)>(goals_2));
	}
	
	public boolean présence(Joueur joueur) {
		for (int i = 0; i < équipe1_joueurs.length ; i++) {
			if (((équipe1_joueurs[i]).getNom()).equals(joueur.getNom())){
				return true;
			}
		}
		return false;
	}
}
