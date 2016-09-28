package br.univel;


import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.univel.EJB.ProcessProduto;
import br.univel.model.produto;


@WebService
public class ServicoProduto {

	@EJB
	ProcessProduto processProduto;

	@WebMethod(operationName = "metododoCadProd")
	@WebResult(name = "resCadProd")
	public produto create(@WebParam(name = "codBarras") String codBarras,
						  @WebParam(name = "descricao") String descricao,
						  @WebParam(name = "valor") double valor) throws Exception {

		produto produto = new produto();
		produto.setCodBarras(codBarras);
		produto.setDescricao(descricao);
		produto.setValor(valor);

		return processProduto.salvar(produto);
	}

	@WebMethod(operationName = "metodoDeleteProd")
	@WebResult(name = "resDeleteProd")
	public String delete(@WebParam(name = "idProduto") Long id) {
		return processProduto.excluir(id);
	}

	@WebMethod(operationName = "metodoLerProduto")
	@WebResult(name = "WRLerProduto")
	public produto read(@WebParam(name = "idProduto") Long id) {
		return processProduto.consultarPorId(id);
	}

	@WebMethod(operationName = "metodoUpdateProd")
	@WebResult(name = "resUpdateProd")
	public produto update(@WebParam(name = "idProduto") Long id,
						  @WebParam(name = "codBarras") String codBarras,
						  @WebParam(name = "descricao") String descricao,
						  @WebParam(name = "valor") double valor) throws Exception {

		produto produto = new produto();
		produto.setId(id);
		produto.setCodBarras(codBarras);
		produto.setDescricao(descricao);
		produto.setValor(valor);

		return processProduto.salvar(produto);
	}

}
