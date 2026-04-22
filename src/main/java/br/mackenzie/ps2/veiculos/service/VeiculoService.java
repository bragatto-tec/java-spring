package br.mackenzie.ps2.veiculos.service;

import br.mackenzie.ps2.veiculos.model.Veiculo;
import br.mackenzie.ps2.veiculos.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo criar(Veiculo veiculo) {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()) {
            throw new IllegalArgumentException("A placa é obrigatória.");
        }

        if (veiculoRepository.existsById(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Já existe veículo com essa placa.");
        }

        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listar() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlacaIgnoreCase(placa);
    }

    public Veiculo atualizar(String placa, Veiculo dadosAtualizados) {
        Optional<Veiculo> existenteOpt = veiculoRepository.findByPlacaIgnoreCase(placa);
        if (existenteOpt.isEmpty()) {
            throw new IllegalArgumentException("Veículo com placa " + placa + " não encontrado.");
        }

        Veiculo existente = existenteOpt.get();

        if (dadosAtualizados.getPlaca() != null
                && !dadosAtualizados.getPlaca().equalsIgnoreCase(placa)
                && veiculoRepository.existsById(dadosAtualizados.getPlaca())) {
            throw new IllegalArgumentException("Já existe veículo com essa placa.");
        }

        existente.setMarca(dadosAtualizados.getMarca());
        existente.setModelo(dadosAtualizados.getModelo());
        existente.setAno(dadosAtualizados.getAno());
        existente.setPlaca(dadosAtualizados.getPlaca());
        existente.setCor(dadosAtualizados.getCor());

        return veiculoRepository.save(existente);
    }

    public void remover(String placa) {
        if (!veiculoRepository.existsById(placa)) {
            throw new IllegalArgumentException("Veículo com placa " + placa + " não encontrado.");
        }

        veiculoRepository.deleteById(placa);
    }
}
