package projeto.web.viacep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.web.viacep.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	public abstract Pessoa findByCep(String string);

	public abstract boolean existsById(Pessoa bd);

}
