package br.univel.classe;

import java.math.BigDecimal;
import java.util.List;

public class Entrega {
	
	private int cod_entrega;
	private List<String> itens;
	private BigDecimal valorTotalMercadoria;
	private String endereco;
	private boolean entregue;
	private String data_saida;
	private String data_entregue;

	public Entrega() {
		// TODO Auto-generated constructor stub
	}

	public int getCod_entrega() {
		return cod_entrega;
	}

	public void setCod_entrega(int cod_entrega) {
		this.cod_entrega = cod_entrega;
	}

	public List<String> getItens() {
		return itens;
	}

	public void setItens(List<String> itens) {
		this.itens = itens;
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

	public boolean isEntregue() {
		return entregue;
	}

	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}

	public String getData_saida() {
		return data_saida;
	}

	public void setData_saida(String data_saida) {
		this.data_saida = data_saida;
	}

	public String getData_entregue() {
		return data_entregue;
	}

	public void setData_entregue(String data_entregue) {
		this.data_entregue = data_entregue;
	}
	
}
