import java.util.ArrayList;

public class CaminhamentoLargura {
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
    private int s; // vértice inicial

    public CaminhamentoLargura(Graph g, int s) {
        this.s = s;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        distTo = new int[g.V()];
        bfs(g, s);
    }

    private void bfs(Graph g, int v) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(v);
        marked[v] = true;
        distTo[v] = 0;
        while(!q.isEmpty()) {
            v = q.dequeue();
            for(int w: g.adj(v)) {
                if(!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v]+1;
                    q.enqueue(w);
                }
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
        CaminhamentoLargura cl = new CaminhamentoLargura(g, 0);
        for (int v = 0; v < g.V(); v++) {
            if (cl.hasPathTo(v)) {
                System.out.println(v + " -> " + cl.pathTo(v));
            }
        }

    }
}
