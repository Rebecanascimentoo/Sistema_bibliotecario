public abstract class ItemAcervo {
    private int id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public ItemAcervo(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    // Método abstrato para exibir detalhes
    public abstract void exibirDetalhes();
}