package web.crawler.Services

import org.jsoup.nodes.Document
import org.jsoup.select.Elements

import java.text.SimpleDateFormat

import static groovyx.net.http.HttpBuilder.configure

class HistoricoComponentes {

    static String initialPage = 'https://www.gov.br/ans/pt-br'

    static Elements getData() {

        Elements data = null
        try{
        Document ansPage = configure { request.uri = initialPage }.get()
        String prestadorDeServicoLink = ansPage.getElementsByClass("cover-banner-tile tile-content")[2].select("a").attr("href")

        Document prestadorDeServicoPage = configure { request.uri = prestadorDeServicoLink }.get()
        String tissLink = prestadorDeServicoPage.getElementsByClass("govbr-card-content")[0].select("a").attr("href")

        Document tissPage = configure { request.uri = tissLink }.get()
        String padraoTissHistoricoLink = tissPage.getElementsByClass("callout").select("a")[1].attr("href")

        Document padraoTissHistoricoPage = configure { request.uri = padraoTissHistoricoLink }.get()
            data = padraoTissHistoricoPage.getElementsByTag('tbody').select("td")
        }catch (Exception e){
            println("Nao foi possivel chegar ao link do Histórico das versões dos Componentes do Padrão TISS")
        }
        return data

    }

    static createFile(){
        File tabelaHistoricoTiss = new File('./downloads/tabelaHistoricoTiss.csv')
        FileWriter fw = new FileWriter(tabelaHistoricoTiss, true)
        if(tabelaHistoricoTiss.length() == 0)
        {
            fw.write("competencia" + "," + "publicacao " + "," + "inicio de vigencia")
            fw.append("\n")
        }
        fw.close()
    }

    static populateFile(Elements dados){
        File tabelaHistoricoTiss = new File('./downloads/tabelaHistoricoTiss.csv')

        // Verifica se o arquivo esta apenas com o cabecalho
        if (tabelaHistoricoTiss.length() == 43){

        FileWriter fw = new FileWriter(tabelaHistoricoTiss, true)
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy")
        Date limitDate = format.parse("01/01/2016")
        for (int i = 0; i < dados.size(); i = i+ 9) {

            String competencia = dados[i].text()
            String publicacao = dados[i + 1].text()
            Date inicio_de_vigencia = format.parse(dados[i + 2].text())

            if (inicio_de_vigencia >= limitDate) {
                fw.write(competencia + "," + publicacao + "," + dados[i + 2].text())
                fw.append("\n")

            }
        }
        println("Salvo na pasta downloads o scrap do Histórico das versões dos Componentes do Padrão TISS")
        fw.close()
        }else{
            println("Arquivo ja existe dentro da pasta de downloads")
        }
    }

}
