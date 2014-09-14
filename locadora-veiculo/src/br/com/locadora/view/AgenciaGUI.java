package br.com.locadora.view;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.locadora.utils.locale.LocaleUtils;
import br.com.locadora.view.componentes.BotoesCrudComponente;
import br.com.locadora.view.componentes.FormularioEnderecoComponente;

public class AgenciaGUI extends JPanel implements Serializable{
	private static final long serialVersionUID = 48295538249135551L;
	
	private JLabel lblRazoSocial;
	private JTextField txtRazaoSocial;
	private JLabel lblFantasia;
	private JTextField txtFantasia;
	private JLabel lblCnpj;
	private JTextField txtCnpj;
	private JLabel lblInscEstadual;
	private JTextField txtIncEstadual;
	private JLabel lblResponsavel;
	private JTextField txtResponsavel;
	
	private String tituloTela;
	
	public AgenciaGUI() {
		inicializar();
	}
	
	/**
	 * Cria o panel agência com um titulo
	 * @param tituloPanel Titulo do panel 
	 * @author Joaquim Neto
	 */
	public AgenciaGUI(String tituloTela) {
		this.tituloTela = tituloTela;
		inicializar();
	}
	
	/**Inicializa todos os componetes da tela veículo
	 * @author Joaquim Neto
	 */
	private void inicializar() {
		setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true), tituloTela, TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		setLayout(null);
		
		lblRazoSocial = new JLabel(LocaleUtils.getLocaleView().getString("lbl_razao_social"));
		lblRazoSocial.setBounds(35, 40, 125, 20);
		add(lblRazoSocial);
		
		txtRazaoSocial = new JTextField(10);
		txtRazaoSocial.setBounds(30, 60, 800, 30);
		add(txtRazaoSocial);
		
		lblFantasia = new JLabel(LocaleUtils.getLocaleView().getString("lbl_nome_fantasia"));
		lblFantasia.setBounds(35, 90, 125, 20);
		add(lblFantasia);
		
		txtFantasia = new JTextField(10);
		txtFantasia.setBounds(30, 110, 370, 30);
		add(txtFantasia);
		
		lblCnpj = new JLabel(LocaleUtils.getLocaleView().getString("lbl_cnpj"));
		lblCnpj.setBounds(410, 90, 125, 20);
		add(lblCnpj);
		
		txtCnpj = new JTextField(10);
		txtCnpj.setBounds(405, 110, 220, 30);
		add(txtCnpj);
		
		lblInscEstadual = new JLabel(LocaleUtils.getLocaleView().getString("lbl_insc_estadual"));
		lblInscEstadual.setBounds(638, 90, 125, 20);
		add(lblInscEstadual);
		
		txtIncEstadual = new JTextField(10);
		txtIncEstadual.setBounds(630, 110, 200, 30);
		add(txtIncEstadual);
		
		lblResponsavel = new JLabel(LocaleUtils.getLocaleView().getString("lbl_responsavel"));
		lblResponsavel.setBounds(35, 145, 125, 20);
		add(lblResponsavel);
		
		txtResponsavel = new JTextField(10);
		txtResponsavel.setBounds(30, 165, 600, 30);
		add(txtResponsavel);
		
		// Componete formulário para endereço
		FormularioEnderecoComponente formularioEndereco = new FormularioEnderecoComponente();
		formularioEndereco.setBounds(30, 200, 800, 170);
		add(formularioEndereco);
		
		// Componente botões
		BotoesCrudComponente botoesCrudComponente = new BotoesCrudComponente();
		botoesCrudComponente.setBounds(623, 408, 200, 45);
		add(botoesCrudComponente);
		
		this.setBounds(15, 10, 859, 500);
		this.setVisible(true);
	}
}