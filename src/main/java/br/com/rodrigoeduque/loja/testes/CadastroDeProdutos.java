package br.com.rodrigoeduque.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.rodrigoeduque.loja.dao.CategoriaDao;
import br.com.rodrigoeduque.loja.dao.ProdutoDao;
import br.com.rodrigoeduque.loja.modelo.Categoria;
import br.com.rodrigoeduque.loja.modelo.Produto;
import br.com.rodrigoeduque.loja.util.JpaUtil;

public class CadastroDeProdutos {

	public static void main(String[] args) {
		cadastrarProduto();

		EntityManager em = JpaUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);

		Produto p = produtoDao.buscarPorId(1l);

		System.out.println("Preço: R$ " + p.getPreco());

		List<Produto> todos = produtoDao.buscarTodos();
		System.out.println("-------------------Buscando Todos-------------------------------");

		todos.forEach(p2 -> System.out
				.println(
						"------------------------------" + 
						"\nId: " + p2.getId() + 
						"\nNome: " + p2.getNome() + 
						"\nDescrição : " + p2.getDescricao() + 
						"\nPreço: R$ :" + p2.getPreco() + 
						"\nCategoria: " + p2.getCategoria().getNome() + 
						"\n------------------------------"));

		System.out.println("-------------------Filtrando por Nome-------------------------------");

		List<Produto> produtoPorNome = produtoDao.buscarPorNome("Xiaomi Redmi");

		produtoPorNome.forEach(p3 -> System.out
				.println(
						"------------------------------" +
						"\nId: " + p3.getId() + 
						"\nNome: " + p3.getNome() + 
						"\nDescrição : " + p3.getDescricao() + 
						"\nPreço: R$ :" + p3.getPreco() + 
						"\nCategoria: " + p3.getCategoria().getNome() + 
						"\n------------------------------"));
		
		
		System.out.println("-------------------Filtrando por Nome Categoria-------------------------------");
		List<Produto> produtoPorNomeCategoria = produtoDao.buscarPorNomeCategoria("FONES");

		produtoPorNomeCategoria.forEach(p4 -> System.out
				.println(
						"------------------------------" +
						"\nId: " + p4.getId() + 
						"\nNome: " + p4.getNome() + 
						"\nDescrição : " + p4.getDescricao() + 
						"\nPreço: R$ :" + p4.getPreco() + 
						"\nCategoria: " + p4.getCategoria().getNome() + 
						"\n------------------------------"));
		
		
		System.out.println("-------------------Filtrando por Nome-------------------------------");

		BigDecimal precoProduto = produtoDao.buscarPrecoProdutoComNome("Fone de Ouvido JBL BT500");

		produtoPorNome.forEach(p4 -> System.out
				.println("Preço : R$ " + precoProduto));


	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria audio = new Categoria("FONES");

		Produto celular = new Produto("Xiaomi Redmi", "Muito Legal", new BigDecimal("800"), celulares);
		Produto fone = new Produto("Fone de Ouvido JBL BT500", "Bluetooth", new BigDecimal("200"), audio);

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
		produtoDao.cadastrar(celular);
		em.getTransaction().commit();

		em.getTransaction().begin();
		produtoDao.cadastrar(fone);
		em.getTransaction().commit();

		em.close();
	}

}
