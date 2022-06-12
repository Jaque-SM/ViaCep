package projeto.web.viacep.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String cep;
	@Column
	private String logradouro;
	@Column
	private String complemento;
	@Column
	private String bairro;
	@Column
	private String localidade;
	@Column
	private String uf;
	@Column
	private String ibge;
	@Column
	private String gia;
	@Column
	private String ddd;
	@Column
	private String siafi;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getSiafi() {
		return siafi;
	}

	public void setSiafi(String siafi) {
		this.siafi = siafi;
	}

	public String nulidade(Pessoa p) {

		if (p.getCep() == null) {
			return null;
		}
		if (p.getLogradouro() == null) {
			return null;
		}
		if (p.getComplemento() == null) {
			return null;
		}
		if (p.getBairro() == null) {
			return null;
		}
		if (p.getLocalidade() == null) {
			return null;
		}
		if (p.getUf() == null) {

			return null;
		}
		if (p.getIbge() == null) {

			return null;
		}
		if (p.getDdd() == null) {
			return null;
		}
		if (p.getGia() == null) {
			return null;
		}
		if (p.getSiafi() == null) {
			return null;
		}

		return "Nao tem nada nulo nessa bagaça";

	}

	@Override
	public int hashCode() {
		return Objects.hash(bairro, cep, complemento, ddd, gia, ibge, id, localidade, logradouro, siafi, uf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(complemento, other.complemento) && Objects.equals(ddd, other.ddd)
				&& Objects.equals(gia, other.gia) && Objects.equals(ibge, other.ibge) && Objects.equals(id, other.id)
				&& Objects.equals(localidade, other.localidade) && Objects.equals(logradouro, other.logradouro)
				&& Objects.equals(siafi, other.siafi) && Objects.equals(uf, other.uf);
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", cep=" + cep + ", logradouro=" + logradouro + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + ", gia=" + gia
				+ ", ddd=" + ddd + ", siafi=" + siafi + "]";
	}

}
