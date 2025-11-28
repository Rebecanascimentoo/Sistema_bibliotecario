import java.sql.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Biblioteca {
    private Connection conn;
    private Map<Integer, ItemAcervo> acervo;
    private Map<Integer, Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.acervo = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new ArrayList<>();
        conectarMySQL();
        carregarDados();
    }

    // Conectar ao MySQL (igual ao original)
    private void conectarMySQL() {
        try {
            String url = "jdbc:mysql://localhost:3306/biblioteca";
            String user = "root";
            String password = "password";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado ao MySQL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Carregar dados (igual ao original)
    private void carregarDados() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM livros");
            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("id"), rs.getString("titulo"), rs.getString("autor"), rs.getString("genero"));
                acervo.put(livro.getId(), livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adicionar item (igual ao original)
    public void adicionarItem(ItemAcervo item) {
        acervo.put(item.getId(), item);
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO livros (id, titulo, autor, genero) VALUES (?, ?, ?, ?)");
            ps.setInt(1, item.getId());
            ps.setString(2, item.getTitulo());
            ps.setString(3, item.getAutor());
            ps.setString(4, ((Livro) item).genero);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar item (igual ao original)
    public ItemAcervo buscarItem(String titulo) {
        for (ItemAcervo item : acervo.values()) {
            if (item.getTitulo().equalsIgnoreCase(titulo)) {
                return item;
            }
        }
        return null;
    }

    // Emprestar item (atualizado com fila.py)
    public void emprestarItem(Usuario usuario, ItemAcervo item) {
        if (item.isDisponivel()) {
            item.setDisponivel(false);
            Emprestimo emprestimo = new Emprestimo(emprestimos.size() + 1, usuario, item);
            emprestimos.add(emprestimo);
            usuario.adicionarAcao("Emprestou: " + item.getTitulo());
            System.out.println("Empréstimo realizado!");
        } else {
            // Adicionar à fila via Python
            try {
                ProcessBuilder pb = new ProcessBuilder("python", "fila.py", "enfileirar", usuario.getNome());
                pb.redirectErrorStream(true);
                Process p = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("Python (Fila): " + line);
                }
                p.waitFor();
            } catch (Exception e) {
                System.err.println("Erro ao enfileirar: " + e.getMessage());
            }
            System.out.println("Item indisponível. Adicionado à fila de reservas.");
        }
    }

    // Devolver item (atualizado com fila.py)
    public void devolverItem(Usuario usuario, ItemAcervo item) {
        item.setDisponivel(true);
        for (Emprestimo emp : emprestimos) {
            if (emp.getUsuario().equals(usuario) && emp.getItem().equals(item)) {
                emp.setDataDevolucao(java.time.LocalDate.now());
                usuario.adicionarAcao("Devolveu: " + item.getTitulo());
                break;
            }
        }
        // Atender próximo na fila via Python
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "fila.py", "desenfileirar");
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String proximo = reader.readLine();
            if (proximo != null && !proximo.isEmpty()) {
                Usuario proximoUsuario = new Usuario(999, proximo); // ID fictício
                emprestarItem(proximoUsuario, item);
            }
            p.waitFor();
        } catch (Exception e) {
            System.err.println("Erro ao desenfileirar: " + e.getMessage());
        }
        System.out.println("Devolução realizada!");
    }

    // Exibir acervo (igual ao original)
    public void exibirAcervo() {
        for (ItemAcervo item : acervo.values()) {
            item.exibirDetalhes();
        }
    }

    // Fechar conexão (igual ao original)
    public void fechar() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}