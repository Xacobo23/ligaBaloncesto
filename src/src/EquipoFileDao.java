import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EquipoFileDao implements Dao<Equipo, String> {
    private final Path path;

    public EquipoFileDao(String filePath) {
        this.path = Paths.get(filePath);
    }


    @Override
    public Equipo get(String id) {
        HashSet<Equipo> equipos = new HashSet<>(getAll());
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(id)) {
                return equipo;
            }
        }
        return null;
    }

    @Override
    public Set<Equipo> getAll() {
        HashSet<Equipo> equipos = new HashSet<>();
        if (!Files.exists(path)) return equipos;

        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
            while (true) {
                try {
                    equipos.add((Equipo) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }


        return new  TreeSet<Equipo>(equipos);
    }



    @Override
    public boolean save(Equipo equipo) {
        boolean append = Files.exists(path);

            try (FileOutputStream fos = new FileOutputStream(path.toFile(), append);
                 ObjectOutputStream oos = append ? new EquipoOutputStream(fos) : new ObjectOutputStream(fos)) {
                oos.writeObject(equipo);
                return true;
            } catch (IOException e) {
                System.out.println("Error al guardar el equipo: " + e.getMessage());
                return false;
            }

    }

    @Override
    public boolean delete(Equipo equipo) {
        HashSet<Equipo> equipos = new HashSet<>(getAll());
        if (equipos.removeIf(e -> e.equals(equipo))) {
            return saveAll(equipos);
        }
        return false;
    }

    @Override
    public boolean deleteById(String equipoId) {
        Set<Equipo> equipos = getAll();  // Recuperamos todos los equipos
        boolean equipoEncontrado = false;

        // Buscamos el equipo a eliminar
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equalsIgnoreCase(equipoId)) {
                equipoEncontrado = true;
                equipos.remove(equipo); // Eliminamos el equipo de la lista
                break;
            }
        }

        if (equipoEncontrado) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
                for (Equipo equipo : equipos) {
                    oos.writeObject(equipo);  // Guardamos cada equipo de nuevo
                }
                return true;  // Eliminación exitosa
            } catch (IOException e) {
                System.out.println("Error al guardar la lista actualizada de equipos.");
                return false;
            }
        } else {
            System.out.println("No se ha encontrado el equipo " + equipoId + ".");
            return false;
        }
    }



    @Override
    public boolean deleteAll() {
        try {
            Files.deleteIfExists(path);
            return true;
        } catch (IOException e) {
            System.out.println("Error al eliminar el archivo de la clasificación.");
            return false;
        }
    }

    @Override
    public void update(Equipo equipo) {
        HashSet<Equipo> equipos = new HashSet<>(getAll());
        delete(equipo);
        save(equipo);
    }

    private boolean saveAll(HashSet<Equipo> equipos) {
        deleteAll();
        for (Equipo equipo : equipos) {
            save(equipo);
        }
        return true;
    }
}

class EquipoOutputStream extends ObjectOutputStream {
    public EquipoOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // No escribe la cabecera
    }
}
