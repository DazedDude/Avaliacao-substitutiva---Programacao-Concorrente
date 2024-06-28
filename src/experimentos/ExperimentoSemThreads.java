package experimentos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import models.Cidade;

public class ExperimentoSemThreads extends Experimento {

    public ExperimentoSemThreads(List<Cidade> cidades) {
        super(cidades);
    }

    @Override
    public void run() {
    	try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./src/dados.txt", true));
			long totalTempoExecucao = 0;
			for (int i = 0; i < 10; i++) {
				long tempoInicio = System.currentTimeMillis();
				processarCidades(cidades, "sem Threads");
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

