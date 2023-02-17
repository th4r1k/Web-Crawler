package web.crawler.Services

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*
import web.crawler.Services.ComponenteOrganizacional

class ComponenteOrganizacionalTeste {

    @Test
   void getLinkTeste(){
        //given
        String expected = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/PadroTISS_ComponenteOrganizacional_202302.pdf"

        //when
        String result = ComponenteOrganizacional.getLink()

        //then
        assertEquals(result, expected)

    }

}
