import sys, json, os

ARQ = "pilha.json"

def ler():
    if os.path.exists(ARQ):
        with open(ARQ) as f:
            return json.load(f)
    return []

def salvar(p):
    with open(ARQ, "w") as arq:
        json.dump(p, arq)

pilha = ler()

if sys.argv[1] == "empilhar":
    pilha.append(sys.argv[2])
    salvar(pilha)
    print("Empilhado")

elif sys.argv[1] == "desempilhar":
    if pilha:
        print(pilha.pop())
        salvar(pilha)
