package List7.DAO;

import java.util.List;

import List7.ENTITY.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FuncionarioDAO extends JpaRepository<Funcionario, Integer> {

	public Funcionario findFirstByCpf(String cpf);

	@Query("select f from Funcionario f where f.cpf = :cpf")
	public Funcionario buscaPrimeiroPorCpf(String cpf);

	@Query(name = "funcionarioPorCpf")
	public Funcionario buscaPorCpfViaConsultaNomeada(String cpf);

	public List<Funcionario> findByNomeStartingWith(String str);

	@Query("select f from Funcionario f where f.nome like :nome%")
	public List<Funcionario> buscaPorNomeIniciadoPor(String nome);
}
