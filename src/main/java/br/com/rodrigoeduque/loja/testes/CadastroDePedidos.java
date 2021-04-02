package br.com.rodrigoeduque.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rodrigoeduque.loja.dao.CategoriaDao;
import br.com.rodrigoeduque.loja.dao.ClienteDao;
import br.com.rodrigoeduque.loja.dao.PedidoDao;
import br.com.rodrigoeduque.loja.dao.ProdutoDao;
import br.com.rodrigoeduque.loja.modelo.Categoria;
import br.com.rodrigoeduque.loja.modelo.Cliente;
import br.com.rodrigoeduque.loja.modelo.ItemPedido;
import br.com.rodrigoeduque.loja.modelo.Pedido;
import br.com.rodrigoeduque.loja.modelo.Produto;
import br.com.rodrigoeduque.loja.util.JpaUtil;
import br.com.rodrigoeduque.loja.vo.RelatorioDeVendasVo;

public class CadastroDePedidos {

	public static void main(String[] args) {
		cadastrarProduto();
		cadastrarCliente();
		cadastrarPedido() ;

		EntityManager em = JpaUtil.getEntityManager();

		PedidoDao pedidoDao = new PedidoDao(em);
		
		BigDecimal totalVendido = pedidoDao.valorTotalVendido();
		
		System.out.println("Total : R$ " + totalVendido);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.relatorioDeVendas();
		
		relatorio.forEach(System.out :: println);
		

		
	}
	
	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria audio = new Categoria("FONES");
		Categoria computador = new Categoria("NOTEBOOK");
		Categoria impressoras = new Categoria("IMPRESSORAS");

		Produto celular = new Produto("Xiaomi Redmi", "Muito Legal", new BigDecimal("800"), celulares);
		Produto fone = new Produto("Fone de Ouvido JBL BT500", "Bluetooth", new BigDecimal("200"), audio);
		Produto notebook = new Produto("Notebook Dell Inspiron 3583-AS80P ", "Intel Core i5 - 8GB 256GB SSD 15,6 Placa Vídeo 2GB Windows 10", new BigDecimal("4179.05"), computador);
		Produto impressora = new Produto("Impressora Multifuncional Epson EcoTank L3110", "Tanque de Tinta Colorida USB", new BigDecimal("1044.05"), impressoras);

		EntityManager em = JpaUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		em.getTransaction().commit();

		em.getTransaction().begin();
		categoriaDao.cadastrar(audio);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(computador);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(impressoras);
		em.getTransaction().commit();

		//
		em.getTransaction().begin();
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();

		em.getTransaction().begin();
		produtoDao.cadastrar(fone);
		em.getTransaction().commit();
		
		em.getTransaction().begin();
		produtoDao.cadastrar(notebook);
		em.getTransaction().commit();

		em.getTransaction().begin();
		produtoDao.cadastrar(impressora);
		em.getTransaction().commit();

		em.close();
	}
	
	//Criado cliente
	private static void cadastrarCliente() {
		
		EntityManager em = JpaUtil.getEntityManager();
		ClienteDao clienteDao = new ClienteDao(em);
		
		Cliente rodrigo = new Cliente("Rodrigo Eustáquio Duque", "074.435.736-50");
		Cliente ana = new Cliente("Ana Camila Mendonça Duque", "077.153.551-00");
		
		em.getTransaction().begin();
		clienteDao.cadastrar(rodrigo);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		clienteDao.cadastrar(ana);
		em.getTransaction().commit();
		em.close();
	}
	
	private static void cadastrarPedido() {
		EntityManager em = JpaUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		// Buscando Produto
		Produto produto = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Produto produto3 = produtoDao.buscarPorId(3l);
		Produto produto4 = produtoDao.buscarPorId(4l);
		
		//Buscando Cliente
		Cliente cliente = clienteDao.buscarPorId(1l);

		//Criado Pedido
		Pedido pedido = new Pedido(cliente);
		//Criando Item Pedido
		ItemPedido itemPedido = new ItemPedido(10, pedido, produto );
		ItemPedido itemPedido2 = new ItemPedido(5, pedido, produto2 );
		ItemPedido itemPedido3 = new ItemPedido(2, pedido, produto3 );
		ItemPedido itemPedido4 = new ItemPedido(7, pedido, produto4 );
		
		//Adicionando item no Pedido
		pedido.adicionarItem(itemPedido);
		pedido.adicionarItem(itemPedido2);
		pedido.adicionarItem(itemPedido3);
		pedido.adicionarItem(itemPedido4);
		//Salvando no Banco de Dados
		
		PedidoDao pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin();
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();
	}
}
