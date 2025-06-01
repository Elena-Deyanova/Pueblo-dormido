import java.util.*;

public final class Humano extends Ciudadano implements IBatalla, ICicloVital {
    private static int totalHumanos = 0;
    private static int ultimoHumano = 0;
    private static final Random ALEATORIO = new Random();

    private final EVulnerable vulnerable = EVulnerable.VAMPIRO;
    private int vida;
    private int cuantosHijos = 0;

    public Humano() {
        super("HUMANO" + (++ultimoHumano));
        vida = ALEATORIO.nextInt(VITALIDAD_MAXIMA) + 1;
        totalHumanos++;
    }

    public static int getPoblacion() {
        return totalHumanos;
    }

    public static void setPoblacion(int numero) {
        totalHumanos = numero;
    }

    @Override
    public Ciudadano combate(Ciudadano oponente) {
        System.out.println(getNombre() + " (H) combate contra " + oponente.getNombre());

        if (oponente.getVulnerable() == EVulnerable.HUMANO) {
            System.out.println("¡" + getNombre() + " ha ganado el combate");
            oponente.morir(null);  // Se elimina
            return oponente;
        } else if (getVulnerable() == oponente.getVulnerable()) {
            System.out.println("Son del mismo tipo, pueden reproducirse");
            return null;
        } else {
            System.out.println(getNombre() + " ha perdido el combate");
            return this;
        }
    }

    @Override
    public void reproducir(ArrayList<Ciudadano> ciudadanos) {
        if (cuantosHijos < NATALIDAD_MAXIMA) {
            Humano hijo = new Humano();
            ciudadanos.add(hijo);
            cuantosHijos++;
            System.out.println(getNombre() + " ha tenido un hijo: " + hijo.getNombre()); // aqui tiene el hijo
        } else {
            System.out.println(getNombre() + " ya ha alcanzado el máximo de natalidad."); // esto es si ya se ha alcanzado el limite de hijos
        }
    }

    @Override
    public void morir(ArrayList<Ciudadano> ciudadanos) {
        if (ciudadanos != null) {
            ciudadanos.remove(this);
        }
        totalHumanos--;
        setPoblacion(getPoblacion() - 1);
        System.out.println(getNombre() + "  H ha muerto.");
    }

    @Override
    public void envejecer(ArrayList<Ciudadano> ciudadanos) {
        vida--;
        System.out.println(getNombre() + " ha envejecido. Vida restante: " + vida);
        if (vida <= 0) {
            System.out.println(getNombre() + " ha muerto de viejo:(");
            morir(ciudadanos);
        }
    }

    @Override
    public EVulnerable getVulnerable() {
        return vulnerable;
    }

    @Override
    public String toString() {
        return super.toString() + " Humano,  Vida: " + vida + " Vulnerable a: " + vulnerable;
    }
}