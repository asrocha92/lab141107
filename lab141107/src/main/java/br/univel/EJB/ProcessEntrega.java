package br.univel.EJB;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import br.univel.classe.Entrega;

/**
 *
 * @author Alex Santos
 *
 */



@Stateless
public class ProcessEntrega {

	@Inject
	private JMSContext context;

	@Resource(lookup = "java:/queue/PedidosQUEUE")
	private Queue queue;

	public void processarEntrega(Entrega entrega) {
		System.out.println("Processo entrega");
		try {
            final Destination destination = queue;

			ObjectMessage obj = context.createObjectMessage();
			obj.setObject(entrega);

			context.createProducer().send(destination, obj);

		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}