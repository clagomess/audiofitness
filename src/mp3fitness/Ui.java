package mp3fitness;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ui {
	final static JProgressBar progresso = new JProgressBar(0, 100);
	private String entrada;
	private String saida;
	
	private JFrame getJanela(int w, int h){
		JFrame returnJanela = new JFrame();
		returnJanela.setTitle("Audio Fitness");
		returnJanela.setSize(w,h);
		returnJanela.setResizable(false);
		returnJanela.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		returnJanela.setLocationRelativeTo(null);
		
		return returnJanela;
	}
	
	public void home(){
		JPanel panel = new JPanel();
		JFrame janela = this.getJanela(220, 380);
		janela.addWindowListener( new WindowAdapter( ){
			public void windowClosing(WindowEvent w){	
				System.exit(0);
			}
		});
		
		janela.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		panel.setLayout(new GridLayout(0,1));
		panel.setPreferredSize(new Dimension(200, 250));
		
		panel.add(new JLabel("Arquivo:"));
		final JFileChooser fArquivo = new JFileChooser("");
		JButton bArquivo = new JButton("Selecionar");
		final JLabel lArquivo = new JLabel("--");
		panel.add(lArquivo);
		bArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fArquivo.setFileFilter(new FileNameExtensionFilter(
					"Audio", "mp3", "MP3", "wma", "WMA",
					"wav", "WAV"
				));
				fArquivo.showOpenDialog(null);
				
				entrada = fArquivo.getSelectedFile().getPath();
				lArquivo.setText(fArquivo.getSelectedFile().getName());
			}
		});
		panel.add(bArquivo);
		
		panel.add(new JLabel("Destino:"));
		JButton bPasta = new JButton("Selecionar");
		final JLabel lPasta = new JLabel("--");
		panel.add(lPasta);
		bPasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				JFileChooser fPasta = new JFileChooser();
				fPasta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fPasta.showOpenDialog(null);
				
				saida = fPasta.getSelectedFile().getPath() + "/" +  fArquivo.getSelectedFile().getName();
				lPasta.setText(fPasta.getSelectedFile().getPath());
			}
		});
		panel.add(bPasta);
		
		JButton bConverter= new JButton("Converter");
		bConverter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(entrada == null){
					JOptionPane.showMessageDialog(null, "É necessário selecionar o arquivo!");
					return;
				}
				
				if(saida == null){
					JOptionPane.showMessageDialog(null, "É necessário selecionar a pasta de destino");
					return;
				}
				
				progresso.setValue(0);
				Thread tConverter = new Thread(new Converter(entrada, saida));
				tConverter.start();
			}
		});
		panel.add(bConverter);
		
		progresso.setPreferredSize(new Dimension(200, 20));
		panel.add(progresso);
		janela.add(panel);
		
		String html = "";
		html += "<html>";
		html += "<center>";
		html += "<strong>Audio Fitness</strong><br/>";
		html += "GitHub: clagomess/audiofitness<br/><br/>";
		html += "</center>";
		html += "</html>";
		JLabel lCredito = new JLabel(html);
		lCredito.setFont(new Font("Verdana", Font.PLAIN, 9));
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		panel.add(lCredito);
		
		janela.add(panel);
		janela.setVisible(true);
	}
}
