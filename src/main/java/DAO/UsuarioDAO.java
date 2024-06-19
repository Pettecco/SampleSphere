package DAO;

import Model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO extends ConnectionDAO{

    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertUsuario(Usuario usuario) {

        connectToDB();

        String sql = "INSERT INTO Usuario (email, nome) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getEmail());
            pst.setString(2, usuario.getNome());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Esse email já esta cadastrado em nosso sistema.");
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

    public boolean updateUsuarioNome(String email, String nome) {
        connectToDB();
        String sql = "UPDATE Usuario SET nome=? where email=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setString(2, email);
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

    public boolean deleteUsuario(String email) {
        connectToDB();
        String sql = "DELETE FROM Usuario where email=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
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


    public ArrayList<Usuario> selectUsuario() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Usuario";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            System.out.println("Lista de usuarios: ");

            while (rs.next()) {

                Usuario usuarioAux = new Usuario(rs.getString("email"), rs.getString("nome"));

                System.out.println("email = " + usuarioAux.getEmail());
                System.out.println("nome = " + usuarioAux.getNome());
                System.out.println("--------------------------------");

                usuarios.add(usuarioAux);
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
        return usuarios;
    }


}

