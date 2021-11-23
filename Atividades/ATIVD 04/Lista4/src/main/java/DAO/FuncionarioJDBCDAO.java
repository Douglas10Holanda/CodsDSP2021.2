package DAO;

import MODEL.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioJDBCDAO implements FuncionarioDAO {
    public FuncionarioJDBCDAO(){};
    @Override
    public void adicionar(Funcionario funcionario)throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        String insert = "insert into funcionarios (cpf ,matricula, nome, email, telefone) values (?, ?, ?, ?, ?)";
        PreparedStatement statement = conexao.prepareStatement(insert);
        statement.setString(1, funcionario.getCpf());
        statement.setString(2, funcionario.getMatricula());
        statement.setString(3, funcionario.getNome());
        statement.setString(4, funcionario.getEmail());
        statement.setString(5, funcionario.getTelefone());

        statement.executeUpdate();
        conexao.close();
    }

    public void deletar(int id)throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        String delete = "delete from funcionarios where id = ?";
        PreparedStatement statement = conexao.prepareStatement(delete);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
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

    public List<Funcionario> buscar()throws SQLException{
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection conexao = ConnectionFactory.getConnection();
        String select = "select * from funcionarios";
        PreparedStatement statement = conexao.prepareStatement(select);
        ResultSet rs = statement.executeQuery();

        while (rs.next()){
            Funcionario funcionario = map(rs);
            funcionarios.add(funcionario);
        }
        statement.close();

        return funcionarios;
    }

    public Funcionario buscar(int id)throws SQLException{
        Funcionario funcionario = new Funcionario();
        Connection conexao = ConnectionFactory.getConnection();
        String select = "select * from funcionarios where id = ?";
        PreparedStatement statement = conexao.prepareStatement(select);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();

        if(rs.next()){
            funcionario = map(rs);
        }
        statement.close();
        return funcionario;
    }
    public void editar(Funcionario funcionario)throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        String update = "update funcionarios set cpf= ?, matricula= ?, nome=?, email=?, telefone=? where id =?";
        PreparedStatement statement = conexao.prepareStatement(update);
        statement.setString(1, funcionario.getCpf());
        statement.setString(2, funcionario.getMatricula());
        statement.setString(3, funcionario.getNome());
        statement.setString(4, funcionario.getEmail());
        statement.setString(5, funcionario.getTelefone());
        statement.setInt(6, funcionario.getId());
        statement.executeUpdate();

    }
}