package br.com.rodrigoeduque.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {
	
	private String produto;
	private Long quantidadeVendida;
	private LocalDate dataUltimaVenda;
	
	public RelatorioDeVendasVo(String produto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
		this.produto = produto;
		this.quantidadeVendida = quantidadeVendida;
		this.dataUltimaVenda = dataUltimaVenda;
	}

	public String getProduto() {
		return produto;
	}

	public Long getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public LocalDate getDataUltimaVenda() {
		return dataUltimaVenda;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVo [produto=" + produto + ", quantidadeVendida=" + quantidadeVendida
				+ ", dataUltimaVenda=" + dataUltimaVenda + "]";
	}
	
	
	

}
