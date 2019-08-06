package MyThread;

import Calendar.BackupSubmitWindows;
import Calendar.Windows;

public class MyThread implements Runnable {

	private Windows windows;
	
	public MyThread(Windows windows) {
		
		this.windows = windows;
		
	}
	
	@Override
	public void run() {
		
		windows.getFrame().setVisible(true);
		
	}

}
