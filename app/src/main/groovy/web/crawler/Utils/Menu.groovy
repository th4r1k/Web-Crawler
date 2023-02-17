package web.crawler.Utils

import web.crawler.Services.ComponenteOrganizacional
import web.crawler.Services.HistoricoComponentes
import web.crawler.Services.TabelasRelacionadas

class Menu {

    static start() {
        System.out.println("1 - Baixar Componente Organizacional Pdf")
        System.out.println("2 - Baixar Tabelas Relacionadas Xlsx")
        System.out.println("3 - Scrap Historico Componentes")
        System.out.println("4 - Baixar todos de uma vez")
        System.out.println("0 - Sair")

        System.out.println("Digite o codigo do comando: ")
        Scanner input = new Scanner(System.in)
        String data = input.nextLine()

        switch (data) {

            case "1":
                ComponenteOrganizacional.downloadFile(ComponenteOrganizacional.getLink())
                goBack()
                break

            case "2": {
                TabelasRelacionadas.downloadFile(TabelasRelacionadas.getLink())
                goBack()
                break
            }

            case "3":
                HistoricoComponentes.createFile()
                HistoricoComponentes.populateFile(HistoricoComponentes.getData())
                goBack()
                break

            case "4":
                ComponenteOrganizacional.downloadFile(ComponenteOrganizacional.getLink())
                TabelasRelacionadas.downloadFile(TabelasRelacionadas.getLink())
                HistoricoComponentes.createFile()
                HistoricoComponentes.populateFile(HistoricoComponentes.getData())
                goBack()
                break


            case "0": {
                input.close()
                System.out.println("Volte sempre!")
                break
            }

            default:
                System.out.println("Opcao invalida")
                start()
        }
    }
       static goBack() {

            System.out.println("")
            System.out.println("________________________________")
            System.out.println("Digite 1 para voltar ao menu")
            Scanner input = new Scanner(System.in)
            String data = input.nextLine()

            switch (data) {
                case "1":
                    start()
                    break

                default:
                    input.close()
                    break
            }


        }
    }
