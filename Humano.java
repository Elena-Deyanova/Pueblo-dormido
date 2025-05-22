import java.util.*;

public class Humano extends Ciudadano implements ICicloVital{

    private static int totalHumanos;
    private static int ultimoHumano;
    private static Random ALEATORIO;
    private int vida;
    private int cuantosHijos;

    public Humano(String nombre) {
        super(nombre);
    }

    @Override
    public int reproducir() {
        return 0;
    }

    @Override
    public int envejecer() {
        return 0;
    }


}
