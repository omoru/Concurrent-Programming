// OSCAR MORUJO FERNANDEZ
package multibufferV2;

import multibufferV1.Producto;

public class Consumer extends Thread{
		private MonitorMB_v2 m;
		@SuppressWarnings("unused")
		private int id;
		private int tam;
		
		public Consumer(MonitorMB_v2 m, int id,int tam) {
			this.m=m;
			this.id=id;
			this.tam= tam;
			}
		
		public void run() {
			while(true) {
				int numero = (int) (Math.random() * (this.tam/2)) + 1;
					try {
						@SuppressWarnings("unused")
						Producto[] extraido =this.m.extraer(numero);
						//usar extraido
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


