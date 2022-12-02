import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Match {
	private Evenement[] événements = new Evenement[1000];
	private Equipe équipe2;
	private int nbEvenements = 0;
	public Match(Equipe équipe2) {
		this.équipe2 = équipe2;
	}
	public Equipe getEquipe() {
		return équipe2;
	}
	//code à factoriser
	public void addEvenement(String type, Joueur joueur, int minute, String équipe) {
		if (type=="Faute") {
			Faute faute = new Faute(joueur, minute, équipe);
			événements[nbEvenements] = faute;
			nbEvenements ++;
		}
		else if (type=="But") {
			But but = new But(joueur, minute, équipe);
			événements[nbEvenements] = but;
			nbEvenements ++;
		}
		else if (type=="PasseDecisive") {
			PasseDecisive passedecisive = new PasseDecisive(joueur, minute, équipe);
			événements[nbEvenements] = passedecisive;
			nbEvenements ++;
		}
		else if (type=="CartonJaune") {
			CartonJaune cartonjaune = new CartonJaune(joueur, minute, équipe);
			événements[nbEvenements] = cartonjaune;
			nbEvenements ++;
		}
		else if (type=="CartonRouge") {
			CartonRouge cartonrouge = new CartonRouge(joueur, minute, équipe);
			événements[nbEvenements] = cartonrouge;
			nbEvenements ++;
		}
	}
	public void creerFichier() {
		File file = new  File("./"+ équipe1 +" - "+ équipe2 +".txt");
		if (!file.exists()) {
			try {
			file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))){
			writer.print("Informations match : " + équipe1 + " contre " + équipe2);
			for (int i=0;i<événements.length;i++) {
				writer.print(événements[i].getMinute() + événements[i].type + événements[i].getEquipe() + événements[i].getNomJoueur() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
}
}
