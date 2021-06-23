import java.sql.*;

class MyConnection{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bdprogrammeurs?serverTimezone=UTC"; // bd faite dans mysql local
	private static final String USAGER = "root"; 
	private static final String PASS = "cod7blackops"; // mettre le mdp de la bd local mysql
	private static ResultSet rs;
	private static Connection con;
	private static Statement stmt;
	
	public static void main(String args[]){  
		try{  
		//Class.forName("com.mysql.jdbc.Driver"); // semble que c'est pu supporté
		//Connection con=DriverManager.getConnection(  "jdbc:mysql://localhostbdprogrammeurs","root","root");  
		Class.forName(JDBC_DRIVER); 
		con = DriverManager.getConnection(DB_URL,USAGER,PASS);  
		//here sonoo is database name, root is username and password  
		stmt = con.createStatement();  
		rs = stmt.executeQuery("select * from dbprogrammeurs");  
		while(rs.next()) {
			//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(1));  
		}
		con.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
		
		try {
			gestionProgrammeurs.nbreTassesMax();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static public ResultSet getRS() {
		return rs;
	}
	static public Statement getSTMT() {
		return stmt;
	}
	static public Connection getCon() {
		return con;
	}
	static public void setSTMT(Statement newStmt) {
		stmt = newStmt;
	}
	static public void setRS(ResultSet newRS) {
		rs = newRS;
	}
	static public void setCon(Connection newCon) {
		con = newCon;
	}
}