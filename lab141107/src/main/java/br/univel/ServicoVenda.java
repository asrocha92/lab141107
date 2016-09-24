package br.univel;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.ejb.EJB;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


import br.univel.EJB.ProcessVenda;
import br.univel.classe.Venda;

@WebService
public class ServicoVenda {

	@EJB
	ProcessVenda processVenda;

	@WebMethod(operationName = "vender")
	@WebResult(name = "statusVenda")
	public String doGet(@WebParam(name = "codigo") int cod,
						@WebParam(name = "cpf")    String cpf) {
		try {
			Venda venda = new Venda();
			venda.setCod_venda(cod);
			venda.setCpf_cliente(cpf);
			List<String> itens = new ArrayList<String>();
			itens.add("Caderno");
			itens.add("Camisa");
			venda.setItens(itens);
			venda.setValor_total(new BigDecimal("200"));

			processVenda.processarVenda(venda);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "Venda Efetuada cm sucesso";
	}
}