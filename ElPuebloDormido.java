import java.util.*;

public class ElPuebloDormido {
    private static final int POBLACION_MINIMA = 5;
    private static final int POBLACION_MAXIMA = 30;

    private ArrayList<Ciudadano> pueblo = new ArrayList<>();
    private Scanner lector = new Scanner(System.in);
    private Random aleatorio = new Random();

    public static void main(String[] args) {
        ElPuebloDormido simulacion = new ElPuebloDormido();
        simulacion.generarPoblacionAleatoria();
        Ciudadano.censar(simulacion.pueblo);

        boolean continuar = true;
        while (continuar) {
            continuar = simulacion.mostrarMenu(continuar);
        }
        System.out.println("Adios");
    }

    public boolean mostrarMenu(boolean continuar) {
        try {
            System.out.println("1. Censo actual"); // menu
            System.out.println("2. Pasar un año");
            System.out.println("3. Salir");
            System.out.print("Que opcion deseas?");
            int opcion = lector.nextInt();

            switch (opcion) {
                case 1:
                    Ciudadano.censar(pueblo);
                    break;
                case 2:
                    pasarAnyo();
                    verificarPoblacion();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Del 1 al 3 porfavor");
            }
        } catch (Exception e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }
        return continuar;
    }

    public void generarPoblacionAleatoria() {
        int cantidad = aleatorio.nextInt(POBLACION_MAXIMA - POBLACION_MINIMA + 1) + POBLACION_MINIMA;
        for (int i = 0; i < cantidad; i++) {
            pueblo.add(obtenerCiudadanoAleatorio());
        }
    }

    public Ciudadano obtenerCiudadanoAleatorio() {
        int tipo = aleatorio.nextInt(3);
        switch (tipo) {
            case 0: return new Humano();
            case 1: return new Lobo();
            default: return new Vampiro();
        }
    }

    public Ciudadano obtenerOponenteAleatorio(int actual) {
        if (pueblo.size() <= 1) {
            throw new IllegalStateException("No hay ningún oponente disponible.");
        }

        int indice;
        do {
            indice = aleatorio.nextInt(pueblo.size());
        } while (indice == actual);
        return pueblo.get(indice);
    }

    public void pasarAnyo() throws Exception {
        if (pueblo.isEmpty()) {
            throw new IllegalStateException("No hay ciudadanos disponibles.");
        }

        try {
            ArrayList<Ciudadano> nuevos = new ArrayList<>();
            ArrayList<Ciudadano> muertos = new ArrayList<>();

            for (int i = 0; i < pueblo.size(); i++) {
                Ciudadano actual = pueblo.get(i);
                Ciudadano oponente = obtenerOponenteAleatorio(i);
                realizarAccion(actual, oponente, nuevos, muertos);
                actualizarEdad(actual);
            }

            pueblo.removeAll(muertos);
            pueblo.addAll(nuevos);
        } catch (Exception e) {
            throw new Exception("Error al pasar el año: " + e.getMessage());
        }
    }

    public void realizarAccion(Ciudadano c1, Ciudadano c2, ArrayList<Ciudadano> nuevos, ArrayList<Ciudadano> muertos) {
        if (c1 == null || c2 == null) {
            throw new IllegalArgumentException("Los oponentes no pueden ser null.");
        }

        if (c1.getClass() == c2.getClass()) {
            if (c1 instanceof ICicloVital) {
                ((ICicloVital) c1).reproducir(nuevos);
            } else {
                System.out.println(c1.getNombre() + " no puede reproducirse.");
            }
        } else {
            combatir(c1, c2, muertos, nuevos);
        }
    }

    public void combatir(Ciudadano c1, Ciudadano c2, ArrayList<Ciudadano> muertos, ArrayList<Ciudadano> nuevos) {
        Ciudadano perdedor = ((IBatalla) c1).combate(c2);
        if (perdedor != null) {
            if (perdedor instanceof Vampiro && !pueblo.contains(perdedor)) {
                // Un humano que se transformo
                nuevos.add(perdedor);
            } else {
                muertos.add(perdedor);
            }
        }
    }

    public void actualizarEdad(Ciudadano ciudadano) {
        if (ciudadano instanceof ICicloVital) {
            ((ICicloVital) ciudadano).envejecer(pueblo);
        }
    }

    public void verificarPoblacion() {
        boolean soloUnTipo = pueblo.stream()
                .allMatch(c -> c.getClass() == pueblo.get(0).getClass());

        if (soloUnTipo) {
            System.out.println("Solo queda un tipo de ser en el pueblo.");
            System.exit(0);
        }
    }
}
