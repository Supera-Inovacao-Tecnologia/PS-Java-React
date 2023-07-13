package br.com.banco.entites;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity()
@Table(name = "transferencia")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data_transferencia", nullable = false)
	private OffsetDateTime transferDate;

	@Column(name = "valor", nullable = false, precision = 20, scale = 2)
	private Double value;

	@Column(name = "tipo", nullable = false, length = 15)
	private String type;

	@Column(name = "nome_operador_transacao", nullable = false, length = 50)
	private String transactionOperatorName;
}
