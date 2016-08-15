package br.univel.mdb;


import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


@MessageDriven(name = "MdbContabilidade", activationConfig = {
	    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/TOPICVenda"),
	    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
	    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
	    })
public class MdbContabilidade implements MessageListener{
	
	private final static Logger LOGGER = Logger.getLogger(MdbContabilidade.class.toString());
	@Override
	public void onMessage(Message rcvMessage) {
		ObjectMessage msg = null;		
		try {
			msg = (ObjectMessage) rcvMessage;
			LOGGER.warning(msg.getObject().toString());
			LOGGER.warning("MdbContabilidade");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
