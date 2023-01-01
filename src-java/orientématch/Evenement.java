package orientématch;
import orientééquipe.Joueur;

public class Evenement {
	private Joueur joueur;
	private int minute;
	private String équipe;
	public String type;
	public Evenement(Joueur joueur, int minute, String équipe) {
		this.joueur = joueur;
		this.minute = minute;
		this.équipe = équipe;
	}
	public String getNomJoueur() {
		return joueur.getNom();
	}
	public int getMinute() {
		return minute;
	}
	public String getEquipe() {
		return équipe;
	}
	public String getType() {
		return type;
	}
}
