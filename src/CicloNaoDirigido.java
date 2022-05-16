import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CicloNaoDirigido {
    private boolean[] marked;
    private Set<String> edgeSet;
    private boolean cycle;

    public CicloNaoDirigido(Graph g) {
        marked = new boolean[g.V()];
        edgeSet = new HashSet<>();
        cycle = false;
        for (int s = 0; s < g.V(); s++) {
            if (!marked[s]) {
                System.out.println("Chamando containsCycle em " + s);
                containsCycle(g, s);
            }
            if (cycle)
                return;
        }
    }

    public void containsCycle(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            String edge = "";
            if (v < w)
                edge = v + "-" + w;
            else
                edge = w + "-" + v;
            if (!marked[w]) {
                edgeSet.add(edge);
                containsCycle(g, w);
                if (cycle)
                    return; // ganhamos tempo...
            } else {
                if (!edgeSet.contains(edge)) {
                    System.out.println("Achei um ciclo! " + edge);
                    cycle = true;
                    return;
                }
            }
        }
    }

    public boolean hasCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        // In in = new In("tinyG.txt");
        // Graph g = new Graph(in);
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        // g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 6);
        System.out.println("Vértices: " + g.V());
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ": ");
            for (int w : g.adj(v))
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println();
        CicloNaoDirigido dc = new CicloNaoDirigido(g);
        System.out.println("Tem ciclo? " + dc.hasCycle());
    }
}
