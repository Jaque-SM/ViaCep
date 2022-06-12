package projeto.web.viacep.controller;

import org.springframework.web.bind.annotation.RestController;

import projeto.web.viacep.model.Pessoa;
import projeto.web.viacep.serviceImpl.PessoaImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

// foi usado o software postman para simular uma interface


/* Classe de controller onde recebe as requisições e 
 * envia as respostas ao usuario*/
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	
	/*É declarado uma variavel que faz a varredura dentro do
	 * repositorio acessando os metodos da classe de serviços
	 * implementados*/
	@Autowired
	private PessoaImpl dados;
	
	/*metodo de listagem dos dados da da tabela */
	@GetMapping
	public List<Pessoa> listar() {
		return dados.listarPessoa();
	} 

	/*metodo de requisição que salva um pessoa em formato Json*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa add(@Validated  @RequestBody Pessoa pae) throws IOException {
		return dados.addPessoa(pae);
	}

	/* metodo de deletar pessoa atraves do Id do usuario*/
	@DeleteMapping("/{Id}")
	public ResponseEntity<String> remover(@PathVariable Long Id) {

		if (dados.buscarPorId(Id) == null) {
			return ResponseEntity.ok("Usuario nao existe");
		}
		dados.removePessoa(Id);
		return ResponseEntity.ok("Usuario removido");
	}
	/* metodo de buscar o usuario pelo cep que é passado na requisição*/
	@PutMapping("/{Id}")
	public ResponseEntity<Pessoa> atualizar(@Validated @PathVariable Long Id, @RequestBody Pessoa pae) {

		if (dados.buscarPorId(Id) == null) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pes = dados.updatePessoa(Id, pae);

		return ResponseEntity.ok(pes);
	}
	/*metodo de buscar a pessoa pelo cep que é passado na requisição*/
	@RequestMapping(value = "/{cep}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscar(@PathVariable String cep) {

		if (dados.buscarPorCep(cep) == null) {
			return ResponseEntity.notFound().build();
		}
		Pessoa profresult = dados.buscarPorCep(cep);
		return ResponseEntity.ok(profresult);

	}
	
	
	
	
	

}
