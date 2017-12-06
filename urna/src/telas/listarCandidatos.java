package telas;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import SQLquery.sql;

import static javax.swing.SwingConstants.CENTER;

public class listarCandidatos extends JFrame implements ActionListener{

    private sql sql = new sql();

    private JPanel painelPrincipal, DISPLAY, tela;
    private JLabel T1, ERRO;
    private JMenuBar BARRA = new JMenuBar();
    private JMenu MENU = new JMenu("Votos");
    private JMenuItem VOTAR = new JMenuItem("VOTAR");
    private JMenuItem APURAR = new JMenuItem("Apurar votos");
    private JScrollPane sp;

    private JPanel[] cdtCARDS;
    private JLabel[] cdtNOMES;
    private JLabel[] cdtNUMEROS;
    private JLabel[] cdtFOTO;
    private JLabel[] cdtPARTIDO;

    private String[] cargos;
    private int[] qtd_dig;

    private int quantidade = 0;

    private Color retornaCor(String partido){
        Color corCARD; // criando a cor do card
        // muda a cor do card de acordo com o partido do candidato
        try{
            if(partido.equals("TP"))
                corCARD = new Color(245, 100, 100); // vermelho
            else if(partido.equals("DF"))
                corCARD = new Color(105, 195, 250); // azul
            else if(partido.equals("TT"))
                corCARD = new Color(110, 250, 130); // verde
            else
                corCARD = new Color(210, 210, 210); // cinza
        }
        catch(Exception e){
            e.printStackTrace();
            corCARD = new Color(210, 210, 210); // cinza
        }
        return corCARD;
    }

