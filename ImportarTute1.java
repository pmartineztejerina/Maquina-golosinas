import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ImportarTute1 {

	public ImportarTute1() {
		// TODO Auto-generated constructor stub
		
		System.out.println("IMPORTAR TUTE");
				
		FileReader fr;
		String linea="";
		try {
			fr=new FileReader("tute.txt");
			BufferedReader br=new BufferedReader(fr);
			linea=br.readLine();
			int numJugadas=Integer.parseInt(linea);
			String [][] partidas= new String [numJugadas][numJugadas];
			for (int x = 0; x < partidas.length; x++) {
				String [] jugada= new String [numJugadas];
				for (int y = 0; y < jugada.length; y++) {
					linea=br.readLine();
					String [] jugador=linea.split(" ");
					partidas[x][y]=jugador[1];
				}
			}
			fr.close();
			br.close();
			System.out.println(Arrays.deepToString(partidas));
			System.out.println();
			for (int x = 0; x < partidas.length; x++) {
				jugarTute(partidas[x]); //mando el fichero a jugar al tute
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private void jugarTute(String[] partidas) {
		// TODO Apéndice de método generado automáticamente
		
		String [] palos = {"o","c","e","b"};
		String [] paloGanador = {"oros","copas","espadas","bastos"};
		int num=(int) Math.floor(Math.random()*(3-0+1)+0);
		System.out.println("El palo ganador es "+paloGanador[num]);
		int acierto=0;
		int canta=0;
		int contador=0; //esto nos va a guiar para ver si no canta nadie
		for (int x = 0; x < partidas.length; x++) {
			for (int y = 0; y < palos.length; y++) {
				if (acierto <= partidas[x].indexOf("11"+palos[y])&& acierto <=partidas[x].indexOf("12"+palos[y])) {
					if (palos[y]==palos[num]) {
						System.out.println("El jugador "+(x+1)+": canta 40 en "+(paloGanador[num]));
						contador++;
						break;
					}
					else {
						System.out.println("El jugador "+(x+1)+": canta 20 en "+(paloGanador[num]));
						contador++;
					}
				}
			}
		}
		if (contador==0) {
			System.out.println("Ningun jugador canta");
		}	
	}
	}


