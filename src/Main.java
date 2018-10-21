import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import Luces.Luces;
//import Server.MQTT;
public class Main {
	public static IMqttClient Iconic;
	public static void main(String[] args) throws MqttException{
		//Argumentos necesarios: Token, ID, IP, UserMQTT, KeyMQTT. Estos 5 datos, son privados
		leerficheros Archivo1= new leerficheros("data-and-settings.txt");
		String token=Archivo1.devolver("Token"); 	//Buscamos linea por linea, el parametro que queremos
		String ID=Archivo1.devolver("ID"); 
		String IP=Archivo1.devolver("IP"); 
		String UserMQTT=Archivo1.devolver("UserMQTT");
		String KeyMQTT=Archivo1.devolver("KeyMQTT");
		Iconic=new MqttClient(IP,ID,new MemoryPersistence());//192.168.1.99 //10.0.60.16
		MqttConnectOptions options = new MqttConnectOptions();
		options.setUserName(UserMQTT);
		options.setPassword(KeyMQTT.toCharArray());	//probemos si esto funciona
		Iconic.connect(options);	//nos conectamos a la red MQTT
		Mensaje();
		Luces tiradeLuces =new Luces(Iconic,633);	//Objeto a pasar MQTT, y Numero de leds disponibles
		tiradeLuces.ON();
		ApiContextInitializer.init();
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		Bot bot = new Bot(Iconic, token, tiradeLuces);	//le pasamos el argumento del servidor mqtt... la pregunta es si sabra usarlo
		try {
			telegramBotsApi.registerBot(bot);
		}
		catch(TelegramApiRequestException e){
			e.printStackTrace();
		}
	}
	static void Mensaje() throws MqttPersistenceException, MqttException {
		byte[] payload={111,110,108,105,110,101};	//Manda "online"
		MqttMessage Mensaje = new MqttMessage(payload);
	    Mensaje.setQos(0);
	    Mensaje.setRetained(true);
		Iconic.publish("Bot", Mensaje);
	}
}
