package org.nantic.ole.impl;

import org.nantic.ole.IUserListener;
import org.nantic.ole.UserEvent;

public class ConsoleUserListener implements IUserListener {

	@Override
	public void performedUserEvent(UserEvent studentEvent) {
		System.out.println(String.format("%s, %s, %s", studentEvent.getEventStatus(), studentEvent.getUsers().getUsername(), studentEvent.getUsers().getId()));
//		File fout = new File("D:\\RAF\\test.txt");
//		FileOutputStream fos;
//		try {
//			fos = new FileOutputStream(fout);
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
//			 
//				bw.write(String.format("%s, %s, %s", studentEvent.getEventStatus(), studentEvent.getStudenti().getStdIme(), studentEvent.getStudenti().getStdPrezime()));
//				bw.newLine();
//		 
//			bw.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	 
	
	}

}
