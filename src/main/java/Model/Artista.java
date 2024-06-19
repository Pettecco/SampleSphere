package Model;

public class Artista {

    private int idArtista;
    private int qntDeAlbuns;
    private final String nome;


    public Artista (String nome) {
        this.nome = nome;
        this.qntDeAlbuns = 0;
    }

    // sobrecarga de construtor para conseguir pegar id e qnt de albuns do banco de dados
    public Artista(int idArtista, int qntDeAlbuns, String nome) {
        this.idArtista = idArtista;
        this.qntDeAlbuns = qntDeAlbuns;
        this.nome = nome;
    }

    public int getIdArtista () {
        return idArtista;
    }

    public int getQntDeAlbuns() {
        return qntDeAlbuns;
    }

    public String getNome() {
        return nome;
    }

}
