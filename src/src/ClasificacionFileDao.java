
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ClasificacionFileDao implements Dao<Clasificacion, String> {
    private final String ruta;

    public ClasificacionFileDao(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public Clasificacion get(String competicion) {
        EquipoFileDao equipoDAO = new EquipoFileDao(ruta + competicion + ".dat");
        Clasificacion clasificacion = new Clasificacion(competicion);
        equipoDAO.getAll().forEach(clasificacion::addEquipo);
        return clasificacion;
    }

    @Override
    public HashSet<Clasificacion> getAll() {
        throw new UnsupportedOperationException("No se ha implementado getAll para clasificaciones.");
    }

    @Override
    public boolean save(Clasificacion clasificacion) {
        EquipoFileDao equipoDAO = new EquipoFileDao(ruta + clasificacion.getCompeticion() + ".dat");
        delete(clasificacion);
        clasificacion.getEquipos().forEach(equipoDAO::save);
        return true;
    }

    @Override
    public boolean delete(Clasificacion clasificacion) {
        return new EquipoFileDao(ruta + clasificacion.getCompeticion() + ".dat").deleteAll();
    }

    @Override
    public boolean deleteById(String competicion) {
        return new EquipoFileDao(ruta + competicion + ".dat").deleteAll();
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException("No se ha implementado deleteAll para clasificaciones.");
    }

    @Override
    public void update(Clasificacion clasificacion) {
        delete(clasificacion);
        save(clasificacion);
    }
}
