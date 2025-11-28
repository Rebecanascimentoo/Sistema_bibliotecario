import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private List<String> historico = new ArrayList<>();

    public Usuario(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void registrarAcao(String acao) {
        historico.add(acao);
    }

    public void exibirHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma ação registrada.");
        } else {
            for (String h : historico) {
                System.out.println("- " + h);
            }
        }
    }
}
