package experimentos;

import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import org.json.JSONObject;

import models.Cidade;
import models.Temperatura;
import request.HttpClientHelper;

public abstract class Experimento implements Runnable {
    protected List<Cidade> cidades;

    public Experimento(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    protected void processarCidades(List<Cidade> cidades, String versao) {
        for (Cidade cidade : cidades) {
            try {
            	BufferedWriter bw = new BufferedWriter(new FileWriter("./src/dados.txt", true));
                JSONObject dados = HttpClientHelper.fetchData(cidade.getLatitude(), cidade.getLongitude(), "2024-01-01", "2024-01-31");
                List<Temperatura> temperaturas = calcularTemperaturas(dados);
                bw.write("\nCidade: " + cidade.getNome() + "\n");
                bw.flush();
                System.out.println("Cidade: " + cidade.getNome());
                int i = 0;
                for (Temperatura temp : temperaturas) {
                	i++;
                	bw.write(versao + " - " + cidade.getNome() + " - Dia " + i + " - " + temp + "\n");
                	bw.flush();
                    System.out.println(versao + " - " + cidade.getNome() + " - Dia " + i + " - " + temp);
                }
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<Temperatura> calcularTemperaturas(JSONObject dados) {
        List<Temperatura> temperaturas = new ArrayList<>();
        // Supondo que 'dados' contém um array de temperaturas horárias para cada dia do mês
        for (int dia = 0; dia < 31; dia++) {
            double soma = 0;
            double minima = Double.MAX_VALUE;
            double maxima = Double.MIN_VALUE;
            for (int hora = 0; hora < 24; hora++) {
                double tempHora = dados.getJSONObject("hourly").getJSONArray("temperature_2m").getDouble(dia * 24 + hora);
                soma += tempHora;
                if (tempHora < minima) minima = tempHora;
                if (tempHora > maxima) maxima = tempHora;
            }
            double media = soma / 24;
            temperaturas.add(new Temperatura(media, minima, maxima));
        }
        return temperaturas;
    }
}
