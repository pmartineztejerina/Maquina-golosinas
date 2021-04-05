import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MaquinaGolosinas {

	public MaquinaGolosinas() {
		// TODO Auto-generated constructor stub
		
		System.out.println("MAQUINA GOLOSINAS");
		
		// lectura del fichero completo
	
		try {
			FileReader fr=new FileReader("golosinas.txt");
			BufferedReader br=new BufferedReader(fr);
			String linea=br.readLine();
			int tamaño=Integer.parseInt(linea);
			
			String [][] maquina=new String [tamaño][tamaño];
			for (int x = 0; x < maquina.length; x++) {
				String [] lineaArray;
				linea=br.readLine();
				lineaArray=linea.split(",");
				for (int y = 0; y < maquina[x].length; y++) {
					maquina[x][y]=lineaArray[y];
				}
			}
			//System.out.println("Maquina: "+Arrays.deepToString(maquina));
			
			double [][] precios=new double[tamaño][tamaño];
			for (int x = 0; x < precios.length; x++) {
				String [] lineaArray;
				linea=br.readLine();
				lineaArray=linea.split(",");
				for (int y = 0; y < precios[x].length; y++) {
					precios[x][y]=Double.parseDouble(lineaArray[y]);
				}
			}
			//System.out.println("Precios: "+Arrays.deepToString(precios));
			
			int [][] existencias=new int [tamaño][tamaño];
			for (int x = 0; x < existencias.length; x++) {
				String [] lineaArray;
				linea=br.readLine();
				lineaArray=linea.split(",");
				for (int y = 0; y < existencias[x].length; y++) {
					existencias[x][y]=Integer.parseInt(lineaArray[y]);
				}
			}
			int [][] ventas=new int [tamaño][tamaño];
			for (int x = 0; x < ventas.length; x++) {
				Arrays.fill(ventas[x], 0);
			}
			
			fr.close();
			br.close();
			//System.out.println("Existencias: "+Arrays.deepToString(existencias));
			
			//menu de la maquina
			System.out.println();
			int opcion=0;
			System.out.println("BUENOS DÍAS, SOY SU MÁQUINA DE GOLOSINAS. ¿QUÉ QUIERE HACER?");
			System.out.println();
			boolean seguir=true;
			do {
				System.out.println("Pedir golosinas: marque 1");
				System.out.println("Mostrar existencias de las golosinas: marque 2");
				System.out.println("Rellenar las golosinas: marque 3");
				System.out.println("Apagar la máquina: marque 4");
				Scanner leer=new Scanner(System.in);
				String dato=leer.next();

				//voy a controlar que marca un numero y que esta en el intervalo que le doy
				
				try {
					opcion=Integer.parseInt(dato);
					if (opcion<1 ||opcion>4) {
						throw new IntervaloException("Opcion fuera del intervalo");
					}
				} catch (NumberFormatException e) {
					// TODO: handle exception
					System.err.println("Por favor, introduzca un valor correcto");
				}
				catch (IntervaloException e) {
					System.err.println(e.getMessage());
				}
				catch (Exception e) {
					// TODO: handle exception
				}	
				
				switch (opcion) {
				case 1: //pedir golosina
					
					ventas=pedirGolosinas(maquina, existencias,precios,ventas);  //para que el metodo me devuelva las existencias corregidas
					
					break;

				case 2: //mostrar golosina
					
					mostrarGolosinas(maquina, existencias,precios);
					
					break;
				
				case 3: //rellenar golosina
					
					existencias=rellenarGolosinas(maquina, existencias);
					
					break;
					
				case 4: //apagar maquina
					
					ventasTotales(maquina, precios, ventas);
					guardarFichero(maquina, precios, existencias);
					seguir=false;
					break;
				}
				leer=null;
			} while (seguir); //no tiene opcion de meter un numero negativo
			
			System.out.println("VUELVA OTRO DIA");
				
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void guardarFichero(String[][] maquina, double[][] precios, int[][] existencias) {
		// TODO Auto-generated method stub
		
		FileWriter fw;
		try {
			fw = new FileWriter("golosinas2.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			String linea="";
			linea+=maquina.length; 
			bw.write(linea);
			bw.newLine();

			for (int x = 0; x < maquina.length; x++) {
				linea="";
				for (int y = 0; y < maquina.length; y++) {
					if (y<maquina.length-1) {
						linea+=maquina[x][y]+",";
					}
					else {
						linea+=maquina[x][y];
					}
				}
				
				bw.write(linea);
				bw.newLine();
			}

			for (int x = 0; x < precios.length; x++) {
				linea="";
				for (int y = 0; y < precios.length; y++) {
					if (y<precios.length-1) {
						linea+=precios[x][y]+",";
					}
					else {
						linea+=precios[x][y];
					}
				}
				bw.write(linea);
				bw.newLine();
			}
			
			for (int x = 0; x < existencias.length; x++) {
				linea="";
				for (int y = 0; y < existencias.length; y++) {
					if (y<existencias.length-1) {
						linea+=existencias[x][y]+",";
					}
					else {
						linea+=existencias[x][y];
					}
				}
				bw.write(linea);
				bw.newLine();
			}
			
			fw.flush();
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void ventasTotales(String[][] maquina, double[][] precios, int[][] ventas) {
		// TODO Auto-generated method stub
		
		double []sumaFila=new double [ventas.length];
		for (int x = 0; x < ventas.length; x++) {
			for (int y = 0; y < ventas[x].length; y++) {
				sumaFila[x]+=ventas[x][y]*precios[x][y];
			}
		}
		double ventasTotales=0;
		for (int x = 0; x < sumaFila.length; x++) {
			ventasTotales+=sumaFila[x];
		}
		
		
		System.out.println("Las ventas totales de hoy han sido");
		System.out.println("==================================");
		String cant="Cantidad";
		String prod="Producto";
		String vent="Importe Total";
				
		System.out.printf("%-9s %-23s %-3s %n",cant,prod,vent);
		for (int x = 0; x < ventas.length; x++) {
			for (int y = 0; y < ventas.length; y++) {
				System.out.printf("%-10d", ventas[x][y]);
				System.out.printf("%-25s", maquina[x][y]);
				System.out.printf("%-3.2f € %n", ventas[x][y]*precios[x][y]);
			}
		}
		System.out.printf("TOTAL CAJA = %3.2f € %n",ventasTotales);
	}

	private int[][] rellenarGolosinas(String[][] maquina, int[][] existencias) {
		// TODO Auto-generated method stub
		
		System.out.println("Buenos días señor técnico, introduzca la contraseña");
		Scanner leer=new Scanner(System.in);
		String contraseñaCorrecta="MaquinaExpendedora2017";
		String contraseña=leer.next();
		
		
		if (contraseñaCorrecta.equals(contraseña)) {
			System.out.println("Introduzca la posicion de la golosina");
			String posicion=leer.next();
			int opcion=0;
			try {
				opcion=Integer.parseInt(posicion);
				if (opcion<0 && opcion>4 || opcion<9 && opcion>14 || opcion<19 && opcion>24 || opcion<29 && opcion>34) {
			
					throw new IntervaloException("Opcion fuera del intervalo");
				}
				
				System.out.println("Introduzca la cantidad");
				int cantidad=leer.nextInt();
				int num=Integer.parseInt(posicion);
				int x=num/10;
				int y=num%10;
				
				existencias[x][y]+=cantidad;
				
			} catch (NumberFormatException e) {
				// TODO: handle exception
				System.err.println("Por favor, introduzca un valor correcto");
			}
			catch (IntervaloException e) {
				System.err.println(e.getMessage());
			}
			catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
				System.err.println("Introduzca una opcion correcta");
				}
			catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		else {
			System.err.println("La contraseña introducida es erronea");
		}
		leer=null;
		return existencias;
	}

	private void mostrarGolosinas(String[][] maquina, int[][] existencias, double[][] precios) {
		// TODO Auto-generated method stub
		
		for (int x = 0; x < maquina.length; x++) {
			
			for (int y = 0; y < maquina[x].length; y++) {
				
				if (existencias[x][y]>0) {
					System.out.printf("%d%-5d", x,y);
					System.out.printf("%-25s", maquina[x][y]);
					System.out.printf("%-3.1f € %n", precios[x][y]);
				}
				
			}
		}
		
	}

	private int[][] pedirGolosinas(String[][] maquina, int[][] existencias, double[][] precios, int[][] ventas) {
		// TODO Auto-generated method stub
		
		System.out.println("¿Qué golosina quiere pedir? Marque la opcion deseada");
		for (int x = 0; x < maquina.length; x++) {
			
			for (int y = 0; y < maquina[x].length; y++) {
				System.out.printf("%d%-5d", x,y);
				System.out.printf("%-25s", maquina[x][y]);
				System.out.printf("%-3.1f € %n", precios[x][y]);
			}
		}
		Scanner leer=new Scanner(System.in);
		String dato=leer.nextLine();
		int opcion=0;
		try {
			opcion=Integer.parseInt(dato);
			if (opcion<0 && opcion>4 || opcion<9 && opcion>14 || opcion<19 && opcion>24 || opcion<29 && opcion>34) {
				throw new IntervaloException("Opcion fuera del intervalo");
			}
			
			//saco las posiciones del array
			
			int num=Integer.parseInt(dato);
			int x=num/10;
			int y=num%10;
			
			//Compruebo si hay existencias y resto lo que vende
			if (existencias[x][y]==0) {
				System.err.println("Lo sentimos, pero no quedan existencias de la opcion seleccionada");
				
			}
			else {
				System.out.println("Recoja su pedido. Muchas gracias");
				
				//modifico las existencias
				existencias[x][y]--;
				
				//controlo lo que vendo
				ventas[x][y]++;
			}
		System.out.println();	
		} catch (NumberFormatException e) {
			// TODO: handle exception
			System.err.println("Por favor, introduzca un valor correcto");
		}
		catch (IntervaloException e) {
			System.err.println(e.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.err.println("Introduzca una opcion correcta");
			}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		leer=null;
		return ventas;
	}

	class IntervaloException extends Exception{
		
		public IntervaloException (String msg) {
			super("Mesaje de error: "+msg);
		}
	}
}
