package com.sge.telas;

import javax.swing.*;
import com.sge.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MatricularAlunoFrame extends JFrame {

    private JTextField alunoIdField; // Campo para nome do aluno
    private JButton consultar, cancelar, matricular; // Botão Matricular adicionado
    private JTextArea resultadoArea; // Área de texto para exibir resultados
    private Aluno alunoSelecionado;

    public MatricularAlunoFrame() {
        setTitle("Matricular Aluno");
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
        gbc.fill = GridBagConstraints.NONE;
        add(consultar, gbc);

        // Botão Cancelar
        cancelar = new JButton("Cancelar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cancelar, gbc);

        // Área de texto para mostrar o resultado
        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        //Frase Curso
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Curso:"), gbc);

        //Opção Cursos
        
        String[] cursos = {"Análise e Desenvolvimento de Sistemas", "Ciência da Computação", "Engenharia de Software"};
    JComboBox<String> cursoComboBox = new JComboBox<>(cursos);
gbc.gridx = 1;
gbc.gridy = 3;
        add(cursoComboBox, gbc);

        // Botão Matricular (aba de matricular o aluno)
        matricular = new JButton("Matricular");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // O botão ocupa duas colunas
        add(matricular, gbc);

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

        // Manipulador de eventos para o botão Cancelar
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Operação Cancelada","Atenção",JOptionPane.ERROR_MESSAGE);
                setVisible(false);
                new TelaInicialFrame().setVisible(true);
            }
        });

        // Manipulador de eventos para o botão Matricular
        matricular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado != null) { // Verifica se um aluno foi selecionado
                    String[] cursosDisponiveis = {"Análise e Desenvolvimento de Sistemas", "Ciência da Computação", "Engenharia de Software"};
                    String cursoEscolhido = (String) JOptionPane.showInputDialog(
                            null,
                            "Selecione o curso para matricular:",
                            "Matricular Aluno",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            cursosDisponiveis,
                            cursosDisponiveis[0]
                    );

                    if (cursoEscolhido != null) { // Verifica se um curso foi selecionado
                        Curso cursoSelecionado = new Curso(cursoEscolhido); // Cria uma instância do curso selecionado
                        cursoSelecionado.addAluno(alunoSelecionado); // Matricula o aluno no curso
                        JOptionPane.showMessageDialog(null, "Aluno matriculado com sucesso no curso: " + cursoEscolhido);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nenhum curso foi selecionado.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, consulte e selecione um aluno antes de matricular.");
                }
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
