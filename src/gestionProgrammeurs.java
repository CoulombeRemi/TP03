/**
 * Rémi Coulombe - 20130013
 * Yanoé Roy-Fitton - 20175985
 * IFT1176 - tp03 - été 21
 * 
 * https://github.com/CoulombeRemi/TP03
 */
//Toutes les question sont des requêtes au serveur.
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class gestionProgrammeurs {
	private static final String FILE_PATH = "documents\\programmeurs.txt";
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
       String sql = "CREATE TABLE programmeurs " +
               "(id INTEGER not NULL AUTO_INCREMENT, " +
               " nom VARCHAR(20), " + 
               " journee VARCHAR(10), " + 
               " tasses INTEGER, " + 
               " PRIMARY KEY ( id ))"; 
       try {
    	   stmt.executeUpdate(sql);
    	   System.out.println("--- La table a été créée ---");
       }catch(SQLException e) {
    	   System.out.println(e);
       }
    }

     // Destruction de la table programmeurs.
    public static void supprimerTable() {
       try {
    	   stmt.executeUpdate("drop table programmeurs;");
    	   System.out.println("--- La table a été supprimée ---");
       } catch (SQLException e) {
    	   System.out.println("La table n'existe pas.");
       }
    }

     // Intialiasitation de la table programmeurs à partir des données lues dans un fichier texte.
    
    public static void chargerBase()  {
    	try(BufferedReader fileR = new BufferedReader(new FileReader(FILE_PATH))){
    		String line;
    		preparedStatement = con.prepareStatement("insert into programmeurs values (default, ?,?,?);");
    		while((line = fileR.readLine()) != null) {
    			String[] row = line.split(" ");
    			String nom = row[0];
    			String jour = row[1];
    			int tasses = Integer.parseInt(row[2]);
    			preparedStatement.setString(1, nom);
    			preparedStatement.setString(2, jour);
    			preparedStatement.setInt(3, tasses);
    			preparedStatement.executeUpdate();
    		}
    		fileR.close();
    		System.out.println("--- La table a été chargée ---");
    	}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
  
     /* Affiche le nom de la personne qui a consommé le plus de tasses de café en une journée ainsi
     * que sa consommation ce jour la, puis la liste des personnes ordonnee par ordre décroissant
     * du nombre de consommations.
     */
    public static void nbreTassesMax() throws SQLException  {
          // variables pour la personne ayant consomé le plus de tasse de café pour une journée
          String nomEmpPT = "";
          String jourPT = "";
          int  nbrTassesPT = 0;
          String outputPlusCons = "Personne ayant la plus grande consommation en une journée :\nNom\t\tJour\t\tTasses\n---------------------------------------\n";
          String outputListe = "Liste des personnes par ordre de consommations :\nNom\t\tJour\t\tTasses\n---------------------------------------\n";
          
          while(rs.next()) {
        	  String nomEmp = rs.getString(DB_NOM);
        	  String jour = rs.getString(DB_JOUR);
        	  int nbrTasses = rs.getInt(DB_TASSES);
        	  // on trouve la personne avec la plus grande consommation
        	  if(nbrTassesPT < nbrTasses) {
        		  nomEmpPT = nomEmp;
        		  jourPT = jour;
        		  nbrTassesPT = nbrTasses;
        	  }
        	  outputListe += lengthCheck(nomEmp) + lengthCheck(jour) + nbrTasses + "\n";
          }
           
          outputPlusCons += lengthCheck(nomEmpPT) + lengthCheck(jourPT) + nbrTassesPT + "\n";
          System.out.println(outputPlusCons + "\n\n" + outputListe);
    }
    
    
     // Affiche le nombre total de tasses consommées.
    public static void nbreTotalTasses(ResultSet result) throws SQLException {
          int tasseTotal = 0;
          while(result.next()) {tasseTotal += result.getInt(DB_TASSES);}
          System.out.println("Le nombre total de consommations : " + tasseTotal + " tasses");
    }
    
    
     /* Renvoie le nombre total de tasses consommées par un programmeur donné et affiche le détail
     * des consommations de celui-ci.
     */
    public static void nbreTotalTassesPgm() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	String name;
    	String sql = "Select * from programmeurs where nom = ?;";
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
			e.printStackTrace();
		}
    }
    
    /*
     * Exécute une requête libre définie par une chaîne donnée au clavier et 
     * affiche les méta données concernant le résultat de cette requête 
     * quelconque.
     */
	 
    public static void requeteLibreEtMetaDonnees() {
    	 String requeteLibre= "SELECT * from programmeurs";
    	 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	 System.out.println("Exemple de requete libre : SELECT * from programmeurs");
    	 System.out.println("veuillez ecrire votre requete libre :");
    	 try {
			requeteLibre= reader.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 String typeRequete =requeteLibre.substring(0, 6);
    	 if(typeRequete.equals("SELECT")) {
    		 try {
    	        	DatabaseMetaData databaseMetaData = con.getMetaData();
    	            ResultSet result = stmt.executeQuery(requeteLibre);
    	            ResultSet column = databaseMetaData.getColumns(null,null, "programmeurs", null);
    		    	if(result.next()) {
    		    		String outputNbrCol = "Nombre de colonnes dans la table : " + result.getMetaData().getColumnCount();
    		    		String outputCol = "Nom col.\tType\n------------------------\n";
    		    		String outputList = "Nom\t\tJournee\t\tTasses\n----------------------------------------\n";
    		    		
    		    		// parcour les column
    		    		while(column.next()) {
    		    			outputCol += column.getString("COLUMN_NAME") + "\t\t" + typeCheck(column.getString("DATA_TYPE")) + "\n";
    		    		}
    		    		// parcour la bd et output l'info
    		    		while(result.next()) {
    		    			String nomEmp = result.getString(DB_NOM);
    		          	 	String jour = result.getString(DB_JOUR);
    		          	 	int nbrTasses = result.getInt(DB_TASSES);
    		          	 	outputList += lengthCheck(nomEmp) + lengthCheck(jour) + nbrTasses+"\n";
    		    		}
    		    		System.out.println(outputNbrCol + "\n\n" + outputCol+"\n"+outputList);
    		    	}
    	        }catch(SQLException e) {
    	        	System.out.println("--- Problème avec la base de données ---");
    	        	System.out.println(e);
    	        }
    	 }else if (typeRequete.equals("INSERT")) {
    		 try {
    			preparedStatement = con.prepareStatement("insert into programmeurs values (default, ?,?,?);");
    			String parantese = requeteLibre.substring(requeteLibre.indexOf("(")+1, requeteLibre.indexOf(")"));
    			String[] param= parantese.split(",");
    			String nom = param[0].trim();
    			String jour = param[1].trim();
    			int tasses = Integer.parseInt(param[2].trim());
    			preparedStatement.setString(1, nom);
    			preparedStatement.setString(2, jour);
    			preparedStatement.setInt(3, tasses);
    			int nbLigne = preparedStatement.executeUpdate();
				System.out.println(nbLigne +" de ligne(s) on ete mit a jour");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    		 
    	 }else if (typeRequete.equals("UPDATE")) {
    		 try {
    			 //preparedStatement = con.prepareStatement("UPDATE programmeurs SET nom=? , journee=?, tasses=? where id=?");
    	    	int nbLigne = stmt.executeUpdate(requeteLibre);
				System.out.println(nbLigne +" de ligne(s) on ete mit a jour");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    		 
    	 }else if (typeRequete.equals("DELETE")) {
    		 try {
     	    	int nbLigne = stmt.executeUpdate(requeteLibre);
 				System.out.println(nbLigne +" de ligne(s) on ete mit a jour");
 			} catch (SQLException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
     		 
     		 
     	 }else {
    		 try {
    			int nbLigne = stmt.executeUpdate(requeteLibre);
  				System.out.println(nbLigne +" de ligne(s) on ete mit a jour");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		 
    	 }
    	 
    	 
        
    }
    // methode pour ajuster le nbr de \t a faire pour un string
    private static String lengthCheck(String str) {
    	if(str.length() >= 8) {
    		str+="\t";
    	}else {
    		str+="\t\t";
    	}
    	return str;
    }
    
    // permet de convertir la valeur retourner par getString("DATA_TYPE") par un mots
    private static String typeCheck(String string) {
    	String rep = null;
    	if(string.equals("4")) {
    		rep = "integer";
    	}else if(string.equals("12")) {
    		rep = "varchar";
    	}
    	return rep;
    }
     
     // Affiche le menu présentant les différentes opérations possibles
    public static void affMenu() {
        System.out.println("\n\n------------------------------------------");
        System.out.println("1 : Creer la table");
        System.out.println("2 : Detruire la table");
        System.out.println("3 : Consommation max");
        System.out.println("4 : Nombre total de tasses");
        System.out.println("5 : Nombre total tasses pour un programmeur");
        System.out.println("6 : Requete Libre et Meta donnees");
        System.out.println("7 : Charger les donnees depuis un fichier");
        System.out.println("0 : Quitter l'application");
		System.out.println("Entrez votre choix : ");
    }
    
    
    public static void main(String[] args) {
    	
    	
        int rep;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String sql = "Select * from programmeurs order by tasses desc;";
        
        Conn.load();
        // Ouvrir une connexion mysql
        // A FAIRE
       
		try{    
			Class.forName(Conn.getJDBC()); 
			con = DriverManager.getConnection(Conn.getURL(), Conn.getUser(),Conn.getPass());
			stmt = con.createStatement();
			 try{
		        	supprimerTable();
		        	creerEtInitialiserTable();
		        	chargerBase();
		        }catch(Exception e) {
		        	System.out.println("yo peter");
		        }
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
	                    case 3 : 
	                    	rs = stmt.executeQuery("Select * from programmeurs order by tasses desc;");
	                    	nbreTassesMax();
	                    break;
	                    case 4 : nbreTotalTasses(stmt.executeQuery("Select tasses from programmeurs;")); // a voir
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
	        
	        // Fermer la connexiona mysql
	        con.close();  
		}catch(Exception e){
			System.out.println(e);
		} 
        
		//Autres si nécessaire
    }
    
}
