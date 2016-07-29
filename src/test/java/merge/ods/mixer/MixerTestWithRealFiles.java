package merge.ods.mixer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.junit.Test;

import merge.ods.mixer.strategy.SimpleMixerStrategy;

public class MixerTestWithRealFiles {

	@Test
	public void test() throws IOException{
		InputStream stream = System.in;
		Scanner scanner = new Scanner(stream);
		
		try {

			Mixer mixer = new Mixer(new SimpleMixerStrategy());
			SpreadSheet result = mixer.mixFiles();
			result.saveAs(new File(mixer.getMixerStrategy().getProcessData().getMixerConfiguration().getResultFileName()));
			
			System.out.println("La mezcla ha sido exitosa\n"
					+ "Su archivo resultado fué generado con el nombre de "+mixer.getMixerStrategy().getProcessData().getMixerConfiguration().getResultFileName()+"\n"
					+ "Lo encontrará en el mismo directorio que los demas archivos");
		
		}
		catch(Exception e) {
			
			System.out.println("La ejecución no finalizó correctamente");
			
			e.printStackTrace();
		}
		finally {
			
//			System.out.println("Presione Enter para finalizar");
//			scanner.nextLine();
//			scanner.close();
//			System.exit(0);
		}
	}

	
}
