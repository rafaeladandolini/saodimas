package br.com.saodimas.view.util;


import br.com.saodimas.view.components.frame.FramePrincipal;

public class WinManager{
	public static boolean erroIniciar = false; 
	private static FramePrincipal janelaInicial;

	public static FramePrincipal getJanelaInicial() {
		return janelaInicial;
		
	}

	public static void iniciar(){
		janelaInicial = new FramePrincipal();

		FrameTools.defaultSize(janelaInicial);
		FrameTools.centralize(janelaInicial);
		
		janelaInicial.mostrarTelaLogin(true);
	}
}
