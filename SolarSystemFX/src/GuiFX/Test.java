package GuiFX;


import javafx.animation.KeyFrame;
import javafx.application.Application;

import javafx.scene.Camera;
import javafx.scene.Group;

import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;

import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.animation.Timeline;

import javafx.util.Duration;

import java.net.URL;

public class Test extends Application {
    CelestialBody[] planet = new CelestialBody[9];

    private static final float WIDTH = 1400;
    private static final float HEIGHT = 800;

    Group world = new Group();
    Group Moon = new Group();
    Group MoonGhost = new Group();

    private Sphere[] pl;


    @Override
    public void start(Stage primaryStage) {

        planet[0] = new CelestialBody("Sun", 0, 0, 0, 0, 0, 0, RealMasses.SUN_MASS);
        planet[1] = new CelestialBody("Mercury", RealDistance.mercuryXDistance, RealDistance.mercuryYDistance,
                RealDistance.mercuryZDistance, RealVelocities.mercuryXVel, RealVelocities.mercuryYVel,
                RealVelocities.mercuryZVel, RealMasses.MERCURY_MASS);
        planet[2] = new CelestialBody("Venus", RealDistance.venusXDistance, RealDistance.venusYDistance,
                RealDistance.venusZDistance, RealVelocities.venusXVel, RealVelocities.venusYVel,
                RealVelocities.venusZVel, RealMasses.VENUS_MASS);
        planet[3] = new CelestialBody("Earth", RealDistance.earthXDistance, RealDistance.earthYDistance,
                RealDistance.earthZDistance, RealVelocities.earthXVel, RealVelocities.earthYVel,
                RealVelocities.earthZVel, RealMasses.EARTH_MASS);
        planet[4] = new CelestialBody("Mars", RealDistance.marsXDistance, RealDistance.marsYDistance,
                RealDistance.marsZDistance, RealVelocities.marsXVel, RealVelocities.marsYVel, RealVelocities.marsZVel,
                RealMasses.MARS_MASS);
        planet[5] = new CelestialBody("Jupiter", RealDistance.jupiterXDistance, RealDistance.jupiterYDistance,
                RealDistance.jupiterZDistance, RealVelocities.jupiterXVel, RealVelocities.jupiterYVel,
                RealVelocities.jupiterZVel, RealMasses.JUPITER_MASS);
        planet[6] = new CelestialBody("Saturn", RealDistance.saturneXDistance, RealDistance.saturneYDistance,
                RealDistance.saturneZDistance, RealVelocities.saturnXVel, RealVelocities.saturnYVel,
                RealVelocities.saturnZVel, RealMasses.SATURNE_MASS);
        planet[7] = new CelestialBody("Uranus", RealDistance.uranusXDistance, RealDistance.uranusYDistance,
                RealDistance.uranusZDistance, RealVelocities.uranusXVel, RealVelocities.uranusYVel,
                RealVelocities.uranusZVel, RealMasses.URANUS_MASS);
        planet[8] = new CelestialBody("Neptune", RealDistance.neptuneXDistance, RealDistance.neptuneYDistance,
                RealDistance.neptuneZDistance, RealVelocities.neptuneXVel, RealVelocities.neptuneYVel,
                RealVelocities.neptuneZVel, RealMasses.NEPTUNE_MASS);


        PhongMaterial mercuryMaterial = new PhongMaterial();
        mercuryMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("images/mercury.jpeg")));

        PhongMaterial sunMaterial = new PhongMaterial();
        sunMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("images/sun.jpeg")));

        PhongMaterial venusMaterial = new PhongMaterial();
        venusMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("images/venus.jpeg")));

        PhongMaterial earthMaterial = new PhongMaterial();
        earthMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("images/earth/earth.normal.jpeg")));

        pl = new Sphere[planet.length];

        for (int i = 0; i < pl.length; i++) {

            pl[i] = new Sphere();
            pl[i].setTranslateX(planet[i].getXPosition() / 1000000000);
            pl[i].setTranslateY(planet[i].getYPosition() / 1000000000);
            pl[i].setTranslateZ(planet[i].getZPosition() / 1000000000);
            if (i == 0) {
                pl[i].setMaterial(sunMaterial);
                pl[i].setRadius(25);
            }
            if (i == 1) {
                pl[i].setMaterial(mercuryMaterial);
                pl[i].setRadius(7.5);
            }
            if (i == 2) {
                pl[i].setMaterial(venusMaterial);
            }
            if (i == 3) {
                pl[i].setMaterial(earthMaterial);
                pl[i].setRadius(12.5);
            }
            world.getChildren().add(pl[i]);
        }

        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(10000);
        camera.translateZProperty().set(-1000);


        Slider slider = prepareSlider();
        world.translateZProperty().bind(slider.valueProperty());
        Moon.translateZProperty().bind(slider.valueProperty());


        URL resource = getClass().getResource("travis.mp3");
        Media media = new Media(resource.toString());

        MediaPlayer player = new MediaPlayer(media);
        //player.play();

        Group root = new Group();
        root.getChildren().add(prepareImageView());
        root.getChildren().add(MoonGhost);
        root.getChildren().add(world);
        root.getChildren().add(Moon);
        root.getChildren().add(slider);

        Scene scene = new Scene(root, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);
        //scene.getStylesheets().add("flatterAdd.css");
        //initMouseControl(world, scene, primaryStage);

        primaryStage.setTitle("Galaxy");
        primaryStage.setScene(scene);
        primaryStage.show();

        prepareAnimation();
    
