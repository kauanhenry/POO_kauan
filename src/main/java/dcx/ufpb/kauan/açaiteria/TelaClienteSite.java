package dcx.ufpb.kauan.açaiteria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class TelaClienteSite extends JFrame {

    private final Color corAçaiRoxo = new Color(75, 0, 130);
    private final Color corFundoBranco = new Color(248, 248, 250);
    private final Color corTextoEscuro = new Color(40, 40, 40);

    private PedidoService pedidoService;

    public TelaClienteSite() {
        this(null);
    }

    public TelaClienteSite(PedidoService pedidoService) {
        this.pedidoService = pedidoService;

        setTitle("Açai Mania - Cardápio Online");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(corAçaiRoxo);
        painelTopo.setBorder(new EmptyBorder(15, 25, 15, 25));

        JPanel painelLogoTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        painelLogoTitulo.setOpaque(false);

        int tamanhoLogo = 100;
        JLabel lblLogo = new JLabel();
        lblLogo.setPreferredSize(new Dimension(tamanhoLogo, tamanhoLogo));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setVerticalAlignment(SwingConstants.CENTER);

        String caminhoDaImagem = "./asserts/img/logo2.png";
        File arquivoImagem = new File(caminhoDaImagem);

        if (arquivoImagem.exists()) {
            ImageIcon iconeOriginal = new ImageIcon(arquivoImagem.getAbsolutePath());
            Image imagemOriginal = iconeOriginal.getImage();

            int larguraOrig = imagemOriginal.getWidth(null);
            int alturaOrig = imagemOriginal.getHeight(null);

            if (larguraOrig > 0 && alturaOrig > 0) {
                int tamanhoCorte = Math.min(larguraOrig, alturaOrig);
                int xInicio = (larguraOrig - tamanhoCorte) / 2;
                int yInicio = (alturaOrig - tamanhoCorte) / 2;
                xInicio -= 100;
                yInicio -= 50;

                xInicio = Math.max(0, Math.min(xInicio, larguraOrig - tamanhoCorte));
                yInicio = Math.max(0, Math.min(yInicio, alturaOrig - tamanhoCorte));

                java.awt.image.BufferedImage imgOriginalBuffer = new java.awt.image.BufferedImage(
                        larguraOrig, alturaOrig, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = imgOriginalBuffer.createGraphics();
                g2d.drawImage(imagemOriginal, 0, 0, null);
                g2d.dispose();

                java.awt.image.BufferedImage imgCortada = imgOriginalBuffer.getSubimage(xInicio, yInicio, tamanhoCorte, tamanhoCorte);
                Image imagemRedimensionada = imgCortada.getScaledInstance(tamanhoLogo, tamanhoLogo, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imagemRedimensionada));
            } else {
                lblLogo.setIcon(new ImageIcon(imagemOriginal.getScaledInstance(tamanhoLogo, tamanhoLogo, Image.SCALE_SMOOTH)));
            }
            lblLogo.setText("");
        } else {
            lblLogo.setOpaque(true);
            lblLogo.setBackground(Color.RED);
            lblLogo.setForeground(Color.WHITE);
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 10));
            lblLogo.setText("LOGO");
        }

        painelLogoTitulo.add(lblLogo);

        JPanel painelTextosVertical = new JPanel(new GridLayout(2, 1, 0, 2));
        painelTextosVertical.setOpaque(false);

        JLabel lblTituloTexto = new JLabel("Açai Mania - Cardápio");
        lblTituloTexto.setForeground(Color.WHITE);
        lblTituloTexto.setFont(new Font("Segoe UI", Font.BOLD, 24));
        painelTextosVertical.add(lblTituloTexto);

        JLabel lblSub = new JLabel("Confira algumas opções já prontas e peça já!");
        lblSub.setForeground(new Color(230, 230, 230));
        lblSub.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        painelTextosVertical.add(lblSub);

        painelLogoTitulo.add(painelTextosVertical);
        painelTopo.add(painelLogoTitulo, BorderLayout.WEST);

        add(painelTopo, BorderLayout.NORTH);

        JTabbedPane abasCardapio = new JTabbedPane();
        abasCardapio.setFont(new Font("Segoe UI", Font.BOLD, 14));
        abasCardapio.setBackground(Color.WHITE);

        abasCardapio.addTab("Copos & Tigelas", criarPainelCoposTigelas());
        abasCardapio.addTab("Barcas Especiais", criarPainelBarcas());
        abasCardapio.addTab("Adicionais & Frutas", criarPainelAdicionais());

        add(abasCardapio, BorderLayout.CENTER);

        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 15));
        painelRodape.setBackground(corFundoBranco);
        painelRodape.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

        JButton btnFazerPedido = new JButton("Voltar");
        btnFazerPedido.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnFazerPedido.setBackground(new Color(46, 139, 87)); // Verde destaque
        btnFazerPedido.setForeground(Color.BLACK);
        btnFazerPedido.setFocusPainted(false);
        btnFazerPedido.setBorder(new EmptyBorder(10, 20, 10, 20));

        btnFazerPedido.addActionListener(e -> {
            String nomeCliente = JOptionPane.showInputDialog(this, "Qual seu nome?", "Açai Mania - Identificação", JOptionPane.QUESTION_MESSAGE);

            if (nomeCliente != null && !nomeCliente.trim().isEmpty()) {

                Pedido novoPedido = new Pedido(nomeCliente.trim());

                Produto produtoSite = new Produto("C01", "Açaí do Site (Cardápio)", 15.00);
                novoPedido.adicionarProduto(produtoSite);

                if (this.pedidoService != null) {
                    this.pedidoService.adicionarPedido(novoPedido);
                }

                JOptionPane.showMessageDialog(this,
                        "Eba, " + nomeCliente.trim() + "!  Oque achou as opções? Gostou? Cuida, peça já o seu!",
                        "Açai Mania!",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

        painelRodape.add(btnFazerPedido);
        add(painelRodape, BorderLayout.SOUTH);
    }

    private JScrollPane criarPainelCoposTigelas() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(corFundoBranco);
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        painel.add(criarItemCardapio("Copo 300ml Tradicional", "R$ 10,00", "Inclui até 3 adicionais grátis (Leite Condensado, Paçoca e Granola)."));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemCardapio("Copo 500ml Campeão", "R$ 14,00", "O queridinho da galera! Inclui até 4 adicionais à sua escolha."));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemCardapio("Tigela 700ml Família", "R$ 19,00", "Acompanha banana, morango, kiwi, leite em pó e cobertura livre."));

        return new JScrollPane(painel);
    }

    private JScrollPane criarPainelBarcas() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(corFundoBranco);
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        painel.add(criarItemCardapio("Barca P (Para 1 pessoa) - 500g", "R$ 28,00", "Açaí puro e cremoso cercado por frutas frescas e 4 complementos especiais."));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemCardapio("Barca M (Para 2 pessoas) - 1kg", "R$ 48,00", "Acompanha KitKat, Bis, morango, banana, leite condensado e muito açaí."));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemCardapio("Barca Gigante G (Para a galera) - 1.5kg", "R$ 69,00", "A maior barca da região com bordas recheadas de creme de avelã e Ninho."));

        return new JScrollPane(painel);
    }

    private JScrollPane criarPainelAdicionais() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(corFundoBranco);
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        painel.add(criarItemCardapio("Adicionais Extras (Cada)", "R$ 3,00", "Leite em Pó, Leite Condensado, Paçoca, Granola, Chocobom, Coco Ralado."));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemCardapio("Frutas Extras", "R$ 4,00", "Porção extra de Morango Fresco, Banana Picada ou Kiwi."));
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(criarItemCardapio("Cremes Especiais", "R$ 5,00", "Adicional de Creme de Ninho, Nutella ou Ovomaltine."));

        return new JScrollPane(painel);
    }

    private JPanel criarItemCardapio(String nome, String preco, String descricao) {
        JPanel card = new JPanel(new BorderLayout(15, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(210, 190, 230), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNome.setForeground(corAçaiRoxo);

        JLabel lblPreco = new JLabel(preco);
        lblPreco.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPreco.setForeground(new Color(46, 139, 87));

        JLabel lblDesc = new JLabel(descricao);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDesc.setForeground(corTextoEscuro);

        JPanel painelNorte = new JPanel(new BorderLayout());
        painelNorte.setOpaque(false);
        painelNorte.add(lblNome, BorderLayout.WEST);
        painelNorte.add(lblPreco, BorderLayout.EAST);

        card.add(painelNorte, BorderLayout.NORTH);
        card.add(lblDesc, BorderLayout.CENTER);

        return card;
    }
}