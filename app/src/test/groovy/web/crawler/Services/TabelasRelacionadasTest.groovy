package web.crawler.Services

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals


class TabelasRelacionadasTest {

    @Test
    void getLinkTest(){
        //given
        String expected = "https://www.gov.br/ans/pt-br/arquivos/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-tiss/padrao-tiss-tabelas-relacionadas/padrao-tiss-tabela-erros-envio-para-ans-padrao-tiss-08022019.xlsx"

        //when
        String result = TabelasRelacionadas.getLink()

        //then
        assertEquals(result, expected)

    }

}

