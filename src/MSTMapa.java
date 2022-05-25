
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *
 * @author cohen
 */
public class MSTMapa {

    private class Ponto {

        private double x, y;

        public Ponto(double x, double y) {
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }
    }

    private ArrayList<Ponto> pontos;
    private int totalPontos;

    // 1. Tarefa 1: ler os pontos para o ArrayList<Ponto>
    public void loadData(String filename) throws IOException {
        In arq = new In(filename);
        while (arq.hasNextLine()) {
            String linha = arq.readLine();
            String[] coords = linha.split(";");
            double y = Double.parseDouble(coords[0]);
            double x = Double.parseDouble(coords[1]);
            pontos.add(new Ponto(x, y));
        }
        arq.close();
    }

    // Tarefa 3: gera arquivo de saída
    public void saveData(EdgeWeightedGraph g, String filename) throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter bw = Files.newBufferedWriter(path, Charset.forName("utf8"))) {
            for (Edge e : g.edges()) {
                int mst = 1;
                // Se a aresta não fizer parte da MST, a "cor" será cinza claro
                // (o código de KruskalMST faz isso)
                if (e.getColor().equals("lightgray"))
                    mst = 0;
                bw.write(e.either() + " " + e.other(e.either()) + " " + mst + "\n");
            }
        }
    }

    public MSTMapa(String filename) {
        pontos = new ArrayList<>();
        try {
            loadData(filename);
        } catch (IOException ex) {
            System.out.println("Erro carregando dados!");
        }
        totalPontos = pontos.size();
        System.out.println("Total pontos: " + totalPontos);

        EdgeWeightedGraph g = new EdgeWeightedGraph(totalPontos);

        // Tarefa 2: passar por todos os pontos, identificando as ligações entre
        // cada ponto e os 3 mais próximos - gerar arestas para eles

        for (int i = 0; i < totalPontos; i++) {
            MinPQ<Edge> pq = new MinPQ<>();
            for (int j = 0; j < totalPontos; j++) {
                if (i == j)
                    continue;
                double distancia = dist(pontos.get(i), pontos.get(j));
                Edge e = new Edge(i, j, distancia);
                pq.insert(e);
            }
            for (int cont = 0; cont < 3; cont++) {
                Edge e = pq.delMin();
                g.addEdge(e);
            }
        }

        // Exemplo: como gerar TODAS as arestas (N * N)

        // for (int i = 0; i < totalPontos; i++) {
        // for (int j = 0; j < totalPontos; j++) {
        // if (i == j)
        // continue;
        // double d = dist(pontos.get(i), pontos.get(j));
        // g.addEdge(new Edge(i, j, d));
        // }
        // }

        System.out.println("Total arestas: " + g.E());

        KruskalMST krus = new KruskalMST(g);
        int cont = 0;
        for (Edge e : krus.edges()) {
            cont++;
        }
        System.out.println("Aplicando Kruskal: " + cont + " arestas");

        try {
            saveData(g, "saida.txt");

        } catch (IOException ex) {
            System.out.println("Erro gravando saida.txt!");
        }

        System.out.println(g.toDot());
    }

    private double dist(Ponto p1, Ponto p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) +
                Math.pow(p1.getY() - p2.getY(), 2));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MSTMapa("dados.csv");
    }

}
