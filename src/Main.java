import java.util.*;
public class Main {

    // Mapa que representa el grafo mediante listas de adyacencia en orden alfabético
    private static final Map<String, List<String>> grafo = new LinkedHashMap<>();

    public static void main(String[] args)
    {
        // 1 Construimos el grafo de la figura
        construirGrafo();

        // 2 Ejecutamos BFS comenzando en "A"
        System.out.println("--- RECORRIDO BFS (COLA) ---");
        bfs("A");

        // 3 Ejecutamos DFS comenzando en "A"
        System.out.println("\n--- RECORRIDO DFS (PILA) ---");
        dfs("A");
    }

    private static void construirGrafo()
    {
        grafo.put("A", Arrays.asList("B", "C"));
        grafo.put("B", Arrays.asList("A", "D", "E"));
        grafo.put("C", Arrays.asList("A", "F", "G"));
        grafo.put("D", Arrays.asList("B", "H"));
        grafo.put("E", Arrays.asList("B", "H", "I"));
        grafo.put("F", Arrays.asList("C", "I", "J"));
        grafo.put("G", Arrays.asList("C", "J"));
        grafo.put("H", Arrays.asList("D", "E", "I"));
        grafo.put("I", Arrays.asList("E", "F", "H", "J"));
        grafo.put("J", Arrays.asList("F", "G", "I"));
    }

    // =========================================================================
    // RECORRIDO EN ANCHURA (BFS) (Cola (FIFO)
    // =========================================================================

    private static void bfs(String inicio)
    {
        // vertices ya entraron a la cola para no visitarlos mas de una vez
        Set<String> descubiertos = new HashSet<>();
        // orden de visita
        List<String> visitados = new ArrayList<>();
        // Cola de pendientes
        Queue<String> cola = new LinkedList<>();

        descubiertos.add(inicio);
        cola.offer(inicio);
        int paso = 0;

        while (!cola.isEmpty())
        {
            paso++;
            // Extraemos el vertice actual del frente de la cola
            String actual = cola.poll();
            visitados.add(actual);

            // Encolamos los vecinos no descubiertos en orden alfabetico
            for (String vecino : grafo.get(actual))
            {
                if (!descubiertos.contains(vecino))
                {
                    descubiertos.add(vecino);
                    cola.offer(vecino);
                }
            }

            String estadoCola = cola.isEmpty() ? "(null)" : cola.toString();
            System.out.println("Paso " + paso
                    + " | Actual: " + actual
                    + " | Visitados: " + visitados
                    + " | Cola: " + estadoCola);
        }

        System.out.println("Secuencia final BFS: " + String.join(" - ", visitados));
    }

    // =========================================================================
    // RECORRIDO EN PROFUNDIDAD (DFS) (Pila (LIFO))
    // =========================================================================
    private static void dfs(String inicio)
    {
        // Vertices ya entraron a la pila para no visitarlos mas de una vez
        Set<String> descubiertos = new HashSet<>();
        // Orden de visita
        List<String> visitados = new ArrayList<>();
        //  Pila de pendientes
        Stack<String> pila = new Stack<>();

        descubiertos.add(inicio);
        pila.push(inicio);
        int paso = 0;

        while (!pila.isEmpty())
        {
            paso++;
            // Extraemos el vertice actual del tope de la pila
            String actual = pila.pop();
            visitados.add(actual);

            // Apilamos los vecinos no descubiertos en orden alfabetico
            for (String vecino : grafo.get(actual))
            {
                if (!descubiertos.contains(vecino))
                {
                    descubiertos.add(vecino);
                    pila.push(vecino);
                }
            }

            String estadoPila = pila.isEmpty() ? "(null)" : pila.toString();
            System.out.println("Paso " + paso
                    + " | Actual: " + actual
                    + " | Visitados: " + visitados
                    + " | Pila: " + estadoPila);
        }

        System.out.println("Secuencia final DFS: " + String.join(" - ", visitados));
        System.out.printf("\n:D\n");
    }
}