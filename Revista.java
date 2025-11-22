public class Revista extends ItemAcervo {
    private String edicao;

    public Revista(int id, String titulo, String autor, String edicao) {
        super(id, titulo, autor);
        this.edicao = edicao;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Revista: " + getTitulo() + " | Autor: " + getAutor() + " | Edição: " + edicao + " | Disponível: " + isDisponivel());
    }
}