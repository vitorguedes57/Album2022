package br.com.cidandrade.album.modelo.dao;

import br.com.cidandrade.album.modelo.bd.AlbumBD;
import br.com.cidandrade.album.modelo.entidade.Irmao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IrmaoDAO {

    private static final String INSERIR_SQL = "Insert into irmao "
            + "(nome,contato) "
            + "values ('%s','%s')";
    private static final String ALTERAR_SQL = "Update irmao "
            + "set nome='%s', contato='%s' where id=%d";
    private static final String REMOVER_SQL = "delete from irmao "
            + "where id=%d";
    private static final String SELECIONAR_SQL = "Select * from irmao";

    public static void inserir(Irmao irmao) {
        String sql = String.format(INSERIR_SQL, 
                irmao.getNome(), irmao.getContato());
        AlbumBD.execute(sql, true);
    }

    public static void alterar(Irmao irmao) {
        String sql = String.format(ALTERAR_SQL,
                irmao.getNome(), irmao.getContato(),
                irmao.getId());
        AlbumBD.execute(sql, true);
    }

    public static void remover(Irmao irmao) {
        String sql = String.format(REMOVER_SQL, irmao.getId());
        AlbumBD.execute(sql, true);
    }

    public static List<Irmao> selecionarTodos() {
        List<Irmao> lista = new ArrayList<>();
        Connection con = AlbumBD.conectar();
        try {
            ResultSet rs = con.createStatement()
                    .executeQuery(SELECIONAR_SQL);
            while (rs.next()) {
                byte id = rs.getByte("id");
                String nome = rs.getString("nome");
                String contato = rs.getString("contato");
                lista.add(new Irmao(id, nome, contato));
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

}
