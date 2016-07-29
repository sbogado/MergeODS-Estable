package merge.ods.mixer;

import java.io.IOException;

import org.jopendocument.dom.spreadsheet.SpreadSheet;

import merge.ods.mixer.strategy.MixerStrategy;

public class Mixer {
	
	private MixerStrategy mixerStrategy;
	
	public Mixer(MixerStrategy strategy){
		this.setMixerStrategy(strategy);
	}
	
	public SpreadSheet mixFiles() throws IOException {
		return mixerStrategy.mixFiles();
	}

	public MixerStrategy getMixerStrategy() {
		return mixerStrategy;
	}

	public void setMixerStrategy(MixerStrategy mixerStrategy) {
		this.mixerStrategy = mixerStrategy;
	}

}
