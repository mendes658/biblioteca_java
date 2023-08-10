package com.pbl.biblioteca.DAO;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import com.pbl.biblioteca.model.*;

public class UsuarioDAO {

    private static ArrayList<Usuario> abrirFileUsuario(String url) throws ClassNotFoundException{
        ArrayList<Usuario> usuarioArrayList = new ArrayList<>();
        FileInputStream arquivoIn;
        ObjectInputStream objetoIn;
        Usuario usuarioAtual;


        try{
            arquivoIn = new FileInputStream(url);
            objetoIn = new ObjectInputStream(arquivoIn);

            while (arquivoIn.available() > 0) {
                usuarioAtual = (Usuario) objetoIn.readObject();
                usuarioArrayList.add(usuarioAtual);
            }

            objetoIn.close();

        } catch (IOException e){
            return usuarioArrayList;
        }

        return usuarioArrayList;
    }

    private static Boolean salvarFileUsuario(ArrayList<Usuario> usuarioList, String url){
        ObjectOutputStream objetoOut;
        FileOutputStream arquivoOut;

        try{
            arquivoOut = new FileOutputStream(url);
            objetoOut = new ObjectOutputStream(arquivoOut);

            for (Usuario usuario : usuarioList) {
                objetoOut.writeObject(usuario);
            }

            objetoOut.close();

        } catch (IOException e){
            return false;
        }

        return true;
    }

    public static void mai(String[] args) throws ClassNotFoundException{
        ArrayList<Usuario> usuarioArrayList;
        usuarioArrayList = abrirFileUsuario("listUsuario.ser");

        Usuario userTest = new Usuario();
        Usuario userTest2 = new Usuario();

        userTest.setUsername("pedrin556");
        userTest2.setUsername("mendes556");

        usuarioArrayList.add(userTest);
        usuarioArrayList.add(userTest2);

        Boolean ok = salvarFileUsuario(usuarioArrayList, "listUsuario.ser");

    }

    public static void main(String[] args) throws ClassNotFoundException{
        ArrayList<Usuario> usuarioArrayList;
        usuarioArrayList = abrirFileUsuario("listUsuario.ser");

        for(Usuario usuario : usuarioArrayList){
            System.out.println(usuario.getUsername());
        }
    }
}
