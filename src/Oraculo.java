import java.util.HashMap;
import java.util.Map;

public class Oraculo {
    public static void main(String[] args) {
        // mapeia um nome (filme ou pessoa) -> nro vértice
        Map<String, Integer> dic = new HashMap<>();
        Map<Integer, String> dicRev = new HashMap<>();
        Graph g = new Graph(120000);
        In arq = new In("movies.txt");
        int atual = 0; // vértice atual
        while (arq.hasNextLine()) {
            String linha = arq.readLine();
            String[] nomes = linha.split("/");
            // System.out.println(nomes[0]);
            int nroFilme = atual;
            dic.put(nomes[0], atual++);
            dicRev.put(nroFilme, nomes[0]);
            for (int pos = 1; pos < nomes.length; pos++) {
                // System.out.println(" " + nomes[pos]);
                if (!dic.containsKey(nomes[pos])) {
                    dic.put(nomes[pos], atual);
                    dicRev.put(atual, nomes[pos]);
                    g.addEdge(nroFilme, atual);
                    atual++;
                } else {
                    int v = dic.get(nomes[pos]);
                    g.addEdge(nroFilme, v);
                }
            }
        }
        arq.close();
        System.out.println("Total: " + dic.size());
        In term = new In();
        System.out.print("De: ");
        String inicial = term.readLine();
        int vertInic = 0, vertFim = 0;
        if (dic.containsKey(inicial)) {
            vertInic = dic.get(inicial);
        } else {
            System.out.println("Não existe!");
            System.exit(1);
        }
        System.out.print("Para: ");
        String fim = term.readLine();
        if (dic.containsKey(fim)) {
            vertFim = dic.get(fim);
        } else {
            System.out.println("Não existe!");
            System.exit(1);
        }
        System.out.println(vertInic + " -> " + vertFim);
        CaminhamentoLargura bfs = new CaminhamentoLargura(g, vertInic);
        if (bfs.hasPathTo(vertFim)) {
            // Mostra o caminho
            for (int v : bfs.pathTo(vertFim)) {
                System.out.print(dicRev.get(v) + " -> ");
            }
            System.out.println();
        } else {
            // Não vai acontecer
            System.out.println("Não há conexão entre essas pessoas!");
        }
    }
}