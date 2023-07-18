package br.com.banco.domain.dto;


import br.com.banco.domain.entity.Transferencia;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class TransferenciaDTO {
    private Long id;
    private String dataTransferencia;
    private Double valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private Long idConta;



    public static TransferenciaDTO toDTO(Transferencia transferencia){
          TransferenciaDTO dto = new TransferenciaDTO();
          dto.setId(transferencia.getId());
          dto.setDataTransferencia(transferencia.getDataTransferencia().toLocalDate().toString());
          dto.setValor(transferencia.getValor());
          dto.setTipo(transferencia.getTipo());
          dto.setNomeOperadorTransacao(transferencia.getNomeOperadorTransacao());
          dto.setIdConta(transferencia.getConta().getIdConta());

          return dto;
    }
}
