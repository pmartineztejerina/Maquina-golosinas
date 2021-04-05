import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ImportarTute2 {

	public ImportarTute2() {
		// TODO Auto-generated constructor stub
		
		System.out.println("IMPORTAR TUTE");
		FileReader fr;
		String linea="";
		try {
			fr=new FileReader("tute.txt");
			BufferedReader br=new BufferedReader(fr);
			linea=br.readLine();
			int numJugadas=Integer.parseInt(linea);
			String [] partida1= new String [numJugadas];
			for (int x = 0; x < partida1.length; x++) {
				linea=br.readLine();
				String [] jugador=linea.split(" ");
				partida1[x]=jugador[1];
			}
			System.out.println("PARTIDA 1");
			System.out.println(Arrays.toString(partida1));
			
			
			String [] partida2= new String [numJugadas];
			for (int x = 0; x < partida2.length; x++) {
				linea=br.readLine();
				String [] jugador=linea.split(" ");
				partida2[x]=jugador[1];
			}
			System.out.println("PARTIDA 2");
			System.out.println(Arrays.toString(partida2));
			
			
			
			String [] partida3= new String [numJugadas];
			for (int x = 0; x < partida3.length; x++) {
				linea=br.readLine();
				String [] jugador=linea.split(" ");
				partida3[x]=jugador[1];
			}
			System.out.println("PARTIDA 3");
			System.out.println(Arrays.toString(partida3));
			
			
			String [] partida4= new String [numJugadas];
			for (int x = 0; x < partida1.length; x++) {
				linea=br.readLine();
				String [] jugador=linea.split(" ");
				partida4[x]=jugador[1];
			}
			System.out.println("PARTIDA 4");
			System.out.println(Arrays.toString(partida4));
		
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
		
	}
}
