import sys, json, os

ARQ = "fila.json"

def ler():
    if os.path.exists(ARQ):
        with open(ARQ) as f:
            return json.load(f)
    return []

def salvar(f):
    with open(ARQ, "w") as arq:
        json.dump(f, arq)

fila = ler()

if sys.argv[1] == "enfileirar":
    fila.append(sys.argv[2])
    salvar(fila)
    print("Enfileirado")

elif sys.argv[1] == "desenfileirar":
    if fila:
        print("Saiu:", fila.pop(0))
        salvar(fila)
    else:
        print("Fila vazia")
