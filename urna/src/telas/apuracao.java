package telas;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import SQLquery.sql;

import static javax.swing.SwingConstants.CENTER;

public class apuracao extends JFrame implements ActionListener{

    private sql sql = new sql();

    private JPanel painelPrincipal, DISPLAY, tela;
    private JLabel T1, ERRO;

    private JMenuBar BARRA = new JMenuBar();
    private JMenu MENU = new JMenu("Votos");
    private JMenuItem VOTAR = new JMenuItem("VOTAR");
    private JMenuItem CANDIDATOS = new JMenuItem("Listar candidatos");
    private JScrollPane sp;

    private String[] cargos;
    private int[] qtd_dig;

    private Color retornaCor(String partido){
        Color corCARD; // criando a cor do card
        // muda a cor do card de acordo com o partido do candidato
        try{
            if(partido.equals("TP"))
                corCARD = new Color(255, 107, 107); // vermelho
            else if(partido.equals("DF"))
                corCARD = new Color(106, 198, 255); // azul
            else if(partido.equals("TT"))
                corCARD = new Color(105, 255, 133); // verde
            else
                corCARD = new Color(210, 210, 210); // cinza
        }
        catch(Exception e){
            e.printStackTrace();
            corCARD = new Color(210, 210, 210); // cinza
        }
        return corCARD;
    }

