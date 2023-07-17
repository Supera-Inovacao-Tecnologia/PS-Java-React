package br.com.banco.repository;


import br.com.banco.domain.entity.Transferencia;
import br.com.banco.domain.filter.ExtratoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransferenciaCustomRepository {

    private final EntityManager entityManager;


    public List<Transferencia> buscarTransferenciasPorConta(Long idConta, ExtratoFilter filtro){
        String jpql = "SELECT T FROM Transferencia as T WHERE T.conta.idConta = :idConta";


        if (filtro.getDataInicio() != null && filtro.getDataFim() != null) {
            jpql += " AND T.dataTransferencia BETWEEN :dataInicio AND :dataFim ";
        } else if (filtro.getDataInicio() != null) {
            jpql += " AND T.dataTransferencia >= :dataInicio ";
        } else if (filtro.getDataFim() != null) {
            jpql += " AND T.dataTransferencia <= :dataFim ";
        }

        if (filtro.getOperadorTransacionado() != null) {
            jpql += " AND T.nomeOperadorTransacao = :operadorTransacao ";
        }


        TypedQuery<Transferencia> query =  entityManager.createQuery(jpql, Transferencia.class);

        query.setParameter("idConta", idConta);

        if (filtro.getDataInicio() != null) {
            query.setParameter("dataInicio", filtro.getDataInicio());
        }

        if (filtro.getDataFim() != null) {
            query.setParameter("dataFim", filtro.getDataFim());
        }

        if (filtro.getDataInicio() != null) {
            query.setParameter("operadorTransacao", filtro.getOperadorTransacionado());
        }


        return query.getResultList();

    }

    public Double buscarSaldoTotalPorConta(Long idConta){
        String jpql = "SELECT SUM(T.valor) FROM Transferencia as T WHERE T.conta.idConta = :idConta";
        return entityManager.createQuery(jpql, Double.class).setParameter("idConta", idConta).getSingleResult();
    }

}
