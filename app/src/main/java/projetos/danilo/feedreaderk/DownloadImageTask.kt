/*
 *
  Created by Danilo Valerio da Silva on 12 / 2019

 */

package projetos.danilo.feedreaderk

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class DownloadImageTask(val imageView: ImageView) : AsyncTask<String, Void, Bitmap>(){
    override fun doInBackground(vararg params: String?): Bitmap {
        //vai pegar a url que está na primeira posição
        val url = params[0]

        val stream = URL(url).openStream()
        val bitmap = BitmapFactory.decodeStream(stream)

        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }

}