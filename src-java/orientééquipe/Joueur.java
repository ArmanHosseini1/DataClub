package orientééquipe;
	import orientématch.But;
	import orientématch.CartonJaune;
	import orientématch.CartonRouge;
	import orientématch.PasseDecisive;
	import orientématch.Evenement;
	public class Joueur {
		private String nom;
		private int age;
		private String nationalité;
		private String poste;
		private String équipe;
		private int[] stats = {0,0,0,0};
		private Evenement[] événements;
		private int nbévénements = 0;
		// Stats 0=Buts 1=PD 2=Fautes 3=CJ 4=CR;
		public Joueur(String nom, int age, String nationalité, String poste, String équipe) {
			this.nom = nom;
			this.age = age;
			this.nationalité = nationalité;
			this.poste = poste;
			this.équipe = équipe;
			événements = new Evenement[1000];
		}
		public void setStats(int stats1, int stats2, int stats3, int stats4) {
			stats[0] = stats1;
			stats[1] = stats2;
			stats[2] = stats3;
			stats[3] = stats4;
		}
		public String getNom(){
			return nom;
		}
		public int getAge(){
			return age;
		}
		public String getNationalité(){
			return nationalité;
		}
		public String getPoste(){
			return poste;
		}
		public int[] getStats(){
			return stats;
		}
		public void setEquipe(String équipe2) {
			équipe = équipe2;
		}
		/*public void creerFichier() {
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
		} */
		public void ajouterBut(int minute, String équipe) {
			But but = new But(this, minute, équipe);
			événements[nbévénements] = but;
			nbévénements++;
			stats[0] ++;
		}
		public void ajouterPD(int minute, String équipe) {
			PasseDecisive PD = new PasseDecisive(this, minute, équipe);
			événements[nbévénements] = PD;
			nbévénements++;
			stats[1] ++;
		}
		public void ajouterCJ(int minute, String équipe) {
			CartonJaune CJ = new CartonJaune(this, minute, équipe);
			événements[nbévénements] = CJ;
			nbévénements++;
			stats[2] ++;
		}
		public void ajouterCR(int minute, String équipe) {
			CartonRouge CR = new CartonRouge(this, minute, équipe);
			événements[nbévénements] = CR;
			nbévénements++;
			stats[3] ++;
		}
	}

