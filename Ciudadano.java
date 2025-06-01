import java.util.*;

public abstract class Ciudadano {
    private static int poblacion = 0;
    private String nombre;

    public Ciudadano(String nombre) {
        this.nombre = nombre;
        poblacion++; // cada vez que haya un ciudadano nuevo, se le sumará a la poblacion.
    }

    public static int getPoblacion() {
        return poblacion;
    }

    public static void setPoblacion(int numero) {
        poblacion = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "Nombre del ciudadano: " + nombre;
    }

    public static void censar(ArrayList<Ciudadano> ciudadanos) {
        for (Ciudadano c : ciudadanos) {
            System.out.println(c);
        }
        poblacionesTotales(ciudadanos);
    }

    public static void poblacionesTotales(ArrayList<Ciudadano> ciudadanos) {
        int humanos = 0, lobos = 0, vampiros = 0;

        for (Ciudadano c : ciudadanos) {
            if (c instanceof Humano) humanos++;
            else if (c instanceof Lobo) lobos++;
            else if (c instanceof Vampiro) vampiros++;
        }

        System.out.println("Total ciudadanos: " + ciudadanos.size());
        System.out.println("Humanos: " + humanos);
        System.out.println("Lobos: " + lobos);
        System.out.println("Vampiros: " + vampiros);
    }

    public abstract void morir(ArrayList<Ciudadano> ciudadanos);
    public abstract EVulnerable getVulnerable();
}