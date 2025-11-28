import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private Usuario usuario;
    private ItemAcervo item;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(int id, Usuario usuario, ItemAcervo item) {
        this.id = id;
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = null;
    }

    // Getters e Setters
    public int getId() { return id;}
    public Usuario getUsuario() { return usuario;}
    public ItemAcervo getItem() { return item; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
}