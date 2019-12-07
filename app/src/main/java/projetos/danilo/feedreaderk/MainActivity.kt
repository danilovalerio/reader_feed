package projetos.danilo.feedreaderk

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

    val listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //retorno é assíncrono, logo não precisamos de uma synctask
        PkRSS.with(this).load("http://rss.tecmundo.com.br/feed").callback(this).async()
    }

    override fun onLoadFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPreload() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        //?verifica se é nulo e mapeia de new articles para nossa lista de item
        newArticles?.mapTo(listItens){
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }
    }

    data class Item(val titulo:String, val autor: String, val data: Long, val link: Uri, val imagem: String)
}
