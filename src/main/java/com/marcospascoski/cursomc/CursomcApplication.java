package com.marcospascoski.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcospascoski.cursomc.domain.Categoria;
import com.marcospascoski.cursomc.domain.Cidade;
import com.marcospascoski.cursomc.domain.Cliente;
import com.marcospascoski.cursomc.domain.Endereco;
import com.marcospascoski.cursomc.domain.Estado;
import com.marcospascoski.cursomc.domain.Produto;
import com.marcospascoski.cursomc.domain.enums.TipoCliente;
import com.marcospascoski.cursomc.repositories.CategoriaRepository;
import com.marcospascoski.cursomc.repositories.CidadeRepository;
import com.marcospascoski.cursomc.repositories.ClienteRepository;
import com.marcospascoski.cursomc.repositories.EnderecoRepository;
import com.marcospascoski.cursomc.repositories.EstadoRepository;
import com.marcospascoski.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 3000.00);
		Produto p2 = new Produto(null, "Impressora", 600.00);
		Produto p3 = new Produto(null, "Mouse", 85.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
 		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
 		Estado est1 = new Estado(null, "Mato Grosso");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Goias");
		
		Cidade c1 = new Cidade(null, "Juara", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Gioania", est3);
		Cidade c5 = new Cidade(null, "Aparecida de Gioania", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c4, c5));
		
 		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		
		Cliente cli1 = new Cliente(null,"Maria Pask", "maria@gmail.com", "1345678911", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("789458945","456125612"));
		
		Endereco e1 = new Endereco(null, "Rua Bari", "662", "N", "Jardim Itália", "78575-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Av. Matos", "1865", "S", "Centro", "75475-000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