    apuracao(String[] cargos, int[] qtd_dig){

        this.cargos = cargos;
        this.qtd_dig = qtd_dig;

        setTitle("Votos apurados");
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

        T1 =  new JLabel("Resultado das eleições!", CENTER);
        T1.setSize(600,50);
        T1.setLocation(225,5);
        T1.setFont(new Font ("Arial", Font.BOLD, 30));

        ERRO = new JLabel("<html><font color='red'>Erro ao realizar apuração.</font></html>"
                , CENTER);
        ERRO.setSize(600, 50);
        ERRO.setLocation(275, 275);
        ERRO.setFont(new Font ("Arial", Font.BOLD, 18));
        ERRO.setVisible(false);

        try{
            // criando a view que será usada para consultar o resultado das eleições
            sql.ExecutarAlteracao("CREATE OR REPLACE VIEW tabela_de_votos AS SELECT c.id_candidato, " +
                    "c.nome_candidato, c.partido, c.cargo, c.img_index, COUNT(v.id_candidato) 'votos' FROM candidato c," +
                    " votacao v WHERE c.id_candidato = v.id_candidato GROUP BY c.id_candidato;");

            int tamanhoTELA = 175;

            // a tela onde serão exibidas as informações dos candidatos muda de tamanho de acordo com a quantidade de candidatos
            tela = new JPanel();
            tela.setLayout(null);
            tela.setBackground(Color.WHITE);

            tela.add(T1);

            // posição no eixo y inicial dos paineis que serão usados para mostrar os candidatos eleitos para o cargo
            int y = 50;

            // para cada cargo encontrado no vetor cargos da tela principal, executaremos as instruções abaixo
            for(String cargo:cargos){

                // query que retorna as informações do(s) candidato(s) com o maior número de votos para o cargo
                ResultSet cdtEleito = sql.ExecutaSelect("SELECT id_candidato, nome_candidato, cargo, partido, " +
                        "img_index, votos FROM tabela_de_votos where cargo = '"+ cargo +"' AND votos >=(" +
                        "SELECT max(votos) FROM tabela_de_votos WHERE cargo = '"+ cargo +"' AND cargo IS NOT NULL);");

                // verificando a quantidade de candidatos retornada pelo resultset
                cdtEleito.last();
                int qtdeleitos = cdtEleito.getRow();
                cdtEleito.first();

                // tamanho dos cards que guardarão as informações do(s) candidato(s) aumenta de acordo com a quantidade de candidatos
                // como teremos 2 candidatos por linha, incrementamos a altura do card de acordo com o resto e o quociente da divisão por 2
                int tamanhoCard = 50 + (qtdeleitos%2)*255 + (qtdeleitos/2)*255;

                JPanel painelEleitos = new JPanel();
                painelEleitos.setSize(1020, tamanhoCard);
                painelEleitos.setLocation(5, y+10);
                painelEleitos.setLayout(null);
                painelEleitos.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));

                tela.add(painelEleitos);

                // label que identifica o cargo
                JLabel cdtCARGO = new JLabel(cargo + " ELEITO(A)!", CENTER);
                cdtCARGO.setSize(1000, 50);
                cdtCARGO.setLocation(0, 5);
                cdtCARGO.setFont(new Font ("Arial", Font.BOLD, 25));
                painelEleitos.add(cdtCARGO);

                // verifica se foi empate (mais de 1 candidato encontado no resultset)
                if( qtdeleitos > 1) {
                    cdtCARGO.setText("<html>" + cargo + " -- <font color='red'>EMPATE!</font></html>");
                }
                // se foi encontrado apenas 1 candidato
                else if (qtdeleitos == 1){
                    String partido = cdtEleito.getString("partido");
                    painelEleitos.setBackground(retornaCor(partido));
                }
                // se nenhum candidato foi encontrado
                else if( qtdeleitos ==0)
                    cdtCARGO.setText("<html><font color='red'>NENHUM VOTO COMPUTADO PARA O CARGO DE "+cargo+"!</font></html>");

                // retornando à posição anterior à primeira linha
                cdtEleito.beforeFirst();

                // variáveis que serão utilizadas para posicionar os cards dos candidatos dentro dos cards dos cargos
                int tempY = 47, tempX = 8;

                while(cdtEleito.next()){

                    // query que retorna a soma dos votos para o cargo atual
                    ResultSet ResQtdVotos = sql.ExecutaSelect("SELECT SUM(votos) 'votos' FROM tabela_de_votos GROUP BY" +
                            " cargo HAVING cargo = '"+cargo+"';");
                    ResQtdVotos.next();

                    // primeiro copiamos a quantidade de votos para o candidato do resultset
                    int votosCandidato = cdtEleito.getInt("votos");
                    // em seguida a soma de votos válidos para o cargo
                    int votosCargo = ResQtdVotos.getInt("votos");
                    // então calculamos a pocentagem de representatividade do candidato com relação aos votos válidos para o cargo
                    float qtdVotos = ((float) votosCandidato/(float) votosCargo) * 100;
                    // por último, formatamos uma string para conter a porcentagem de representatividade do candidato com 2 casas decimais + "%"
                    String votosTxt = String.format("%.02f", qtdVotos);

                    JPanel cardCDT = new JPanel();
                    cardCDT.setSize(500, 250);
                    cardCDT.setLocation(tempX, tempY);
                    cardCDT.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
                    cardCDT.setLayout(null);

                    String numeracao = cdtEleito.getString("id_candidato");
                    // se o cargo atual for governador(a), retiramos o último caractere - "a" - da numeração do candidato
                    if(cargo.equals("GOVERNADOR(A)"))
                        numeracao = numeracao.substring(0,numeracao.length()-1);

                    // texto "número" que aparece no card do candidato
                    JLabel numero = new JLabel("Número: ");
                    numero.setSize(150,50);
                    numero.setLocation(10, 200);
                    numero.setFont(new Font ("Arial", Font.BOLD, 20));
                    cardCDT.add(numero);

                    /* para cada dígito na numeração do candidato, criamos uma label com o respectivo dígito e a adicionamos
                     na parte inferior do card do candidato, tomando cuidado para calcular o espaçamento entre elas
                     de acordo com a quantidade de dígitos e o índice "i" do loop */
                    for(int i = 0; i < numeracao.length(); i++){
                        JLabel digito = new JLabel(numeracao.substring(i,i+1), CENTER);
                        digito.setSize(40,40);
                        digito.setLocation(100 + 50*i, 200);
                        digito.setFont(new Font ("Arial", Font.BOLD, 20));
                        digito.setBackground(Color.WHITE);
                        digito.setOpaque(true);
                        digito.setBorder(new MatteBorder(1,1,1,1,Color.BLACK));
                        cardCDT.add(digito);
                    }

                    // label com o texto "nome:" + o nome do candidato eleito
                    JLabel nome = new JLabel("<html><b>Nome:</b> <i>"+
                            cdtEleito.getString("nome_candidato")+"</i></html>");
                    nome.setSize(350, 50);
                    nome.setLocation(135, 10);
                    nome.setFont(new Font ("Arial", Font.PLAIN, 20));
                    cardCDT.add(nome);

                    // label com o texto "partido: " + o nome do partido do candidato eleito
                    JLabel partido = new JLabel("<html><b>Partido: </b><i>"+
                            cdtEleito.getString("partido")+"</i></html>");
                    partido.setSize(350,50);
                    partido.setLocation(135, 50);
                    partido.setFont(new Font ("Arial", Font.PLAIN, 20));
                    cardCDT.add(partido);

                    // label com o texto "votos: " + a quantidade de votos do candidato
                    JLabel votos = new JLabel("<html><b>Votos: </b><i>"+votosCandidato+"</i></html>");
                    votos.setSize(350, 50);
                    votos.setLocation(135, 90);
                    votos.setFont(new Font ("Arial", Font.PLAIN, 20));
                    cardCDT.add(votos);

                    // label com o texto "percentual de votos: " + o percentual de representatividade do candidato eleito
                    JLabel percentual = new JLabel("Percentual de votos: "+votosTxt+"%");
                    percentual.setSize(350, 50);
                    percentual.setLocation(135, 130);
                    percentual.setFont(new Font ("Arial", Font.BOLD, 20));
                    cardCDT.add(percentual);

                    // foto do candidato
                    Image imgFOTO = new ImageIcon(this.getClass().getResource("../imagens/"
                            +cdtEleito.getString("img_index")+".png")).getImage();

                    // label que irá mostrar a foto do candidato
                    JLabel imagem = new JLabel();
                    imagem.setSize(120,160);
                    imagem.setLocation(10,10);
                    imagem.setBorder(new MatteBorder(3,3,3,3, Color.BLACK));
                    imagem.setIcon(new ImageIcon(imgFOTO));
                    cardCDT.add(imagem);

                    /* se a linha atual do resultset for par (2º,4º,6º...nparº candidato), incrementamos a altura dos cards
                     e voltamos a coordenada horizontal do próximo card para 8 (esquerda do grid)*/
                    if(cdtEleito.getRow()%2 == 0){
                        tempX = 8;
                        tempY+=255;
                    }
                    /* se a linha atual do resultset for impar (1º,3º,5º...nimparº candidato), coloca a coordenada horizontal
                      do proximo card para 510 (direta do grid) */
                    else {
                        tempX = 510;
                    }
                    // finalmente adicionamos o card do candidato no painel de eleitos para o cargo
                    painelEleitos.add(cardCDT);
                }
                // incrementamos a variavel que posiciona o card do proximo cargo com o tamanho do card do candidato
                y+= tamanhoCard + 20;
                // incrementamos a variavel que calcula o tamanho visível da tela com o tamanho do card do candidato
                tamanhoTELA += tamanhoCard + 20;
            }

