package list6.DAO;

import list6.MODEL.Funcionario;
import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDAO {
    public void adicionar(Funcionario funcionario) throws SQLException;
    public void deletar(int id) throws SQLException;
    public List<Funcionario> buscarTodos() throws SQLException;
    public Funcionario buscar(int id) throws SQLException;
    public void editar(Funcionario funcionario) throws SQLException;
}
