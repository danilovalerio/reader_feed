package projetos.danilo.feedreaderk

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Callback {

    lateinit var listView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
    val listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //gerenciador de layout
        val layout = LinearLayoutManager(this)

        listView = rv
        listView.layoutManager = layout

        adapter = ItemAdapter(listItens, this)
        listView.adapter = adapter

        //retorno é assíncrono, logo não precisamos de uma synctask
        PkRSS.with(this).load("http://rss.tecmundo.com.br/feed").callback(this).async()
    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        listItens.clear()
        //?verifica se é nulo e mapeia de new articles para nossa lista de item
        newArticles?.mapTo(listItens){
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }

        adapter.notifyDataSetChanged()
    }

    override fun onLoadFailed() {

    }

    override fun onPreload() {

    }

    data class Item(val titulo:String, val autor: String, val data: Long, val link: Uri, val imagem: String)
}