/*
    Thread thread =  new Thread() {
     
    	@Override
        public void run() {
        	MoonCoords();                                                                     
        }
    };    
    thread.start();
*/

    }

    private void prepareAnimation() {
        /*AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //rotation around own axis
                sphere.rotateProperty().set(sphere.getRotate() + 1);
                sphere2.rotateProperty().set(sphere2.getRotate() + 0.2);
                sphereMoon.rotateProperty().set(sphereMoon.getRotate() + 1);

                Rotate xRotate;
                Rotate zRotate;
                Rotate yRotate;
                world.getTransforms().addAll(
                        xRotate = new Rotate(0, Rotate.X_AXIS),
                        yRotate = new Rotate(0, Rotate.Y_AXIS),
                        zRotate = new Rotate(0, Rotate.Z_AXIS)

                );

                Rotate xRotateMoon;
                Rotate yRotateMoon;
                Moon.getTransforms().addAll(
                        xRotateMoon = new Rotate(0, Rotate.X_AXIS),
                        yRotateMoon = new Rotate(0, Rotate.Y_AXIS)
                );
                yRotateMoon.pivotYProperty().bind(EarthAxis);

                xRotate.angleProperty().bind(angleX);
                yRotate.angleProperty().bind(angleY);
                zRotate.angleProperty().bind(angleZ);
                xRotateMoon.angleProperty().bind(angleX);
                yRotateMoon.angleProperty().bind(angleY);

                //angle defines the way of rotation
                //angleX.set(1);
                angleZ.set(1);




            }
        };*/
        planet[0] = new CelestialBody("Sun", 0, 0, 0, 0, 0, 0, RealMasses.SUN_MASS);
        planet[1] = new CelestialBody("Mercury", RealDistance.mercuryXDistance, RealDistance.mercuryYDistance,
                RealDistance.mercuryZDistance, RealVelocities.mercuryXVel, RealVelocities.mercuryYVel,
                RealVelocities.mercuryZVel, RealMasses.MERCURY_MASS);
        planet[2] = new CelestialBody("Venus", RealDistance.venusXDistance, RealDistance.venusYDistance,
                RealDistance.venusZDistance, RealVelocities.venusXVel, RealVelocities.venusYVel,
                RealVelocities.venusZVel, RealMasses.VENUS_MASS);
        planet[3] = new CelestialBody("Earth", RealDistance.earthXDistance, RealDistance.earthYDistance,
                RealDistance.earthZDistance, RealVelocities.earthXVel, RealVelocities.earthYVel,
                RealVelocities.earthZVel, RealMasses.EARTH_MASS);
        planet[4] = new CelestialBody("Mars", RealDistance.marsXDistance, RealDistance.marsYDistance,
                RealDistance.marsZDistance, RealVelocities.marsXVel, RealVelocities.marsYVel, RealVelocities.marsZVel,
                RealMasses.MARS_MASS);
        planet[5] = new CelestialBody("Jupiter", RealDistance.jupiterXDistance, RealDistance.jupiterYDistance,
                RealDistance.jupiterZDistance, RealVelocities.jupiterXVel, RealVelocities.jupiterYVel,
                RealVelocities.jupiterZVel, RealMasses.JUPITER_MASS);
        planet[6] = new CelestialBody("Saturn", RealDistance.saturneXDistance, RealDistance.saturneYDistance,
                RealDistance.saturneZDistance, RealVelocities.saturnXVel, RealVelocities.saturnYVel,
                RealVelocities.saturnZVel, RealMasses.SATURNE_MASS);
        planet[7] = new CelestialBody("Uranus", RealDistance.uranusXDistance, RealDistance.uranusYDistance,
                RealDistance.uranusZDistance, RealVelocities.uranusXVel, RealVelocities.uranusYVel,
                RealVelocities.uranusZVel, RealMasses.URANUS_MASS);
        planet[8] = new CelestialBody("Neptune", RealDistance.neptuneXDistance, RealDistance.neptuneYDistance,
                RealDistance.neptuneZDistance, RealVelocities.neptuneXVel, RealVelocities.neptuneYVel,
                RealVelocities.neptuneZVel, RealMasses.NEPTUNE_MASS);


        Timeline timer = new Timeline(
                new KeyFrame(Duration.millis(20), e -> {
                    for (int i = 0; i < planet.length; i++) {

                        planet[i].computeGravityStep(planet, 2160);
                        redraw();

                    }
                }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();


    }

    //Background
    private ImageView prepareImageView() {
        Image image = new Image(Test.class.getResourceAsStream("galaxy.jpeg"));
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.getTransforms().add(new Translate(-image.getWidth() / 2, -image.getHeight() / 2, 800));
        return imageView;
    }

    //Slider for Zoom
    private Slider prepareSlider() {
        Slider slider = new Slider();
        slider.setMax(800);
        slider.setMin(-400);
        slider.setPrefWidth(300d);
        slider.setLayoutX(-150);
        slider.setLayoutY(200);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setTranslateZ(5);
        slider.setStyle("-fx-base: black");
        return slider;
    }

    private void redraw() {


        for (int i = 0; i < pl.length; i++) {


            pl[i].setTranslateX(planet[i].getXPosition() / 1000000000);
            pl[i].setTranslateY(planet[i].getYPosition() / 1000000000);
            pl[i].setTranslateZ(planet[i].getZPosition() / 1000000000);


        }
    }
}

