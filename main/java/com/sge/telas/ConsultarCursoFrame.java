package com.sge.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.sge.util.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultarCursoFrame extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private String name;
    private JLabel number;
    private Curso cursos;
    private List<Aluno> alunos = new ArrayList<>();

    public ConsultarCursoFrame(String cursoname) {
        this.name = cursoname;
        setTitle(name);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        //Alunos cadastrados
        cursos = new Curso(this.name);
        number = new JLabel("Número de alunos cadastrados em " + cursos.getName() + ": \n" + alunos.size());
        add(number,BorderLayout.NORTH);

        // Criar tabela
        String[] colunas = {"Nome", "Data de Nascimento", "Email", "Matrícula", "Nota"};
        tableModel = new DefaultTableModel(colunas, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Adicionar botão Voltar
        JButton voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(e -> {
            setVisible(false);
            new TelaInicialFrame().setVisible(true);
        });

        add(voltarButton, BorderLayout.SOUTH);

        //Inserir dados na tabela

        // Limpa a tabela antes de adicionar os dados
        tableModel.setRowCount(0);

        List<Aluno> alunos = CadastrarAlunoFrame.getAlunosCadastrados();
        for (Aluno aluno : alunos) {
            Object[] row = {
                aluno.getNome(),
                aluno.getDataNascimento(),
                aluno.getEmail(),
                aluno.getId(),
                aluno.getNota() >= 0 ? aluno.getNota() : "Não cadastrada"
            };
            tableModel.addRow(row);
        }



        setVisible(true);

    }
}