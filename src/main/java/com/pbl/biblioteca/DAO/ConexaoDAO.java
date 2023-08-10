package com.pbl.biblioteca.DAO;

import com.pbl.biblioteca.model.*;

import java.io.*;
import java.util.ArrayList;



public class ConexaoDAO {

    private static final String usuarioFileUrl = "usuarios.ser";
    private static final String emprestimoFileUrl = "emprestimos.ser";
    private static final String livroFileUrl = "livros.ser";
    private static final String operadorFileUrl = "operadores.ser";


    private static Object getQualquerListSalva(String fileUrl){
        FileInputStream arquivoIn;
        ObjectInputStream objetoIn;
        Object geralList;

        try{
            arquivoIn = new FileInputStream(usuarioFileUrl);
            objetoIn = new ObjectInputStream(arquivoIn);
            geralList = objetoIn.readObject();

            objetoIn.close();

        } catch (IOException | ClassNotFoundException e){
            return null;
        }

        return geralList;
    }

    private static boolean salvarQualquerList(Object objetoList, String fileUrl){
        ObjectOutputStream objetoOut;
        FileOutputStream arquivoOut;

        try{
            arquivoOut = new FileOutputStream(fileUrl);
            objetoOut =  new ObjectOutputStream(arquivoOut);
            objetoOut.writeObject(objetoList);
            objetoOut.close();

        } catch (IOException e){
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Usuario> getUsuarioList(){
        return (ArrayList<Usuario>) getQualquerListSalva(usuarioFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Emprestimo> getEmprestimoList(){
        return (ArrayList<Emprestimo>) getQualquerListSalva(emprestimoFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Operador> getOperadorList(){
        return (ArrayList<Operador>) getQualquerListSalva(operadorFileUrl);
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Livro> getLivroList(){
        return (ArrayList<Livro>) getQualquerListSalva(livroFileUrl);
    }

    public static boolean salvarLivro(Livro objetoLivro){
        ArrayList<Livro> listLivros = getLivroList();
        if (listLivros == null){
            return false;
        }

        listLivros.add(objetoLivro);

        return salvarQualquerList(listLivros, livroFileUrl);
    }

    public static boolean salvarEmprestimo(Emprestimo objetoEmprestimo){
        ArrayList<Emprestimo> listEmprestimos = getEmprestimoList();
        if (listEmprestimos == null){
            return false;
        }

        listEmprestimos.add(objetoEmprestimo);

        return salvarQualquerList(listEmprestimos, emprestimoFileUrl);
    }

    public static boolean salvarUsuario(Usuario objetoUsuario){
        ArrayList<Usuario> listUsuarios = getUsuarioList();
        if (listUsuarios == null){
            listUsuarios = new ArrayList<Usuario>();
        }

        listUsuarios.add(objetoUsuario);

        return salvarQualquerList(listUsuarios, usuarioFileUrl);
    }

    public static boolean salvarOperador(Operador objetoOperador){
        ArrayList<Operador> listOperador = getOperadorList();
        if (listOperador == null){
            return false;
        }

        listOperador.add(objetoOperador);

        return salvarQualquerList(listOperador, operadorFileUrl);
    }



    public static Usuario getUsuarioByUsername(String username){
        ArrayList<Usuario> listUsuarios = getUsuarioList();
        for (Usuario usuario : listUsuarios){
            if (usuario.getUsername().equals(username)){
                return usuario;
            }
        }
        return null;
    }


    public static void main (String[] args) throws ClassNotFoundException{
        Usuario testeUser;
        Usuario teste2 = new Usuario();
        boolean ok;
        teste2.setUsername("pedro658");
        teste2.setNome("Pedro Mendes");
        teste2.setSenha("12345");

        ok = salvarUsuario(teste2);

        System.out.println("jajaj");


        for (int i = 0; i<52; i++){
            teste2 = new Usuario();
            teste2.setSenha("54321" + i );
            teste2.setNome("Pedrin Matador" + i);
            teste2.setUsername("mendes" + i);

            ok = salvarUsuario(teste2);
        }

        testeUser = getUsuarioByUsername("mendes51");

        if (testeUser != null){
            System.out.println(testeUser.getUsername());
            System.out.println(testeUser.getId());
            System.out.println(testeUser.getNome());

            testeUser = getUsuarioByUsername("mendes33");
            if (testeUser != null){
                System.out.println(testeUser.getUsername());
                System.out.println(testeUser.getId());
                System.out.println(testeUser.getNome());
            }

        }


    }
}
