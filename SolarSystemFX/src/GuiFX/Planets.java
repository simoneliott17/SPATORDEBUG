package GuiFX;

public class Planets {
    static CelestialBody[] planet = new CelestialBody[9];
    public Planets()

    {
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
    }
}
