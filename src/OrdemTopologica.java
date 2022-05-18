import java.util.ArrayList;

public class OrdemTopologica {
    private boolean[] marked;
    private Stack<Integer> reversePostOrder;
    private ArrayList<Integer> preOrder;
    private ArrayList<Integer> postOrder;

    public OrdemTopologica(Digraph g) {
        marked = new boolean[g.V()];
        reversePostOrder = new Stack<>();
        preOrder = new ArrayList<>();
        postOrder = new ArrayList<>();
        for(int v=0; v<g.V(); v++)
            if(!marked[v])
                dfs(g, v);
    }

    public void dfs(Digraph g, int v) {
        marked[v] = true;
        preOrder.add(v);
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        postOrder.add(v);
        reversePostOrder.push(v);
    }

    public Iterable<Integer> ordemTopologica() {
        return reversePostOrder;
    }

    public Iterable<Integer> ordemPre() {
        return preOrder;
    }

    public Iterable<Integer> ordemPos() {
        return postOrder;
    }

    public static void main(String[] args) {
        In in = new In("tinyDAG.txt");
        Digraph g = new Digraph(in);
        System.out.println("Vértices: " + g.V());
        for (int v = 0; v < g.V(); v++) {
            System.out.print(v + ": ");
            for (int w : g.adj(v))
                System.out.print(w + " ");
            System.out.println();
        }
        OrdemTopologica ot = new OrdemTopologica(g);
        System.out.println();
        System.out.println("Pré-ordem:");
        for(int v: ot.ordemPre())
            System.out.print(v + " ");
        System.out.println();
        System.out.println("Pós-ordem:");
        for(int v: ot.ordemPos())
            System.out.print(v + " ");
        System.out.println();
        System.out.println("Ordem topológica (pós-ordem reversa):");
        for(int v: ot.ordemTopologica())
            System.out.print(v + " ");
        System.out.println();
    }
}
