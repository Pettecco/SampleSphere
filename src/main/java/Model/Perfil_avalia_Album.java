package Model;

public class Perfil_avalia_Album {
    private final String Perfil_username;
    private final int Album_idAlbum;
    private double nota;
    private final String faixaFavorita;

    public Perfil_avalia_Album(String perfil_username, int album_idAlbum, double nota, String faixaFavorita) {
        Perfil_username = perfil_username;
        Album_idAlbum = album_idAlbum;
        this.nota = nota;
        this.faixaFavorita = faixaFavorita;
    }

    public String getPerfil_username() {
        return Perfil_username;
    }

    public int getAlbum_idAlbum() {
        return Album_idAlbum;
    }

    public double getNota() {
        return nota;
    }

    public String getFaixaFavorita() {
        return faixaFavorita;
    }
}
