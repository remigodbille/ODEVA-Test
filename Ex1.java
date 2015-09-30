import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Iterator;

/** @Collaborators
* Bellart Clement
* Knapik Christopher
* Godbille Remi */

//  Bonjour je m'incruste dans ton TP !
// Je modifie
// On fait quoi maintenant ?
// Normalement on dois faire des conflits dans le code pour les gérer
// Les conflits c'est ok

// J'embête Rémi


public class Ex1{
	
	private ArrayList<String> usager, theme;
	public int matrice[][];
	public String logclientsthemes;
	
	public Ex1(String nomfichier){
		this.logclientsthemes = nomfichier;
		this.usager = new ArrayList<String>();
		this.theme = new ArrayList<String>();

	}

	public String getLogClientsThemes(){
		return this.logclientsthemes;
	}

	public int[][] getMatrice(){
		return this.matrice;
	}

	public ArrayList<String> getUsager(){
		return this.theme;
	}
	
	public ArrayList<String> getTheme(){
		return this.usager;
	}
	
	// Création du fichier contenant les usagers
	public void usager(String logclientsthemes, ArrayList<String> usagers) throws IOException {
		
		FileReader freader = new FileReader(logclientsthemes);
		BufferedReader breader = new BufferedReader(freader);
		String ligne = "";
		
		// Parcours du fichier, ici le Log-clients-themes.txt
		while ((ligne = breader.readLine()) != null){
			
			String[] donnee = ligne.split(";");
			
			String usager = donnee[1];
			
			// Si dans la liste usagers, il n'y a pas l'usager de la ligne inspectée
			if (usagers.contains(usager)==false){
				// Alors on l'ajoute à la liste usagers
				usagers.add(usager);
			}
				
		}
		
		breader.close();
		freader.close();
		
		String nomfichier = "usager_trie.txt";

		FileWriter fichierusager = new FileWriter (nomfichier);
		
		Iterator<String> iterator = usagers.iterator();
		
		// Parcours de la liste usagers
		while (iterator.hasNext()){
			// Ecriture dans le fichier dont le nom a été choisi avant
			fichierusager.write(iterator.next()+"\n");
		}
		
		fichierusager.close();
		
	}
	
	// Création du fichier contenant les themes
	public void theme(String logclientsthemes, ArrayList<String> themes ) throws IOException {
		
		FileReader freader = new FileReader(logclientsthemes);
		BufferedReader breader = new BufferedReader(freader);
		String ligne = "";
		
		while ((ligne = breader.readLine()) != null){
			
			String[] donnee = ligne.split(";");
							
			String theme = donnee[2];
				
			if (themes.contains(theme) == false){
				themes.add(theme);
			}
				
		}
		
		breader.close();
		freader.close();

		String nomfichier = "theme_trie.txt";
		
		FileWriter fichiertheme = new FileWriter (nomfichier);
		
		Iterator<String> iterator = themes.iterator();

		while (iterator.hasNext()){
			fichiertheme.write(iterator.next()+" - ");
		}
		
		fichiertheme.close();
		
	}

	// Création du fichier pour la matrice mut
	public void mut(String logclientsthemes, int matrice[][], ArrayList<String> usagers, ArrayList<String> themes) throws IOException {

		FileReader freader = new FileReader(logclientsthemes);
		BufferedReader breader = new BufferedReader(freader);
		String ligne = "";

		int idusager, idtheme = 0;

		// On crée un nouveau tableau à 2 dimensions avec ici la taille de la liste des usagers (nombre d'usagers est égal à 9)
		// et celle des themes (nombre de themes est égal à 5)
		matrice = new int[usagers.size()][themes.size()];

		while ((ligne = breader.readLine()) != null){

			String[] donnee = ligne.split(";");

			if (donnee.length == 3){

				String usager = donnee[1];
				String theme = donnee[2];

				// Inspection des listes usagers et themes 
				idusager = usagers.indexOf(usager);
				idtheme = themes.indexOf(theme);

				// Ajout de 1 dans la matrice à la valeur étant aux indices usager et theme
				matrice[idusager][idtheme] = matrice[idusager][idtheme]+1;

			}

		}

		breader.close();
		freader.close();

		String nomfichier = "matrice_mut.txt";

		FileWriter fichiermut = new FileWriter (nomfichier);

		int nbrU = matrice.length;
		int i = 0;
		int j = 0;
		
		// Parcours i fois du nombre d'usager (9 fois)
		while(i<nbrU){

			j = 0;

			int nbrT = matrice[i].length;

			// Parcours j fois du nombre de theme (5 fois)
			while(j<nbrT){
				
				fichiermut.write(""+matrice[i][j]);

				if(j<matrice[i].length-1){
					fichiermut.write(" - ");
				}

				j++;

			}

			i++;

			if(i<matrice.length){
				fichiermut.write("\n");
			}

		}

		fichiermut.close();

	}

	public static void main(String args[]) throws IOException{
		String fichier = "Log-clients-themes.txt";
		Ex1 ex1 = new Ex1(fichier);
		ex1.usager(ex1.getLogClientsThemes(), ex1.getUsager());
		ex1.theme(ex1.getLogClientsThemes(), ex1.getTheme());
		ex1.mut(ex1.getLogClientsThemes(), ex1.getMatrice(),ex1.getUsager(),ex1.getTheme());
	}

}
