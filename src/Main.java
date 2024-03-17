import java.util.*;

public class Main {

    public static void main(String[] args) {
        jugarJuego();
    }

    public static void jugarJuego() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> accionLobos = new ArrayList<>();
        boolean pocionSalvacion = false;
        boolean pocionMatar = false;

        registrarJugadores(nombres, ids);
        asignarRoles(roles);
        mostrarRolesAsignados(nombres, ids, roles);

        // Inicio del juego
        System.out.println("\nVamos a comenzar el juego!! que la suerte este de su lado (frase robada :)");
        System.out.println("Comencemos!");

        int lobosActuados = 0;
        jugar(nombres, roles, accionLobos, pocionSalvacion, pocionMatar);
    }

    public static void registrarJugadores(ArrayList<String> nombres, ArrayList<Integer> ids){
        Scanner scanner = new Scanner(System.in);

        //Se registran los jugadores
        System.out.println("Bienvenidos al juego de LOBO");


        //control de errores para que no este vacio cuando ingrese el nombre
        for (int i = 1; i <= 7; i++) {
            String nombre;
            boolean repetido;
            do {
                repetido = false;
                System.out.println("Ingrese el nombre del Jugador " + i + ": ");
                nombre = scanner.nextLine();
                if (nombre.contains(nombre)) {
                    System.out.println("El nombre ya esta registrado, ingrese uno nuevo");
                    repetido = true;
                }
                if (nombre.isEmpty()) {
                    System.out.println("El nombre no puede estar vacio, ingrese uno");
                }
            }while (nombre.isEmpty());

            nombres.add(nombre);
            ids.add(i);
            System.out.println(nombre + " debes recordar este numero de ID ya que luego tu rol estara asociado a este numero ---> ID: " + i);
            System.out.println("Presiona enter si estas listo para que otro juagdor se registre");
            scanner.nextLine();

            //agregamos estas lineas, para que el jugador que sigue, no pueda ver el ID del jugador que se registro antes
            for (int j = 0; j < 12; j++) {
                System.out.println("-------------------------------");
            }
        }
    }

    public static void asignarRoles(ArrayList<String> roles) {
        roles.add("Cupido");
        roles.add("Lobo");
        roles.add("Lobo");
        roles.add("Aldeano");
        roles.add("Bruja");
        roles.add("Aldeano");
        roles.add("Vidente");

        Collections.shuffle(roles);
    }

    public static void mostrarRolesAsignados(ArrayList<String> nombres, ArrayList<Integer> ids, ArrayList<String> roles) {
        System.out.println("\nHora de comenzar a jugar. Los roles son:");
        for (int i = 0; i < nombres.size(); i++) {
            System.out.println("ID " + ids.get(i) + ": " + roles.get(i));
        }
    }


    public static void jugar(ArrayList<String> nombres, ArrayList<String> roles, ArrayList<String> accionLobos, boolean pocionSalvacion, boolean pocionMatar) {

        System.out.println("\nEs de noche en la aldea, y se comienzan a despertar los personajes!!");

        lobo(nombres, accionLobos);
        pocionSalvacion = bruja(nombres, pocionSalvacion, pocionMatar, accionLobos);
        cupido(nombres, accionLobos);
        vidente(nombres, roles);
        // Votación
        votacion(nombres, accionLobos, roles);
    }

    public static void cupido(ArrayList<String> nombres, ArrayList<String> accionLobos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Cupido se despierta!! Y elije a dos jugadores para enamorar");

        System.out.println("Lista de Jugadores");
        for (String nombre : nombres){
            System.out.println(nombre);
        }

        System.out.println("Elije al primer enamorado");
        String enamorado1 = scanner.nextLine();

        System.out.println("Elije al segundo enamorado");
        String enamorado2 = scanner.nextLine();

        System.out.println(enamorado1 + "y" + enamorado2 + "ahora son enamorados");

        if (accionLobos.contains("Atacaron a " + enamorado1) || accionLobos.contains("Atacaron a " + enamorado2)) {
            System.out.println("Lamentablemente, uno de los enamorados ha muerto durante la noche!");
            System.out.println("¡El otro enamorado también sufre la muerte");
            accionLobos.add("Atacaron a " + enamorado1 + " y " + enamorado2); // registro de ambas muertes
        }
    }


    public static void vidente(ArrayList<String> nombres, ArrayList<String> roles) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("La vidente se despierta y elije el rol de que jugador quiere saber");

        System.out.println("Lista de Jugadores");
        for (String nombre : nombres){
            System.out.println(nombre);
        }

        System.out.println("Seleccione al jugadr que desea saber el rol");
        String nombreJugador = scanner.nextLine();

        int index = nombres.indexOf(nombreJugador);
        if (index != -1) {
            String rolJugador = roles.get(index);
            System.out.println("El jugador" + nombreJugador + "tiene el rol: " + rolJugador);
        }else{
            System.out.println("El nombre del jugador no existe en la partida");
        }
    }

    public static void lobo(ArrayList<String> nombres, ArrayList<String> accionLobos) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Se despiertan los lobos y deciden a quien quieren matar esta noche");

        System.out.println("Lista de Jugadores");
        for (String nombre : nombres){
            System.out.println(nombre);
        }

        System.out.println("Seleccione al jugador que desean matar");
        String jugadorMatado = scanner.nextLine();

        System.out.println("- - - - - - - - - - - - - - - -");

        if (nombres.contains(jugadorMatado)) {
            accionLobos.add("Atacaron a " + jugadorMatado);
        }else {
            System.out.println("Ingrese un nombre valido");
        }
    }

    public static boolean bruja(ArrayList<String> nombres, boolean pocionSalvacion, boolean pocionMatar, ArrayList<String> accionesLobos) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Se despierta la bruja!! y decide si usar sus pocimas");

        // Mostrar a la bruja a quién han matado los lobos
        String jugadorAtacado = accionesLobos.get(accionesLobos.size() - 1).substring(10);
        System.out.println("Los lobos han atacado a: " + jugadorAtacado);

        if (!pocionSalvacion) {
            System.out.println("La bruja puede usar la poción de salvación para salvar a " + jugadorAtacado + ".");
            System.out.println("¿Quieres usar la poción de salvación? (s/n)");
            String respuestaSalvacion = scanner.nextLine();

            if (respuestaSalvacion.equalsIgnoreCase("s")) {
                System.out.println("La bruja ha usado la poción de salvación para salvar a " + jugadorAtacado + ".");
                pocionSalvacion = true;
                // Eliminar la acción de lobos correspondiente
                accionesLobos.remove(accionesLobos.size() - 1);
            }
        }

        if (!pocionMatar) {
            System.out.println("¿Quieres usar la poción de matar? (s/n)");
            String respuestaMatar = scanner.nextLine();

            if (respuestaMatar.equalsIgnoreCase("s")) {
                System.out.println("¿A quién quieres matar?");

                System.out.println("Lista de Jugadores");
                for (String nombre : nombres){
                    System.out.println(nombre);
                }

                System.out.println("Seleccione al jugador que desean matar");
                String jugadorAMatar = scanner.nextLine();

                // Lógica para matar al jugador
                System.out.println("La bruja ha usado la poción de matar para eliminar a " + jugadorAMatar + ".");
                accionesLobos.add("La bruja ha matado a " + jugadorAMatar); // guardo el nombre del jugador matado
            }
        }
        return pocionSalvacion;
    }


    public static void votacion(ArrayList<String> nombres, ArrayList<String> accionLobos, ArrayList<String> roles) {
        Scanner scanner = new Scanner(System.in);
        int[] votos = new int[nombres.size()];

        System.out.println("Es de día en la aldea! Todoos se despiertan, es hora de ver que ocurrio a la noche");

        //el narrador hace saber quienes murieron por la noche
        if (!accionLobos.isEmpty()) {
            System.out.println("Lamentablemente durante la noche, ocurrieron muertes, y fueron: ");
            List<String> jugadoresMuertos = new ArrayList<>();
            for (String accion : accionLobos) {
                System.out.println(accion);
                String jugadorMuerto = accion.substring(10);
                jugadoresMuertos.add(jugadorMuerto);
            }
            nombres.remove(jugadoresMuertos);
        }else {
            System.out.println("Afortunadamente nadie muerio durante la noche");
        }

        System.out.println("Ahora es tiempo de votar a quien se cree, que puede ser un lobo");

        // turno de votación
        for (int i = 0; i < nombres.size(); i++) {
            System.out.println("Jugador " + nombres.get(i) + ", es tu turno de votar:");
            System.out.println("¿A quién votas para eliminar? (Escribe el nombre del jugador)");
            String voto = scanner.nextLine();

            // Contar votos
            int votoIndex = nombres.indexOf(voto);
            if (votoIndex != -1) {
                votos[votoIndex]++;
            } else {
                System.out.println("¡El jugador ingresado no existe!");
            }
        }

        // Encontrar al jugador más votado
        int maxVotos = 0;
        int jugadorEliminado = -1;
        for (int i = 0; i < votos.length; i++) {
            if (votos[i] > maxVotos) {
                maxVotos = votos[i];
                jugadorEliminado = i;
            }
        }

        // Anunciar jugador eliminado
        String jugadorEliminadoNombre = nombres.get(jugadorEliminado);
        System.out.println("El jugador " + jugadorEliminadoNombre + " ha sido eliminado.");
        int index = nombres.indexOf(jugadorEliminadoNombre);
        if (index != -1) {
            String rolEliminado = roles.get(index);
            System.out.println("El jugador " + jugadorEliminadoNombre + "era: " + rolEliminado);
            nombres.remove(index);
            roles.remove(index);
        }
    }

}





