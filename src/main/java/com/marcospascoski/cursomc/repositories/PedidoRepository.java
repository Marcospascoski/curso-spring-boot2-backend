package com.marcospascoski.cursomc.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcospascoski.cursomc.domain.Cliente;
import com.marcospascoski.cursomc.domain.Pedido;

@Repository
@Transactional(readOnly=true)
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);
	
}
