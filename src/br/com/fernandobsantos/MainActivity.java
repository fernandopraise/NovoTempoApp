package br.com.fernandobsantos;

import java.io.IOException;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.*;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
/*
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
*/
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
//senha geracao fbs221079

public class MainActivity extends Activity {	
	
	private SeekBar volumeSeekBar;
	private AudioManager audioManager;		
	private ProgressDialog pDialog;

    //AQUI COMEÇA AS VARIAVEIS DA MEDIA PLAYER
	static MediaPlayer mPlayer;	
	//String url = "http://174-36-1-94.webnow.net.br:80/alpha.mp3";
	
	String url = "http://sysrad.net:31800/";	
	
	//String url = "http://stream.novotempo.com/radio/smil:rntCampinasSP.smil/playlist.m3u8";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		
		
		//showNotification();
		
		//CRIANDO OS BOTOES DO LAYOUT activity_main
		
		final ImageButton stop = (ImageButton) findViewById(R.id.stop);				
        final ImageButton play = (ImageButton) findViewById(R.id.play);
        final ImageButton sair = (ImageButton) findViewById(R.id.txt_sair);
        final ImageButton informacao = (ImageButton) findViewById(R.id.informacao);
        final ImageView facebook = (ImageView) findViewById(R.id.facebook);
        final ImageView twitter = (ImageView) findViewById(R.id.twitter);
        final ImageView feed = (ImageView) findViewById(R.id.feed);
        final ImageView youtube = (ImageView) findViewById(R.id.youtube);    
        boolean conexao = TestarConexao.verificaConexao(this);
             
        
        //VERIFICA SE NÃO TEM CONEXÃO COM A INTERNET
        if(!conexao){ 
        	play.setEnabled(false);
        	stop.setEnabled(false);
        	facebook.setEnabled(false);
        	twitter.setEnabled(false);
        	youtube.setEnabled(false);
        	feed.setEnabled(false);
        	
        	feed.isOpaque();

        	//CRIANDO ALERTA SOBRE CONECTIVIDADE
        	new AlertDialog.Builder(MainActivity.this)
        	.setTitle("Aviso do Sistema")
        	.setMessage("Ops, Não encontramos conexão com a Internet, verifique!!!")
        	.setIcon(R.drawable.ic_launcher)
        	.setNeutralButton("Ok", null)
        	.show();
        	
        }
	        	    
        //POR DEFAULT BOTAO STOP DESABILITADO
        stop.setEnabled(false);         
        
	        //AO CLICAR NO BOTÃO PLAY
	        play.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	               
		            pDialog = new ProgressDialog(MainActivity.this);
		            pDialog.setMessage("Carregando, aguarde...");
		            pDialog.setIndeterminate(false);
		            pDialog.setCancelable(false);
		            pDialog.show();	 
	            	
	                //MUDA STATUS DOS BOTÕES
	            	play.setEnabled(false); 
	                stop.setEnabled(true); 	               	             
	                
	                
	               //MOSTRA MENSAGEM 
	              // Toast.makeText(getApplicationContext(), "Abrindo o Áudio", Toast.LENGTH_SHORT).show();
	               
