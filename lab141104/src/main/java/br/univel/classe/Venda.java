package br.univel.classe;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Alex Santos Rocha
 *
 */

public class Venda implements Serializable{
	
	private int cod_venda;
	private String cpf_cliente;
	private List<String> itens;
	private BigDecimal valor_total;

	public Venda() {
		// TODO Auto-generated constructor stub
	}

	public int getCod_venda() {
		return cod_venda;
	}

	public void setCod_venda(int cod_venda) {
		this.cod_venda = cod_venda;
	}

	public String getCpf_cliente() {
		return cpf_cliente;
	}

	public void setCpf_cliente(String cpf_cliente) {
		this.cpf_cliente = cpf_cliente;
	}

	public List<String> getItens() {
		return itens;
	}

	public void setItens(List<String> itens) {
		this.itens = itens;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

}
