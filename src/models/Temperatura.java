package models;

public class Temperatura {
    private double media;
    private double minima;
    private double maxima;

    public Temperatura(double media, double minima, double maxima) {
        this.media = media;
        this.minima = minima;
        this.maxima = maxima;
    }

    public double getMedia() {
        return media;
    }

    public double getMinima() {
        return minima;
    }

    public double getMaxima() {
        return maxima;
    }

    @Override
    public String toString() {
        return "Temperatura [media=" + media + ", minima=" + minima + ", maxima=" + maxima + "]";
    }
}


