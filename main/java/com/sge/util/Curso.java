package com.sge.util;

import java.util.ArrayList;
import java.util.List;

import com.sge.telas.CadastrarAlunoFrame;

public class Curso {
    private String name;

    // Lista estática para armazenar os alunos cadastrados
    private static List<Aluno> alunosCadastrados = new ArrayList<>();

    public Curso(String name){
 
        this.name = name;
    }

    // Getters
    public String getName(){
        return name;
    }

  
    // Setters
    public void setName(String name){
        this.name = name;
    }

  

    public void addAluno(Aluno aluno){
        alunosCadastrados.add(aluno); // Implementado o método addAluno
    }

    public void removeAluno(Aluno aluno){
        alunosCadastrados.remove(aluno); // Implementado o método removeAluno
    }

    // Retornando informações do Curso
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(name);
           return sb.toString();
    }

    // Método para obter a lista de alunos cadastrados
    public static List<Aluno> getAlunosCadastrados() {
        return alunosCadastrados; // Retorna a lista estática de alunos
    }

}