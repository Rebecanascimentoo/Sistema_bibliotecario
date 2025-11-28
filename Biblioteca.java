import java.sql.*;
import java.util.*;

public class Biblioteca {

    private List<ItemAcervo> itens = new ArrayList<>();
    private Connection conn;

    public Biblioteca() {
        conectar();
        if (conn != null) {
            carregarDados();
        }
    }

    private void conectar() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/biblioteca",
                    "root",
                    ""
            );
            System.out.println("Banco conectado com sucesso.");
        } catch (Exception e) {
            conn = null;
            System.out.println("Banco não conectado. Sistema rodará sem banco.");
        }
    }

    private void carregarDados() {
        carregarLivros();
        carregarRevistas();
        carregarMidias();
    }

    private void carregarLivros() {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM livros")) {

            while (rs.next()) {
                itens.add(new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("genero")
                ));
            }
        } catch (Exception e) {
            System.out.println("Sem tabela 'livros' ou erro ao carregar livros.");
        }
    }

    private void carregarRevistas() {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM revistas")) {

            while (rs.next()) {
                itens.add(new Revista(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getInt("edicao")
                ));
            }
        } catch (Exception e) {
            System.out.println("Tabela 'revistas' não encontrada.");
        }
    }

    private void carregarMidias() {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM midias")) {

            while (rs.next()) {
                itens.add(new Midia(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("tipo")
                ));
            }
        } catch (Exception e) {
            System.out.println("Tabela 'midias' não encontrada.");
        }
    }

    public void adicionarItem(ItemAcervo item) {
        itens.add(item);

        if (conn != null) {
            salvarNoBanco(item);
        } else {
            System.out.println("Salvo somente em memória (banco offline).");
        }
    }

    private void salvarNoBanco(ItemAcervo item) {

        try {

            if (item instanceof Livro l) {

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO livros VALUES (?, ?, ?, ?)"
                );
                ps.setInt(1, l.getId());
                ps.setString(2, l.getTitulo());
                ps.setString(3, l.getAutor());
                ps.setString(4, l.getGenero());
                ps.executeUpdate();

            } else if (item instanceof Revista r) {

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO revistas VALUES (?, ?, ?, ?)"
                );
                ps.setInt(1, r.getId());
                ps.setString(2, r.getTitulo());
                ps.setString(3, r.getAutor());
                ps.setInt(4, r.getEdicao());
                ps.executeUpdate();

            } else if (item instanceof Midia m) {

                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO midias VALUES (?, ?, ?, ?)"
                );
                ps.setInt(1, m.getId());
                ps.setString(2, m.getTitulo());
                ps.setString(3, m.getAutor());
                ps.setString(4, m.getTipo());
                ps.executeUpdate();
            }

            System.out.println("Item salvo no banco.");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar no banco: " + e.getMessage());
        }
    }

    public void listar() {
        if (itens.isEmpty()) {
            System.out.println("Nenhum item no acervo.");
        } else {
            for (ItemAcervo i : itens) {
                i.exibirDetalhes();
            }
        }
    }

    // ✅ MÉTODO QUE FALTAVA (BUSCAR)
    public ItemAcervo buscarItem(String titulo) {
        for (ItemAcervo i : itens) {
            if (i.getTitulo().equalsIgnoreCase(titulo)) {
                return i;
            }
        }
        return null;
    }

    // ✅ MÉTODO QUE FALTAVA (EMPRESTAR)
    public void emprestarItem(Usuario usuario, ItemAcervo item) {
        if (item.isEmprestado()) {
            System.out.println("Item já está emprestado.");
        } else {
            item.setEmprestado(true);
            usuario.registrarAcao("Emprestou: " + item.getTitulo());
            System.out.println("Item emprestado com sucesso.");
        }
    }

    // ✅ MÉTODO QUE FALTAVA (DEVOLVER)
    public void devolverItem(Usuario usuario, ItemAcervo item) {
        if (!item.isEmprestado()) {
            System.out.println("Este item não está emprestado.");
        } else {
            item.setEmprestado(false);
            usuario.registrarAcao("Devolveu: " + item.getTitulo());
            System.out.println("Item devolvido com sucesso.");
        }
    }

    public void fechar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão com banco encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar banco.");
        }
    }
}
