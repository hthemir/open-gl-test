package com.example.hugo.testopengl;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by Hugo on 11/10/2017.
 */

public class OpenGLES20Activity extends Activity {

    //substitui views (botoes, textos, listas)
    private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //instancia a GLSurfaceView
        mGLView = new MyGLSurfaceView(this);
        //estabelece a mGLView como view da activity
        setContentView(mGLView);
    }

    //GLSurfaceView eh uma view onde se desenham graficos OpenGL ES
    class MyGLSurfaceView extends GLSurfaceView {

        private final MyGLRenderer mRenderer;

        public MyGLSurfaceView(Context context) {
            super(context);

            //cria um contexto OpenGL ES 2.0
            setEGLContextClientVersion(2);

            //instancia o renderer
            mRenderer = new MyGLRenderer();
            //estabelece o mRenderer como o renderer para desenhar nesta view
            setRenderer(mRenderer);

            //opcional:
            //renderiza a view somente quando ocorre mudanca nos dados de desenho
            //evita que a view seja redesenhada ate ser chamado requestRender()
            //setRenderMode(RENDERMODE_WHEN_DIRTY);
        }
    }
}
