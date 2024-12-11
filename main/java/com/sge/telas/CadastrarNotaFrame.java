package com.sge.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sge.util.Aluno;
import java.util.List;

public class CadastrarNotaFrame extends JFrame {

    private JTextField notaField, alunoIdField;
    private JButton consultar, cancelar, cadastrarNota;
    private JTextArea resultadoArea;
    private Aluno alunoSelecionado;

    public CadastrarNotaFrame() {
        setTitle("Cadastrar Nota");
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

        consultar = new JButton("Pesquisar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(consultar, gbc);

        cancelar = new JButton("Cancelar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(cancelar, gbc);

        resultadoArea = new JTextArea(10, 30);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nota:"), gbc);

        notaField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(notaField, gbc);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 3, 10, 10));

        cadastrarNota = new JButton("Cadastrar Nota");
        painelBotoes.add(cadastrarNota);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(painelBotoes, gbc);

        consultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAluno = alunoIdField.getText();
                if (!nomeAluno.isEmpty()) {
                    alunoSelecionado = buscarAluno(nomeAluno);
                    if (alunoSelecionado != null) {
                        resultadoArea.setText(alunoSelecionado.toString());
                    } else {
                        resultadoArea.setText("Aluno não encontrado.");
                    }
                } else {
                    resultadoArea.setText("Por favor, insira o nome do aluno.");
                }
            }
        });

        cadastrarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alunoSelecionado != null) {
                    try {
                        double nota = Double.parseDouble(notaField.getText());
                        alunoSelecionado.setNota(nota); // Atualiza a nota do aluno
                        JOptionPane.showMessageDialog(null, "Nota cadastrada com sucesso!");
                        resultadoArea.setText(alunoSelecionado.toString());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Por favor, insira uma nota válida.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno primeiro.");
                }
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Operação Cancelada","Atenção",JOptionPane.ERROR_MESSAGE);
                setVisible(false);
                new TelaInicialFrame().setVisible(true);
            }
        });

        setVisible(true);
    }

    private Aluno buscarAluno(String nome) {
        List<Aluno> alunos = CadastrarAlunoFrame.getAlunosCadastrados(); // Busca na lista correta
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                return aluno; // Retorna o aluno se encontrado
            }
        }
        return null; // Retorna null se não encontrar
    }
}
