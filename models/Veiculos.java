package com.autolatina.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "veiculos")
public class Veiculos {
	
	@Column(name = "marca")
	private String marca;
	@Column(name = "modelo")
	private String modelo;
	@Column(name = "motor")
	private String motor;
	@Column(name = "ano")
	private int ano;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "chassi")
	private long chassi;
	
	private long propIden;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public long getChassi() {
		return chassi;
	}

	public void setChassi(long chassi) {
		this.chassi = chassi;
	}

	public long getPropIden() {
		return propIden;
	}

	public void setPropIden(long propIden) {
		this.propIden = propIden;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, chassi, marca, modelo, motor, propIden);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculos other = (Veiculos) obj;
		return ano == other.ano && chassi == other.chassi && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(motor, other.motor)
				&& propIden == other.propIden;
	}

	@Override
	public String toString() {
		return "Veiculos [marca=" + marca + ", modelo=" + modelo + ", motor=" + motor + ", ano=" + ano + ", chassi="
				+ chassi + ", propIden=" + propIden + "]";
	}
	
}
