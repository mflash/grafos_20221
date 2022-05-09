import java.util.ArrayList;

public class CaminhamentoProfundidade {
    private boolean[] marked;
    private int[] edgeTo;
    private int s; // vértice inicial

    public CaminhamentoProfundidade(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    public void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public ArrayList<Integer> pathTo(int w) {
        if (!hasPathTo(w))
            return null;
        ArrayList<Integer> aux = new ArrayList<>();
        // Enquanto não chegar no inicial (s)
        while (w != s) {
            aux.add(0, w); // insere no início do arraylist
            w = edgeTo[w];
        }
        aux.add(0, s);
        return aux;
    }

    public static void main(String[] args) {
        In in = new In("tinyG.txt");
        Graph g = new Graph(in);
        System.out.println("Vértices: " + g.V());
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ": ");
            for (int w : g.adj(v))
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println();
        CaminhamentoProfundidade cp = new CaminhamentoProfundidade(g, 0);
        for (int v = 0; v < g.V(); v++) {
            if (cp.hasPathTo(v)) {
                System.out.println(v + " -> " + cp.pathTo(v));
            }
        }

    }
}
