package br.com.banco.repositorys;

import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrasnferenciaRepository extends JpaRepository<Transferencia, Long> {

}