            int qtdBRANCOS = 0;
            // query que busca a quantidade de votos brancos
            ResultSet RsBRANCO = sql.ExecutaSelect("SELECT SUM(votos) 'votos' FROM tabela_de_votos GROUP BY " +
                    "id_candidato HAVING id_candidato = 'branco';");
            // verifica se existe uma próxima linha no result set
            if(RsBRANCO.next())
                qtdBRANCOS = RsBRANCO.getInt("votos");

            int qtdNULOS = 0;
            // query que busca a quantidade de votos nulos
            ResultSet RsNULO = sql.ExecutaSelect("SELECT SUM(votos) 'votos' FROM tabela_de_votos GROUP BY " +
                    "id_candidato HAVING id_candidato = 'nulo';");
            // verifica se existe uma próxima linha no result set
            if(RsNULO.next())
                qtdNULOS = RsNULO.getInt("votos");

            // painel que guardará os cards dos votos brancos e nulos
            JPanel brancosNulos = new JPanel();
            brancosNulos.setSize(1020, 105);
            brancosNulos.setLocation(5, y+10);
            brancosNulos.setBackground(Color.LIGHT_GRAY);
            brancosNulos.setLayout(null);
            brancosNulos.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));

            // label com o texto "brancos e nulos"
            JLabel BEN = new JLabel("Brancos e nulos", CENTER);
            BEN.setSize(500, 50);
            BEN.setLocation(260, 5);
            BEN.setFont(new Font ("Arial", Font.BOLD, 25));

            // card que mostrará a quantidade de votos brancos
            JPanel BRANCOS = new JPanel();
            BRANCOS.setSize(500, 50);
            BRANCOS.setLocation(5,47);
            BRANCOS.setLayout(null);
            BRANCOS.setBackground(Color.WHITE);
            BRANCOS.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));

            // label com o texto "brancos: " + quantidade de votos brancos na eleição
            JLabel tBRANCOS = new JLabel("BRANCOS: " + qtdBRANCOS);
            tBRANCOS.setSize(500, 50);
            tBRANCOS.setLocation(20, 0);
            tBRANCOS.setFont(new Font ("Arial", Font.BOLD, 22));
            BRANCOS.add(tBRANCOS);

            // card que mostrará a quantidade de votos nulos
            JPanel NULOS = new JPanel();
            NULOS.setSize(500, 50);
            NULOS.setLocation(510,47);
            NULOS.setLayout(null);
            NULOS.setBackground(Color.WHITE);
            NULOS.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));

            // label com o texto "nulos: " + quantidade de votos nulos na eleição
            JLabel tNULOS = new JLabel("NULOS: " + qtdNULOS);
            tNULOS.setSize(500, 50);
            tNULOS.setLocation(20, 0);
            tNULOS.setFont(new Font ("Arial", Font.BOLD, 22));
            NULOS.add(tNULOS);

            brancosNulos.add(BRANCOS);
            brancosNulos.add(NULOS);
            brancosNulos.add(BEN);

            tela.add(brancosNulos);

            // alterando a porção visível da tela para o tamanho calculado de acordo com a quantidade de candidatos eleitos
            tela.setPreferredSize(new Dimension(1050, tamanhoTELA));

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
            e.printStackTrace();
            // caso algum erro ocorra durante a apuração, a label com a mensagem de erro será mostrada no lugar da apuração
            ERRO.setVisible(true);
            DISPLAY.add(ERRO);
        }

        // descomentar o trecho de código abaixo para deixar a apuração mais verossímil (apuração só ocorre no final das eleições)!

//        // query para truncar as tabelas de eleitores e votação
//        sql.ExecutarAlteracao("SET FOREIGN_KEY_CHECKS = 0;");
//        sql.ExecutarAlteracao("TRUNCATE TABLE eleitor;");
//        sql.ExecutarAlteracao("TRUNCATE TABLE votacao;");
//        sql.ExecutarAlteracao("SET FOREIGN_KEY_CHECKS = 1;");

        VOTAR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent menu) {
                new principal();
                sql.encerrarConexao();
                dispose();
            }
        });
        CANDIDATOS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent menu) {
                new listarCandidatos(cargos, qtd_dig);
                sql.encerrarConexao();
                dispose();
            }
        });

        MENU.add(VOTAR);
        MENU.add(CANDIDATOS);
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