import sys

# Simular pilha em memória (para simplicidade; em produção, use arquivo persistente)
pilha = []

if len(sys.argv) > 1:
    comando = sys.argv[1]
    if comando == "empilhar":
        if len(sys.argv) > 2:
            acao = sys.argv[2]
            pilha.append(acao)
            print("Empilhado:", acao)
        else:
            print("Erro: Ação não fornecida")
    elif comando == "desempilhar":
        if pilha:
            item = pilha.pop()
            print(item)
        else:
            print("")  # Indica vazio
    elif comando == "esta_vazia":
        print("true" if not pilha else "false")
    else:
        print("Comando inválido")
else:
    print("Uso: python pilha.py <comando> [parametro]")