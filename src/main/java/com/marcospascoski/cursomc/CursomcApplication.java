package com.marcospascoski.cursomc;

import java.text.SimpleDateFormat;
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
import com.marcospascoski.cursomc.domain.ItemPedido;
import com.marcospascoski.cursomc.domain.Pagamento;
import com.marcospascoski.cursomc.domain.PagamentoComBoleto;
import com.marcospascoski.cursomc.domain.PagamentoComCartao;
import com.marcospascoski.cursomc.domain.Pedido;
import com.marcospascoski.cursomc.domain.Produto;
import com.marcospascoski.cursomc.domain.enums.EstadoPagamento;
import com.marcospascoski.cursomc.domain.enums.TipoCliente;
import com.marcospascoski.cursomc.repositories.CategoriaRepository;
import com.marcospascoski.cursomc.repositories.CidadeRepository;
import com.marcospascoski.cursomc.repositories.ClienteRepository;
import com.marcospascoski.cursomc.repositories.EnderecoRepository;
import com.marcospascoski.cursomc.repositories.EstadoRepository;
import com.marcospascoski.cursomc.repositories.ItemPedidoRepository;
import com.marcospascoski.cursomc.repositories.PagamentoRepository;
import com.marcospascoski.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
				
	}
}
