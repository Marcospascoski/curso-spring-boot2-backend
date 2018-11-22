package com.marcospascoski.cursomc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marcospascoski.cursomc.domain.Estado;

@Repository
@Transactional(readOnly=true)
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	public List<Estado> findAllByOrderByNome();
}
