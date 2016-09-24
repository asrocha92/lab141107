package br.univel.EJB;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

import br.univel.classe.Venda;

/**
 *
 * @author Alex Santos
 *
 */


@Stateless
public class ProcessVenda {

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/topic/VendasTOPIC")
	private Topic topic;

	public void processarVenda(Venda venda) {
		System.out.println("Processo Venda");
		try {

			final Destination destination = topic;

			ObjectMessage obj = context.createObjectMessage();
			obj.setObject(venda);

			context.createProducer().send(destination, obj);

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}