# estruturas.py - Implementação de pilhas e filas em Python

class Pilha:
    def __init__(self):
        self.itens = []

    def empilhar(self, item):
        self.itens.append(item)

    def desempilhar(self):
        if not self.esta_vazia():
            return self.itens.pop()
        return None

    def esta_vazia(self):
        return len(self.itens) == 0

    def topo(self):
        if not self.esta_vazia():
            return self.itens[-1]
        return None

class Fila:
    def __init__(self):
        self.itens = []

    def enfileirar(self, item):
        self.itens.append(item)

    def desenfileirar(self):
        if not self.esta_vazia():
            return self.itens.pop(0)
        return None

    def esta_vazia(self):
        return len(self.itens) == 0

    def frente(self):
        if not self.esta_vazia():
            return self.itens[0]
        return None