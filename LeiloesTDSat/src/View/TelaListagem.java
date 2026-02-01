package View;

import DAO.ProdutosDAO;
import Model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TelaListagem extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaListagem() {
        setTitle("Listagem de Produtos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Preço", "Status"}, 0
        );

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        JButton btnVender = new JButton("Vender");
        JButton btnVendas = new JButton("Consultar Vendas");

        btnVender.addActionListener(e -> venderProduto());
        btnVendas.addActionListener(e -> abrirVendas());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnVender);
        painelBotoes.add(btnVendas);

        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        listarProdutos();
    }

    private void listarProdutos() {
        modelo.setRowCount(0);
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<Produto> lista = dao.listarProdutos();

        for (Produto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getStatus()
            });
        }
    }

    private void venderProduto() {
        int linha = tabela.getSelectedRow();

        if (linha >= 0) {
            int id = Integer.parseInt(tabela.getValueAt(linha, 0).toString());
            ProdutosDAO dao = new ProdutosDAO();
            dao.venderProduto(id);

            JOptionPane.showMessageDialog(this, "Produto vendido!");
            listarProdutos();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto.");
        }
    }

    private void abrirVendas() {
        new TelaVendas().setVisible(true);
    }

    public static void main(String[] args) {
        new TelaListagem().setVisible(true);
    }
}
