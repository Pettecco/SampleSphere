package org.example;

import DAO.*;
import Exceptions.NotaInvalidaException;
import Exceptions.UsernameNotFoundException;
import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NotaInvalidaException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        ArtistaDAO artistaDAO = new ArtistaDAO();
        AlbumDAO albumDAO = new AlbumDAO();
        Perfil_avalia_AlbumDAO perfil_avalia_albumDAO = new Perfil_avalia_AlbumDAO();
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
            System.out.println("6. Avaliar/Reavaliar um album");
            System.out.println("7. Listar albuns avaliados");
            System.out.println("8. Remover um album");
            System.out.println("9. Atualizar um album");
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
                    entrada.nextLine();
                    System.out.println("Digite o nome do album:");
                    String nomeAlbum = entrada.nextLine();
                    System.out.println("Digite o genero musical do album:");
                    String generoMusical = entrada.nextLine();

                    System.out.println("Qual artista fez o album? (Selecione pelo numero)");
                    ArrayList<Artista> artistasCadastrados = artistaDAO.selectArtista();
                    for (Artista artista: artistasCadastrados){
                        System.out.println(artista.getIdArtista() + " - " + artista.getNome());
                    }
                    int artistaResposavel = entrada.nextInt();
                    Album novoAlbum = new Album(nomeAlbum, generoMusical,artistaResposavel);
                    albumDAO.insertAlbum(novoAlbum);
                    break;
                case 4:
                    System.out.println("Albuns cadastrados:");
                    System.out.println("----------------------------------");
                    ArrayList<Album> albunsCadastrados = albumDAO.selectAlbum();
                    for(Album album: albunsCadastrados){
                        System.out.println("Nome: " + album.getNome());
                        System.out.println("Genero musical: " + album.getGeneroMusical());
                        System.out.println("Feito por: " + album.getNomeArtista());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 5:
                    System.out.println("Artistas cadastrados:");
                    System.out.println("----------------------------------");
                    ArrayList<Artista> artistaArrayList = artistaDAO.selectArtista();
                    for (Artista artista: artistaArrayList){
                        System.out.println("Nome: " + artista.getNome());
                        System.out.println("Qnt de albuns cadastrados: " + artista.getQntDeAlbuns());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 6:
                    System.out.println("Digite seu username: ");
                    entrada.nextLine();
                    String usernameAvalia = entrada.nextLine();
                    System.out.println("Qual album deseja avaliar?");
                    System.out.println("----------------------------------");
                    ArrayList<Album> albunsParaAvaliar = albumDAO.selectAlbum();
                    for(Album album: albunsParaAvaliar) {
                        System.out.println(album.getIdAlbum() +". "+ album.getNome());
                    }
                    int idAlbumParaAvaliar = entrada.nextInt();
                    System.out.println("Qual nota voce da para o album? (0.0 - 10.0)");
                    double nota = entrada.nextDouble();
                    entrada.nextLine();
                    try{
                        if(perfil_avalia_albumDAO.avaliarAlbum(usernameAvalia, idAlbumParaAvaliar, nota)){
                            System.out.println("Album avaliado com sucesso!");
                        } else{
                            System.out.println("Usurname não cadastrado");
                        }
                    } catch (NotaInvalidaException | UsernameNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Digite seu username: ");
                    entrada.nextLine();
                    String usernameListar = entrada.nextLine();
                    List<Album> albunsAvaliados = perfil_avalia_albumDAO.listarAlbunsAvaliados(usernameListar);

                    if (albunsAvaliados.isEmpty()) {
                        System.out.println("Nenhum álbum avaliado encontrado para o usuário: " + usernameListar);
                    } else {
                        System.out.println("Álbuns avaliados pelo usuário: " + usernameListar);
                        for (Album album : albunsAvaliados) {
                            System.out.println("Nome: " + album.getNome() + ", Gênero: " + album.getGeneroMusical() + ", Nota: " + album.getNota());
                        }
                    }
                    break;
                case 8:
                    System.out.println("Qual album deseja remover?");
                    System.out.println("----------------------------------");
                    ArrayList<Album> albunsParaRemover = albumDAO.selectAlbum();
                    for(Album album: albunsParaRemover) {
                        System.out.println(album.getIdAlbum() +". "+ album.getNome());
                    }
                    int remover = entrada.nextInt();
                    albumDAO.deleteAlbum(remover);
                    break;
                case 9:
                    System.out.println("Qual album voce deseja atualizar?");
                    ArrayList<Album> albumArrayList = albumDAO.selectAlbum();
                    for(Album album: albumArrayList) {
                        System.out.println(album.getIdAlbum() +". "+ album.getNome());
                    }
                    entrada.nextLine();
                    int idAlbum = entrada.nextInt();
                    System.out.println("O que voce deseja atualizar?");
                    int alteracao;
                    do {
                        System.out.println("1. Alterar nome");
                        System.out.println("2. Alterar genero musical");
                        alteracao = entrada.nextInt();
                    } while(alteracao != 1 && alteracao != 2);
                    entrada.nextLine();
                    if(alteracao == 1){
                        System.out.println("Digite o novo nome: ");
                        String novoNome = entrada.nextLine();
                        if(albumDAO.atualizarNomeAlbum(idAlbum, novoNome)){
                            System.out.println("Album atualizado com sucesso!");
                        }
                    } else {
                        System.out.println("Digite o novo genero musical: ");
                        String novoGeneroMusical = entrada.nextLine();
                        if(albumDAO.atualizarGeneroMusical(idAlbum, novoGeneroMusical)){
                            System.out.println("Album atualizado com sucesso!");
                        }
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:;
            }
        }

    }
}