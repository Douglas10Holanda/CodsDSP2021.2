package list5.DAO;

import list5.MODEL.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioJDBCDAO implements FuncionarioDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void adicionar(Funcionario funcionario)throws SQLException {
        String insert = "insert into funcionarios (cpf ,matricula, nome, email, telefone) " +
                "values (:cpf, :matricula, :nome, :email, :telefone)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cpf", funcionario.getCpf())
                .addValue("matricula",  funcionario.getMatricula())
                .addValue("nome",  funcionario.getNome())
                .addValue("email",  funcionario.getEmail())
                .addValue("telefone",  funcionario.getTelefone());
        jdbcTemplate.update(insert, params);
    }

    public void deletar(int id)throws SQLException{
        String delete = "delete from funcionarios where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id",  id);
        jdbcTemplate.update(delete, params);
    }

    private Funcionario map(ResultSet resultSet) throws SQLException{
        Funcionario funcionario = new Funcionario();
        funcionario.setId(resultSet.getInt("id"));
        funcionario.setCpf(resultSet.getString("cpf"));
        funcionario.setMatricula(resultSet.getString("matricula"));
        funcionario.setNome(resultSet.getString("nome"));
        funcionario.setEmail(resultSet.getString("email"));
        funcionario.setTelefone(resultSet.getString("telefone"));

        return funcionario;
    }

    @Override
    public List<Funcionario> buscar()throws SQLException{
        String find = "select * from funcionarios";
        return jdbcTemplate.query(find, (rs, rowNum) -> map(rs));
    }

    public Funcionario buscar(int id)throws SQLException{
        String findId = "select * from funcionarios where id = :id_";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id_", id);
        return jdbcTemplate.queryForObject(findId, namedParameters, (rs, rowNum) -> map(rs));
    }

    @Override
    public void editar(Funcionario funcionario)throws SQLException{
        String atualizar = "update funcionarios set cpf= :cpf, matricula= :matricula, " +
                "nome= :nome, email= :email, telefone= :telefone where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cpf", funcionario.getCpf())
                .addValue("matricula",  funcionario.getMatricula())
                .addValue("nome",  funcionario.getNome())
                .addValue("email",  funcionario.getEmail())
                .addValue("telefone",  funcionario.getTelefone());

        params.addValue("id", funcionario.getId());
        jdbcTemplate.update(atualizar, params);
    }
}