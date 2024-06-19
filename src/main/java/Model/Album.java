package Model;

public class Album {
    private int idAlbum;
    private final String nome;
    private final double nota;
    private final String generoMusical;
    private final String faixaFavorita;
    private final int Artista_idArtista;


    public Album(String nome, double nota, String generoMusical, String faixaFavorita, int artista_idArtista) {
        this.nome = nome;
        this.nota = nota;
        this.generoMusical = generoMusical;
        this.faixaFavorita = faixaFavorita;
        this.Artista_idArtista = artista_idArtista;
    }

    // sobrecarga de construtor para conseguir pegar id do banco de dados
    public Album(int idAlbum, String nome, double nota, String generoMusical, String faixaFavorita, int artista_idArtista) {
        this.idAlbum = idAlbum;
        this.nome = nome;
        this.nota = nota;
        this.generoMusical = generoMusical;
        this.faixaFavorita = faixaFavorita;
        Artista_idArtista = artista_idArtista;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public String getFaixaFavorita() {
        return faixaFavorita;
    }

    public int getArtista_idArtista() {
        return Artista_idArtista;
    }
}
