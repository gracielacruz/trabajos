


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.*;
/**
 *
 * @author Graciela
 */
public class VectorDistancia {
    
      
    List<Aristas> aristas = new LinkedList<>();
    List<Aristas> aristaImp = new LinkedList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private  int pesos[];
    @SuppressWarnings("FieldMayBeFinal")
    private  int predecesor[];
    @SuppressWarnings("FieldMayBeFinal")
    private  int numVertices;
    private  int totalAristas;
    @SuppressWarnings("FieldMayBeFinal")
    private  int nodoOrigen;
    private final int INFINITY = 9999;
    
    FileInputStream fis = new FileInputStream("Escenario3.txt");
    Scanner sc = new Scanner(fis);

   public static class Aristas {

        int origen, destino;
        int coste;

            public Aristas(int a, int b, int c) {
            origen = a;
            destino = b;
            coste = c;
        }
        
                public int getOrigen() {
			return origen;
		}

		public void setOrigen(int origen) {
			this.origen = origen;
		}

		public int getDestino() {
			return destino;
		}

		public void setDestino(int destino) {
			this.destino = destino;
		}

		public float getCoste() {
			return coste;
		}

		public void setCoste(int coste) {
			this.coste = coste;
		}

		@Override
        public String toString() {
            return "Aristas{" + "origen=" + origen + ", destino=" + destino + ", coste=" + coste + '}';
        }
    }

    public VectorDistancia() throws IOException {
        
        
        DataInputStream in = new DataInputStream(System.in);
        System.out.print("Introduce la cantidad de vertices ");
        numVertices = Integer.parseInt(in.readLine());
        System.out.println("Matriz de costos: ");
     
        
        //Leer Archivo de Distancias
        LeerArchivoDistancia();
                     
        //Imprimir la matriz
        imprimirMatriz();
                       
        totalAristas = aristas.size()  ;
        pesos = new int[numVertices];
        predecesor = new int[numVertices];
        System.out.println("\n Introducir el vertice origen ");
        nodoOrigen = Integer.parseInt(in.readLine()) - 1;
    }

    private void relajoArista() {
        int i, j;
        for (i = 0; i < numVertices; ++i) {
            pesos[i] = INFINITY;
        }
        pesos[nodoOrigen] = 0;
        
        System.out.println("Total Aristas: " + totalAristas  + "\n  Total Vertices: " + numVertices);
    
        
        for (i = 0; i < numVertices - 1; ++i) {
            for (j = 0; j < totalAristas; ++j) {
           
               
                
                if (pesos[ aristas.get(j).origen] + aristas.get(j).coste < pesos[aristas.get(j).destino]) {
                    pesos[aristas.get(j).destino] = pesos[aristas.get(j).origen] + aristas.get(j).coste;
                    predecesor[aristas.get(j).destino] = aristas.get(j).origen;
                  
                }
              
            }
            for (int p = 0; pesos.length < p; p++) {
                System.out.println("\tPesos: " + pesos[p]);
            }
        }
      }
    

    private boolean ciclo() {
        int j;
        for (j = 0; j < totalAristas; ++j) {
            if (pesos[aristas.get(j).origen] + aristas.get(j).coste < pesos[aristas.get(j).destino]) {
                return false;
            }
          	        
        }
        return true;
    }

    
    private void LeerArchivoDistancia() throws IOException {
    	int vorigen  = sc.nextInt();
        int vdestino = sc.nextInt();
        int costo = sc.nextInt();
 
        while(vorigen != 999)
 		{
 			System.out.println(vorigen + ", " + vdestino + ", " + costo);
 			
 			if (costo != 0 && costo != 999) {
 				aristas.add(new Aristas(vorigen, vdestino, costo));
 			}
                        aristaImp.add(new Aristas(vorigen, vdestino, costo));
 			vorigen = sc.nextInt();
 			vdestino = sc.nextInt();
 			costo = sc.nextInt();
 		}
 		
 		fis.close();
    }
    
   private void imprimirMatriz() {
    	
         
         System.out.print("\n   ");
         for (int i=0; i < numVertices; i++)
         {
         	System.out.print("   "+i); 
         }
         
         System.out.print("\n ");
         for (int i = 0; i < numVertices*2; i++)
         {
         		System.out.print("___");        
         }
  
         //Imprimir la matriz (Lista de distancias)
         for (int i = 0; i < numVertices; i++) {
         	System.out.print("\n " + i+" |  ");
         	
             for (int j = i*numVertices; j < i*numVertices + numVertices; j++) {
                             	 
                 	System.out.print(aristaImp.get(j).coste + "   ");               	                	                         	                 
             }
              
         }
         
         	
    }
    
    public static void main(String args[]) throws IOException {
        VectorDistancia bellman = new VectorDistancia();
        bellman.relajoArista();
        if (bellman.ciclo()) {
            for (int i = 0; i < bellman.numVertices; i++) {
                System.out.println("Coste desde " + bellman.nodoOrigen + " a " + (i ) + " =>" + bellman.pesos[i]);
            }
            for (int i = 0; i < bellman.numVertices; i++) {
                System.out.println("El predecesor de " + (i ) + " es " + (bellman.predecesor[i] ));
            }
        } else {
            System.out.println("Hay un ciclo negativo");
        }
    }
}
