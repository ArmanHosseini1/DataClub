package orientématch;
import orientééquipe.Joueur;
public class PasseDecisive extends Evenement {
	public PasseDecisive(Joueur joueur, int minute, String équipe) {
		super(joueur, minute, équipe);
		super.type = "PasseDecisive";
	}
}
