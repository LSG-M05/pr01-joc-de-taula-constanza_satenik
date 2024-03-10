import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class  Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
     Main juego=new Main(); //Creamos el objeto juego
     juego.iniciarJuego();
    }
    public void iniciarJuego() {
        System.out.println("Bienvenidos al juego.");
        registrarJugadores();
    }

    public void registrarJugadores() {
        System.out.println("Introduce el numero de tus jugadores: ");
        int numeroJugadores=Integer.parseInt(scanner.nextLine());
        for (int i = 1; i <= numeroJugadores;i++) {
            System.out.println("Nombre del jugador" + i + ":");
            String nombre = scanner.nextLine();
        }
    }
}