package br.bmcopias.boletos;


import java.io.File;
import java.util.Vector;

import org.eclipse.swt.program.Program;
import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jboleto.control.Generator;
import org.jboleto.control.PDFGenerator;

import br.bmcopias.bean.VendaBean;
import br.bmcopias.configuration.ConfiguracaoPrincipal;
import br.bmcopias.util.Util;

/**
 * 
 */
public class GeraBoletoItau {
	
	private static ConfiguracaoPrincipal con = ConfiguracaoPrincipal.getInstance();
	private String numeroNf;

    public String getNumeroNf() {
		return numeroNf;
	}

	public void setNumeroNf(String numeroNf) {
		this.numeroNf = numeroNf;
	}

	public GeraBoletoItau() {
    }

    public void gerar(VendaBean vb) {

        try {

            JBoletoBean jBoletoBean = new JBoletoBean();

            jBoletoBean.setDataDocumento(Util.dateToString(vb.getDataVenda()));
            jBoletoBean.setDataProcessamento( Util.getDataHojeDDMMYYYY()) ;

            jBoletoBean.setCedente(con.getString("bmc.boleto.cedente"));

            jBoletoBean.setNomeSacado(vb.getCliente().getNome());
            jBoletoBean.setEnderecoSacado(vb.getCliente().getEndereco());
            jBoletoBean.setBairroSacado(vb.getCliente().getBairro());
            jBoletoBean.setCidadeSacado(vb.getCliente().getCidade());
            jBoletoBean.setUfSacado(vb.getCliente().getEstado());
            jBoletoBean.setCepSacado(vb.getCliente().getCep());
            jBoletoBean.setCpfSacado(vb.getCliente().getCnpj());

            Vector descricoes = new Vector();
            descricoes.add("");

            //descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
            //descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
            //descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
            //descricoes.add("Extra - teste de descricao4 - R$ 78,90");
            jBoletoBean.setDescricoes(descricoes);

            //jBoletoBean.setImagemMarketing("/home/fabio/template_logo.png");

            jBoletoBean.setDataVencimento("10/06/2006");
            jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
            jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
            jBoletoBean.setInstrucao3("");
            jBoletoBean.setInstrucao4("");

            jBoletoBean.setCarteira("175");
            jBoletoBean.setAgencia("0772");
            jBoletoBean.setContaCorrente("65175");
            jBoletoBean.setDvContaCorrente("8");

            jBoletoBean.setNossoNumero("34556",8);
            jBoletoBean.setNoDocumento(Util.convertLongToString(vb.getIdVenda()));
            jBoletoBean.setValorBoleto(Util.convertDoubleToString(vb.getVlrVenda()));

            Generator generator = new PDFGenerator(jBoletoBean, JBoleto.ITAU);
            JBoleto jBoleto = new JBoleto(generator, jBoletoBean, JBoleto.ITAU);

            String caminho = con.getString("bmc.pastaBoletos") + vb.getIdVenda() +"itau.pdf";
            
            jBoleto.addBoleto();
            jBoleto.closeBoleto(caminho);
            
            File f = new File(caminho);
			
			Program.launch(f.getAbsolutePath());
            

        }
        catch (Exception ex) {

            ex.printStackTrace();
        }

    }
}
