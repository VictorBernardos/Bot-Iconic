package Server;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
public class MQTT{
	private String ID_MQTT;
	private String IP;
	public static IMqttClient Server;//Creamos el objeto, y luego lo construimos... esas clases de java a las que nunca fuiste... se te ha olvidado todo xD
	//constructor
	public MQTT(String ID, String IP) throws MqttException {	//Solo creamos un cliente, y se crea desde el main
		this.ID_MQTT=ID;
		this.IP= IP;
		Server = new MqttClient("tcp://"+IP+":1883",ID_MQTT,new MemoryPersistence());
		Server.connect();	//192.168.1.99:1883 
		byte[] payload={111,110,108,105,110,101};
		MqttMessage Mensaje = new MqttMessage(payload);
	    Mensaje.setQos(0);
	    Mensaje.setRetained(true);
		Server.publish("Bot", Mensaje);          ////porque lo envias en esta linea y no en la 25???
	//Para testear: "tcp://iot.eclipse.org:1883"
	}
	public boolean publicar(String tema, byte[] Informacion) {  ///Si no usas esta funcion lukas para que la codificas
		//int longitud = Informacion.length;	//Adquirimos la longitud   
		//Server.publish(arg0, arg1);                            //Lo dejo comentado 
		return true;
	}

}
//El famoso basurero de codigo
/*IMqttClient Iconic;
Iconic = new MqttClient("tcp://192.168.1.99:1883","Iconic",new MemoryPersistence());
Iconic.connect();
byte[] payload=new byte[10];
payload[0]=111;
payload[1]=110;
payload[2]=108;
payload[3]=105;
payload[4]=110;
payload[5]=101;
MqttMessage Mensaje = new MqttMessage(payload);
Mensaje.setQos(0);
Mensaje.setRetained(true);
Iconic.publish("Bot", Mensaje);*/
