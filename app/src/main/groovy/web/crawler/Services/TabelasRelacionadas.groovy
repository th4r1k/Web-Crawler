package web.crawler.Services

import groovyx.net.http.optional.Download
import org.jsoup.nodes.Document

import static groovyx.net.http.HttpBuilder.configure

class TabelasRelacionadas {

    static String initialPage = 'https://www.gov.br/ans/pt-br'

    static String getLink() {

        String link =""
        try{
        Document ansPage = configure { request.uri = initialPage }.get()
        String prestadorDeServicoLink = ansPage.getElementsByClass("cover-banner-tile tile-content")[2].select("a").attr("href")

        Document prestadorDeServicoPage = configure { request.uri = prestadorDeServicoLink }.get()
        String tissLink = prestadorDeServicoPage.getElementsByClass("govbr-card-content")[0].select("a").attr("href")

        Document tissPage = configure { request.uri = tissLink }.get()
        String padraoTissTabelasRelacionadasLink = tissPage.getElementsByClass("callout").select("a")[2].attr("href")

        Document padraoTissTabelasRelacionadas = configure { request.uri = padraoTissTabelasRelacionadasLink }.get()
        link = padraoTissTabelasRelacionadas.getElementsByClass("callout").select("a").attr("href")
        }catch (Exception e){
            println("Nao foi possivel chegar ao link da Tabela de erros no envio para ANS")
        }
        return link
    }

    static downloadFile(String link) {
        try {
            configure {
                request.uri = link
            }.get() {
                Download.toFile(delegate, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", new File('./downloads/tabelasDeErro.xlsx'))
                println("Download da tabela Erros no Envio para ANS concluido")
            }
        } catch (Exception) {
            println("Nao foi possivel baixar o arquivo de Erros no Envio para a ANS, verificar se os links estao ok")
        }
    }

}
