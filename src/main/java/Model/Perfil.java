package Model;

public class Perfil {
    private final String username;
    private int qntAlbuns;
    private final String usuario_email;

    public Perfil (String username, String usuario_email) {
        this.username = username;
        this.usuario_email =  usuario_email;
    }

    public String getUsername() {
        return username;
    }

    public int getQntAlbuns() {
        return qntAlbuns;
    }

    public String getUsuario_email() {
        return usuario_email;
    }

}
