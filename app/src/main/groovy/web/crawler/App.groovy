package web.crawler

import web.crawler.Utils.Menu

class App {

    static void main(String[] args) {

        File downloadFolder = new File("./downloads")
        downloadFolder.mkdir()
        Menu.start()

    }
}
