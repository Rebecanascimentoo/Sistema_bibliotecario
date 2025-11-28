public class Livro extends ItemAcervo {
    private String genero;

    public Livro(int id, String titulo, String autor, String genero) {
        super(id, titulo, autor);
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Livro: " + getTitulo() +
                " | Autor: " + getAutor() +
                " | Gênero: " + genero +
                " | Disponível: " + isDisponivel());
    }
}
