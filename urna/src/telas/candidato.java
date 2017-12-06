package telas;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import SQLquery.sql;

import static javax.swing.SwingConstants.CENTER;

public class candidato extends teclado{

    private JPanel painelPrincipal, DISPLAY, tela;
    private JLabel T1, ERRO, NAOENCONTRADO;
    private JLabel[] label_cdt;
    private String codCandidato = "";
    private int indiceCodigo = 0;
    private long cod_eleitor;
    private int indexVoto;
    private String[] cargos;
    private int[] qtd_dig;

    private sql sql = new sql();
    private ResultSet candidatosBD;
    private String[] candidatosListagem;

    candidato(String[] cargos, int[] qtd_dig, int indexVoto, long cod_eleitor){

        new teclado();

        try{
            int cont = 0;
            
            candidatosBD = sql.ExecutaSelect("SELECT id_candidato, cargo FROM candidato;");
            
            candidatosBD.last();
            int tamanho = candidatosBD.getRow();
            candidatosBD.beforeFirst();
            candidatosListagem = new String[tamanho];

            while (candidatosBD.next()){
                candidatosListagem[cont] = candidatosBD.getString("id_candidato");
                cont++;
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        this.cargos = cargos;
        this.indexVoto = indexVoto;
        this.qtd_dig = qtd_dig;
        this.cod_eleitor = cod_eleitor;

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

        // criando as labels dos números a serem digitados pelo eleitor
        label_cdt = new JLabel[this.qtd_dig[this.indexVoto]];
        int x = (650/2) - (50 * this.qtd_dig[this.indexVoto])/2; // centralizando as labels dos dígitos
        for(int cont = 0; cont < label_cdt.length; cont++){
            label_cdt[cont] = new JLabel("", CENTER);
            label_cdt[cont].setSize(40,40);
            label_cdt[cont].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
            label_cdt[cont].setLocation(x,175);
            label_cdt[cont].setFont(new Font("Arial", Font.BOLD, 25));
            tela.add(label_cdt[cont]);
            x += 50; // espaçamento para a próxima label
        }

        T1 =  new JLabel(cargos[indexVoto], CENTER);
        T1.setSize(420,50);
        T1.setLocation(115,120);
        T1.setFont(new Font ("Arial", Font.BOLD, 20));

        ERRO = new JLabel("<html><font color='red'>Preencha todos os números do candidato.</font></html>"
                , CENTER);
        ERRO.setSize(600, 50);
        ERRO.setLocation(25, 270);
        ERRO.setFont(new Font ("Arial", Font.BOLD, 18));
        ERRO.setVisible(false);

        NAOENCONTRADO = new JLabel("<html><font color='red'>Número do candidato não encontrado.</font></html>"
                , CENTER);
        NAOENCONTRADO.setSize(600, 50);
        NAOENCONTRADO.setLocation(25, 270);
        NAOENCONTRADO.setFont(new Font ("Arial", Font.BOLD, 18));
        NAOENCONTRADO.setVisible(false);

        tela.add(T1);
        tela.add(ERRO);
        tela.add(NAOENCONTRADO);

        DISPLAY.add(tela);

        painelPrincipal.add(DISPLAY);
        painelPrincipal.add(TECLADO);

        getContentPane().add(painelPrincipal);
        
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(codCandidato.length() < label_cdt.length){ // verificando se todos os dígitos já foram preenchidos
            if(e.getSource() == B0){
                label_cdt[indiceCodigo].setText("0");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++; // aumenta o índice para a próxima label
            }
            else if(e.getSource() == B1){
                label_cdt[indiceCodigo].setText("1");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B2){
                label_cdt[indiceCodigo].setText("2");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B3){
                label_cdt[indiceCodigo].setText("3");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B4){
                label_cdt[indiceCodigo].setText("4");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B5){
                label_cdt[indiceCodigo].setText("5");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B6){
                label_cdt[indiceCodigo].setText("6");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B7){
                label_cdt[indiceCodigo].setText("7");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B8){
                label_cdt[indiceCodigo].setText("8");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
            else if(e.getSource() == B9){
                label_cdt[indiceCodigo].setText("9");
                codCandidato += label_cdt[indiceCodigo].getText();
                indiceCodigo++;
            }
        }

        if(e.getSource() == bConfirma){ // botão confirmar
            if(codCandidato.length() == qtd_dig[indexVoto]){ // verificando se todos os dígitos já foram preenchidos
                // transforma o código do candidato na string "nulo" caso o regex acuse que o código é formado apenas de zeros
                if(codCandidato.matches("^[0]+$"))
                    codCandidato = "nulo";
                for(String cdt : candidatosListagem){
                    // cria um caso especial para o cargo de governador
                    if(cargos[indexVoto].equals("GOVERNADOR(A)") && !codCandidato.equals("nulo")){
                        /* como o sistema eleitoral brasileiro indica que número do governador tenha a mesma quantidade
                        de dígitos que o presidente, concatenamos "a" no fim do código do candidato já que governador
                        e presidente não podem possuir a mesma chave no banco de dados */
                        String tempCodCandidato = codCandidato + "a";
                        if(cdt.equals(tempCodCandidato)){
                            new confirma(codCandidato, cargos, qtd_dig, indexVoto, cod_eleitor, label_cdt);
                            sql.encerrarConexao();
                            dispose();
                            break;
                        }
                    }
                    else{
                        if(cdt.equals(codCandidato)){
                            new confirma(codCandidato, cargos, qtd_dig, indexVoto, cod_eleitor, label_cdt);
                            sql.encerrarConexao();
                            dispose();
                            break;
                        }
                    }
                }
                // erro aparece caso o código do usuário não seja igual à um dos candidatos elegíveis
                // como os comandos condicionais acima levam o usuário para outra tela, o erro só é mostrado caso ele continue na mesma tela
                NAOENCONTRADO.setVisible(true);
                // adicionando timer para esconder a mensagem de erro depois de 5 segundos
                Timer t = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NAOENCONTRADO.setVisible(false); // esconde a mensagem de erro
                    }
                });
                t.setRepeats(false);
                t.start(); // começa a contar o tempo
            }
            else{
                ERRO.setVisible(true); // exibe mensagem de erro em vermelho
                // adicionando timer para esconder a mensagem de erro depois de 5 segundos
                Timer t = new Timer(5000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ERRO.setVisible(false); // esconde a mensagem de erro
                    }
                });
                t.setRepeats(false);
                t.start(); // começa a contar o tempo
            }
        }

        else if(e.getSource() == bCorrige && codCandidato.length() > 0) { // apaga o último dígito
            indiceCodigo--; // decremento do índice
            label_cdt[indiceCodigo].setText("");
            codCandidato = codCandidato.substring(0,codCandidato.length()-1); // pop no último caractere do código do candidato
        }

        else if(e.getSource() == bBranco) { // Vota em branco
            codCandidato = "branco";
            new confirma(codCandidato, cargos, qtd_dig, indexVoto, cod_eleitor, label_cdt); // tela de confirmação com candidato em branco
            sql.encerrarConexao();
            dispose();
        }
    }
}