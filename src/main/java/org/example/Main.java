package org.example;

import DAO.AlbumDAO;
import DAO.ArtistaDAO;
import DAO.PerfilDAO;
import DAO.UsuarioDAO;
import Model.Album;
import Model.Artista;
import Model.Perfil;
import Model.Usuario;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        ArtistaDAO artistaDAO = new ArtistaDAO();
        AlbumDAO albumDAO = new AlbumDAO();
        Scanner entrada = new Scanner(System.in);
        int op;
        boolean flag = true;
        System.out.println("----------- Bem vindo ao SampleSphere -----------");


        while(flag){
            System.out.println("Selecione uma opção: ");
            System.out.println("1. Criar uma conta");
            System.out.println("2. Cadastrar um artista");
            System.out.println("3. Cadastrar um album");
            System.out.println("4. Listar albuns cadastrados");
            System.out.println("5. Listar artistas cadastrados");
            System.out.println("6. Avaliar um album");
            System.out.println("7. Remover um album");
            System.out.println("8. Listar albuns avaliados");
            System.out.println("0. Sair");
            op = entrada.nextInt();
            boolean sucesso;

            switch (op){
                case 1:
                    entrada.nextLine();
                    System.out.println("Digite o email:");
                    String email = entrada.nextLine();
                    System.out.println("Digite seu nome:");
                    String nome = entrada.nextLine();
                    Usuario novoUsuario = new Usuario(email, nome);
                    sucesso = usuarioDAO.insertUsuario(novoUsuario);
                    if(sucesso){
                        boolean sucessoUsername = false;
                        while(!sucessoUsername){
                            System.out.println("Digite seu username:");
                            String username = entrada.nextLine();
                            Perfil novoPerfil = new Perfil(username, novoUsuario.getEmail());
                            sucessoUsername = perfilDAO.insertPerfil(novoPerfil);
                        }
                    }
                     break;
                case 2:
                    System.out.println("Digite o nome do artista:");
                    entrada.nextLine();
                    String nomeArtista = entrada.nextLine();
                    Artista novoArtista = new Artista(nomeArtista);
                    artistaDAO.insertArtista(novoArtista);
                    break;
                case 3:
                    System.out.println("Digite o nome do album:");
                    entrada.nextLine();
                    String nomeAlbum = entrada.nextLine();
                    System.out.println("Digite o genero musical do album:");
                    String generoMusical = entrada.nextLine();
                    System.out.println("Digite sua faixa favorita:");
                    String faixaFavorita = entrada.nextLine();
                    System.out.println("Digite uma nota para o album (0.0 a 10.0):");
                    double nota = entrada.nextFloat();

                    System.out.println("Qual artista fez o album? (Selecione pelo numero)");
                    ArrayList<Artista> artistasCadastrados = artistaDAO.selectArtista();
                    for (Artista artista: artistasCadastrados){
                        System.out.println(artista.getIdArtista() + " - " + artista.getNome());
                    }
                    int artistaResposavel = entrada.nextInt();

                    Album novoAlbum = new Album(nomeAlbum, nota, generoMusical,faixaFavorita, artistaResposavel);
                    albumDAO.insertAlbum(novoAlbum);
                    break;
                default:

            }
        }

    }
}