package week_6;

public class main {

    public static void main(String[] args) {
        fasta s1 = new fasta(">Rosalind_0498\nAAATAAA");
        fasta s2 = new fasta(">Rosalind_2391\nAAATTTT");
        fasta s3 = new fasta(">Rosalind_2323\nTTTTCCC");
        fasta s4 = new fasta(">Rosalind_0442\nAAATCCC");
        fasta s5 = new fasta(">Rosalind_5013\nGGGTGGG");
        fasta[] input = {s1, s2, s3, s4, s5};
        graphBuilder graph = new graphBuilder(input, 3);
        System.out.println(graph);
    }
}
