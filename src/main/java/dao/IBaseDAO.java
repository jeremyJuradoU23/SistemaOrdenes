package dao;

import java.util.List;

public interface IBaseDAO<T> {
    
    boolean insertar(T t);
    
    boolean actualizar(T t);
    
    boolean eliminar(int id);
    
    T obtener(int id);
    
    List<T> listar();
}
