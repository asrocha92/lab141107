package br.univel.classe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Entrega implements Serializable{

	private int cod_entrega;
	private String endereco;
	private BigDecimal valorTotalMercadoria;
	private boolean status;
	private String data_entregue;


	public Entrega() {
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getCod_entrega() {
		return cod_entrega;
	}

	public void setCod_entrega(int cod_entrega) {
		this.cod_entrega = cod_entrega;
	}

	public BigDecimal getValorTotalMercadoria() {
		return valorTotalMercadoria;
	}

	public void setValorTotalMercadoria(BigDecimal valorTotalMercadoria) {
		this.valorTotalMercadoria = valorTotalMercadoria;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getData_entregue() {
		return data_entregue;
	}

	public void setData_entregue(String data_entregue) {
		this.data_entregue = data_entregue;
	}

	@Override
	public String toString() {
		return "\nEntrega(\n"
				+ "cod:" + this.cod_entrega + "\n"
				+ "Data Entrega: " + this.data_entregue + "\n"
			    + "Endereço: " + this.endereco + "\n"
			    + "Status: " + this.status + "\n"
			    + "Valor: " + this.valorTotalMercadoria.toString() + ");";
	}


}
