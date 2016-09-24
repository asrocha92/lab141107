package br.univel.mdb;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import br.univel.classe.Venda;

@MessageDriven(name = "MdboFinanceiro", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/VendasTOPIC"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class MdbFinanceiro implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(MdbFinanceiro.class.toString());

	@Override
	public void onMessage(Message rcvMessage) {
		ObjectMessage obj = null;
		try {

			if(rcvMessage instanceof ObjectMessage){
				obj = (ObjectMessage) rcvMessage;
				Venda venda = (Venda) obj.getObject();
				LOGGER.info("MBD - Financeiro");
				LOGGER.info(venda.toString());
			} else {
				LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}
