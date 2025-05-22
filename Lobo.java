import java.util.*;
public class Lobo extends Ciudadano implements ICicloVital{

      private  static int totalLobos;
      private static int ultimoLobo;
      private static Random ALEATORIO;
      private enum VULNERABLE{};
      private int vida;
      private int cuantosHijos;

    public Lobo(String nombre) {
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
