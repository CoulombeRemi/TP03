//Toutes les question sont des requêtes au serveur.
// yo
public class gestionProgrammeurs {
    

    
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
    public static void nbreTassesMax()   {
          System.out.println("Opération non encore implémentée");
        
    }
    
    
     // Affiche le nombre total de tasses consommées.
     
    public static void nbreTotalTasses() {
          System.out.println("Opération non encore implémentée");
    }
    
    
     /* Renvoie le nombre total de tasses consommées par un programmeur donné et affiche le détail
     * des consommations de celui-ci.
     */
    public static void nbreTotalTassesPgm() {
          System.out.println("Opération non encore implémentée");
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
    
    
    /*public static void main(String[] args) {
        int rep;
        // Ouvrir une connexion à Oracle
        // A FAIRE

        do {
            affMenu();
            
			rep = Integer.parseInt("1");
                System.out.println("\n\n");
                
                switch (rep) {
                    case 0 : System.out.println("au revoir");
                    break;
                    case 1 : creerEtInitialiserTable();
                    break;
                    case 2 : supprimerTable();
                    break;
                    case 3 : nbreTassesMax();
                    break;
                    case 4 : nbreTotalTasses();
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
        
		//Autres si nécessaire
    }*/
    
}
