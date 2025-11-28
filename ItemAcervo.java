public abstract class ItemAcervo {

    private int id;
    private String titulo;
    private String autor;
    private boolean emprestado = false;

    public ItemAcervo(int id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isEmprestado() {
        return emprestado;
    }

    public void setEmprestado(boolean emprestado) {
        this.emprestado = emprestado;
    }

    public boolean isDisponivel() {
        return !emprestado;
    }

    public abstract void exibirDetalhes();
}
