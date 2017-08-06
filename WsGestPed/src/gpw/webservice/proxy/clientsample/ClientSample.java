package gpw.webservice.proxy.clientsample;

import gpw.webservice.proxy.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        WsGestPed_Service service1 = new WsGestPed_Service();
	        System.out.println("Create Web Service...");
	        WsGestPed port1 = service1.getWsGestPed();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.obtenerPersonasNoSinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.recibirPedidosASinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.servicioFuncional());
	        System.out.println("Server said: " + port1.obtenerPedidosNoSinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.recibirPersonasASinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.recibirProductosASinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        WsGestPed port2 = service1.getWsGestPed();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.obtenerPersonasNoSinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.recibirPedidosASinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.servicioFuncional());
	        System.out.println("Server said: " + port2.obtenerPedidosNoSinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.recibirPersonasASinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.recibirProductosASinc(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
