package Main;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import orientééquipe.Equipe;
import orientééquipe.Joueur;
import orientématch.*;

public class WriteToCsvJava {
	public static void SaveFile(Equipe equipe, Main main) {
		Joueur[] joueurs = equipe.getJoueurs();
		Match[] matchs = equipe.getMatchs();
		Equipe[] équipes = main.getEquipes();
		int taille_joueurs = equipe.getnbJoueurs();
		int taille_matchs = equipe.TailleEffective();
		int taille_équipes = main.TailleEffective();
		createFile();
		System.out.println("Fichier créés.");
		clearFile();
		System.out.println("Fichier nettoyés.");
		saveRecord1("TFC-joueurs.csv", taille_joueurs, joueurs);
		saveRecord2("TFC-équipes-adverses.csv", taille_équipes, équipes);
		saveRecord3("TFC-matchs.csv", taille_matchs, matchs);
	}
	public static void clearFile()

	{ 

	    try{

	    FileWriter fw1 = new FileWriter("TFC-joueurs.csv", false);

	    PrintWriter pw1 = new PrintWriter(fw1, false);

	    pw1.flush();

	    pw1.close();

	    fw1.close();
	    
	    FileWriter fw2 = new FileWriter("TFC-matchs.csv", false);

	    PrintWriter pw2 = new PrintWriter(fw2, false);

	    pw2.flush();

	    pw2.close();

	    fw2.close();
	    
	    FileWriter fw3 = new FileWriter("TFC-équipes-adverses.csv", false);

	    PrintWriter pw3 = new PrintWriter(fw3, false);

	    pw3.flush();

	    pw3.close();

	    fw3.close();

	    }catch(Exception exception){

	        System.out.println("Exception has been caught");

	    }

	}
	public static void createFile() {
		try {
		      File myObj = new File("TFC-joueurs.csv");
		      if (myObj.createNewFile()) {
		        System.out.println("Fichier TFC-joueurs créé: " + myObj.getName());
		      } else {
		        System.out.println("Le fichier TFC-joueurs existe déjà.");
		      }
		      File myObj3 = new File("TFC-matchs.csv");
		      if (myObj3.createNewFile()) {
			        System.out.println("Fichier TFC-matchs créé: " + myObj3.getName());
			      } else {
			        System.out.println("Le fichier TFC-joueurs existe déjà.");
			      }
		      File myObj2 = new File("TFC-équipes-adverses.csv");
		      if (myObj2.createNewFile()) {
			        System.out.println("Fichier TFC-équipes-adverses créé: " + myObj2.getName());
			      } else {
			        System.out.println("Le fichier TFC-équipes-adverses existe déjà.");
			      }
		    } catch (IOException e) {
		      System.out.println("Une erreur a eu lieu.");
		      e.printStackTrace();
		    }
	}
	public static void saveRecord1(String filepath, int taille, Joueur[] joueurs) {
		try {
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			for (int i=0; i<taille;i++) {
				pw.println(joueurs[i].getNom() + "," + joueurs[i].getAge() + "," + joueurs[i].getNationalité() + "," + joueurs[i].getPoste() + "," + joueurs[i].getStats()[0] + "," + joueurs[i].getStats()[1] + "," + joueurs[i].getStats()[2] + "," + joueurs[i].getStats()[3] + "," + joueurs[i].getStats()[4]);
				pw.flush();
			}
			pw.close();
			System.out.println("Fichier 1 sauvegardé.");
		}
		catch(Exception E) {
			System.out.println("Fichier  1 non sauvegardé.");
		}
	}
	public static void saveRecord3(String filepath, int taille, Match[] matchs) {
		try {
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			String stringTemp = "";
			for (int i=0; i<taille;i++) {
				stringTemp = matchs[i].getEquipe2().getNom();
				for (int k=0; k<11;k++)
				{
					stringTemp = stringTemp + "," + (matchs[i].getEquipe1_joueurs())[k].getNom();
				}
				for (int j=0; j<matchs[i].getnbEvenements();j++) {
				stringTemp = stringTemp + "," + (matchs[i].getEvenements())[j].getType() + "," + (matchs[i].getEvenements())[j].getEquipe() + "," + (matchs[i].getEvenements())[j].getMinute() + "," + (matchs[i].getEvenements())[j].getNomJoueur();
				}
				pw.println(stringTemp);
				pw.flush();
			}
			pw.close();
			System.out.println("Fichier 3 sauvegardé");
		}
		catch(Exception E) {
			System.out.println("Fichier 3 non sauvegardé");
		}
	}
public static void saveRecord2(String filepath, int taille, Equipe[] équipes) {
	try {
		FileWriter fw = new FileWriter(filepath, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);
		if (taille>1) {
			pw.println(équipes[1].getNom());
			pw.flush();
		for (int i=2; i<taille;i++) {
			pw.println(","+équipes[i].getNom());
			pw.flush();
		}
		}
		pw.close();
		System.out.println("Fichier 2 sauvegardé");
	}
	catch(Exception E) {
		System.out.println("Fichier 2 non sauvegardé");
	}
}
}
