package br.bmcopias.mail;

import java.io.File;

public class MailBean {

	private String[] destinatarios;
	private String de;
	private String assunto;
	private String conteudo;
	private File[] anexos;

	public String[] getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(String[] destinatarios) {
		this.destinatarios = destinatarios;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public File[] getAnexos() {
		return anexos;
	}

	public void setAnexos(File[] anexos) {
		this.anexos = anexos;
	}

}
