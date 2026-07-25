package dcx.ufpb.kauan.açaiteria;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelaAcaiMania extends JFrame {
    private CardapioService cardapioService = new CardapioService();
    private PedidoService pedidoService = new PedidoService();
    private final String ARQUIVO_DADOS = "acaimania_dados.dat";

    private DefaultListModel<String> modeloLista = new DefaultListModel<>();
    private JList<String> listaVisualizacao = new JList<>(modeloLista);

    private Color corAçaiRoxo = new Color(74, 20, 140);
    private Color corAçaiClaro = new Color(123, 31, 162);
    private Color corFundo = new Color(245, 245, 250);


    public TelaAcaiMania() {
        setTitle("Açai Mania - Sistema de Gerenciamento");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        try {
            ArquivoService.recuperarDados(pedidoService, cardapioService, ARQUIVO_DADOS);
            atualizarListaComTodos();
        } catch (Exception e) {

        }

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setBackground(corAçaiRoxo);
        painelTopo.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel painelLogoTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        painelLogoTitulo.setOpaque(false);

        int tamanhoLogo = 95;

        JLabel lblLogo = new JLabel();
        lblLogo.setPreferredSize(new Dimension(tamanhoLogo, tamanhoLogo));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setVerticalAlignment(SwingConstants.CENTER);

        String caminhoDaImagem = "./asserts/img/logo.png";
        java.io.File arquivoImagem = new java.io.File(caminhoDaImagem);

        if (arquivoImagem.exists()) {
            ImageIcon iconeOriginal = new ImageIcon(arquivoImagem.getAbsolutePath());
            Image imagemOriginal = iconeOriginal.getImage();

            int larguraOrig = imagemOriginal.getWidth(null);
            int alturaOrig = imagemOriginal.getHeight(null);

            if (larguraOrig > 0 && alturaOrig > 0) {
                int tamanhoCorte = 1100;
                int xInicio = (larguraOrig - tamanhoCorte) / 2;
                int yInicio = (alturaOrig - tamanhoCorte) / 2;

                xInicio -= 1;
                yInicio -= 1;

                xInicio = Math.max(0, Math.min(xInicio, larguraOrig - tamanhoCorte));
                yInicio = Math.max(0, Math.min(yInicio, alturaOrig - tamanhoCorte));

                java.awt.image.BufferedImage imgOriginalBuffer = new java.awt.image.BufferedImage(
                        larguraOrig, alturaOrig, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                java.awt.Graphics2D g2d = imgOriginalBuffer.createGraphics();
                g2d.drawImage(imagemOriginal, 0, 0, null);
                g2d.dispose();

                java.awt.image.BufferedImage imgCortada = imgOriginalBuffer.getSubimage(xInicio, yInicio, tamanhoCorte, tamanhoCorte);

                Image imagemRedimensionada = imgCortada.getScaledInstance(tamanhoLogo, tamanhoLogo, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imagemRedimensionada));
            } else {
                Image imagemRedimensionada = imagemOriginal.getScaledInstance(tamanhoLogo, tamanhoLogo, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imagemRedimensionada));
            }

            lblLogo.setText("");
        } else {
            lblLogo.setOpaque(true);
            lblLogo.setBackground(Color.RED);
            lblLogo.setForeground(Color.WHITE);
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 10));
            lblLogo.setText("ERRO IMG");
        }

        painelLogoTitulo.add(lblLogo);

        JLabel lblTituloTexto = new JLabel("Açai Mania");
        lblTituloTexto.setForeground(Color.WHITE);
        lblTituloTexto.setFont(new Font("Segoe UI", Font.BOLD, 26));

        JPanel painelTextosVertical = new JPanel(new GridLayout(2, 1, 0, 2));
        painelTextosVertical.setOpaque(false);
        painelTextosVertical.add(lblTituloTexto);

        JLabel lblSub = new JLabel("Sistema Desenvolvido por KMNT");
        lblSub.setForeground(new Color(230, 230, 230));
        lblSub.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        painelTextosVertical.add(lblSub);

        painelLogoTitulo.add(painelTextosVertical);

        painelTopo.add(painelLogoTitulo, BorderLayout.WEST);

        add(painelTopo, BorderLayout.NORTH);

        // ================= CENTRO: LISTA DE EXIBIÇÃO =================
        listaVisualizacao.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        listaVisualizacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaVisualizacao);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setBackground(corFundo);
        add(scrollPane, BorderLayout.CENTER);

        // ================= PAINEL DE BOTÕES / AÇÕES =================
        JPanel painelBotoes = new JPanel(new GridLayout(2, 5, 8, 8));
        painelBotoes.setBorder(new EmptyBorder(15, 15, 15, 15));
        painelBotoes.setBackground(corFundo);

        JButton btnCadPedido = criarBotaoEstilizado("CADASTRAR PEDIDO");
        JButton btnAddCardapio = criarBotaoEstilizado("ADICIONAR CARDÁPIO");
        JButton btnRemCardapio = criarBotaoEstilizado("Rev. DO CARDÁPIO");
        JButton btnRemPedido = criarBotaoEstilizado("Rev. PEDIDO/FILA");
        JButton btnFinPedido = criarBotaoEstilizado("FINALIZAR PEDIDO");

        JButton btnPesqNome = criarBotaoEstilizado("Pesq. POR NOME");
        JButton btnPesqData = criarBotaoEstilizado("Pesq. POR DATA");
        JButton btnPesqStatus = criarBotaoEstilizado("Pesq. POR STATUS");
        JButton btnSalvar = criarBotaoEstilizado("SALVAR DADOS");
        JButton btnListarTudo = criarBotaoEstilizado("VER TODOS");

        painelBotoes.add(btnCadPedido);
        painelBotoes.add(btnAddCardapio);
        painelBotoes.add(btnRemCardapio);
        painelBotoes.add(btnRemPedido);
        painelBotoes.add(btnFinPedido);

        painelBotoes.add(btnPesqNome);
        painelBotoes.add(btnPesqData);
        painelBotoes.add(btnPesqStatus);
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnListarTudo);

        add(painelBotoes, BorderLayout.SOUTH);

        JButton btnAbrirSiteCliente = new JButton("CARDÁPIO");
        btnAbrirSiteCliente.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAbrirSiteCliente.setBackground(new Color(138, 43, 226));
        btnAbrirSiteCliente.setForeground(Color.BLACK);
        btnAbrirSiteCliente.setFocusPainted(false);

        btnAbrirSiteCliente.addActionListener(e -> {
            new TelaClienteSite(pedidoService).setVisible(true);
        });

        painelBotoes.add(btnAbrirSiteCliente);

        btnCadPedido.addActionListener(e -> {
            if (cardapioService.getCardapio().isEmpty()) {
                JOptionPane.showMessageDialog(this, "O cardápio está vazio! Cadastre itens primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String cliente = JOptionPane.showInputDialog(this, "Nome do Cliente:");
            if (cliente == null || cliente.trim().isEmpty()) return;

            List<Produto> itensPedido = new ArrayList<>();
            boolean adicionando = true;

            while (adicionando) {
                StringBuilder menuStr = new StringBuilder("Escolha o código do produto:\n\n");
                for (Produto p : cardapioService.getCardapio().values()) {
                    menuStr.append(p.toString()).append("\n");
                }

                String cod = JOptionPane.showInputDialog(this, menuStr.toString() + "\nDigite o código (ou deixe vazio para encerrar itens):");
                if (cod == null || cod.trim().isEmpty()) {
                    break;
                }

                Produto prod = cardapioService.buscarItem(cod.trim());
                if (prod != null) {
                    itensPedido.add(prod);
                    int continuar = JOptionPane.showConfirmDialog(this, "Adicionado: " + prod.getNome() + "\nDeseja adicionar outro item?", "Continuar", JOptionPane.YES_NO_OPTION);
                    if (continuar == JOptionPane.NO_OPTION) {
                        adicionando = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (!itensPedido.isEmpty()) {
                try {
                    Pedido novo = pedidoService.cadastrarPedido(cliente, itensPedido);
                    JOptionPane.showMessageDialog(this, "Pedido #" + novo.getIdPedido() + " cadastrado com sucesso!");
                    atualizarListaComPedidos(pedidoService.getPedidos().values());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhum item adicionado. Pedido cancelado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });


        btnAddCardapio.addActionListener(e -> {
            JTextField campoCodigo = new JTextField(10);
            JTextField campoNome = new JTextField(15);
            JTextField campoPreco = new JTextField(10);

            JPanel painel = new JPanel(new GridLayout(3, 2, 5, 5));
            painel.add(new JLabel("Código do Produto:"));
            painel.add(campoCodigo);
            painel.add(new JLabel("Nome do Açaí/Adicional:"));
            painel.add(campoNome);
            painel.add(new JLabel("Preço (R$):"));
            painel.add(campoPreco);

            int result = JOptionPane.showConfirmDialog(this, painel, "Adicionar Item ao Cardápio", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String codigo = campoCodigo.getText().trim();
                    String nome = campoNome.getText().trim();
                    double preco = Double.parseDouble(campoPreco.getText().trim().replace(",", "."));

                    cardapioService.adicionarItem(codigo, nome, preco);
                    JOptionPane.showMessageDialog(this, "Item adicionado ao cardápio com sucesso!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de preço inválido. Use números (ex: 15.50)", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnRemCardapio.addActionListener(e -> {
            if (cardapioService.getCardapio().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cardápio vazio.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String cod = JOptionPane.showInputDialog(this, "Digite o código do produto que deseja remover do cardápio:");
            if (cod != null && !cod.trim().isEmpty()) {
                boolean removido = cardapioService.removerItem(cod.trim());
                if (removido) {
                    JOptionPane.showMessageDialog(this, "Item removido do cardápio com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRemPedido.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Digite o ID do pedido que deseja remover da fila:");
            if (id != null && !id.trim().isEmpty()) {
                boolean removido = pedidoService.removerPedido(id.trim());
                if (removido) {
                    JOptionPane.showMessageDialog(this, "Pedido removido da fila com sucesso!");
                    atualizarListaComPedidos(pedidoService.getPedidos().values());
                } else {
                    JOptionPane.showMessageDialog(this, "Pedido não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnFinPedido.addActionListener(e -> {
            String id = JOptionPane.showInputDialog(this, "Digite o ID do pedido para Finalizar/Cobrar:");
            if (id != null && !id.trim().isEmpty()) {
                Pedido p = pedidoService.getPedidos().get(id.trim());
                if (p != null) {
                    if ("FINALIZADO".equalsIgnoreCase(p.getStatus())) {
                        JOptionPane.showMessageDialog(this, "Este pedido já está finalizado!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int confirmar = JOptionPane.showConfirmDialog(this,
                                "Cliente: " + p.getNomeCliente() + "\nValor Total: R$ " + String.format("%.2f", p.getValorTotal()) +
                                        "\n\nConfirmar pagamento e finalizar?", "Finalizar Pedido", JOptionPane.YES_NO_OPTION);
                        if (confirmar == JOptionPane.YES_OPTION) {
                            pedidoService.finalizarPedido(id.trim());
                            JOptionPane.showMessageDialog(this, "Pedido finalizado com sucesso!");
                            atualizarListaComPedidos(pedidoService.getPedidos().values());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Pedido não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnPesqNome.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog(this, "Digite o nome (ou parte do nome) do cliente:");
            if (nome != null && !nome.trim().isEmpty()) {
                List<Pedido> resultados = pedidoService.pesquisarPorNome(nome.trim());
                atualizarListaComPedidos(resultados);
                JOptionPane.showMessageDialog(this, "Encontrados " + resultados.size() + " pedido(s).");
            }
        });


        btnPesqData.addActionListener(e -> {
            String dataStr = JOptionPane.showInputDialog(this, "Digite a data no formato DD/MM/AAAA:");
            if (dataStr != null && !dataStr.trim().isEmpty()) {
                try {
                    LocalDate data = LocalDate.parse(dataStr.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    List<Pedido> resultados = pedidoService.pesquisarPorData(data);
                    atualizarListaComPedidos(resultados);
                    JOptionPane.showMessageDialog(this, "Encontrados " + resultados.size() + " pedido(s).");
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de data inválido. Use DD/MM/AAAA", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        btnPesqStatus.addActionListener(e -> {
            String status = (String) JOptionPane.showInputDialog(this, "Selecione o Status:", "Pesquisar por Status",
                    JOptionPane.QUESTION_MESSAGE, null, new Object[]{"PENDENTE", "FINALIZADO"}, "PENDENTE");
            if (status != null) {
                List<Pedido> resultados = pedidoService.pesquisarPorStatus(status);
                atualizarListaComPedidos(resultados);
                JOptionPane.showMessageDialog(this, "Encontrados " + resultados.size() + " pedido(s).");
            }
        });


        btnSalvar.addActionListener(e -> {
            try {
                ArquivoService.salvarDados(pedidoService, cardapioService, ARQUIVO_DADOS);
                JOptionPane.showMessageDialog(this, "Dados salvos com sucesso no arquivo!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnListarTudo.addActionListener(e -> atualizarListaComTodos());
    }

    private JButton criarBotaoEstilizado(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(corAçaiClaro);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void atualizarListaComPedidos(Iterable<Pedido> pedidos) {
        modeloLista.clear();
        for (Pedido p : pedidos) {
            modeloLista.addElement(p.toString());
        }
        if (modeloLista.isEmpty()) {
            modeloLista.addElement("Nenhum pedido encontrado.");
        }
    }

    private void atualizarListaComTodos() {
        atualizarListaComPedidos(pedidoService.getPedidos().values());
    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new TelaAcaiMania().setVisible(true);
        });
    }
}
