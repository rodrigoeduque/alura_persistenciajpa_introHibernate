package br.com.rodrigoeduque.loja.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

	private String nome;
	private String cpf;
	
	
	
	public DadosPessoais() {
	}

	public String getNome() {
		return nome;
	}
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}


	public String getCpf() {
		return cpf;
	}

}
