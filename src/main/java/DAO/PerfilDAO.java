package DAO;

import Model.Perfil;

import java.sql.SQLException;
import java.util.ArrayList;


public class PerfilDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPerfil(Perfil perfil) {

        connectToDB();

        String sql = "INSERT INTO Perfil (username, qntAlbuns, Usuario_email) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, perfil.getUsername());
            pst.setInt(2, perfil.getQntAlbuns());
            pst.setString(3, perfil.getUsuario_email());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Esse username já está sendo usado.");
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

    public boolean updatePerfilUsername(String username, String usuario_email) {
        connectToDB();
        String sql = "UPDATE Perfil SET username=? where Usuario_email=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, usuario_email);
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

    public boolean deletePerfil(String username) {
        connectToDB();
        String sql = "DELETE FROM Perfil where username=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro = " + ex.getMessage());
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro: " + exc.getMessage());
            }
        }
        return false;
    }


    public ArrayList<Perfil> selectPerfil() {
        ArrayList<Perfil> perfis = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Perfil";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de perfis: ");

            while (rs.next()) {

                Perfil perfisAux = new Perfil(rs.getString("username"), rs.getString("Usuario_email"));

                System.out.println("username = " + perfisAux.getUsername());
                System.out.println("qntAlbuns = " + perfisAux.getQntAlbuns());
                System.out.println("email = " + perfisAux.getUsuario_email());
                System.out.println("--------------------------------");

                perfis.add(perfisAux);
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
        return perfis;
    }


}

