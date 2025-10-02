package ui;

import java.util.LinkedList;
import java.util.Scanner;
import structures.PilaGenerica;
import structures.TablasHash;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public void ejecutar() throws Exception {
        while (true) {
            System.out.println("\nSeleccione la opcion:");
            System.out.println("1. Punto 1, Verificar balanceo de expresión");
            System.out.println("2. Punto 2, Encontrar pares con suma objetivo");
            System.out.println("3. Salir del programa");
            System.out.print("Opcion: ");

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese expresion a verificar:");
                    String expresion = sc.nextLine();
                    boolean resultado = verificarBalanceo(expresion);
                    System.out.println("Resultado: " + (resultado ? "TRUE" : "FALSE"));
                    break;

                case 2:
                    System.out.println("Ingrese numeros separados por espacio: ");
                    String lineaNumeros = sc.nextLine();
                    System.out.println("Ingrese suma objetivo: ");
                    int objetivo = Integer.parseInt(sc.nextLine());

                    String[] partes = lineaNumeros.trim().split("\\s+");
                    int[] numeros = new int[partes.length];
                    for (int i = 0; i < partes.length; i++) {

                        numeros[i] = Integer.parseInt(partes[i]);
                    }


                    encontrarParesConSuma(numeros, objetivo);
                    break;

                case 3:
                    System.out.println("Chao");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no permitida");
            }
        }
    }

    /**
     * Verifica si la expresion esta balanceada usando PilaGenerica
     * @param s expresion a verificar
     * @return true si esta balanceada, false si no
     */
    public boolean verificarBalanceo(String s) {
        
        PilaGenerica<Character> pila = new PilaGenerica<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                pila.Push(c);
             
            } else if ((c == ')' || c == '}' || c == ']') && pila.getTop() > 0) {
                
                if (pila.getTop() == 0) {
                    return false;
            
                } 
                char apertura = pila.Pop();
                if (!esParCorrecto(apertura, c)) {
                    return false;
                }
     
            }
        }
        return pila.getTop() == 0;
    }

    public boolean esParCorrecto(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||(apertura == '{' && cierre == '}') ||(apertura == '[' && cierre == ']');
       
    }
    /**
     * Encuentra y muestra todos los pares unicos de numeros que sumen objetivo usando TablasHash.
     * @param numeros arreglo de numeros enteros
     * @param objetivo suma objetivo
     */
    public void encontrarParesConSuma(int[] numeros, int objetivo) {
  
        try {
        TablasHash tabla = new TablasHash(100);
        LinkedList<String> paresEncontrados = new LinkedList<>();
        
        for(int i = 0; i < numeros.length; i++) {
            tabla.insert(numeros[i], numeros[i]);
        }

        for(int i = 0; i < numeros.length; i++) {
            int complemento = objetivo - numeros[i];
            
            if(tabla.search(complemento, complemento)) {
    
                int menor, mayor;
                if (numeros[i] < complemento) {
                    menor = numeros[i];
                    mayor = complemento;
                } else {
                    menor = complemento;
                    mayor = numeros[i];
                }

                String par = menor + ", " + mayor;

                if(!paresEncontrados.contains(par)) {
                    paresEncontrados.add(par);
                    System.out.println("Par encontrado: (" + par + ")");
                }
            }
        }
        } catch (Exception e) {
            
        }
        
    }
    public static void main(String[] args) throws Exception {
        Main app = new Main();
        app.ejecutar();
    }
}
