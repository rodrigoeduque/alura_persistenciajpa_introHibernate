package br.com.rodrigoeduque.loja.testes;

import java.math.BigDecimal;

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

public class PerformanceConsultas {

	public static void main(String[] args) {
		
		popularBancoDeDados();
		
		EntityManager em = JpaUtil.getEntityManager();
		Pedido pedido = em.find(Pedido.class, 2l);
		System.out.println(pedido.getCliente().getDadosPessoais().getNome());
		
	}
	
	
	private static void popularBancoDeDados() {
		
		Categoria informatica = new Categoria("INFORMÁTICA");
		Categoria videogames = new Categoria("GAMES");
		Categoria celulares = new Categoria("CELULARES");
		
		
		Produto celular = new Produto("Smartphone Xiaomi Redmi Note 9 Pro", "128GB 6GB RAM Versão Global Interstellar Grey", new BigDecimal("2219.00"), celulares);
		Produto videogame = new Produto("Console Playstation 5", "825GB SSD + Controle Sem Fio DualSense - Branco", new BigDecimal("7978.10"), videogames);
		Produto macbook = new Produto("Apple MacBook Air 13.3", "Chip M1, 8GB RAM, 256GB SSD - Space Gray", new BigDecimal("8926.49"), informatica);
		
		Cliente cliente = new Cliente("Rodrigo Eustáquio Duque", "074.435.736-50");

		Pedido pedido = new Pedido(cliente);
		
		pedido.adicionarItem(new ItemPedido(10, pedido, celular));
		pedido.adicionarItem(new ItemPedido(5, pedido, videogame));
		pedido.adicionarItem(new ItemPedido(5, pedido, macbook));
		
		Pedido pedido2 = new Pedido(cliente);
		pedido2.adicionarItem(new ItemPedido(3, pedido, videogame));
		pedido2.adicionarItem(new ItemPedido(7, pedido, macbook));
		
		
		EntityManager em = JpaUtil.getEntityManager();
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		em.getTransaction().commit();

		em.getTransaction().begin();
		categoriaDao.cadastrar(videogames);
		em.getTransaction().commit();

		em.getTransaction().begin();
		categoriaDao.cadastrar(informatica);
		em.getTransaction().commit();

		
		em.getTransaction().begin();
		produtoDao.cadastrar(macbook);
		em.getTransaction().commit();

		
		em.getTransaction().begin();
		produtoDao.cadastrar(videogame);
		em.getTransaction().commit();

		
		em.getTransaction().begin();
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();

		
		em.getTransaction().begin();
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();

		em.getTransaction().begin();
		pedidoDao.cadastrar(pedido);
		em.getTransaction().commit();

		em.getTransaction().begin();
		pedidoDao.cadastrar(pedido2);
		em.getTransaction().commit();

		
		em.close();
		
		
	}
}
