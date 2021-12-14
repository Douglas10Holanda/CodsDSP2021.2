package list6.DAO;

import list6.MODEL.Funcionario;
import list6.UTILS.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioJPADAO implements FuncionarioDAO {

    @Autowired
    EntityManager entMan;

    @Override
    public void adicionar(Funcionario funcionario) throws SQLException {
        entMan = JPAUtil.getEntityManager();
        JPAUtil.beginTransaction();
        entMan.persist(funcionario);
        JPAUtil.commit();
    }

    @Override
    public void deletar(int id) throws SQLException {
        entMan = JPAUtil.getEntityManager();
        Funcionario funcionario = buscar(id);
        entMan.getTransaction().begin();
        entMan.remove(funcionario);
        JPAUtil.commit();
    }

    @Override
    public List<Funcionario> buscarTodos() throws SQLException {
        entMan = JPAUtil.getEntityManager();
        List<Funcionario> funcionarios = entMan.createQuery("select f from Funcionario as f", Funcionario.class).getResultList();
        return funcionarios;
    }

    @Override
    public Funcionario buscar(int id) throws SQLException {
        entMan = JPAUtil.getEntityManager();
        return entMan.find(Funcionario.class, id);
    }

    @Override
    public void editar(Funcionario funcionario) throws SQLException {
        entMan = JPAUtil.getEntityManager();
        entMan.merge(funcionario);
        JPAUtil.commit();
    }
}