    listarCandidatos(String[] cargos, int[] qtd_dig){

        this.cargos = cargos;
        this.qtd_dig = qtd_dig;

        setTitle("Lista de candidatos");
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
        DISPLAY.setSize(1150,600);
        DISPLAY.setLocation(70,20);
        DISPLAY.setLayout(null);
        DISPLAY.setBackground(new Color(210, 210, 210));
        DISPLAY.setBorder(new MatteBorder(2, 2, 2, 2, Color.BLACK));
        DISPLAY.setVisible(true);

        ERRO = new JLabel("<html><font color='red'>Erro ao exibir os candidatos.</font></html>"
                , CENTER);
        ERRO.setSize(600, 50);
        ERRO.setLocation(275, 275);
        ERRO.setFont(new Font ("Arial", Font.BOLD, 18));
        ERRO.setVisible(false);

        T1 =  new JLabel("Lista de candidatos:", CENTER);
        T1.setSize(420,50);
        T1.setLocation(315,0);
        T1.setFont(new Font ("Arial", Font.BOLD, 25));

        try{
            // executando a query que resgata as informações de todos os candidatos que não sejam "nulo" ou "banco"
            ResultSet candidatosBD = sql.ExecutaSelect("SELECT * FROM candidato WHERE nome_candidato NOT IN " +
                    "('branco','nulo') ORDER BY partido ASC");

            candidatosBD.last(); // pulando para a última linha
            quantidade = candidatosBD.getRow(); // quantidade recebe o índice referente à última linha
            candidatosBD.beforeFirst(); // volta para a posição anterior à primeira linha

            tela = new JPanel();
            // a tela onde serão exibidas as informações dos candidatos muda de tamanho de acordo com a quantidade de candidatos
            tela.setPreferredSize(new Dimension(1050, 50+(quantidade*255)/2));
            tela.setLayout(null);
            tela.setBackground(Color.WHITE);

            tela.add(T1);

            // vetores de elementos da gui que guardarão as informações dos candidatos
            cdtCARDS = new JPanel[quantidade];
            cdtNOMES = new JLabel[quantidade];
            cdtFOTO = new JLabel[quantidade];
            cdtPARTIDO = new JLabel[quantidade];
            cdtNUMEROS = new JLabel[quantidade];

            // contador/índice do candidato
            int cont = 0;

            // altura do primeiro card/container com as informações dos candidatos
            int y = 50;

            while(candidatosBD.next()){

                // quantidade de dígitos da numeração do candidato começa com 0
                int digitos = 0;

                // resgatando as informações dos candidatos guardadas no resultset
                String nome = candidatosBD.getString("nome_candidato");
                String partido = candidatosBD.getString("partido");
                String cargo = candidatosBD.getString("cargo");
                String numeracao = candidatosBD.getString("id_candidato");

                JLabel nomecdt, partidocdt, cargocdt;

                // procurando a quantidade de dígitos da númeração do candidato
                // a busca é feita comparando o cargo do resultset com o vetor contendo os diferentes tipos de cargos
                // uma vez que o cargo é encontrado, o contador é utilizado como índice para resgatar a quantidade de dígitos
                int cargocont = 0;
                for(String cg : cargos){
                    if(cargo.equals(cg)){
                        digitos = qtd_dig[cargocont];
                        break; // saindo do loop após encontrar a quantidade de dígitos correta
                    }
                    else
                        cargocont++;
                }
                JLabel[] numerocdt = new JLabel[digitos];

                // card/container que vai guardar as informações dos candidatos
                cdtCARDS[cont] = new JPanel();
                cdtCARDS[cont].setSize(510, 250);
                cdtCARDS[cont].setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
                cdtCARDS[cont].setLayout(null);

                cdtCARDS[cont].setBackground(retornaCor(partido));

                // criando nova imagem de acordo com o índice encontrado no resultset
                Image imgFOTO = new ImageIcon(this.getClass().getResource("../imagens/"
                        +candidatosBD.getString("img_index")+".png")).getImage();

                // foto do candidato
                cdtFOTO[cont] = new JLabel();
                cdtFOTO[cont].setSize(120,160);
                cdtFOTO[cont].setLocation(10,12);
                cdtFOTO[cont].setBorder(new MatteBorder(3,3,3,3, Color.BLACK));
                cdtFOTO[cont].setIcon(new ImageIcon(imgFOTO));

                cdtNOMES[cont] = new JLabel("Nome:");
                cdtNOMES[cont].setSize(100, 50);
                cdtNOMES[cont].setLocation(140, 60);
                cdtNOMES[cont].setFont(new Font ("Arial", Font.BOLD, 20));

                cdtPARTIDO[cont] = new JLabel("Partido:");
                cdtPARTIDO[cont].setSize(100, 50);
                cdtPARTIDO[cont].setLocation(140, 105);
                cdtPARTIDO[cont].setFont(new Font ("Arial", Font.BOLD, 20));

                cdtNUMEROS[cont] = new JLabel("Número:");
                cdtNUMEROS[cont].setSize(100, 50);
                cdtNUMEROS[cont].setLocation(10, 190);
                cdtNUMEROS[cont].setFont(new Font ("Arial", Font.BOLD, 20));

                int x = 0; // índice e multiplicador da coordenada horizontal inicial para as labels da numeração do candidato
                // para cada dígito, uma nova label com um dos números do candidato será criada, sequencialmente
                for(JLabel label:numerocdt){
                    label = new JLabel(numeracao.substring(x,x+1), CENTER);
                    label.setSize(40,40);
                    label.setLocation(100 + x*50, 190);
                    label.setFont(new Font("Arial", Font.BOLD, 25));
                    label.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
                    label.setOpaque(true);
                    label.setBackground(Color.WHITE);
                    cdtCARDS[cont].add(label);

                    x++;
                }

                // cargo do candidato <TOPO>
                cargocdt = new JLabel(cargo, CENTER);
                cargocdt.setSize(380, 60);
                cargocdt.setLocation(107, 5);
                cargocdt.setFont(new Font ("Arial", Font.BOLD, 25));

                // nome do candidato <DIREITA DE NOME>
                nomecdt = new JLabel(nome);
                nomecdt.setSize(250, 50);
                nomecdt.setLocation(204, 60);
                nomecdt.setFont(new Font ("Arial", Font.ITALIC, 20));

                // partido do candidato <DIREITA DE PARTIDO>
                partidocdt = new JLabel(partido);
                partidocdt.setSize(250,50);
                partidocdt.setLocation(224, 105);
                partidocdt.setFont(new Font ("Arial", Font.ITALIC, 20));

                // condicional verifica se o índice do candidato é par
                // se par, card fica à esquerda do grid
                // se impar, card fica à direita do grid e y recebe um incremento com a altura do card
                if(cont%2 == 0)
                    cdtCARDS[cont].setLocation(5, y);
                else {
                    cdtCARDS[cont].setLocation(517, y);
                    y+=255; // pulando de linha
                }
                cdtCARDS[cont].add(cdtNOMES[cont]);
                cdtCARDS[cont].add(cdtFOTO[cont]);
                cdtCARDS[cont].add(cdtPARTIDO[cont]);
                cdtCARDS[cont].add(cdtNUMEROS[cont]);
                cdtCARDS[cont].add(cargocdt);
                cdtCARDS[cont].add(nomecdt);
                cdtCARDS[cont].add(partidocdt);
                tela.add(cdtCARDS[cont]);

                cont++;
            }

            sp = new JScrollPane();
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setSize(1050, 550);
            sp.setLocation(50,25);
            sp.setBackground(Color.white);
            sp.getVerticalScrollBar().setUnitIncrement(20);
            sp.getViewport().add(tela);

            DISPLAY.add(sp);

        } catch (Exception e){
            // exibindo mensagem de erro caso algum erro aconteça durante a execução do código
            e.printStackTrace();
            ERRO.setVisible(true);
            DISPLAY.add(ERRO);
        }

        // apuração
        APURAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent menu) {
                new apuracao(cargos, qtd_dig);
                sql.encerrarConexao();
                dispose();
            }
        });

        // volta pra tela de votação
        VOTAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent menu) {
                new principal();
                sql.encerrarConexao();
                dispose();
            }
        });

        MENU.add(VOTAR);
        MENU.add(APURAR);
        BARRA.add(MENU);
        setJMenuBar(BARRA);

        painelPrincipal.add(DISPLAY);

        getContentPane().add(painelPrincipal);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}