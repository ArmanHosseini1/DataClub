public class Faute extends Evenement {
	public String type = "faute";
	public Faute(Joueur joueur, int minute, String équipe) {
		super(joueur, minute, équipe);
	}
}
