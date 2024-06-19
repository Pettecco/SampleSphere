package Model;

public class Perfil_avalia_Album {
    private final String Perfil_username;
    private final int Album_idAlbum;

    public Perfil_avalia_Album(String perfil_username, int album_idAlbum) {
        Perfil_username = perfil_username;
        Album_idAlbum = album_idAlbum;
    }

    public String getPerfil_username() {
        return Perfil_username;
    }

    public int getAlbum_idAlbum() {
        return Album_idAlbum;
    }
}
