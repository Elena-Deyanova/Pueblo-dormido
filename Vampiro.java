import java.util.*;

public final class Vampiro extends Ciudadano implements IBatalla {
    private static int totalVampiros = 0; // se declaran los vampiros iniciales
    private static int ultimoVampiro = 0;
    private final EVulnerable vulnerable = EVulnerable.LOBO;

    public Vampiro() {
        super("VAMPIRO" + (++ultimoVampiro));
        totalVampiros++;
    }

    public static int getPoblacion() {
        return totalVampiros;
    }

    public static void setPoblacion(int numero) {
        totalVampiros = numero;
    }

    @Override
    public Ciudadano combate(Ciudadano oponente) {
        System.out.println(getNombre() + " (V) combate contra " + oponente.getNombre());

        if (oponente.getVulnerable() == EVulnerable.VAMPIRO) {
            // Aqui el vampitro gana
            if (oponente instanceof Humano) {
                    // Aqui el vampiro convierte al humano.
                String nombreOriginal = oponente.getNombre();
                oponente.morir(null);  // se eliminará del array
                Vampiro convertido = new Vampiro();
                System.out.println(nombreOriginal + " ha sido convertido en " + convertido.getNombre());
                return convertido;
            } else {
                oponente.morir(null);
                return oponente;
            }
        } else if (getVulnerable() == oponente.getVulnerable()) {
            System.out.println("Misma especie, pero no se reproduce, son muertos vivientes");
            return null;
        } else {
            System.out.println(getNombre() + " ha perdido.");
            return this;
        }
    }

    @Override
    public void morir(ArrayList<Ciudadano> ciudadanos) {
        if (ciudadanos != null) {
            ciudadanos.remove(this);
        }
        totalVampiros--;
        setPoblacion(getPoblacion() - 1);
        System.out.println(getNombre() + " V ha muerto.");
    }

    @Override
    public EVulnerable getVulnerable() {
        return vulnerable;
    }

    @Override
    public String toString() {
        return super.toString() + "Vampiro es  vulnerable a " + vulnerable;
    }
}
