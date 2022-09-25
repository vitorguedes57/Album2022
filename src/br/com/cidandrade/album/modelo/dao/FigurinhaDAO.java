package br.com.cidandrade.album.modelo.dao;

import br.com.cidandrade.album.modelo.bd.AlbumBD;
import br.com.cidandrade.album.modelo.entidade.Figurinha;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FigurinhaDAO {

    private static final String INSERIR_SQL = "Insert into figurinha "
            + "(numero,pagina,descricao,qtde) "
            + "values (%d,%d,'%s',%d)";
    private static final String ALTERAR_SQL = "Update figurinha "
            + "set descricao='%s', qtde=%d where numero=%d";
    private static final String ALTERAR_QUANTIDADE_SQL = "Update figurinha "
            + "set qtde=%d where numero=%d";
    private static final String ALTERAR_DESCRICAO_SQL = "Update figurinha "
            + "set  descricao='%s' where numero=%d";
    private static final String REMOVER_SQL = "delete from figurinha "
            + "where numero=%d";
    private static final String SELECIONAR_SQL = "Select * from figurinha";
    private static final String SELECIONAR_PAGINA_SQL = "Select * "
            + "from figurinha where pagina=%d";

    public static void inserir(Figurinha figurinha) {
        String sql = String.format(INSERIR_SQL,
                figurinha.getNumero(),
                figurinha.getPagina(),
                figurinha.getDescricao(),
                figurinha.getQuantidade());
        AlbumBD.execute(sql, true);
    }

    public static void inserir(int numero) {
        String sql = String.format(INSERIR_SQL,
                numero, (int) Math.ceil(numero / 10) + 1,
                "Indefinida", 0);
        AlbumBD.execute(sql, true);
    }

    public static void alterar(Figurinha figurinha) {
        String sql = String.format(ALTERAR_SQL,
                figurinha.getDescricao(),
                figurinha.getQuantidade(),
                figurinha.getNumero());
        AlbumBD.execute(sql, true);
    }

    public static void alterarQuantidade(Integer numero, Integer quantidade) {
        String sql = String.format(ALTERAR_QUANTIDADE_SQL,
                quantidade, numero);
        AlbumBD.execute(sql, true);
    }

    public static void alterarDescricao(Integer numero, String descricao) {
        String sql = String.format(ALTERAR_DESCRICAO_SQL,
                descricao, numero);
        AlbumBD.execute(sql, true);
    }

    public static void remover(Figurinha figurinha) {
        String sql = String.format(REMOVER_SQL,
                figurinha.getNumero());
        AlbumBD.execute(sql, true);
    }

    private static List<Figurinha> selecionar(String sql) {
        List<Figurinha> lista = new ArrayList<>();
        Connection con = AlbumBD.conectar();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                int numero = rs.getInt("numero");
                byte pagina = rs.getByte("pagina");
                String descricao = rs.getString("descricao");
                byte quantidade = rs.getByte("qtde");
                lista.add(new Figurinha(numero, pagina,
                        descricao, quantidade));
            }
            AlbumBD.desconectar(con);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

    public static List<Figurinha> selecionarTodos() {
        return selecionar(SELECIONAR_SQL);
    }

    public static List<Figurinha> selecionarPorPagina(int pagina) {
        return selecionar(String.format(
                SELECIONAR_PAGINA_SQL, pagina));
    }
}
