package com.autolatina.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proprietarios")
public class Proprietarios {
	
	
	@Column(name = "nome")
	private String nome;
	@Column(name = "email")
	private String email;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "identificacao", nullable = false)
	private long ident;
	

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getIdent() {
		return ident;
	}

	public void setIdent(long ident) {
		this.ident = ident;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, ident, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proprietarios other = (Proprietarios) obj;
		return Objects.equals(email, other.email) && Objects.equals(ident, other.ident)
				&& Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Proprietarios [nome=" + nome + ", email=" + email + ", ident=" + ident + "]";
	}

	

	
}
