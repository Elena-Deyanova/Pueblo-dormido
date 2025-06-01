import java.util.*;

public interface ICicloVital { // otra interfaz, en la que usamos dos arraylits y tambien fijamos las dos constantes de natalidad y vitalidad.
        int NATALIDAD_MAXIMA = 1;
        int VITALIDAD_MAXIMA = 2;

        void reproducir(ArrayList<Ciudadano> ciudadanos);
        void envejecer(ArrayList<Ciudadano> ciudadanos);
}