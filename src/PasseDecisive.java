public class PasseDecisive extends Evenement {
	public String type = "passe decisive";
	public PasseDecisive(Joueur joueur, int minute, String équipe) {
		super(joueur, minute, équipe);
	}
}
