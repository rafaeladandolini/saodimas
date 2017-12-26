package br.com.saodimas.backup;

import java.io.IOException;

import javax.swing.JOptionPane;

public class AdminBancoDados {

	public void efetuarBackupMySql(){

		String mensagem = "Backup efetuado com sucesso.";
			try {
				Runtime.getRuntime().exec("C:\\SAODIMAS\\backup-saodimas.bat");
			} catch (IOException e) {
				mensagem = "Erro ao efetuar o backup. Informe o administrador do sistema. " + e.getMessage();
				e.printStackTrace();
			}catch (Exception e) {
				mensagem = "Erro ao efetuar o backup. Informe o administrador do sistema. " + e.getMessage();
				e.printStackTrace();
			}finally{
				JOptionPane.showMessageDialog(null, mensagem);
			}
		
	}
}
