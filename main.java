import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioAtual = null; // Usuário atual para simulação de sessão

        while (true) {
            System.out.println("\n--- Sistema Bibliotecário (Integrado com Python) ---");
            System.out.println("1. Criar/Selecionar Usuário");
            System.out.println("2. Adicionar Item ao Acervo");
            System.out.println("3. Exibir Acervo");
            System.out.println("4. Buscar Item");
            System.out.println("5. Emprestar Item");
            System.out.println("6. Devolver Item");
            System.out.println("7. Ver Histórico de Ações (Pilha Python)");
            System.out.println("8. Sair");
            System.out.print("Escolha: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir newline

            switch (opcao) {
                case 1:
                    // Criar ou selecionar usuário
                    System.out.print("ID do Usuário: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome do Usuário: ");
                    String nome = scanner.nextLine();
                    usuarioAtual = new Usuario(id, nome);
                    System.out.println("Usuário selecionado: " + nome);
                    break;
                case 2:
                    // Adicionar item ao acervo (exemplo para Livro)
                    System.out.print("Tipo de Item (1-Livro, 2-Revista, 3-Mídia): ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID: ");
                    int itemId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    if (tipo == 1) {
                        System.out.print("Gênero: ");
                        String genero = scanner.nextLine();
                        Livro livro = new Livro(itemId, titulo, autor, genero);
                        biblioteca.adicionarItem(livro);
                        System.out.println("Livro adicionado!");
                    } else if (tipo == 2) {
                        System.out.print("Edição: ");
                        String edicao = scanner.nextLine();
                        Revista revista = new Revista(itemId, titulo, autor, edicao);
                        biblioteca.adicionarItem(revista);
                        System.out.println("Revista adicionada!");
                    } else if (tipo == 3) {
                        System.out.print("Tipo de Mídia: ");
                        String tipoMidia = scanner.nextLine();
                        Midia midia = new Midia(itemId, titulo, autor, tipoMidia);
                        biblioteca.adicionarItem(midia);
                        System.out.println("Mídia adicionada!");
                    }
                    break;
                case 3:
                    biblioteca.exibirAcervo();
                    break;
                case 4:
                    System.out.print("Título: ");
                    titulo = scanner.nextLine();
                    ItemAcervo item = biblioteca.buscarItem(titulo);
                    if (item != null) {
                        item.exibirDetalhes();
                    } else {
                        System.out.println("Item não encontrado.");
                    }
                    break;
                case 5:
                    if (usuarioAtual == null) {
                        System.out.println("Selecione um usuário primeiro!");
                        break;
                    }
                    System.out.print("Título do Item: ");
                    titulo = scanner.nextLine();
                    item = biblioteca.buscarItem(titulo);
                    if (item != null) {
                        biblioteca.emprestarItem(usuarioAtual, item);
                    } else {
                        System.out.println("Item não encontrado.");
                    }
                    break;
                case 6:
                    if (usuarioAtual == null) {
                        System.out.println("Selecione um usuário primeiro!");
                        break;
                    }
                    System.out.print("Título do Item: ");
                    titulo = scanner.nextLine();
                    item = biblioteca.buscarItem(titulo);
                    if (item != null) {
                        biblioteca.devolverItem(usuarioAtual, item);
                    } else {
                        System.out.println("Item não encontrado.");
                    }
                    break;
                case 7:
                    if (usuarioAtual == null) {
                        System.out.println("Selecione um usuário primeiro!");
                        break;
                    }
                    usuarioAtual.exibirHistorico(); // Usa a pilha Python
                    break;
                case 8:
                    biblioteca.fechar();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}