	                //CRIANDO MEDIA PLAYER
					mPlayer = new MediaPlayer();
					mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);                                                    
	        
	                       try {                          
	                               mPlayer.setDataSource(url);
	                           } catch (IOException ex) {
	                               Toast.makeText(getApplicationContext(), "1 - You might not set the URI correctly! " + ex, Toast.LENGTH_LONG).show();
	                           } catch (IllegalArgumentException ex) {
	                               Toast.makeText(getApplicationContext(), "2 - You might not set the URI correctly! " + ex, Toast.LENGTH_LONG).show();
	                           } catch (SecurityException ex) {
	                               Toast.makeText(getApplicationContext(), "3 - You might not set the URI correctly! " + ex, Toast.LENGTH_LONG).show();
	                           } catch (IllegalStateException ex) {
	                               Toast.makeText(getApplicationContext(), "4 - You might not set the URI correctly! " + ex, Toast.LENGTH_LONG).show();
	                           }
	                           
	                           mPlayer.setLooping(true);
	                           mPlayer.prepareAsync();   
	                           
	                           mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
	                   	        public void onPrepared(MediaPlayer mp) {	                   	                 

	                   	            mPlayer.start();
	                   	            pDialog.dismiss(); 
	                   	          }
	                   	       });

	                           mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
	                   	        public void onCompletion(MediaPlayer mp) {	                   	            	                      	       
	                   	            
	                   	            	                  	            
	                   	        	
	                   	         }
	                   	       });

	                           mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
	                   	        public boolean onError(MediaPlayer mp, int what, int extra) {
	                   	            if(pDialog != null) {
	                   	            	pDialog.dismiss();
	                   	            }

	                   	            return false;
	                   	        }
	                   	    });
	                   			   
	            }
	        });    
	        
	        stop.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {          	
	            	Toast.makeText(getApplicationContext(), "Ops, o áudio foi parado!!!", Toast.LENGTH_SHORT).show();
	            	
	            	//MUDA STATUS DOS BOTÕES
	            	stop.setEnabled(false); 
	            	play.setEnabled(true); 
	            	
					if(mPlayer!=null && mPlayer.isPlaying()){
						mPlayer.stop();
	                    mPlayer.release();
					}                                     	               
	            }
	        });   
	       
        
        
        
        sair.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
			   
			//   cancelNotification(1234);	
				
               Toast.makeText(getApplicationContext(), "Saindo do Sistema", Toast.LENGTH_SHORT).show();              
               
               System.exit(0);
            }
        });    	
                             
        informacao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
															
	        	//CRIANDO ALERTA SOBRE CONECTIVIDADE
	        	new AlertDialog.Builder(MainActivity.this)
	        	.setTitle("Sobre o Desenvolvedor")
	        	.setMessage("Fernando Santos - Acesse meu site www.fernandobsantos.com.br")
	        	.setIcon(R.drawable.ic_launcher)
	        	.setNeutralButton("Ok", null)
	        	.show();
	        				
			}
		});
        
        facebook.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/novotempocampinas"));
					
				startActivity(intent);
				
				Toast.makeText(getApplicationContext(), "Legal, vamos abrir o Facebook, curta nossa página!!!", Toast.LENGTH_SHORT).show();
			}
		});               
        
        twitter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com/novotempo830"));
				
				startActivity(intent);
				
				Toast.makeText(getApplicationContext(), "Vamos ao Twitter, siga a Novo Tempo Campinas!!!", Toast.LENGTH_SHORT).show();
			}
		});  
               
        feed.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.novotempocampinas.com.br"));

				startActivity(intent);
				
				Toast.makeText(getApplicationContext(), "Notícias da Novo Tempo e do Mundo!!!", Toast.LENGTH_SHORT).show();
			}
		});         
        
        youtube.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/user/ApaCVideos"));

				startActivity(intent);
				
				Toast.makeText(getApplicationContext(), "Assista nossos vídeos e compartilhe!!!", Toast.LENGTH_SHORT).show();
			}
		});                  
        
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        initControls();
        
		
	}

	//CONTROLES DO VOLUME DO ÁUDIO
	private void initControls() {

     try{
    	 volumeSeekBar = (SeekBar) findViewById(R.id.sb);
    	 audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    	 volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
    	 volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
    	 
    	 volumeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
				
			}
		});
    	 
     } catch (Exception e){
    	 
     }
		
	}

 @Override
protected void onDestroy() {
	super.onDestroy();
	if (mPlayer != null) {
		mPlayer.release();
		mPlayer = null;
	}
  }
 
 /* VERSÃO 2.2 DO ANDROID NAO FUNCIONA COM NOTIFICAÇÕES
  * 
 //NOTIFICAÇÃO NO STATUS BAR DO TELEFONE
	public void showNotification(){

		
		final int NOTIF_ID = 1234;  
		
		 NotificationManager notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
		 
		 Notification note = new Notification(R.drawable.ic_launcher, "Novo Tempo Campinas", System.currentTimeMillis());  
		 
		 PendingIntent intent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);  
		 
		 note.setLatestEventInfo(this, "Novo Tempo Campinas", "Ouça a Novo Tempo", intent);  
		 
		 notifManager.notify(NOTIF_ID, note); 

	}
	
	public void cancelNotification(int notificationId){		
         if (Context.NOTIFICATION_SERVICE!=null) {
             String ns = Context.NOTIFICATION_SERVICE;
             NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
             nMgr.cancel(notificationId);
         }
	}     
 */
}
