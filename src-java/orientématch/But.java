package orientématch;
import orientééquipe.Joueur;
public class But extends Evenement {
	public But(Joueur joueur, int minute, String équipe) {
		super(joueur, minute, équipe);
		super.type = "But";
	}
}
