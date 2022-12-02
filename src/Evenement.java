
public class Evenement {
	private Joueur joueur;
	private int minute;
	private String équipe;
	public String type = "default";
	public Evenement(Joueur joueur, int minute, String équipe) {
		this.joueur = joueur;
		this.minute = minute;
		this.équipe = équipe;
	}
	public String getNomJoueur() {
		String a = joueur.getNom();
		return a;
	}
	public int getMinute() {
		return minute;
	}
	public String getEquipe() {
		return équipe;
	}
}
