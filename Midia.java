public class Midia extends ItemAcervo {
    private String tipo;

    public Midia(int id, String titulo, String autor, String tipo) {
        super(id, titulo, autor);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Mídia: " + getTitulo() +
                " | Autor: " + getAutor() +
                " | Tipo: " + tipo +
                " | Disponível: " + isDisponivel());
    }
}
