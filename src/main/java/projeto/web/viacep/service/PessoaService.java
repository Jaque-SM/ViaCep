package projeto.web.viacep.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import projeto.web.viacep.model.Pessoa;

public interface PessoaService {
	
	
	public abstract Pessoa addPessoa(Pessoa pae) throws FileNotFoundException, IOException;
	
	public abstract Pessoa updatePessoa (Long cep, Pessoa pae);
	
	public abstract String removePessoa (Long cep);
	
	public abstract List<Pessoa> listarPessoa ();
	
	public abstract Pessoa buscarPorId(Long id);

	public abstract Pessoa buscarPorCep(String cep);
	
	
	
	

}
