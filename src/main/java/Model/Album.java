package Model;

public class Album {
    private int idAlbum;
    private final String nome;
    private final String generoMusical;
    private int Artista_idArtista;
    private double nota;
    private String nomeArtista;

    public Album(String nome, String generoMusical, int artista_idArtista) {
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.Artista_idArtista = artista_idArtista;
    }

    // sobrecarga de construtor para conseguir pegar id do banco de dados
    public Album(int idAlbum, String nome, String generoMusical, int artista_idArtista) {
        this.idAlbum = idAlbum;
        this.nome = nome;
        this.generoMusical = generoMusical;
        Artista_idArtista = artista_idArtista;
    }

    public Album(int idAlbum, String nome, String generoMusical, double nota) {
        this.idAlbum = idAlbum;
        this.nome = nome;
        this.generoMusical = generoMusical;
        this.nota = nota;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public String getNome() {
        return nome;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public int getArtista_idArtista() {
        return Artista_idArtista;
    }

    public double getNota() {
        return nota;
    }
}
