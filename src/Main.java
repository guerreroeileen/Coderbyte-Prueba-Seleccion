import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * 
 * @author Eileen Guerrero Gómez
 *
 */
public class Main {
	
	/**
	 * Convierte un InputStream a una cadena.
	 * @param inputStream a convertir
	 * @return cadena con los valores del inputStream
	 */
	public static String helperConvertInputAsString (InputStream inputStream) {
		String text = null;
	    try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
	        text = scanner.useDelimiter("\\A").next();
	    }
	    return text;
	}
	
	/**
	 * Cuenta la cantidad de valores que cumplan el rango de edad definido
	 * @param words
	 * @return
	 */
	public static int countAges (String words[]) {
		int resp= 0;
		for (int i = 0; i < words.length; i++) {
			if (words[i].contains("age")) {
				String value =words[i].replace("{", "").replace("}", "").replaceAll("\"" , "").split("=")[1];
				int age = Integer.parseInt(value);
				if (age>=50) {
					resp++;
				}
			}
		}
		
		return resp;
	}
	
	public static void main (String[] args) { 
	    System.setProperty("http.agent", "Chrome");
	    try { 
	      URL url = new URL("https://coderbyte.com/api/challenges/json/age-counting");
	      try {
	        URLConnection connection = url.openConnection();
	        InputStream inputStream = connection.getInputStream();
	        String words [] = helperConvertInputAsString(inputStream).split(",");
	        System.out.println(countAges(words));
	      } catch (IOException ioEx) {
	        System.out.println(ioEx);
	      }
	    } catch (MalformedURLException malEx) {
	      System.out.println(malEx);
	    }
	  }   

}
