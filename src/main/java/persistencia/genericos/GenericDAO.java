package persistencia.genericos;

import java.util.ArrayList;

public interface GenericDAO<T> {

	public ArrayList<T> buscarTodos();
	public int contarTodos();
	public int insertar(T t);
	public int actualizar(T t);
	public int borrar(T t);

}