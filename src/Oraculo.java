public class Oraculo
{
    public static void main(String[] args) {
        Graph g = new Graph(120000);
        In arq = new In("movies.txt");
        while(arq.hasNextLine()) {
            String linha = arq.readLine();
            String[] nomes = linha.split("/");
            System.out.println(nomes[0]);
            for(int pos=1; pos<nomes.length; pos++)
                System.out.println("    "+nomes[pos]);
        }
        arq.close();
    }
}