package telas;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

import SQLquery.sql;

public class principal extends teclado {

    private JPanel painelPrincipal, DISPLAY, tela;
    private JTextField cEleitor;
    private JLabel T1, ERRO, JAVOTOU;
    private String codEleitor = "";

    private JMenuBar BARRA = new JMenuBar();
    private JMenu MENU = new JMenu("Votos");
    private JMenuItem APURAR = new JMenuItem("Apurar votos");
    private JMenuItem CANDIDATOS = new JMenuItem("Listar candidatos");
    private JMenuItem NOVAELEICAO = new JMenuItem("Nova eleição - reseta votos");
    private JMenuItem dummy = new JMenuItem("");

    private sql sql = new sql();

    // as variáveis abaixo podem ser modificadas para melhor atender outros tipo de eleições
    // tanto os cargos elegíveis quanto a quantidade de dígitos necessários para digitação podem ser modificados
    private final String[] cargos = {"PRESIDENTE", "GOVERNADOR(A)", "SENADOR(A)", "DEPUTADO(A) FEDERAL", "DEPUTADO(A) ESTADUAL"};
    private final int[] qtd_dig = {2, 2, 3, 4, 5};

    principal(){

        new teclado(); // instancia o teclado e seus elementos

        setTitle("URNA ELETRÔNICA");
        setSize(1300,700);
        setLocation(0,0);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        painelPrincipal = new JPanel();
        painelPrincipal.setSize(1300,700);
        painelPrincipal.setLocation(0,0);
        painelPrincipal.setLayout(null);
        painelPrincipal.setBackground(Color.WHITE);
        painelPrincipal.setVisible(true);

        DISPLAY = new JPanel();
        DISPLAY.setSize(750,600);
        DISPLAY.setLocation(70,20);
        DISPLAY.setLayout(null);
        DISPLAY.setBackground(new Color(210, 210, 210));
        DISPLAY.setBorder(new MatteBorder(2, 2, 2, 0, Color.BLACK));
        DISPLAY.setVisible(true);

        tela = new JPanel();
        tela.setSize(650,400);
        tela.setLocation(50,70);
        tela.setLayout(null);
        tela.setBackground(Color.WHITE);
        tela.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
        tela.setVisible(true);

        cEleitor = new JTextField(codEleitor, CENTER);
        cEleitor.setSize(180,50);
        cEleitor.setLocation(230,175);
        cEleitor.setFont(new Font ("Arial", Font.BOLD, 26));
        cEleitor.setEditable(false);

        T1 =  new JLabel("Digite o número de identificação do eleitor:", CENTER);
        T1.setSize(420,50);
        T1.setLocation(115,120);
        T1.setFont(new Font ("Arial", Font.BOLD, 20));

        ERRO = new JLabel("<html><font color='red'>O título de eleitor deve ter exatamente 12 dígitos!</font></html>"
        , CENTER);
        ERRO.setSize(600, 50);
        ERRO.setLocation(25, 270);
        ERRO.setFont(new Font ("Arial", Font.BOLD, 18));
        ERRO.setVisible(false);

        JAVOTOU = new JLabel("<html><font color='red'>Você já votou!</font></html>"
                , CENTER);
        JAVOTOU.setSize(600, 50);
        JAVOTOU.setLocation(25, 270);
        JAVOTOU.setFont(new Font ("Arial", Font.BOLD, 18));
        JAVOTOU.setVisible(false);

        APURAR.addActionListener(new ActionListener() { // abre a tela de apuração
            @Override
            public void actionPerformed(ActionEvent menu) {
                new apuracao(cargos, qtd_dig);
                dispose();
            }
        });

        CANDIDATOS.addActionListener(new ActionListener() { // abre a tela de listagem dos candidatos
            @Override
            public void actionPerformed(ActionEvent menu) {
                new listarCandidatos(cargos, qtd_dig);
                dispose();
            }
        });

        NOVAELEICAO.addActionListener(new ActionListener() { // começa uma nova eleição
            @Override
            public void actionPerformed(ActionEvent menu) {
                // query para truncar as tabelas de eleitores e votação
                sql.ExecutarAlteracao("SET FOREIGN_KEY_CHECKS = 0;");
                sql.ExecutarAlteracao("TRUNCATE TABLE eleitor;");
                sql.ExecutarAlteracao("TRUNCATE TABLE votacao;");
                sql.ExecutarAlteracao("SET FOREIGN_KEY_CHECKS = 1;");
            }
        });

        MENU.add(APURAR);
        MENU.add(CANDIDATOS);
        MENU.add(NOVAELEICAO);
        BARRA.add(MENU);
        BARRA.add(dummy);
        setJMenuBar(BARRA);

        tela.add(cEleitor);
        tela.add(T1);
        tela.add(ERRO);
        tela.add(JAVOTOU);

        DISPLAY.add(tela);

        painelPrincipal.add(DISPLAY);
        painelPrincipal.add(TECLADO); // adicionando os elementos herdados da classe teclado

        getContentPane().add(painelPrincipal);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(cEleitor.getText().length() < 12){ // verifica se o código do eleitor < 12 dígitos
            if(e.getSource() == B0){
                cEleitor.setText(cEleitor.getText() + "0");
            }
            else if(e.getSource() == B1){
                cEleitor.setText(cEleitor.getText() + "1");
            }
            else if(e.getSource() == B2){
                cEleitor.setText(cEleitor.getText() + "2");
            }
            else if(e.getSource() == B3){
                cEleitor.setText(cEleitor.getText() + "3");
            }
            else if(e.getSource() == B4){
                cEleitor.setText(cEleitor.getText() + "4");
            }
            else if(e.getSource() == B5){
                cEleitor.setText(cEleitor.getText() + "5");
            }
            else if(e.getSource() == B6){
                cEleitor.setText(cEleitor.getText() + "6");
            }
            else if(e.getSource() == B7){
                cEleitor.setText(cEleitor.getText() + "7");
            }
            else if(e.getSource() == B8){
                cEleitor.setText(cEleitor.getText() + "8");
            }
            else if(e.getSource() == B9){
                cEleitor.setText(cEleitor.getText() + "9");
            }
        }

        if(e.getSource() == bConfirma) { // botão confirmar
            codEleitor = cEleitor.getText();
            if (codEleitor.length() == 12) {
                if(sql.ExecutarInsercao("INSERT INTO eleitor(id_eleitor) VALUES ("+ codEleitor +");")){
                    new candidato(cargos, qtd_dig, 0, Long.parseLong(codEleitor));
                    sql.encerrarConexao();
                    dispose();
                }
                else{
                    JAVOTOU.setVisible(true); // exibe mensagem de erro em vermelho
                    // adicionando timer para esconder a mensagem de erro depois de 5 segundos
                    Timer t = new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JAVOTOU.setVisible(false);
                        }
                    });
                    t.setRepeats(false);
                    t.start(); // começa a contar o tempo
                }
            }
        }
        else if(e.getSource() == bCorrige && cEleitor.getText().length() > 0) // apaga o último caractere
            cEleitor.setText(cEleitor.getText().substring(0,cEleitor.getText().length()-1));

        else if(e.getSource() == bBranco) // apaga o campo
            cEleitor.setText("");

        if(e.getSource() == bConfirma && cEleitor.getText().length() < 12){ // vefica se o código de eleitor é menor 12 digitos
            ERRO.setVisible(true); // exibe mensagem de erro em vermelho
            // adicionando timer para esconder a mensagem de erro depois de 5 segundos
            Timer t = new Timer(5000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ERRO.setVisible(false);
                }
            });
            t.setRepeats(false);
            t.start(); // começa a contar o tempo
        }
    }

    // iniciando o main async
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new principal();
            }
        });
    }
}