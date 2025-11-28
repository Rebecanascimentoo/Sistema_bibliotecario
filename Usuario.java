import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Usuario {
    private int id;
    private String nome;

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Adicionar ação ao histórico (chamando pilha.py via subprocess)
    public void adicionarAcao(String acao) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "pilha.py", "empilhar", acao);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Python (Pilha): " + line);
            }
            p.waitFor();
        } catch (Exception e) {
            System.err.println("Erro ao chamar Python para empilhar: " + e.getMessage());
        }
    }

    // Exibir histórico (desempilhar via pilha.py)
    public void exibirHistorico() {
        System.out.println("Histórico de ações para " + nome + ":");
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "pilha.py", "desempilhar");
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    System.out.println(line);
                }
            }
            p.waitFor();
        } catch (Exception e) {
            System.err.println("Erro ao chamar Python para desempilhar: " + e.getMessage());
        }
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
}