package br.com.banco.services;

import br.com.banco.model.QTransferencia;
import br.com.banco.model.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;


    public Page<Transferencia> findPage(Integer page,
                                        Integer linesPerPage,
                                        String orderBy,
                                        String direction,
                                        String filterDataInicio,
                                        String filterDataFim,
                                        String filterNomeOperadorTransacao) {

        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, sort);

        QTransferencia qTransferencia = QTransferencia.transferencia;
        BooleanBuilder filter = new BooleanBuilder();

        if(!Objects.equals(filterDataInicio, "")) {
            filter.and(qTransferencia.dataTransferencia.gt(LocalDateTime.parse(filterDataInicio)));
        }

        if(!Objects.equals(filterDataFim, "")) {
            filter.and(qTransferencia.dataTransferencia.lt(LocalDateTime.parse(filterDataFim)));
        }

        if(!Objects.equals(filterNomeOperadorTransacao, "")) {
            filter.and(qTransferencia.nomeOperadorTransacao.like(filterNomeOperadorTransacao));
        }

        return transferenciaRepository.findAll(filter,pageRequest);
    }

}
