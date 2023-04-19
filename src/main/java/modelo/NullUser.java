package modelo;


public class NullUser extends Usuario {

	public static Usuario build() {
		return new NullUser();
	}
	
	public NullUser() {
		super();
	}
	
	public boolean isNull() {
		return true;
	}
	
}
