public abstract class Ciudadano {
    private static int poblacion;
    private String nombre;
    private static int contadorPoblacion;
    //poner el vulnerable


    public Ciudadano(String nombre) {
        this.nombre = nombre;
    }

    public static int getPoblacion() {
        return poblacion;
    }

    public String getNombre() {
        return nombre;
    }

    public static void setPoblacion(int poblacion) {
        Ciudadano.poblacion = poblacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
