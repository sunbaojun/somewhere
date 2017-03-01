/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package test.videoTest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author sw
 */
public class NewJApplet extends  javax.swing.JApplet {
    private static final String MEDIA_URL = "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
//    private static final String MEDIA_URL = "file:///F:/weiyongqing/stockClient/res/video/capture-1~1.flv";//capture-1.flv

    private MediaPlayer mediaPlayer;
    JFXPanel jfxp;
    MediaControl mediaControl;
    /**
     * Initializes the applet NewJApplet
     */
    @Override
    public void init() {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJApplet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        initComponents();

        jfxp = new JFXPanel();
        jfxp.setPreferredSize(new Dimension(400, 300));
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(jfxp, BorderLayout.CENTER);
        /*
         * Create and display the applet
         */

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                
                createScene();
            }
        });


       
    }
   public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {}
                
                JFrame frame = new JFrame("JavaFX 2.0 in Swing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new NewJApplet();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                applet.start();
            }
        });
    }
    static class MediaControl extends BorderPane {
        private MediaPlayer mp;
        private MediaView mediaView;
        private final boolean repeat = false;
        private boolean stopRequested = false;
        private boolean atEndOfMedia = false;
        private Duration duration;
        private Slider timeSlider;
        private Label playTime;
        private Slider volumeSlider;
        private HBox mediaBar;
//        private static final Image PlayButtonImage = new Image(NewJFrame.class.getResourceAsStream("playbutton.png"));
 //       private static final Image PauseButtonImage = new Image(NewJFrame.class.getResourceAsStream("pausebutton.png"));
        ImageView imageViewPlay = new ImageView();
        ImageView imageViewPause = new ImageView();

        @Override protected void layoutChildren() {
            if (mediaView != null && getBottom() != null) {
                mediaView.setFitWidth(getWidth());
                mediaView.setFitHeight(getHeight() - getBottom().prefHeight(-1));
            }
            super.layoutChildren();
            if (mediaView != null) {
                mediaView.setTranslateX((((Pane)getCenter()).getWidth() - mediaView.prefWidth(-1)) / 2);
                mediaView.setTranslateY((((Pane)getCenter()).getHeight() - mediaView.prefHeight(-1)) / 2);
            }
        }

        @Override protected double computeMinWidth(double height) {
            return mediaBar.prefWidth(-1);
        }

        @Override protected double computeMinHeight(double width) {
            return 200;
        }

        @Override protected double computePrefWidth(double height) {
            return Math.max(mp.getMedia().getWidth(), mediaBar.prefWidth(height));
        }

        @Override protected double computePrefHeight(double width) {
            return mp.getMedia().getHeight() + mediaBar.prefHeight(width);
        }

        @Override protected double computeMaxWidth(double height) { return Double.MAX_VALUE; }

        @Override protected double computeMaxHeight(double width) { return Double.MAX_VALUE; }

        public MediaControl(final MediaPlayer mp) {
            this.mp = mp;
            setStyle("-fx-background-color: #bfc2c7;"); // TODO: Use css file
            mediaView = new MediaView(mp);
            Pane mvPane = new Pane() {

            };
            mvPane.getChildren().add(mediaView);
            mvPane.setStyle("-fx-background-color: black;"); // TODO: Use css file
            setCenter(mvPane);
            mediaBar = new HBox();
            mediaBar.setPadding(new Insets(5, 10, 5, 10));
            mediaBar.setAlignment(Pos.CENTER_LEFT);
            BorderPane.setAlignment(mediaBar, Pos.CENTER);

            final Button playButton  = new Button();
            playButton.setGraphic(imageViewPlay);
            playButton.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    updateValues();
                    MediaPlayer.Status status = mp.getStatus();
                    if (status == MediaPlayer.Status.UNKNOWN
                        || status == MediaPlayer.Status.HALTED)
                    {
                        // don't do anything in these states
                        return;
                    }

                    if (status == MediaPlayer.Status.PAUSED
                        || status == MediaPlayer.Status.READY
                        || status == MediaPlayer.Status.STOPPED)
                    {
                        // rewind the movie if we're sitting at the end
                        if (atEndOfMedia) {
                            mp.seek(mp.getStartTime());
                            atEndOfMedia = false;
                            playButton.setGraphic(imageViewPlay);
                            //playButton.setText(">");
                            updateValues();
                        }
                        mp.play();
                        playButton.setGraphic(imageViewPause);
                        //playButton.setText("||");
                    }
                    else {
                        mp.pause();
                    }
                }
            });
            mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                    updateValues();
                }
            });
            mp.setOnPlaying(new Runnable() {
                public void run() {
                    //System.out.println("onPlaying");
                    if (stopRequested) {
                        mp.pause();
                        stopRequested = false;
                    } else {
                        playButton.setGraphic(imageViewPause);
                        //playButton.setText("||");
                    }
                }
            });
            mp.setOnPaused(new Runnable() {
                public void run() {
                    //System.out.println("onPaused");
                    playButton.setGraphic(imageViewPlay);
                    //playButton.setText("||");
                }
            });
            mp.setOnReady(new Runnable() {
                public void run() {
                    duration = mp.getMedia().getDuration();
                    updateValues();
                }
            });
            
            mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
            mp.setOnEndOfMedia(new Runnable() {
                public void run() {
                    if (!repeat) {
                        playButton.setGraphic(imageViewPlay);
                        //playButton.setText(">");
                        stopRequested = true;
                        atEndOfMedia = true;
                    }
                }
            });
            mediaBar.getChildren().add(playButton);
            // Add spacer
            Label spacer = new Label("   ");
            mediaBar.getChildren().add(spacer);
            // Time label
            Label timeLabel = new Label("Time: ");
            mediaBar.getChildren().add(timeLabel);
            // Time slider
            timeSlider = new Slider();
            HBox.setHgrow(timeSlider, Priority.ALWAYS);
            timeSlider.setMinWidth(50);
            timeSlider.setMaxWidth(Double.MAX_VALUE);
            timeSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov) {
                    if (timeSlider.isValueChanging()) {
                        // multiply duration by percentage calculated by slider position
                        if(duration!=null) {
                            mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                        }
                        updateValues();

                    }
                }
            });
            mediaBar.getChildren().add(timeSlider);
            // Play label
            playTime = new Label();
            playTime.setPrefWidth(130);
            playTime.setMinWidth(50);
            mediaBar.getChildren().add(playTime);
            // Volume label
            Label volumeLabel = new Label("Vol: ");
            mediaBar.getChildren().add(volumeLabel);
            // Volume slider
            volumeSlider = new Slider();
            volumeSlider.setPrefWidth(70);
            volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
            volumeSlider.setMinWidth(30);
            volumeSlider.valueProperty().addListener(new InvalidationListener() {
                public void invalidated(Observable ov) {
                }
            });
            volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if (volumeSlider.isValueChanging()) {
                        mp.setVolume(volumeSlider.getValue() / 100.0);
                    }
                }
            });
            mediaBar.getChildren().add(volumeSlider);
            setBottom(mediaBar);
        }

        protected void updateValues() {
            if (playTime != null && timeSlider != null && volumeSlider != null && duration != null) {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Duration currentTime = mp.getCurrentTime();
                        playTime.setText(formatTime(currentTime, duration));
                        timeSlider.setDisable(duration.isUnknown());
                        if (!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging()) {
                            timeSlider.setValue(currentTime.divide(duration).toMillis() * 100.0);
                        }
                        if (!volumeSlider.isValueChanging()) {
                            volumeSlider.setValue((int) Math.round(mp.getVolume() * 100));
                        }
                    }
                });
            }
        }

        private static String formatTime(Duration elapsed, Duration duration) {
            int intElapsed = (int)Math.floor(elapsed.toSeconds());
            int elapsedHours = intElapsed / (60 * 60);
            if (elapsedHours > 0) {
                intElapsed -= elapsedHours * 60 * 60;
            }
            int elapsedMinutes = intElapsed / 60;
            int elapsedSeconds = intElapsed - elapsedHours * 60 * 60 - elapsedMinutes * 60;

            if (duration.greaterThan(Duration.ZERO)) {
                int intDuration = (int)Math.floor(duration.toSeconds());
                int durationHours = intDuration / (60 * 60);
                if (durationHours > 0) {
                    intDuration -= durationHours * 60 * 60;
                }
                int durationMinutes = intDuration / 60;
                int durationSeconds = intDuration - durationHours * 60 * 60 - durationMinutes * 60;

                if (durationHours > 0) {
                    return String.format("%d:%02d:%02d/%d:%02d:%02d",
                                         elapsedHours, elapsedMinutes, elapsedSeconds,
                                         durationHours, durationMinutes, durationSeconds);
                } else {
                    return String.format("%02d:%02d/%02d:%02d",
                                         elapsedMinutes, elapsedSeconds,
                                         durationMinutes, durationSeconds);
                }
            } else {
                if (elapsedHours > 0) {
                    return String.format("%d:%02d:%02d",
                                         elapsedHours, elapsedMinutes, elapsedSeconds);
                } else {
                    return String.format("%02d:%02d",
                                         elapsedMinutes, elapsedSeconds);
                }
            }
        }
    }
    private void createScene() {
        mediaPlayer = new MediaPlayer(new Media(MEDIA_URL));
        mediaPlayer.setAutoPlay(true);
        mediaControl = new MediaControl(mediaPlayer);
        mediaControl.setMinSize(480,280);
        mediaControl.setPrefSize(480,280);
        mediaControl.setMaxSize(480,280);
        jfxp.setScene(new Scene(mediaControl));
//        jfxp.setScene(new Scene(mediaControl));

  
    }
    @Override
    public void stop(){
        mediaPlayer.stop();
    }
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        
    // Variables declaration - do not modify                     
    private javax.swing.JPanel jPanel1;
    // End of variables declaration                   
}
