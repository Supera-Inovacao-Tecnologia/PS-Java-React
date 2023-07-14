package br.com.banco.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long id;
	
	private Instant  dataTransferencia;
	private double valor;
	private String Tipo;
	private String nomeOperadorTransacao;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CONTA_ID")
	private Conta contaBancaria;
		
	public Transferencia() {
		
	}
	
	

	public Transferencia(long id, Instant dataTransferencia, double valor, String tipo, String nomeOperadorTransacao,
			Conta contaBancaria) {
		super();
		this.id = id;
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		Tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.contaBancaria = contaBancaria;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getData_transferencia() {
		return dataTransferencia;
	}

	public void setData_transferencia(Instant dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getNome_operador() {
		return nomeOperadorTransacao;
	}

	public void setNome_operador(String nomeOperadorTransacao) {
		this.nomeOperadorTransacao = nomeOperadorTransacao;
	}
	
	
	

	public Conta getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(Conta contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		return id == other.id;
	}
	
	

}
