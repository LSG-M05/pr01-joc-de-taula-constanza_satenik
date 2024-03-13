import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class  Main {
    public static List<String> nombresJugadores = new ArrayList<>();
    public static List<String> rolesJugadores = new ArrayList<>();
    public static List<Boolean> estadoVivoJugadores = new ArrayList<>();
    public static List<Integer> jugadoresReveladosPorElVidente = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static int enamorado1 = -1;
    public static int enamorado2 = -1;
    public static boolean cupidoHaActuado = false;

    public static void main(String[] args) {
        System.out.println("Bienvenido al juego de Lobos y Aldeanos.");
    }
    public void iniciarJuego() {
        System.out.println("Bienvenidos al juego.");
        registrarJugadores();
    }

    public void registrarJugadores() {
        System.out.println("Introduce el número de jugadores:");
        int numeroJugadores = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numeroJugadores; i++) {
            System.out.println("Nombre del jugador " + (i + 1) + ":");
            String nombre = scanner.nextLine();
            nombresJugadores.add(nombre);
            estadoVivoJugadores.add(true); // Todos los jugadores comienzan vivos
            rolesJugadores.add(""); // Los roles están vacíos
        }
    }
    public void asignarRoles() {
        int numeroJugadores = jugadores.size();
        List<String> rolesDisponibles = new ArrayList();
        rolesDisponibles.add("Cupido");
        rolesDisponibles.add("Vidente");

        int i;
        for (i =0; i < 2; i++) {
            rolesDisponibles.add("Lobo");
        }
        for(i = rolesDisponibles.size(); i < numeroJugadores; i++) {
            rolesDisponibles.add ("Aldeano");
        }
        Iterator var6 = jugadores.iterator();
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

            while (var2.hasNext()) {
                jugador = (Jugador) var2.next();
                if (jugador.vivo && !jugador.rol.equals("Lobo")) {
                    System.out.println(jugador.id + ": " + jugador.nombre);
                }
            }
            int victimaId = scanner.nextInt();
            boolean esAldeano = false;
            Iterator var4 = jugadores.iterator();

            while (var4.hasNext()) {
                Jugador jugador = (Jugador) var4.next();
                if (jugador.id == victimaId && !jugador.rol.equals("Lobo")) {
                    jugador.vivo = false;
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

        static class Jugador {
            int id;
            String nombre;
            String rol;
            boolean vivo =true;
            public Jugador (int id, String nombre ) {
                this.id = id;
                this.nombre =nombre;
            }
            public void setRol(String rol) {
                this.rol = rol;
            }
        }
    }
}