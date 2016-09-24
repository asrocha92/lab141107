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

	@Override
	public String toString() {
		String rst = "\nVenda(" +
					 "Códido: " + this.cod_venda + "\n" +
			         "CPF: " + this.cpf_cliente + "\n" +
			         "itens:\n";
		for (int i = 0; i < this.itens.size(); i++) {
			rst = rst + "     - "+ this.itens.get(i) + "\n";
		}

		rst = rst + "Valor Total: " + this.getValor_total().toString() + ");\n";
		return rst;
	}



}
