import java.sql.*;

class MyConnection{
	//static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	//static final String DB_URL = "jdbc:mysql://localhost/bdprogrammeurs?serverTimezone=UTC";
	
	//static final String USAGER = "root"; 
	//static final String PASS = "";

	public static void main(String args[]){  
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  "jdbc:mysql://localhostbdprogrammeurs","root","root");  
		//here sonoo is database name, root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select * from programmeurs");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(1));  
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
		}  
	
}