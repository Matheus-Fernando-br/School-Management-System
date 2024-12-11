package com.sge.util;

import java.util.Objects;

public class Aluno {
    private final String nome;
    private final String dataNascimento;
    private String email;
    private final int id;
    private double nota;

    public Aluno(String nome, String dataNascimento, String email, int id) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.id = id;
        this.nota = -1;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Aluno other = (Aluno) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String info = "Nome: " + nome + "\n" +
                "Data de Nascimento: " + dataNascimento + "\n" +
                "E-mail: " + email + "\n" +
                "Matrícula: " + id;
        if (nota >= 0) {
            info += "\nNota: " + nota;
        } else {
            info += "\nNota: Não cadastrada";
        }
        return info;
    }
}