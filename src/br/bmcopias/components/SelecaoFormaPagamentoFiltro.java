package br.bmcopias.components;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.bmcopias.enumerations.FormaPagamentoEnum;
import br.bmcopias.util.Util;

public class SelecaoFormaPagamentoFiltro {
	
	public JLabel ID_PARCELA_LBL             = new JLabel("");
	public JLabel CONCRETIZADO_LBL           = new JLabel("");
	public JLabel NR_COMPROVANTE_LBL         = new JLabel("");
	public DateAndLabel DATA_VCTO_ORIG_PARCELA_DATE_LBL = new DateAndLabel("Data Vencimento :", true, 12, true);
	public DateAndLabel DATA_PAGTO_PARCELA_DATE_LBL   = new DateAndLabel("Data Pagamento :", true, 12, true);
	public JLabel VLR_ORIG_PARCELA_LBL       = new JLabel("Valor :");
	public JLabel VLR_PAGTO_PARCELA_LBL      = new JLabel();
	public JLabel FORMA_PARCELA_LBL          = new JLabel("Forma Pagamento :");
	public JLabel NR_DOCUMENTO_LBL           = new JLabel("Nr NF :");
	public JLabel NR_BANCO_LBL               = new JLabel("Nr Banco :");
	public JLabel NR_AGENCIA_LBL             = new JLabel("Nr Agência :");
	public JLabel NR_CONTA_LBL               = new JLabel("Nr Conta :");
	public JLabel OBSERVACAO_LBL             = new JLabel("Obs. :");
	public JLabel VLR_JUROS_LBL              = new JLabel("Valor Juros :");
	public JLabel VLR_MORA_LBL               = new JLabel("Valor Mora  :");
	public JLabel VLR_OUTRAS_DESPESAS_LBL    = new JLabel("Valor Outr. Desp. :");
	public JLabel DESCRICAO_OUTRAS_DESPESA_LBL = new JLabel("Descrição Outr. Desp. :");
	public JLabel VLR_MULTA_LBL                = new JLabel("Valor Multa :");
	
	public JFormattedTextField VLR_ORIG_PARCELA_TXT   = null;
	public JFormattedTextField VLR_PAGTO_PARCELA_TXT  = null;
	public JFormattedTextField NR_BANCO_TXT           = null;
	public JFormattedTextField NR_AGENCIA_TXT         = null;
	public JFormattedTextField NR_CONTA_TXT           = null;
	public JFormattedTextField DIG_NR_CONTA_TXT       = null;
	public JTextArea OBSERVACAO_TXT_AREA              =  new JTextArea(5, 20);

	public JFormattedTextField VLR_JUROS_TXT          = null;
	public JFormattedTextField VLR_MORA_TXT           = null;
	public JFormattedTextField VLR_OUTRAS_DESPESAS_TXT  = null;
	public JTextArea DESCRICAO_OUTRAS_DESPESA_AREA      =  new JTextArea(5, 20);
	public JFormattedTextField VLR_MULTA_TXT            = null;
	JScrollPane OBSERVACAO_TEXT_AREA_SCROLL           = null;
	JScrollPane OUTRAS_DESP_TEXT_AREA_SCROLL          = null;
	
	private String[] formasStrings;
	public JComboBox formasPagamentoList;
	
	public SelecaoFormaPagamentoFiltro(){
		getFormasPagamento();
		
		VLR_ORIG_PARCELA_TXT  = Util.getTextFormatoValor();
		VLR_PAGTO_PARCELA_TXT = Util.getTextFormatoValor();
		
		NR_BANCO_TXT   = Util.getTextFormatoQuantidade(4, null);
		NR_BANCO_TXT.setFormatterFactory(Util.getFormatterTextField("####"));
		
		NR_AGENCIA_TXT = Util.getTextFormatoQuantidade(3, null);
		NR_AGENCIA_TXT.setFormatterFactory(Util.getFormatterTextField("####"));
		
		NR_CONTA_TXT = Util.getTextFormatoQuantidade(6, null);
		NR_CONTA_TXT.setFormatterFactory(Util.getFormatterTextField("######"));
		
		DIG_NR_CONTA_TXT = Util.getTextFormatoQuantidade(1, null);
		DIG_NR_CONTA_TXT.setFormatterFactory(Util.getFormatterTextField("#"));
		
		OBSERVACAO_TXT_AREA.setEditable(true);
		OBSERVACAO_TEXT_AREA_SCROLL = new JScrollPane(OBSERVACAO_TXT_AREA);
		
		DESCRICAO_OUTRAS_DESPESA_AREA.setEditable(true);
		OUTRAS_DESP_TEXT_AREA_SCROLL = new JScrollPane(DESCRICAO_OUTRAS_DESPESA_AREA);
		
		VLR_MORA_TXT            = Util.getTextFormatoValor();
		VLR_JUROS_TXT           = Util.getTextFormatoValor();
		VLR_OUTRAS_DESPESAS_TXT = Util.getTextFormatoValor();
		VLR_MULTA_TXT           = Util.getTextFormatoValor();
		
		DATA_VCTO_ORIG_PARCELA_DATE_LBL.setText(Util.getDataHojeDDMMYYYY());
		
	}
	
	private void getFormasPagamento(){

		try {
			formasStrings   = FormaPagamentoEnum.obterFormaPagamento();
		} catch (Exception e) {
			Util.logar("Erro ao tentar obter os produtos...", e.getMessage());
		}
		
		formasPagamentoList  = new JComboBox(formasStrings);
		formasPagamentoList.setModel(new DefaultComboBoxModel(formasStrings));
		formasPagamentoList.setSize(400, 400);
	}	

}
