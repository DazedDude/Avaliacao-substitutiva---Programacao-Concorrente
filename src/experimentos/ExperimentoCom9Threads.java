package experimentos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import models.Cidade;

public class ExperimentoCom9Threads extends Experimento {

    public ExperimentoCom9Threads(List<Cidade> cidades) {
        super(cidades);
    }

    @Override
    public void run() {
        long totalTempoExecucao = 0;
        try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./src/dados.txt", true));
			for (int i = 0; i < 10; i++) {
				long tempoInicio = System.currentTimeMillis();
				ExecutorService executor = Executors.newFixedThreadPool(9);
				int size = cidades.size() / 9;
				
				for (int j = 0; j < 9; j++) {
					int start = j * size;
					int end = (j == 8) ? cidades.size() : (j + 1) * size;
					List<Cidade> sublist = cidades.subList(start, end);
					executor.submit(() -> processarCidades(sublist, "9 Threads"));
				}
				
				executor.shutdown();
				try {
					executor.awaitTermination(1, TimeUnit.HOURS);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				long tempoFim = System.currentTimeMillis();
				long tempoExecucao = tempoFim - tempoInicio;
				totalTempoExecucao += tempoExecucao;
				bw.write("\nTempo de execução da rodada " + (i + 1) + ": " + tempoExecucao + "ms" + "\n");
				bw.flush();
				System.out.println("Tempo de execução da rodada " + (i + 1) + ": " + tempoExecucao + "ms");
			}
			long tempoMedioExecucao = totalTempoExecucao / 10;
			bw.write("\nTempo médio de execução: " + tempoMedioExecucao + "ms" + "\n");
			bw.flush();
			System.out.println("Tempo médio de execução: " + tempoMedioExecucao + "ms");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

