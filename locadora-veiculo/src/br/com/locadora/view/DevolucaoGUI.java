package br.com.locadora.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.locadora.controller.DevolucaoControl;
import br.com.locadora.model.entity.Locacao;
import br.com.locadora.utils.SystemUtils;
import br.com.locadora.utils.locale.LocaleUtils;
import br.com.locadora.view.componentes.CartaoCreditoComponente;
import br.com.locadora.view.componentes.CartaoDebitoComponente;
import br.com.locadora.view.componentes.InputSoNumeros;

public class DevolucaoGUI extends JDialog {
	private static final long serialVersionUID = 2391312642421242922L;
	
	// Lables
	private JLabel lblConsultarLocao;
	private JLabel lblFormaPagamento;
	private JLabel lblValorAPagar;
	
	// Inputs
	private JTextField txtConsultaLocacao;
	private JTextField txtValorPagar;
	
	// Buttons
	private JButton btnConsultar;
	private JRadioButton rdbtnCartoCredito;
	private JRadioButton rdbtnCartoDebito;
	
	// Componentes
	private CartaoDebitoComponente cartaoDebitoComponente;
	private CartaoCreditoComponente cartaoCreditoComponente;

	private JPanel panelDetalheLocacao;
	private JButton btnConcluir;
	private JButton btnCancelar;
	private JTextArea txtDetalheLocacao;
	
	private Locacao locacao;
	
	public DevolucaoGUI() {
		setTitle(LocaleUtils.getLocaleView().getString("titulo_tela_devolucao"));
		inicializar();
	}
	
	/**Inicializa todos os componetes da tela
	 * @author Joaquim Neto
	 */
	private void inicializar() {
		InputSoNumeros soNumeros = new InputSoNumeros();
		
		
		getContentPane().setLayout(null);
		panelDetalheLocacao = new JPanel();
		
		TitledBorder titledBorder = new TitledBorder(new LineBorder(Color.GRAY, 1, true), (LocaleUtils.getLocaleView().getString("titulo_detalhe_locacao")), 
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE);
		
		panelDetalheLocacao.setBorder(titledBorder);
		panelDetalheLocacao.setBounds(30, 90, 730, 200);
		panelDetalheLocacao.setLayout(null);
		getContentPane().add(panelDetalheLocacao);
		
		txtDetalheLocacao = new JTextArea();
		txtDetalheLocacao.setBounds(10, 20, 710, 170);
		panelDetalheLocacao.add(txtDetalheLocacao);
		
		lblConsultarLocao = new JLabel((LocaleUtils.getLocaleView().getString("lbl_consultar_locacao")));
		lblConsultarLocao.setBounds(35, 20, 150, 20);
		getContentPane().add(lblConsultarLocao);
		
		txtConsultaLocacao = new JTextField(10);
		txtConsultaLocacao.setInputVerifier(soNumeros);
		txtConsultaLocacao.setBounds(30, 40, 150, 30);
		getContentPane().add(txtConsultaLocacao);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(190, 40, 100, 30);
		getContentPane().add(btnConsultar);
		
		lblFormaPagamento = new JLabel(LocaleUtils.getLocaleView().getString("lbl_forma_pagamento"));
		lblFormaPagamento.setBounds(460, 300, 220, 20);
		getContentPane().add(lblFormaPagamento);
		
		// Grupo radio button
		ButtonGroup grupRadio = new ButtonGroup();
		
		rdbtnCartoCredito = new JRadioButton(LocaleUtils.getLocaleView().getString("cartao_credito"));
		rdbtnCartoCredito.setBounds(460, 325, 150, 20);
		grupRadio.add(rdbtnCartoCredito);
		getContentPane().add(rdbtnCartoCredito);
		
		rdbtnCartoDebito = new JRadioButton(LocaleUtils.getLocaleView().getString("cartao_debito"));
		rdbtnCartoDebito.setBounds(610, 325, 150, 20);
		grupRadio.add(rdbtnCartoDebito);
		getContentPane().add(rdbtnCartoDebito);
		
		lblValorAPagar = new JLabel((LocaleUtils.getLocaleView().getString("lbl_valor_a_pagar")));
		lblValorAPagar.setBounds(35, 300, 150, 20);
		getContentPane().add(lblValorAPagar);
		
		txtValorPagar = new JTextField(10);
		txtValorPagar.setBackground(Color.GREEN);
		txtValorPagar.setEditable(false);
		txtValorPagar.setBounds(30, 320, 150, 30);
		getContentPane().add(txtValorPagar);
		
		cartaoDebitoComponente = new CartaoDebitoComponente();
		cartaoDebitoComponente.setBounds(30, 350, 618, 255);
		getContentPane().add(cartaoDebitoComponente);
		
		btnConcluir = new JButton((LocaleUtils.getLocaleView().getString("btn_concluir")));
		btnConcluir.setBounds(660, 420, 100, 65);
		getContentPane().add(btnConcluir);
		
		btnCancelar = new JButton((LocaleUtils.getLocaleView().getString("btn_cancelar")));
		btnCancelar.setBounds(660, 505, 100, 65);
		getContentPane().add(btnCancelar);
		
		/**
		 * Eventos
		 *
		 */
		
		//Pesquisa a locação
		btnConsultar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idLocacao = txtConsultaLocacao.getText();
				if (idLocacao.length() > 0){
					DevolucaoControl devolucaoControl = new DevolucaoControl();
					locacao = devolucaoControl.buscaLocacaoPorCodigo(Integer.parseInt(idLocacao));
					
					if (!SystemUtils.isNuloOuVazio(locacao)) {
						preencherCampos(locacao);
					} else {
						JOptionPane.showMessageDialog(txtDetalheLocacao, "Nenhuma locação foi encontrada com o código informado");
					}
				}
			}
		});
		
		btnConcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((locacao != null) && (SystemUtils.isCamposObrigatoriosPreenchidos(cartaoDebitoComponente.getDadoDoCartao()))){
					DevolucaoControl devolucaoControl = new DevolucaoControl();
					locacao.setPagamento(cartaoDebitoComponente.getDadoDoCartao());
					devolucaoControl.fazerDevolucao(locacao);
					limparCampos();
				}else{
					JOptionPane.showMessageDialog(txtDetalheLocacao, LocaleUtils.getLocaleMessages().getString("falha_locacao_naopesquisada"));
				}
			}
		});
		
		setBounds(10, 0, 790, 630);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	private Locacao obterLocacao(int id){
		DevolucaoControl devolucaoControl = new DevolucaoControl();
		return devolucaoControl.buscaLocacaoPorCodigo(id);
	}
	
	private void preencherCampos(Locacao locacao){
		StringBuilder info = new StringBuilder();
		if (locacao != null){
			info.append("Nome do cliente: ").append(locacao.getCliente().getNome());
			info.append("\nVeículo locado: ").append(locacao.getVeiculo().getPlaca());
			info.append("\nAgência de devolução: ").append(locacao.getObjetoAgenciaDevolucao().getRazaoSocial());
			info.append("\nData da locação: ").append(SystemUtils.formatarDataParaTela(locacao.getDataHoraLocacao().toString()));
			info.append("\nData prevista de devolução: ").append(SystemUtils.formatarDataParaTela(locacao.getDataHoraPrevistaDevolucao().toString()));
			txtDetalheLocacao.setText(info.toString());
			txtValorPagar.setText(String.valueOf(locacao.getValorAcrescimo()));
		}
	}
	
	private void limparCampos(){
		txtConsultaLocacao.setText("");
		txtDetalheLocacao.setText("");
		txtValorPagar.setText("");
	}
	
}
