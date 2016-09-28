package br.univel.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.univel.model.produto;

@Stateless
public class ProcessProduto {

	@PersistenceContext
	private EntityManager entityManager;

	public produto salvar(produto produto) {
		if (produto.getId() == null){
			entityManager.persist(produto);
		} else {
			produto = entityManager.merge(produto);
		}
		return produto;
	}

	public String excluir(Long id) {
		produto produto = entityManager.find(produto.class, id);
		entityManager.remove(produto);

		return "Produto deletado com sucesso.";
	}


	public produto consultarPorId(Long id) {
		produto produto = entityManager.find(produto.class, id);
		return produto;
	}
}