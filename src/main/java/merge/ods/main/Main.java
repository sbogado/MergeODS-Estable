package merge.ods.main;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.mixer.Mixer;
import merge.ods.mixer.strategy.SimpleMixerStrategy;

public class Main {

	
	public static void main(String[] args) {
		
		try {

			Mixer mixer = new Mixer(new SimpleMixerStrategy());
			SpreadSheet result = mixer.mixFiles();
			result.saveAs(new File(mixer.getMixerStrategy().getProcessData().getMixerConfiguration().getResultFileName()));
			
			System.out.println("La mezcla ha sido exitosa\n"
					+ "Su archivo resultado fué generado con el nombre de "+ mixer.getMixerStrategy().getProcessData().getMixerConfiguration().getResultFileName()+"\n");
		
		}
		catch(Exception e) {
			
			System.out.println("La ejecución no finalizó correctamente");
			
			e.printStackTrace();
		}
	}

}
