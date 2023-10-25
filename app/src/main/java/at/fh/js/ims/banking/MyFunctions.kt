package at.fh.js.ims.banking

import android.content.Context
import android.media.MediaPlayer

class MyFunctions {

    companion object {
        fun playNo(context: Context) {
            val NoSound = MediaPlayer.create(context, R.raw.no)

            NoSound.setOnCompletionListener {
                it.release()
            }
            NoSound.start()
        }

        fun playSkibididopdop(context: Context) {
            val skibidiSound = MediaPlayer.create(context, R.raw.brrskibidi)

            skibidiSound.setOnCompletionListener {
                it.release()
            }
            skibidiSound.start()
        }


    }
}