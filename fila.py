import sys

# Simular fila em memória
fila = []

if len(sys.argv) > 1:
    comando = sys.argv[1]
    if comando == "enfileirar":
        if len(sys.argv) > 2:
            usuario = sys.argv[2]
            fila.append(usuario)
            print("Enfileirado:", usuario)
        else:
            print("Erro: Usuário não fornecido")
    elif comando == "desenfileirar":
        if fila:
            item = fila.pop(0)
            print(item)
        else:
            print("")  # Indica vazio
    elif comando == "esta_vazia":
        print("true" if not fila else "false")
    else:
        print("Comando inválido")
else:
    print("Uso: python fila.py <comando> [parametro]")