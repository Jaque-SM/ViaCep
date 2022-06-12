package projeto.web.viacep.serviceImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import projeto.web.viacep.model.Pessoa;
import projeto.web.viacep.model.PessoaJson;
import projeto.web.viacep.repository.PessoaRepository;
import projeto.web.viacep.service.PessoaService;

@Service
public class PessoaImpl implements PessoaService {

	@Autowired
	private PessoaRepository pae;

	static Gson gson;
	static Pessoa bd;
	static PessoaJson dados;
	static Reader ler;
	static URL url;

	public PessoaImpl() throws IOException {
	
		url = new URL("https://viacep.com.br/ws/17013905/json");
	
	}
	@Override
	public Pessoa addPessoa(Pessoa pessoa) throws IOException {
		
		Long id=(long) 1;
		
		ler = new InputStreamReader(url.openStream());
		
		gson = new Gson();
		dados = new PessoaJson();
		dados = gson.fromJson(ler, PessoaJson.class);
		
			bd =new Pessoa();
					
			if (!pae.existsById(id)) {
			bd.setCep(dados.getCep());
			bd.setLogradouro(dados.getLogradouro());
			bd.setComplemento(dados.getComplemento());
			bd.setBairro(dados.getBairro());
			bd.setLocalidade(dados.getLocalidade());
			bd.setUf(dados.getLocalidade());
			bd.setIbge(dados.getIbge());
			bd.setGia(dados.getGia());
			bd.setDdd(dados.getDdd());
			bd.setSiafi(dados.getSiafi());
			
			escrever_json(dados);
			
			return pae.save(bd);
		
		}
		
		return pae.save(pessoa);
		
	}
	public void escrever_json(PessoaJson dados) {
		String json = gson.toJson(dados);

		try {
			//Escreve Json convertido em arquivo chamado "dados.json"
			FileWriter writer = new FileWriter("C:\\Users\\JAQUE\\"
					+ "Desktop\\Projects\\ViaCep\\src\\main\\java\\"
					+ "projeto\\web\\viacep\\serviceImpl\\dados.json");
			writer.write(json+"\n");
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(json);

	    }		

	@Override
	public Pessoa updatePessoa(Long cep, Pessoa pessoa) {
		if (!pae.existsById(cep)) {
			return null;
		}
		pessoa.setId(cep);
		pessoa = pae.save(pessoa);
		return pessoa;
	}

	@Override
	public String removePessoa(Long cep) {

		if (!pae.existsById(cep)) {
			return "Usuario não encontrado";
		}

		pae.deleteById(cep);
		return "Usuario removido da tabela";
	}

	@Override
	public List<Pessoa> listarPessoa() {
		
	
		
		
		return pae.findAll();
	}

	@Override
	public Pessoa buscarPorId(Long id) {
		Optional<Pessoa> paed = pae.findById(id);

		if (paed.isPresent()) {
			return paed.get();
		}

		return null;
	}

	public Pessoa buscarPorCep(String cep) {
		Optional<Pessoa> paed = Optional.ofNullable(pae.findByCep(cep));

		if (paed.isPresent()) {
			return paed.get();
		}

		return null;
	}



}
