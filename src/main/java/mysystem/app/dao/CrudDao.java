package mysystem.app.dao;

import java.util.List;

public interface CrudDao<T> {
    boolean save(T model);

    boolean update(T model);

    boolean delete(T model);

    List<T> findAll();
}
