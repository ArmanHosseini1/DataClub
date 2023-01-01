package Main;
import java.io.*;
import orientééquipe.*;
import orientématch.*;

public class LoadDataFromCSV {
	public static void LoadFile1(Main main) {
	String file = "TFC-joueurs.csv";
	BufferedReader reader = null;
	String line = "";
	try {
		reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine())!= null) {
			String[] row = line.split(",");
			Joueur joueurTemp = new Joueur(row[0], Integer.parseInt(row[1]), row[2], row[3], "TFC");
			main.ajouterJoueur(joueurTemp);
			main.setStats(Integer.parseInt(row[4]),Integer.parseInt(row[5]),Integer.parseInt(row[6]),Integer.parseInt(row[7]));
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			reader.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
	
	public static void LoadFile3(Main main) {
		/* Il faut load le fichier 3 d'abord */
		String file = "TFC-matchs.csv";
		BufferedReader reader = null;
		String line = "";
		int r = 0;
		Match[] matchTemp = new Match[1000];
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine())!= null) {
				String[] row = new String[10000];
				System.out.println(row.length);
				row = line.split(",");
				/* reprendre ici */
				Joueur[] équipe1_joueurs = new Joueur[11];
				for (int k=1;k<12;k++) {
					équipe1_joueurs[k-1] = main.getEquipe().trouverJoueur(row[k]);
				}
				matchTemp[r] = new Match(main.getEquipe(), main.trouverEquipe(row[0]), main, équipe1_joueurs);
				System.out.println(row.length);
				for (int k=0;k<1000;k=k+4) {
					if ((12+k<row.length)&&(13+k<row.length)&&(14+k<row.length)&&(15+k<row.length)) {
						matchTemp[r].ajouterEvenement(row[12+k], main.trouverEquipe(row[13+k]).trouverJoueur(row[15+k]), Integer.parseInt(row[14+k]), row[13+k]);
						System.out.println("Test : " + matchTemp[r].getnbEvenements());
					}
				}
				main.getEquipe().setMatch(matchTemp[r]);
				r++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		}
	
	public static void LoadFile2(Main main) {
		String file = "TFC-équipes-adverses.csv";
		BufferedReader reader = null;
		String line = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			int i = 0;
			while ((line = reader.readLine())!= null) {
				String[] row = line.split(",");
				if (i==0) {
				Equipe equipeTemp = new Equipe(row[i]);
				i++;
				main.setEquipe(equipeTemp);
				}
				else {
				Equipe equipeTemp = new Equipe(row[i]);
				main.setEquipe(equipeTemp);
				}
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		}
}
