package br.com.locadora.controller;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.locadora.view.LoginGUI;

public class MainSistemaLocadora {
	
	public static void main(String[] args) {
		// Define o look in feel Nimbus para aplicação
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Garante que a lista de login está ordernada
		Autenticacao autenticacao = new Autenticacao();
		autenticacao.ordenarListaLogins();
		
//		System.out.println(autenticacao.getQuantidadeUsuarios());
//		autenticacao.criptografar();
		new LoginGUI();
	}	
}
