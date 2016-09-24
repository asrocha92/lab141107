package br.univel.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import br.univel.classe.Venda;

@MessageDriven(name = "MdbContabilidade", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/VendasTOPIC"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MdbContabilidade implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(MdbContabilidade.class.toString());

	@Override
	public void onMessage(Message rcvMessage) {
		ObjectMessage obj = null;
		try {

			if(rcvMessage instanceof ObjectMessage){
				obj = (ObjectMessage) rcvMessage;
				Venda venda = (Venda) obj.getObject();
				LOGGER.info("MBD - Contabilidade");
				LOGGER.info(venda.toString());
			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
