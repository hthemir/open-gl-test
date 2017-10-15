package com.example.hugo.testopengl;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

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

        private final float TOUCH_SCALE_FACTOR = 180f/320;
        private float mPreviousX;
        private float mPreviousY;

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
            setRenderMode(RENDERMODE_WHEN_DIRTY);
        }

        //motion events informam detalhes sobre a tela de touch e outros comandos de entrada
        //neste caso, sera de interesse somente quando a posicao do toque mudar
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    float dx = x - mPreviousX;
                    float dy = y - mPreviousY;

                    //inverte a direcao da rotacao acima do centro
                    if (y > getHeight()/2) {
                        dx = dx * -1;
                    }

                    //inverte a direcao da rotacao aa esquerda do centro
                    if (x < getWidth()/2) {
                        dy = dy * -1;
                    }

                    mRenderer.setAngle(mRenderer.getAngle() + ((dx + dy) * TOUCH_SCALE_FACTOR));
                    requestRender();
            }
            mPreviousX = x;
            mPreviousY = y;
            return true;
        }
    }
}
