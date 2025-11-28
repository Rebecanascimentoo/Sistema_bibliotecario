import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioAtual = null;

        String titulo;
        ItemAcervo item;

        while (true) {
            try {
                System.out.println("\n--- Sistema Bibliotecário (Integrado com Python) ---");
                System.out.println("1. Criar/Selecionar Usuário");
                System.out.println("2. Adicionar Item ao Acervo");
                System.out.println("3. Exibir Acervo");
                System.out.println("4. Buscar Item");
                System.out.println("5. Emprestar Item");
                System.out.println("6. Devolver Item");
                System.out.println("7. Ver Histórico de Ações");
                System.out.println("8. Sair");
                System.out.print("Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {

                    case 1:
                        try {
                            System.out.print("ID do usuário: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nome do usuário: ");
                            String nome = scanner.nextLine();
                            usuarioAtual = new Usuario(id, nome);
                            System.out.println("Usuário ativo: " + nome);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Tipo (1-Livro, 2-Revista, 3-Mídia): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("ID do item: ");
                        int itemId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Título: ");
                        titulo = scanner.nextLine();

                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();

                        if (tipo == 1) {
                            System.out.print("Gênero: ");
                            String genero = scanner.nextLine();
                            Livro livro = new Livro(itemId, titulo, autor, genero);
                            biblioteca.adicionarItem(livro);
                            System.out.println("Livro adicionado!");

                        } else if (tipo == 2) {
                            System.out.print("Edição (número): ");
                            int edicao = scanner.nextInt();
                            scanner.nextLine();

                            Revista revista = new Revista(itemId, titulo, autor, edicao);
                            biblioteca.adicionarItem(revista);
                            System.out.println("Revista adicionada!");

                        } else if (tipo == 3) {
                            System.out.print("Tipo de mídia: ");
                            String tipoMidia = scanner.nextLine();
                            Midia midia = new Midia(itemId, titulo, autor, tipoMidia);
                            biblioteca.adicionarItem(midia);
                            System.out.println("Mídia adicionada!");

                        } else {
                            System.out.println("Tipo inválido!");
                        }
                        break;

                    case 3:
                        biblioteca.listar();
                        break;

                    case 4:
                        System.out.print("Título: ");
                        titulo = scanner.nextLine();
                        item = biblioteca.buscarItem(titulo);
                        if (item != null) item.exibirDetalhes();
                        else System.out.println("Item não encontrado.");
                        break;

                    case 5:
                        if (usuarioAtual == null) {
                            System.out.println("Selecione um usuário primeiro.");
                            break;
                        }
                        System.out.print("Título do item: ");
                        titulo = scanner.nextLine();
                        item = biblioteca.buscarItem(titulo);
                        if (item != null) biblioteca.emprestarItem(usuarioAtual, item);
                        else System.out.println("Item não encontrado.");
                        break;

                    case 6:
                        if (usuarioAtual == null) {
                            System.out.println("Selecione um usuário primeiro.");
                            break;
                        }
                        System.out.print("Título do item: ");
                        titulo = scanner.nextLine();
                        item = biblioteca.buscarItem(titulo);
                        if (item != null) biblioteca.devolverItem(usuarioAtual, item);
                        else System.out.println("Item não encontrado.");
                        break;

                    case 7:
                        if (usuarioAtual == null) {
                            System.out.println("Selecione um usuário primeiro.");
                        } else {
                            usuarioAtual.exibirHistorico();
                        }
                        break;

                    case 8:
                        biblioteca.fechar();
                        System.out.println("Sistema encerrado.");
                        System.exit(0);

                    default:
                        System.out.println("Opção inválida.");

                }

            } catch (InputMismatchException e) {
                System.out.println("Erro: digite apenas números onde solicitado!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
