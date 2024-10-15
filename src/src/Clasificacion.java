import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Clasificacion implements Serializable {
    private String competicion;
    private Set<Equipo> equipos;

    public Clasificacion() {
        this.competicion = "Liga ACB";
        this.equipos = new TreeSet<>();
    }

    public Clasificacion(String competicion) {
        this.competicion = competicion;
        this.equipos = new TreeSet<>();
    }

    public String getCompeticion() {
        return competicion;
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public void addEquipo(Equipo equipo) {
        Scanner sc = new Scanner(System.in);

        long count = equipos.stream()
                .filter(e -> e.getNombre().equalsIgnoreCase(equipo.getNombre()))
                .count();

        if (count >= 1) {
            System.out.println("El equipo " + equipo.getNombre() + " ya existe más de dos veces. ¿Quieres actualizarlo? (S/N): ");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                equipos.removeIf(e -> e.getNombre().equalsIgnoreCase(equipo.getNombre()));
                equipos.add(equipo);
                System.out.println("Equipo actualizado: " + equipo.getNombre());
            } else {
                System.out.println("No se ha actualizado el equipo " + equipo.getNombre() + ".");
            }
        } else {
            equipos.add(equipo);
            System.out.println("Nuevo equipo añadido: " + equipo.getNombre());
        }
    }

    public void removeEquipo(Equipo equipo) {
        equipos.remove(equipo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Clasificación de " + competicion + ":\n");
        sb.append("--------------------------------------------------------\n");
        sb.append("Equipo              | V  | D  | PJ  | PtsF  | PtsC  | Dif\n");
        sb.append("--------------------------------------------------------\n");
        for (Equipo equipo : equipos) {
            sb.append(String.format("%-18s | %-2d | %-2d | %-3d | %-5d | %-5d | %-4d\n",
                    equipo.getNombre(),
                    equipo.getVictorias(),
                    equipo.getDerrotas(),
                    equipo.getPartidosJugados(),
                    equipo.getPuntosAfavor(),
                    equipo.getPuntosEnContra(),
                    equipo.getDiferenciaDePuntos()));
        }
        sb.append("--------------------------------------------------------\n");
        return sb.toString();
    }

}
