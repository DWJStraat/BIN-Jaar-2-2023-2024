package week_2.gene_sort;

import java.util.Objects;

public class Gene implements Comparable<Gene>{
    private int GeneID;
    private String symbol;
    private int chromosome;
    private Float mapLocation;
    public Gene(String gene_contents){
        String[] contents = gene_contents.split("\t");
        GeneID = Integer.parseInt(contents[1]);
        symbol = contents[2];
        try {
            chromosome = Integer.parseInt(contents[6]);
        } catch (NumberFormatException ignored) {}
        System.out.println(contents[7]);
        try {
            String chromosomeMapLocation = contents[7].split("[|-]")[0].replaceAll(".*[pq]", "");
            mapLocation = Float.parseFloat(chromosomeMapLocation);
        } catch (ArrayIndexOutOfBoundsException|NumberFormatException ignored){
            this.mapLocation = (float) 0;
        }
    }

    public int getGeneID() {
        return GeneID;
    }

    public void setGeneID(int geneID) {
        GeneID = geneID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getChromosome() {
        return chromosome;
    }

    public void setChromosome(int chromosome) {
        this.chromosome = chromosome;
    }

    public Float getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(Float mapLocation) {
        this.mapLocation = mapLocation;
    }

    @Override
    public int compareTo(Gene gene) {
        int geneChromosome = gene.getChromosome();
        Float geneMapLocation = gene.getMapLocation();
        if(chromosome == geneChromosome){
            if(Objects.equals(mapLocation, geneMapLocation)){
                return 0;
            }
            else if (mapLocation > geneMapLocation){
                return 1;
            }
            else {
                return -1;
            }

        }
        else if(chromosome < geneChromosome){
            return 1;
        }
        else{
            return -1;
        }
    }
}
