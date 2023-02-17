package web.crawler.Services

import groovyx.net.http.optional.Download
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

class ComponenteOrganizacional {

    static String initialPage = 'https://www.gov.br/ans/pt-br'

    static String getLink() {
        String link =""
        try{
        Document ansPage = configure { request.uri = initialPage }.get()
        String prestadorDeServicoLink = ansPage.getElementsByClass("cover-banner-tile tile-content")[2].select("a").attr("href")

        Document prestadorDeServicoPage = configure { request.uri = prestadorDeServicoLink }.get()
        String tissLink = prestadorDeServicoPage.getElementsByClass("govbr-card-content")[0].select("a").attr("href")

        Document tissPage = configure { request.uri = tissLink }.get()
        String padraoTissLink = tissPage.getElementsByClass("callout").select("a").attr("href")

        Document padraoTissPage = configure { request.uri = padraoTissLink }.get()
         link = padraoTissPage.getElementsByClass("table table-bordered").select("a").attr("href")
        }catch (Exception e){
            println("Nao foi possivel chegar ao link do Componente Organizacional")
        }
        return link
    }

   static downloadFile(String link) {
        try {
            configure {
                request.uri = link
            }.get() {
                Download.toFile(delegate, "application/pdf", new File('./downloads/componenteOrganizacional.pdf'))
                println("Download do componente Organizacional concluido")
            }
        } catch (Exception e) {
            println("Nao foi possivel baixar o arquivo Componente Organizacional, verificar se os links estao ok")
        }

    }
}