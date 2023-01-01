package orientématch;
import orientééquipe.Joueur;
public class CartonRouge extends Evenement {
	public CartonRouge(Joueur joueur, int minute, String équipe) {
		super(joueur, minute, équipe);
		super.type = "CartonRouge";
	}
}
