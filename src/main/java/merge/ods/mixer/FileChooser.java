package merge.ods.mixer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.jopendocument.dom.spreadsheet.Table;

public class FileChooser extends JFileChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3471631501989332189L;
	
	private JFrame mainWindow;
	
	public FileChooser(JFrame mainWindow){
		this.setMainWindow(mainWindow);
	}

	public JFrame getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(JFrame mainWindow) {
		this.mainWindow = mainWindow;
	}

	public static void main(String[] args) {
		System.out.println(Table.toStr(27));
	}
}
