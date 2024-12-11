package com.sge.telas;

import javax.swing.*;
import com.sge.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ConsultarAlunoFrame extends JFrame {

    private JTextField alunoIdField; // Campo para nome do aluno
    private JButton consultar, editar, excluir, voltar;
    private JTextArea resultadoArea; // Área de texto para exibir resultados
    private Aluno alunoSelecionado;

    public ConsultarAlunoFrame() {
        setTitle("Consultar Aluno");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Adicionando o campo para pesquisa por nome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nome do Aluno:"), gbc);

        alunoIdField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(alunoIdField, gbc);

        // Botão Consultar
        consultar = new JButton("Consultar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preencher horizontalmente
gbc.gridwidth = GridBagConstraints.REMAINDER; // Ocupa o restante da 
        add(consultar, gbc);

       

        // Área de texto para mostrar o resultado
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        // Botão Editar
        editar = new JButton("Editar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        add(editar, gbc);

        //Botão Excluir
        excluir = new JButton("Excluir");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.BOTH;
        add(excluir, gbc);

        //Botão Voltar
        voltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        add(voltar, gbc);


        // Manipulador de eventos para o botão Consultar
        consultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAluno = alunoIdField.getText();
                if (!nomeAluno.isEmpty()) {
                    alunoSelecionado = buscarAluno(nomeAluno);
                    if (alunoSelecionado != null) {
                        resultadoArea.setText(alunoSelecionado.toString());
                    } else {
                        resultadoArea.setText("Aluno não encontrado. Verifique o nome e tente novamente.");
                    }
                } else {
                    resultadoArea.setText("Por favor, insira o nome do aluno.");
                }
            }
        });

        // Manipulador de eventos para o botão Voltar

        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new TelaInicialFrame().setVisible(true);
            }
        });

        setVisible(true);
    }

    private Aluno buscarAluno(String nome) {
        List<Aluno> alunos = CadastrarAlunoFrame.getAlunosCadastrados(); // Busca na lista correta
        System.out.println("Lista de alunos cadastrados: " + alunos); // Log para depuração
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                return aluno; // Retorna o aluno se encontrado
            }
        }
        return null; // Retorna null se não encontrar
    }
}
