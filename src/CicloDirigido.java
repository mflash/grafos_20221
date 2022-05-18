public class CicloDirigido {

    private enum Marca { WHITE, GRAY, BLACK };
    private Marca[] marked;    
    private boolean cycle;

    public CicloDirigido(Digraph g) {
        marked = new Marca[g.V()];
        for(int s=0; s<g.V(); s++)
            marked[s] = Marca.WHITE;
        cycle = false;
        for (int s = 0; s < g.V(); s++) {
            if (marked[s] == Marca.WHITE) {
                System.out.println("Chamando containsCycle em " + s);
                if(visit(g, s))
                    cycle = true;
            }
            if (cycle)
                return;
        }
    }

    public boolean visit(Digraph g, int v) {
        marked[v] = Marca.GRAY;
        System.out.println("Comecei o "+v);
        for (int w : g.adj(v)) {
            if(marked[w] == Marca.GRAY) {
                System.out.println("Achei de "+v+" para "+w);
                return true; // achou ciclo
            }
            else if(marked[w] == Marca.WHITE)
                if(visit(g,w))
                    return true;
        }
        marked[v] = Marca.BLACK; // terminou
        System.out.println("Terminei o "+v);
        return false;
    }

    public boolean hasCycle() {
        return cycle;
    }

    public static void main(String[] args) {
        /*
        In in = new In("tinyDG.txt");
        Digraph g = new Digraph(in);
        */
        /**/
        Digraph g = new Digraph(7);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        //g.addEdge(2, 3);
        g.addEdge(3, 1);
        g.addEdge(4, 5);
        g.addEdge(6, 4);
        g.addEdge(5, 6);
        /**/
        System.out.println(g.toDot());
        System.out.println("Vértices: " + g.V());
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ": ");
            for (int w : g.adj(v))
                System.out.print(w + " ");
            System.out.println();
        }
        System.out.println();
        CicloDirigido dc = new CicloDirigido(g);
        System.out.println("Tem ciclo? " + dc.hasCycle());
    }
}
