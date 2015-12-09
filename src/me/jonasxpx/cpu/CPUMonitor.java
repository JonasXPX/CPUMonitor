package me.jonasxpx.cpu;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import oshi.hardware.CentralProcessor;
import oshi.hardware.platform.linux.LinuxCentralProcessor;



public class CPUMonitor{

	
	 public static void main(String[] args) {
		 CentralProcessor sp = new LinuxCentralProcessor();
		 for(;;){
			try {
				System.out.println(sp.getSystemCpuLoad());
				if((sp.getSystemCpuLoad() * 100) >= Integer.parseInt(args[2])){
				 	sendEmail(args[0], args[1], (sp.getSystemCpuLoad() * 100));
			 	}
				Thread.sleep(1000 * 60);
			} catch (EmailException | InterruptedException e1) {
				e1.printStackTrace();
			}
		 }
	 }
	
	 

	 public static void sendEmail(String user, String passw, double use) throws EmailException{
		 HtmlEmail email = new HtmlEmail();
		 email.setAuthentication(user, passw);
		 email.setHostName("mail.endcraft.com.br");
		 email.setSmtpPort(587);
		 email.addTo("endcraftpvp@hotmail.com");
		 email.setFrom("recrutamento@endcraft.com.br");
		 email.setSubject("CPU WARN!!");
		 email.setMsg("Aviso!!<br/><p>Uso do CPU acima do normal! User:  <strong>"+use + "%</strong></p>");
		 email.send();
	 }
}


