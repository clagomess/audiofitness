package mp3fitness;

public class EncoderProgressListener implements it.sauronsoftware.jave.EncoderProgressListener {
	public void sourceInfo(it.sauronsoftware.jave.MultimediaInfo info){
		
	}
	
	public void progress(int permil){
		int percent = (int) Math.ceil(permil / 10);
		Ui.progresso.setValue(percent);
	}
	
	public void message(java.lang.String message){
		
	}
}
