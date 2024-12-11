package com.sge.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sge.util.Aluno;
import java.util.ArrayList;
import java.util.List;

public class CadastrarAlunoFrame extends JFrame {
    private JTextField nomeField, dataNascimentoField, emailField;
    private JButton cadastrar, cancelar;
    private static List<Aluno> alunosCadastrados = new ArrayList<>();
    private static int contadorId = 1;

    public CadastrarAlunoFrame() {
        setTitle("Cadastro de Aluno");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nome:"));
        nomeField = new JTextField();
        add(nomeField);

        add(new JLabel("Data de Nascimento:"));
        dataNascimentoField = new JTextField();
        add(dataNascimentoField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        cadastrar = new JButton("Cadastrar");
        add(cadastrar);

        cancelar = new JButton("Cancelar");
        add(cancelar);

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Operação Cancelada","Atenção",JOptionPane.ERROR_MESSAGE);
                setVisible(false);
                new TelaInicialFrame().setVisible(true);
            }
        });

        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nomeField.getText().isEmpty() || dataNascimentoField.getText().isEmpty() || emailField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!");
                    return;
                }

                int id = contadorId++;
                Aluno aluno = new Aluno(nomeField.getText(), dataNascimentoField.getText(), emailField.getText(), id);
                alunosCadastrados.add(aluno); // Adiciona à lista

                System.out.println("Aluno cadastrado: " + aluno); // Log do cadastro

                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!", "CADASTRO COM SUCESSO", JOptionPane.INFORMATION_MESSAGE);

                limparCampos();

                setVisible(false);
                new TelaInicialFrame().setVisible(true);
            }
        });

        setVisible(true);
    }

    private void limparCampos() {
        nomeField.setText("");
       dataNascimentoField.setText("");
       emailField.setText("");
    }

    public static List<Aluno> getAlunosCadastrados() {
       System.out.println("Lista atual de alunos cadastrados: " + alunosCadastrados); // Log da lista
       return alunosCadastrados; 
   }
}
