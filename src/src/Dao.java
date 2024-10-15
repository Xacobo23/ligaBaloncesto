import java.util.Set;

public interface Dao<T, K> {
    T get(K id);
    Set<T> getAll();
    boolean save(T objeto);
    boolean delete(T obj);
    boolean deleteById(K id);
    boolean deleteAll();
    void update(T obj);
}
