//Toutes les question sont des requêtes au serveur.
// yo
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;
public class gestionProgrammeurs {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bdprogrammeurs?serverTimezone=UTC"; // bd faite dans mysql local
	private static final String USAGER = "root"; 
	private static final String PASS = "MySQLP4ss!"; // mettre le mdp de la bd local mysql
	private static ResultSet rs = null;
	private static Connection con = null;
	private static Statement stmt = null;
    private static PreparedStatement preparedStatement = null;
    // variables pour le nom des tables de la bd
	private static final String DB_NOM = "nom";
	private static final String DB_JOUR = "journee";
	private static final String DB_TASSES = "tasses";

     // Création de la table programmeurs
     
    public static void creerEtInitialiserTable() {
       System.out.println("Opération non encore implémentée");
    }

    
     // Destruction de la table programmeurs.
     
    public static void supprimerTable() {
       System.out.println("Opération non encore implémentée");
    }

   
     // Intialiasitation de la table programmeurs à partir des données lues dans un fichier texte.
    
    public static void chargerBase()  {
          System.out.println("Opération non encore implémentée");
    }
    
  
     /* Affiche le nom de la personne qui a consommé le plus de tasses de café en une journée ainsi
     * que sa consommation ce jour la, puis la liste des personnes ordonnee par ordre décroissant
     * du nombre de consommations.
     */
    public static void nbreTassesMax(ResultSet result) throws SQLException  {
          // variables pour la personne ayant consomé le plus de tasse de café pour une journée
          String nomEmpPT = "";
          String jourPT = "";
          int  nbrTassesPT = 0;
          String outputPlusCons = "Personne ayant la plus grande consommation en une journée :\nNom\tJour\tTasses\n----------------------\n";
          String outputListe = "Liste des personnes par ordre de consommations :\nNom\tJour\tTasses\n----------------------\n";
          
          while(result.next()) {
        	  String nomEmp = result.getString(DB_NOM);
        	  String jour = result.getString(DB_JOUR);
        	  int nbrTasses = result.getInt(DB_TASSES);
        	  // on trouve la personne avec la plus grande consommation
        	  if(nbrTassesPT < nbrTasses) {
        		  nomEmpPT = nomEmp;
        		  jourPT = jour;
        		  nbrTassesPT = nbrTasses;
        	  }
        	  outputListe += nomEmp + "\t" + jour + "\t" + nbrTasses + "\n";
          }
           
          outputPlusCons += nomEmpPT + "\t" + jourPT + "\t" + nbrTassesPT + "\n";
          System.out.println(outputPlusCons + "\n\n" + outputListe);
    }
    
    
     // Affiche le nombre total de tasses consommées.
     
    public static void nbreTotalTasses(ResultSet result) throws SQLException {
          String output = "Le nombre total de tasses consommées : ";
          int tasseTotal = 0;
          
          while(result.next()) {
        	  tasseTotal += result.getInt(DB_TASSES);
          }
          System.out.println(output + tasseTotal + " tasses");
    }
    
    
     /* Renvoie le nombre total de tasses consommées par un programmeur donné et affiche le détail
     * des consommations de celui-ci.
     */
    public static void nbreTotalTassesPgm() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	String name;
    	String sql = "Select * from programmeurs where nom = ?";
    	int nbrTasses = 0;
    	
    	System.out.print("Entrer le nom du programmeur : ");
    	
    	try {
    		name = reader.readLine();
    		preparedStatement = con.prepareStatement(sql);
    		preparedStatement.setString(1, name);
    		rs = preparedStatement.executeQuery();
    		while(rs.next()) {
    			nbrTasses += rs.getInt(DB_TASSES);
    		}
    		System.out.println("Le nombre total de tasses consommées par " + name + " est : " + nbrTasses);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
     * Exécute une requête libre définie par une chaîne donnée au clavier et 
     * affiche les méta données concernant le résultat de cette requête 
     * quelconque.
     */
	 
    public static void requeteLibreEtMetaDonnees() {
          System.out.println("Opération non encore implémentée");
        
    }
     
       
     // Affiche le menu présentant les différentes opérations possibles
     
    public static void affMenu() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("1 : Créer la table");
        System.out.println("2 : Détruire la table");
        System.out.println("3 : Consommation max");
        System.out.println("4 : Nombre total de tasses");
        System.out.println("5 : Nombre total tasses pour un programmeur");
        System.out.println("6 : Requete Libre et Méta données");
        System.out.println("7 : Charger les données depuis un fichier");
        System.out.println("0 : Quitter l'application");
		System.out.println("Entrez votre choix : ");
    }
    
    
    public static void main(String[] args) {
        int rep;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sql = "Select * from programmeurs order by tasses desc";
        // Ouvrir une connexion à Oracle
        // A FAIRE
		try{    
			Class.forName(JDBC_DRIVER); 
			con = DriverManager.getConnection(DB_URL,USAGER,PASS);
			stmt = con.createStatement();  
			rs = stmt.executeQuery(sql);  
		
	        do {
	            affMenu();
	            
				rep = Integer.parseInt(reader.readLine());
	                System.out.println("\n\n");
	                
	                switch (rep) {
	                    case 0 : System.out.println("au revoir");
	                    break;
	                    case 1 : creerEtInitialiserTable();
	                    break;
	                    case 2 : supprimerTable();
	                    break;
	                    case 3 : nbreTassesMax(rs);
	                    break;
	                    case 4 : nbreTotalTasses(stmt.executeQuery("Select * from programmeurs")); // a voir
	                    break;
	                    case 5 : nbreTotalTassesPgm();
	                    break;
	                    case 6 : requeteLibreEtMetaDonnees();
	                    break;
	                    case 7 : chargerBase();
	                    break;
	                    
	                    default:
	                        System.out.println("Valeur erronée !");
	                }  // end switch
	                
	
	        } while (rep != 0);
	        
	        // Fermer la connexion à Oracle
	        con.close();  
		}catch(Exception e){
			System.out.println(e);
		} 
        
		//Autres si nécessaire
    }
    
}
