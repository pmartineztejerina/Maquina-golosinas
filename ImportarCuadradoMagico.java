import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ImportarCuadradoMagico {

	public ImportarCuadradoMagico() {
		// TODO Auto-generated constructor stub
		
		System.out.println("IMPORTAR CUADRADO MAGICO");
		
		int numCuadrados=0;
		
		try {
			FileReader fr = new FileReader("cuadradoMagico.txt");
			BufferedReader br=new BufferedReader(fr);
			String linea="";
			
			linea=br.readLine();
			numCuadrados=Integer.parseInt(linea);
			int [][] cuadradoMagico;
			for (int x = 0; x < numCuadrados; x++) {
				 cuadradoMagico=leerFichero(br);	
				 System.out.println(Arrays.deepToString(cuadradoMagico));
				 esCuadradoMagico(cuadradoMagico);
			}
			
			
			
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}

	}

	private void esCuadradoMagico(int[][] cuadradoMagico) {
		// TODO Auto-generated method stub
		
		int []sumaFila= new int [cuadradoMagico.length];
		int []sumaColumna=new int [cuadradoMagico.length];
		int sumaPrimDiagonal=0;
		int sumaSegundDiagonal=0;
		
		for (int x = 0; x < cuadradoMagico.length; x++) {
			for (int y = 0; y < cuadradoMagico[x].length; y++) {
				sumaFila[x]+=cuadradoMagico[x][y];
			}
		}
		
		for (int y = 0; y < cuadradoMagico[0].length; y++) {
			for (int x = 0; x < sumaColumna.length; x++) {
				sumaColumna[y]+=cuadradoMagico[x][y];
			}
		}
		
		for (int x = 0; x < cuadradoMagico.length; x++) {
			for (int y = 0; y < cuadradoMagico.length; y++) {
				if (x==y) {
					sumaPrimDiagonal+=cuadradoMagico[x][y];
				}
			}
		}
		
		int cont=0;
		for (int x = cuadradoMagico.length-1; x >=0; --x) {
			sumaSegundDiagonal+=cuadradoMagico[x][cont++];
		}
		boolean esMagico=true;
		//comprobamos si es magico
		
		for (int x = 0; x < sumaFila.length-1; x++) {
			if (sumaFila[x]!=sumaFila[x+1]) {
				esMagico=false;
			}
		}
		
		for (int y = 0; y < sumaColumna.length-1; y++) {
			if (sumaColumna[y]!=sumaColumna[y+1]) {
				esMagico=false;
			}
		}
		
		if (sumaFila[0]!=sumaPrimDiagonal || sumaFila[0]!=sumaSegundDiagonal) {
			esMagico=false;
		}
		if (esMagico) {
			System.out.println("El array es mágico");
		}
		else {
			System.err.println("El array no es mágico");
		}
		
	}

	private int[][] leerFichero(BufferedReader br) throws IOException {
		// TODO Auto-generated method stub
		 
		String linea="";
		linea=br.readLine();
		int tamaño=Integer.parseInt(linea);
		int [][]cuadradoMagico=new int[tamaño][tamaño];
		for (int x = 0; x < cuadradoMagico.length; x++) {
			String [] lineaCuadrado;
			linea=br.readLine();
			lineaCuadrado=linea.split(" ");
			for (int y = 0; y < cuadradoMagico[x].length; y++) {	
				cuadradoMagico[x][y]= Integer.parseInt(lineaCuadrado[y]);
			}
		}

		return cuadradoMagico;
	}





}
