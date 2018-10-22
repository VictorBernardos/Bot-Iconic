package Luces;
import Server.MQTT;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import MQTT;
public class Luces {	//Sistema que maneja las luces de la asociacion
	//Bueno tenemos que crear la clase Luces. Que nuu tengo ni puta idea de como hacer
	//En realidad no se ni porque creo una clase, para algo que es obvio que es Unico. Una clase es una fabrica, no un objeto...
	int PixelCount; //Vaya caca. Se me olvido que un Byte no tiene mas de 256 posiciones, y que no puedo mandar un int con formato de byte
	IMqttClient Server;

	public Luces(IMqttClient iconic, int PixelCount) {
		this.Server=iconic;
		this.PixelCount=PixelCount;
	}
	
	public boolean SetAll(ColorRGB color) {
		return SetColor(color, 0, PixelCount);
	}
	public boolean SetColor(ColorRGB color, int inicio, int ledfinal) {
		if(color==null) {
			return false;
		}
		else {
			byte[] payload=new byte[7];
			payload[0]=color.getRed();
			payload[1]=color.getGreen();
			payload[2]=color.getBlue();
			payload[3]=(byte) (inicio & 0xFF);	//Cogemos los 8 bit inferiores
			payload[4]=(byte) ((inicio >> 8) & 0xFF);	//Cogemos los 8 bit superiores
			payload[5]=(byte) (ledfinal & 0xFF);
			payload[6]=(byte) ((ledfinal >> 8) & 0xFF);
			return enviar("SetColor",payload);
		}
	}
	public void ON() {
		byte[] payload={49};	//Encendemos la fuente 49 es 1 en ascii
		enviar("Rosita",payload);
	}
	public void OFF() {
		byte[] payload={48};	//Apagamosla fuente 48 es un 0 en ascii	
		enviar("Rosita",payload);
	}
	private boolean enviar(String Comando,byte[] payload) {
		boolean hecho=true;
		System.out.println(Comando);
		MqttMessage Mensaje = new MqttMessage(payload);
	     Mensaje.setQos(0);
	     Mensaje.setRetained(false);
		try {
			Server.publish(Comando, Mensaje);	//Enviamos los datos, primero tema, y luego datos
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hecho=false;
		}
		return hecho;
	}
}
