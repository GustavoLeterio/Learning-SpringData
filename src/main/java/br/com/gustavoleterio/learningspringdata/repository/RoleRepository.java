package br.com.gustavoleterio.learningspringdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gustavoleterio.learningspringdata.orm.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{
	
}
