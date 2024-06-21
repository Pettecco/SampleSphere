package DAO;

import Exceptions.NotaInvalidaException;
import Exceptions.UsernameNotFoundException;
import Model.Album;

import java.sql.*;
import java.util.ArrayList;

public class Perfil_avalia_AlbumDAO extends ConnectionDAO{

    boolean sucesso = false; //Para saber se funcionou

    private boolean verificaUsername(String username) {

        connectToDB();

        String sql = "SELECT username FROM Perfil WHERE username = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Username não encontrado.");
            sucesso = false;
        }
        return sucesso;
    }

    public boolean avaliarAlbum(String username, int idAlbum, double nota) throws NotaInvalidaException, UsernameNotFoundException {

        if (nota < 0 || nota > 10) {
            throw new NotaInvalidaException("A nota deve estar entre 0 e 10.");
        }

        connectToDB();

        if (!verificaUsername(username)) {
            throw new UsernameNotFoundException("Username não encontrado");
        }

        String sql = "INSERT INTO Perfil_avalia_Album (Perfil_username, Album_idAlbum, nota) " +
                "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE nota = VALUES(nota)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setInt(2, idAlbum);
            pst.setDouble(3, nota);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Não foi possivel avaliar o album");
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
            }
        }
        return sucesso;
    }

    public ArrayList<Album> listarAlbunsAvaliados(String username) {
        ArrayList<Album> albunsAvaliados = new ArrayList<>();
        connectToDB();

        String sql = "SELECT a.idAlbum, a.nome, a.generoMusical, p.nota FROM Album a " +
                "JOIN Perfil_avalia_Album p ON a.idAlbum = p.Album_idAlbum " +
                "WHERE p.Perfil_username = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();

            while (rs.next()) {
                int idAlbum = rs.getInt("idAlbum");
                String nome = rs.getString("nome");
                String generoMusical = rs.getString("generoMusical");
                double nota = rs.getDouble("nota");

                Album album = new Album(idAlbum, nome, generoMusical, nota);
                albunsAvaliados.add(album);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro ao listar albuns avaliados: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
                rs.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
        return albunsAvaliados;
    }
}

