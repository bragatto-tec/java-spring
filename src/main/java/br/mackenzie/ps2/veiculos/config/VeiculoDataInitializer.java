package br.mackenzie.ps2.veiculos.config;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import br.mackenzie.ps2.veiculos.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VeiculoDataInitializer implements CommandLineRunner {

    private final VeiculoRepository veiculoRepository;

    public VeiculoDataInitializer(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Adicionar dados de teste
        veiculoRepository.save(new Veiculo("Toyota", "Corolla", 2023, "ABC1234", "Branco"));
        veiculoRepository.save(new Veiculo("Honda", "Civic", 2022, "DEF5678", "Preto"));
        veiculoRepository.save(new Veiculo("Volkswagen", "Gol", 2021, "GHI9012", "Prata"));
        veiculoRepository.save(new Veiculo("Hyundai", "HB20", 2023, "JKL3456", "Vermelho"));
        veiculoRepository.save(new Veiculo("Ford", "Focus", 2022, "MNO7890", "Azul"));
    }
}
