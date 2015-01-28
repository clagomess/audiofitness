package mp3fitness;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;

import java.io.File;

import javax.swing.JOptionPane;

public class Converter implements Runnable {
	private String entrada;
	private String saida;
	
	public Converter(String entrada, String saida){
		this.entrada = entrada;
		this.saida = saida + ".amr";
	}
	
	@Override
	public void run() {
		System.out.println(entrada);
		System.out.println(saida);
		
		Encoder encoder = new Encoder();
		EncodingAttributes attributes = new EncodingAttributes();
	    attributes.setFormat("amr");
	    AudioAttributes audio = new AudioAttributes();
	    audio.setBitRate(new Integer(12200));
	    audio.setSamplingRate(new Integer(8000));
	    audio.setChannels(new Integer(1));
	    attributes.setAudioAttributes(audio);

	    File source = new File(entrada);
	    File target = new File(saida);
	    
	    EncoderProgressListener listener = new EncoderProgressListener();
	    
	    try {
	        encoder.encode(source, target, attributes, listener);
	        JOptionPane.showMessageDialog(null, "Sucesso!");
	        Ui.progresso.setValue(0);       
	    } catch (Exception e) {
	    	JOptionPane.showMessageDialog(null, "Deu ruim! =(");
	    	System.out.print(e.getMessage());
	    }
	}
}
