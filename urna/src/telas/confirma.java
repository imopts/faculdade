package telas;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;

import SQLquery.sql;

import static javax.swing.SwingConstants.CENTER;

public class confirma extends teclado{

    private JPanel painelPrincipal, DISPLAY, tela;
    private JLabel T1, CARGO, NUMERO, NOME, PARTIDO, FOTO, VICE, FOTOVICE, NULO, BRANCO;
    private JLabel cdtNOME, cdtPARTIDO, cdtVICE;
    private JLabel RODAPE;
    private int[] qtd_dig;
    private long cod_eleitor;
    private int indexVoto;
    private String[] cargos;
    private String codCandidato;

    private sql sql = new sql();

    confirma(String codCandidato, String[] cargos, int[] qtd_dig, int indexVoto, long cod_eleitor, JLabel[] label_cdt){

        new teclado();

        // utilizando os valores da tela anterior para preenchimento das globais
        this.cargos = cargos;
        this.indexVoto = indexVoto;
        this.qtd_dig = qtd_dig;
        this.cod_eleitor = cod_eleitor;
        this.codCandidato = codCandidato;

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

        T1 =  new JLabel("SEU VOTO PARA");
        T1.setSize(200,50);
        T1.setLocation(15,0);
        T1.setFont(new Font ("Arial", Font.BOLD, 20));

        CARGO = new JLabel(this.cargos[this.indexVoto]);
        CARGO.setSize(400, 50);
        CARGO.setLocation(15, 50);
        CARGO.setFont(new Font ("Arial", Font.BOLD, 30));

        NUMERO = new JLabel("Numero: ");
        NUMERO.setSize(100, 50);
        NUMERO.setLocation(15, 145);
        NUMERO.setFont(new Font ("Arial", Font.BOLD, 20));

        NOME = new JLabel("Nome:");
        NOME.setSize(100, 50);
        NOME.setLocation(15, 195);
        NOME.setFont(new Font ("Arial", Font.BOLD, 20));

        PARTIDO = new JLabel("Partido:");
        PARTIDO.setSize(100, 50);
        PARTIDO.setLocation(15, 245);
        PARTIDO.setFont(new Font ("Arial", Font.BOLD, 20));

        FOTO = new JLabel();
        FOTO.setSize(120, 160);
        FOTO.setLocation(510, 30);
        FOTO.setBorder(new MatteBorder(3, 3, 3, 3, Color.BLACK));

        VICE = new JLabel("Vice-presidente:");
        VICE.setSize(150, 50);
        VICE.setLocation(15,295);
        VICE.setFont(new Font ("Arial", Font.BOLD, 15));

        FOTOVICE = new JLabel();
        FOTOVICE.setSize(96, 128);
        FOTOVICE.setLocation(534, 200);
        FOTOVICE.setBorder(new MatteBorder(3, 3, 3, 3, Color.BLACK));

        RODAPE = new JLabel("<html><pre>  Aperte a tecla:" +
                "<br>        <strong><font color='green'>VERDE</font></strong> para CONFIRMAR este voto" +
                "<br>        <strong><font color='orange'>LARANJA</font></strong> para REINICIAR este voto</pre></html>");
        RODAPE.setSize(650, 60);
        RODAPE.setLocation(0,340);
        RODAPE.setFont(new Font ("Arial", Font.BOLD, 12));
        RODAPE.setBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK));

        tela.add(T1);
        tela.add(CARGO);
        tela.add(RODAPE);

        DISPLAY.add(tela);

        painelPrincipal.add(DISPLAY);
        painelPrincipal.add(TECLADO);

        getContentPane().add(painelPrincipal);

        if(!this.codCandidato.equals("branco")){ // verifica se o voto não é branco
            if(!this.codCandidato.equals("nulo")){ // verifica se o voto não é nulo
                if(this.cargos[this.indexVoto].equals("GOVERNADOR(A)") && !this.codCandidato.equals("branco")
                        && !this.codCandidato.equals("nulo"))
                    this.codCandidato += "a";

                ResultSet candidato;
                try{
                    candidato = sql.ExecutaSelect("SELECT nome_candidato, nome_vice, partido, img_index " +
                            "FROM candidato WHERE id_candidato = '"+ this.codCandidato +"';");
                    candidato.next();

                    cdtNOME = new JLabel(candidato.getString("nome_candidato"));
                    cdtNOME.setSize(300, 50);
                    cdtNOME.setLocation(85, 195);
                    cdtNOME.setFont(new Font ("Arial", Font.BOLD, 20));
                    tela.add(cdtNOME);

                    cdtPARTIDO = new JLabel(candidato.getString("partido"));
                    cdtPARTIDO.setSize(200, 50);
                    cdtPARTIDO.setLocation(100, 245);
                    cdtPARTIDO.setFont(new Font ("Arial", Font.BOLD, 20));
                    tela.add(cdtPARTIDO);

                    Image cdtFOTO = new ImageIcon(this.getClass().getResource("../imagens/"
                            +candidato.getString("img_index")+".png")).getImage();
                    FOTO.setIcon(new ImageIcon(cdtFOTO));

                    if(this.cargos[this.indexVoto].equals("PRESIDENTE")){
                        cdtVICE = new JLabel(candidato.getString("nome_vice"));
                        cdtVICE.setSize(300, 50);
                        cdtVICE.setLocation(137, 295);
                        cdtVICE.setFont(new Font ("Arial", Font.BOLD, 15));
                        tela.add(cdtVICE);

                        Image cdtFOTOVICE = new ImageIcon(this.getClass().getResource("../imagens/"
                                +candidato.getString("img_index")+"-1.png")).getImage();
                        FOTOVICE.setIcon(new ImageIcon(cdtFOTOVICE));
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }

                tela.add(NOME);
                tela.add(PARTIDO);
                tela.add(FOTO);

                if(this.cargos[this.indexVoto].equals("PRESIDENTE")){
                    tela.add(VICE);
                    tela.add(FOTOVICE);
                }
            }
            else{ // voto nulo
                NULO = new JLabel("VOTO NULO", CENTER);
                NULO.setSize(400, 60);
                NULO.setLocation(125, 230);;
                NULO.setFont(new Font ("Arial", Font.BOLD, 30));
                tela.add(NULO);
            }
            tela.add(NUMERO);
            int x = 115;
            for(int cont = 0; cont < label_cdt.length; cont++) {
                tela.add(label_cdt[cont]);
                label_cdt[cont].setLocation(x,145);
                x += 50;
            }
        }
        else{ // votos em branco
            BRANCO = new JLabel("VOTO EM BRANCO", CENTER);
            BRANCO.setSize(400, 60);
            BRANCO.setLocation(125, 170);;
            BRANCO.setFont(new Font ("Arial", Font.BOLD, 30));
            tela.add(BRANCO);
        }

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bConfirma){ // botão confirmar voto
            // adiciona 0 no final do código do cargo se o voto é parao cargo de governador(a)
            sql.ExecutarInsercao("INSERT INTO votacao (id_candidato, id_eleitor) VALUES ('"+codCandidato+"'," +
                    " '"+cod_eleitor+"');");

            if(indexVoto + 1 >= cargos.length) // verifica se o eleitor está votando em seu último candidato
                new principal();
            else
                new candidato(cargos, qtd_dig, indexVoto + 1, cod_eleitor);

            sql.encerrarConexao();
            dispose();
        }

        else if(e.getSource() == bCorrige) { // cancela o voto e volta para a última tela
            new candidato(cargos, qtd_dig, indexVoto, cod_eleitor);
            sql.encerrarConexao();
            dispose();
        }
    }
}