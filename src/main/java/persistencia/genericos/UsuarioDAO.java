package persistencia.genericos;

import modelo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario buscarPorIdUsuario(long username);
	public abstract Usuario buscarPorEmailyPassword(String email, String password);
	public int desactivar(long id);

}