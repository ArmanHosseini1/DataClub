import java.io.*;
public class Joueur {
	private String Nom;
	private int Age;
	private String Nationalité;
	private int Taille;
	private String Poste;
	private String équipe;
	private int[] Stats = {0,0,0,0,0};
	private Evenement[] événements;
	private int nbévénements = 0;
	// Stats 0=Buts 1=PD 2=Fautes 3=CJ 4=CR;
	public Joueur(String Nom, int Age, String Nationalité, int Taille, String Poste, String équipe) {
		this.Nom = Nom;
		this.Age = Age;
		this.Nationalité = Nationalité;
		this.Taille = Taille;
		this.Poste = Poste;
		this.équipe = équipe;
		événements = new Evenement[1000];
	}
	public String getNom(){
		return Nom;
	}
	public int getAge(){
		return Age;
	}
	public String getNationalité(){
		return Nationalité;
	}
	public float getTaille(){
		return Taille;
	}
	public String getPoste(){
		return Poste;
	}
	public int[] getStats(){
		return Stats;
	}
	public void setEquipe(String équipe2) {
		équipe = équipe2;
	}
	public void creerFichier() {
			File file = new  File("./"+getNom()+".txt");
			if (!file.exists()) {
				try {
				file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try (PrintWriter writer = new PrintWriter(new FileOutputStream(file))){
				writer.print("Informations joueur");
				writer.print(getNom()+"\n");
				writer.print(Age + "\n");
				writer.print(Nationalité + "\n");
				writer.print(Taille + "\n");
				writer.print(Poste + "\n");
				for (int i=0;i<4;i++) {
					writer.print(Stats[i] + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public static void main(String[] args) {
		
	}
	public void ajouterBut(int minute, String équipe) {
		But but = new But(this, minute, équipe);
		événements[nbévénements] = but;
		nbévénements++;
		Stats[0] ++;
	}
	public void ajouterPD(int minute, String équipe) {
		PasseDecisive PD = new PasseDecisive(this, minute, équipe);
		événements[nbévénements] = PD;
		nbévénements++;
		Stats[1] ++;
	}
	public void ajouterFaute(int minute, String équipe) {
		Faute faute = new Faute(this, minute, équipe);
		événements[nbévénements] = faute;
		nbévénements++;
		Stats[2] ++;
	}
	public void ajouterCJ(int minute, String équipe) {
		CartonJaune CJ = new CartonJaune(this, minute, équipe);
		événements[nbévénements] = CJ;
		nbévénements++;
		Stats[3] ++;
	}
	public void ajouterCR(int minute, String équipe) {
		CartonRouge CR = new CartonRouge(this, minute, équipe);
		événements[nbévénements] = CR;
		nbévénements++;
		Stats[4] ++;
	}
}
