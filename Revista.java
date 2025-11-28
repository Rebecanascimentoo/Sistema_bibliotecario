public class Revista extends ItemAcervo {
    private int edicao;

    public Revista(int id, String titulo, String autor, int edicao) {
        super(id, titulo, autor);
        this.edicao = edicao;
    }

    public int getEdicao() {
        return edicao;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("Revista: " + getTitulo() +
                " | Autor: " + getAutor() +
                " | Edição: " + edicao +
                " | Disponível: " + isDisponivel());
    }
}
