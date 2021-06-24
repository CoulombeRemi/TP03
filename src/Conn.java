/**
 * Rémi Coulombe - 20130013
 * Yanoé Roy-Fitton - 20175985
 * IFT1176 - tp03 - été 21
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conn {
	
	private static String user;
	private static String pass;
	private static String jdbc;
	private static String url;
	
	public static String getUser() {
		return user;
	}
	public static String getPass() {
		return pass;
	}
	public static String getJDBC() {
		return jdbc;
	}
	public static String getURL() {
		return url;
	}
	public static void load() {
		try(InputStream in = new FileInputStream("resources//config.properties")){
			Properties prop = new Properties();
			prop.load(in);
			user = prop.getProperty("db.user");
			pass = prop.getProperty("db.pass");
			jdbc = prop.getProperty("db.jdbc");
			url = prop.getProperty("db.url");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
