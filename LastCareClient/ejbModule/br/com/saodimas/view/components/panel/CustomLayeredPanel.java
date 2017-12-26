package br.com.saodimas.view.components.panel;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.saodimas.view.components.iframe.LogonIFrame;
import br.com.saodimas.view.components.iframe.ManutencaoContaIFrame;
import br.com.saodimas.view.components.iframe.PreferenciasIFrame;
import br.com.saodimas.view.components.iframe.SobreIFrame;
import br.com.saodimas.view.util.FrameTools;
import br.com.saodimas.view.util.WinManager;


@SuppressWarnings("serial")
public class CustomLayeredPanel extends JLayeredPane{
	private GlassPanel glass;
	private CustomDesktop desktop;
	private JPanel painelPrincipal;
	
	private SobreIFrame iframeSobre;
	private LogonIFrame iframeLogon;
	private ManutencaoContaIFrame iframeManutencaoConta;
	private PreferenciasIFrame iframePrefs;
	
	public CustomLayeredPanel(){
		initialize();
		configure();
	}

	public SobreIFrame getIframeSobre() {
		return iframeSobre;
	}

	public void setIframeSobre(SobreIFrame iframeSobre) {
		this.iframeSobre = iframeSobre;
	}

	public LogonIFrame getIframeLogon() {
		return iframeLogon;
	}

	public void setIframeLogon(LogonIFrame iframeLogon) {
		this.iframeLogon = iframeLogon;
	}

	public ManutencaoContaIFrame getIframeManutencaoConta() {
		return iframeManutencaoConta;
	}

	public void setIframeManutencaoConta(ManutencaoContaIFrame iframeManutencaoConta) {
		this.iframeManutencaoConta = iframeManutencaoConta;
	}

	public PreferenciasIFrame getIframePrefs() {
		return iframePrefs;
	}

	public void setIframePrefs(PreferenciasIFrame iframePrefs) {
		this.iframePrefs = iframePrefs;
	}

	public GlassPanel getGlass() {
		return glass;
	}

	public void setGlass(GlassPanel glass) {
		this.glass = glass;
	}

	public CustomDesktop getDesktop() {
		return desktop;
	}

	public void setDesktop(CustomDesktop desktop) {
		this.desktop = desktop;
	}

	public JPanel getPainelPrincipal() {
		return painelPrincipal;
	}

	public void setPainelPrincipal(JPanel painelPrincipal) {
		this.painelPrincipal = painelPrincipal;
	}
	
	public int mostraConfirmacao(String titulo, String mensagem, int tipo){
		int opcao = Integer.MIN_VALUE;
		glass.setVisible(true);
		opcao = JOptionPane.showConfirmDialog(WinManager.getJanelaInicial(), mensagem, titulo, tipo);
		glass.setVisible(false);
		return opcao;
	}
	
	public int mostraAviso(String titulo, String mensagem, int tipo){
		int opcao = Integer.MIN_VALUE;
		glass.setVisible(true);
		opcao = JOptionPane.showConfirmDialog(WinManager.getJanelaInicial(), mensagem, titulo, JOptionPane.WARNING_MESSAGE , tipo);
		glass.setVisible(false);
		return opcao;
	}
	
	private final void initialize(){
		glass = new GlassPanel();
		desktop = new CustomDesktop();

		int frameWidth = FrameTools.getDefaultDimension().width;
		int frameHeight = FrameTools.getDefaultDimension().height;
		
		painelPrincipal = new JPanel(new BorderLayout());
		painelPrincipal.setLocation(new Point(0, 0));
		painelPrincipal.setSize(frameWidth - 7, frameHeight - 50);
		
		int x = desktop.getWidth() / 2;
		int y = desktop.getHeight() / 2;
		
		iframeManutencaoConta = new ManutencaoContaIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (glass != null) glass.setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeManutencaoConta.setLocation(x - iframeManutencaoConta.getSize().width / 2, y - iframeManutencaoConta.getSize().height / 2);
		
		iframeLogon = new LogonIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (glass != null) glass.setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeLogon.setLocation(x - iframeLogon.getSize().width / 2, y - iframeLogon.getSize().height / 2);
		
		iframePrefs = new PreferenciasIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (glass != null) glass.setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframePrefs.setLocation(x - iframePrefs.getSize().width / 2, y - iframePrefs.getSize().height / 2);
		
		iframeSobre = new SobreIFrame(){
			@Override
			public void setVisible(boolean flag) {
				if (glass != null) glass.setVisible(flag);
				super.setVisible(flag);
			}
		};
		iframeSobre.setLocation(x - iframeSobre.getSize().width / 2, y - iframeSobre.getSize().height / 2);
		
		desktop.add(iframeManutencaoConta);
		desktop.add(iframeLogon);
		desktop.add(iframePrefs);
		desktop.add(iframeSobre);
	}
	
	private final void configure(){
		add(painelPrincipal, JLayeredPane.DEFAULT_LAYER, 0);
		add(glass, JLayeredPane.MODAL_LAYER, 1);
		add(desktop, JLayeredPane.DRAG_LAYER, 2);
		setOpaque(false);
	}
}
