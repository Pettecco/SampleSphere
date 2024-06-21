package DAO;

import Model.Album;

import java.sql.SQLException;
import java.util.ArrayList;


public class AlbumDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertAlbum(Album album) {

        connectToDB();

        String sql = "INSERT INTO Album (nome, generoMusical, Artista_idArtista) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, album.getNome());
            pst.setString(2, album.getGeneroMusical());
            pst.setInt(3, album.getArtista_idArtista());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Artista criador do album não está cadastrado no sistema...");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    public ArrayList<Album> selectAlbum() {
        ArrayList<Album> albuns = new ArrayList<>();
        connectToDB();
        String sql = "SELECT a.idAlbum, a.Nome, a.GeneroMusical, a.Artista_idArtista, ar.nome AS NomeArtista " +
                "FROM Album a " +
                "JOIN Artista ar ON a.Artista_idArtista = ar.idArtista";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de Albuns: ");

            while (rs.next()) {

                Album AlbunsAux = new Album(rs.getInt("idAlbum"), rs.getString("Nome"), rs.getString("GeneroMusical"), rs.getInt("Artista_idArtista"));
                String nomeArtista = rs.getString("NomeArtista");  rs.getInt("Artista_idArtista");
                AlbunsAux.setNomeArtista(nomeArtista);
                albuns.add(AlbunsAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return albuns;
    }


    public boolean deleteAlbum(int idAlbum) {
        connectToDB();
        String sql = "DELETE FROM Album WHERE idAlbum=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idAlbum);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean atualizarNomeAlbum(int idAlbum, String novoNome) {

        connectToDB();

        String sql = "UPDATE Album SET nome =? WHERE idAlbum =?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoNome);
            pst.setInt(2, idAlbum);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Não foi possível atualizar o nome do álbum");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar a conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean atualizarGeneroMusical(int idAlbum, String novoGenero) {
        connectToDB();

        String sql = "UPDATE Album SET generoMusical =? WHERE idAlbum =?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, novoGenero);
            pst.setInt(2, idAlbum);
            pst.executeUpdate();
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Não foi possível atualizar o genero musical do álbum");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar a conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }
}