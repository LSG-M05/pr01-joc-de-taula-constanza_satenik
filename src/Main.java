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
    public void ejecutarFaseNocturna() {
        System.out.println("Coomienza la fase nocturna. ");
        List<Jugador>lobos =new ArrayList();
        Iterator var2=jugadores.iterator();

        Jugador jugador;
        while(var2.hasNext()) {
            jugador = (Jugador)var2.next();
            if(jugador.rol.equals("Lobo") && jugador.vivo) {
                lobos.add(jugador);
            }
        }
        if (lobos.isEmpty()) {
            System.out.println("No hay lobos vivos. ");
        }else {
            System.out.println("Lobos, elijan a quién quieren atacar: ");
            var2 = jugadores.iterator();

            while(var2.hasNext()) {
                jugador = (Jugador)var2.next();
                if (jugador.vivo && !jugador.rol.equals("Lobo")) {
                    System.out.println(jugador.id+ ": "+ jugador.nombre);
                }
            }
            int victimaId = scanner.nextInt();
            boolean esAldeano = false;
            Iterator var4 =jugadores.iterator();

            while (var4.hasNext()) {
                Jugador jugador = (Jugador)var4.next();
                if (jugador.id == victimaId && !jugador.rol.equals("Lobo")) {
                    jugador.vivo =false;
                    esAldeano = true;
                    System.out.println(jugador.nombre + "ha sido eliminada. Su rol era: " + jugador.rol);
                    break;
                }
            }
            if (!esAldeano) {
                System.out.println("Los lobos deben elegir a un aldeano como víctima. Por favor, elijan nuevamente.");
                this.ejecutarFaseNocturna();
            }
        }

    }
}