
import java.io.Serializable;
import java.util.Objects;

public class Equipo implements Comparable<Equipo>, Serializable {
    private String nombre;
    private int victorias;
    private int derrotas;
    private int puntosAfavor;
    private int puntosEnContra;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public Equipo(String nombre, int victorias, int derrotas, int puntosAfavor, int puntosEnContra) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.puntosAfavor = puntosAfavor;
        this.puntosEnContra = puntosEnContra;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getPuntosAfavor() {
        return puntosAfavor;
    }

    public int getPuntosEnContra() {
        return puntosEnContra;
    }

    public int getPartidosJugados() {
        return victorias + derrotas;
    }

    public int getDiferenciaDePuntos() {
        return puntosAfavor - puntosEnContra;
    }

    @Override
    public int compareTo(Equipo otro) {
        if (this.getVictorias() != otro.getVictorias()) {
            return Integer.compare(otro.getVictorias(), this.getVictorias());
        } else {
            if (this.getDerrotas() != otro.getDerrotas()) {
                return Integer.compare(otro.getDerrotas(), this.getDerrotas());
            } else return Integer.compare(otro.getDiferenciaDePuntos(), this.getDiferenciaDePuntos());

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Equipo)) return false;
        Equipo equipo = (Equipo) obj;
        return nombre.equalsIgnoreCase(equipo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", victorias=" + victorias +
                ", derrotas=" + derrotas +
                ", puntosAfavor=" + puntosAfavor +
                ", puntosEnContra=" + puntosEnContra +
                '}';
    }
}
