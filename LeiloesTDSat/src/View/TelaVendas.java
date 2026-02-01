package View;

import DAO.ProdutosDAO;
import Model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class TelaVendas extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;

    public TelaVendas() {
        setTitle("Vendas");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(
            new Object[]{"ID", "Nome", "Preço", "Status"}, 0
        );

        tabela = new JTable(modelo);
        add(new JScrollPane(tabela));

        listarVendas();
    }

    private void listarVendas() {
        modelo.setRowCount(0);
        ProdutosDAO dao = new ProdutosDAO();
        ArrayList<Produto> lista = dao.listarProdutosVendidos();

        for (Produto p : lista) {
            modelo.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getStatus()
            });
        }
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
