import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class Biblioteca {
    // ... (outros atributos)
    private PyObject filaReservas; // Referência à fila Python

    public Biblioteca() {
        // ... (conexão MySQL e carregamento)
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("from estruturas import Fila");
        this.filaReservas = interpreter.eval("Fila()");
    }

    // Modificar emprestarItem para usar fila Python
    public void emprestarItem(Usuario usuario, ItemAcervo item) {
        if (item.isDisponivel()) {
            // ... (lógica existente)
        } else {
            filaReservas.invoke("enfileirar", new PyObject[]{new org.python.core.PyString(usuario.getNome())});
            System.out.println("Item indisponível. Adicionado à fila de reservas.");
        }
    }

    // Modificar devolverItem para atender fila Python
    public void devolverItem(Usuario usuario, ItemAcervo item) {
        // ... (lógica existente)
        PyObject proximo = (PyObject) filaReservas.invoke("desenfileirar");
        if (proximo != null) {
            // Criar usuário fictício ou buscar do mapa
            Usuario proximoUsuario = new Usuario(2, proximo.toString()); // Exemplo
            emprestarItem(proximoUsuario, item);
        }
    }

    // ... (restante do código)
}