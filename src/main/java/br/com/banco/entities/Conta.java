package br.com.banco.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Conta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private long idConta;
	
	private String nomeResponsavel;
	
	
	@OneToMany(mappedBy="contaBancaria")
	private List<Transferencia> transferencias = new ArrayList<>();
	
	public Conta() {
		
	}

	public Conta(long idConta, String nomeResponsavel) {
		super();
		this.idConta = idConta;
		this.nomeResponsavel = nomeResponsavel;
	}

	public long getId() {
		return idConta;
	}

	public void  setId(long idConta) {
		this.idConta = idConta;
	}

	public String getnomeResponsavel() {
		return nomeResponsavel;
	}

	public void  setnomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}
	
	

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(List<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeResponsavel, idConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		return Objects.equals(nomeResponsavel, other.nomeResponsavel) && idConta == other.idConta;
	}
	
	

}
