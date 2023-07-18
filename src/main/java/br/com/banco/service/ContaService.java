package br.com.banco.service;

import br.com.banco.domain.dto.ExtratoDTO;
import br.com.banco.domain.dto.TransferenciaDTO;
import br.com.banco.domain.entity.Conta;
import br.com.banco.domain.entity.Transferencia;
import br.com.banco.domain.filter.ExtratoFilter;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaCustomRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final TransferenciaCustomRepository transferenciaCustomRepository;
    private final ContaRepository contaRepository;
    public ExtratoDTO emitirExtratoBancario(Long idConta, ExtratoFilter filtro){
        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new ObjectNotFoundException(idConta, "Conta nao encontrada."));

        List<Transferencia> transferencias = transferenciaCustomRepository.buscarTransferenciasPorConta(conta.getIdConta(), filtro);

        ExtratoDTO extrato = new ExtratoDTO();

        extrato.setSaldoTotal(transferenciaCustomRepository.buscarSaldoTotalPorConta(conta.getIdConta()));
        extrato.setSaldoPeriodo(calcularSaldoPeriodo(transferencias));
        extrato.setTransferencias(transferencias.stream().map(TransferenciaDTO::toDTO).collect(Collectors.toList()));
        return extrato;

    }


    private Double calcularSaldoPeriodo(List<Transferencia> transferencias){
        return transferencias.stream().mapToDouble(
                Transferencia::getValor).sum();
    }
}
