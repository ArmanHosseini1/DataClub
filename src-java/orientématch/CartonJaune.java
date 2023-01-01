package orientématch;
import orientééquipe.Joueur;
public class CartonJaune extends Evenement {
	public CartonJaune(Joueur joueur, int minute, String équipe) {
		super(joueur, minute, équipe);
		super.type = "CartonJaune";
	}
}
