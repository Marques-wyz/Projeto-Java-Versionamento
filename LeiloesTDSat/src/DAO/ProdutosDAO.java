package DAO;

import Model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    private Connection conn;

    public ProdutosDAO() {
        conn = Conexao.conectar();
    }

    public ArrayList<Produto> listarProdutos() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // 🔹 VENDER PRODUTO

    /**
     *
     * @param idProduto
     */
    public void venderProduto(int idProduto) {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idProduto);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 LISTAR PRODUTOS VENDIDOS
    public ArrayList<Produto> listarProdutosVendidos() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    }

