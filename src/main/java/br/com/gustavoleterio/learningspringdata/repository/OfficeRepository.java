package br.com.gustavoleterio.learningspringdata.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.gustavoleterio.learningspringdata.orm.Office;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Integer> {

}
