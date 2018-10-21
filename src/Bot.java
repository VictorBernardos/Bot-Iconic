import Luces.*;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.ChatMember;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import Luces.Luces;
import Luces.ColorRGB;
public class Bot extends TelegramLongPollingBot  {
	IMqttClient Server;
	Luces TiradeLuz;
	private String token;
	public Bot(IMqttClient iconic, String token,Luces TiradeLuz) {
		super();	//Tenemos que formarlo arriba, no aqui abajo, pero esto lo necesitamos para pasar los parametros del servidor
		this.Server=iconic;
		this.token=token;
		this.TiradeLuz=TiradeLuz;
	}
	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "Interconnectedtoolsbot";
	}

	@Override
	public void onUpdateReceived(Update update) {

	    // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        // Set variables
	    	String mensaje=update.getMessage().getText();
	    	if(mensaje.equals("/puerta")) {
	    		//A ver, si son iguales, simplemente manda un OPEN 0, por MQTT, y borra el mensaje de /puerta
	    		byte[] payload={0};
	    		MqttMessage Mensaje = new MqttMessage(payload);
	    	    Mensaje.setQos(0);
	    	    Mensaje.setRetained(true);
	    		try {
					Server.publish("OPEN", Mensaje);
				} catch (MqttException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	else if(mensaje.startsWith("/setcolor")) {	//Esto se hara en forma de juego en html5
	    		char[] letra=null;
	    		ColorRGB color=new ColorRGB(60,0,130);
	    		for(int h=9;h<mensaje.length();h++) {	//miramos letra a letra, y los convertimos
	    			System.out.println(mensaje.charAt(h));
	    			//mensaje.
	    		}
	    		//String MensajeCortado=mensaje.substring(9,12);
	    		//System.out.println(Byte.valueOf(MensajeCortado));
	    		//No se porque coño se cambio el codigo, pero esto no esta bien para nada
	    		TiradeLuz.ON();	//Iniciamos la fuente
	    		TiradeLuz.SetColor(color,0,633);	//Y ponemos el color. Por ahora solo sera toda la tira. Evolucionara.
	    	}
	        int ID=update.getMessage().getFrom().getId();
	        String nombre=update.getMessage().getFrom().getUserName();
	        long chat_id = update.getMessage().getChatId();
	        String message_text ="El usuario " + nombre +" con ID: "+ ID +" ha dicho "+ update.getMessage().getText();
	        SendMessage message = new SendMessage() // Create a message object object
	                .setChatId(chat_id)
	                .setText(message_text);
	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }

	    }
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return token;
	}
	
	

}
