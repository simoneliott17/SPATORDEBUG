package GuiFX;

public class CelestialBody {
    double xLoc = 0;
    double yLoc = 0;
    double zLoc = 0;
    double velX = 0;
    double velY = 0;
    double velZ = 0;
    double mass = 0;
    double accX = 0;
    double accY = 0;
    double accZ = 0;
    String name = "";
    private final double GRAVITY_CONSTANT = 6.673 * Math.pow(10, -11);

    public CelestialBody(String name, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity,
                         double bodyMass) {
        xLoc = x;
        yLoc = y;
        zLoc = z;
        velX = xVelocity;
        velY = yVelocity;
        velZ = zVelocity;
        mass = bodyMass;
        this.name = name;
    }
    public double getMass() {
        return mass;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public double getVelZ() {
        return velZ;
    }

    public double getAccX() {
        return accX;
    }

    public double getAccY() {
        return accY;
    }

    public double getAccZ() {
        return accZ;
    }

    public void setXPosition(double X) {
        xLoc = X;
    }

    public void setYPosition(double Y) {
        yLoc = Y;
    }

    public void setZPosition(double Z) {
        zLoc = Z;
    }

    public double getXPosition() {

        return xLoc;
    }

    public void changeVelocity(double vx, double vy, double vz) {
        velX += vx;
        velY += vy;
        velZ += vz;
    }

    public Point getLocation() {
        Point pt = new Point(xLoc, yLoc, zLoc);
        return pt;
    }

    public Point getVelocity() {
        Point pt = new Point(velX, velY, velZ);
        return pt;
    }

    public double getYPosition() {
        return yLoc;
    }

    public double getZPosition() {
        return zLoc;
    }

    public String getName() {
        return this.name;
    }
    public void setVelX(double d) {
        velX = d;

    }

    public void setVelY(double d) {
        velY = d;
    }

    public void setVelZ(double d) {
        velZ = d;
    }

    public void setAccX(double d) {
        accX = d;

    }

    public void setAccY(double d) {
        accY = d;
    }

    public void setAccZ(double d) {
        accZ = d;
    }

    public void calculate_single_body_acceleration(CelestialBody[] bodies, int index) {

        //Point acceleration = new Point();
        CelestialBody targetBody = bodies[index];

        for (int i = 0; i < bodies.length; i++) {
            if (i != index) {
                double r = (Math.pow((targetBody.getXPosition() - bodies[i].getXPosition()), 2) + Math.pow((targetBody.getYPosition() - bodies[i].getYPosition()), 2) + Math.pow((targetBody.getZPosition() - bodies[i].getZPosition()), 2));
                r = Math.sqrt(r);
                double tmp = GRAVITY_CONSTANT * bodies[index].getMass() / (Math.pow(r, 3));


                bodies[i].setAccX(tmp * (targetBody.getXPosition() -  bodies[i].getXPosition()));
                bodies[i].setAccY(tmp * (bodies[i].getYPosition() -  bodies[i].getYPosition()));
                bodies[i].setAccZ(tmp * (bodies[i].getZPosition() -  bodies[i].getZPosition()));
                System.out.println(targetBody.getXPosition() -  bodies[i].getXPosition());


            }
        }

    }

    // Updates the velocities
    // dv = a.dt;
    private void computeVelocity(CelestialBody[] bodies, int timeStep) {
        for (int i = 0; i < bodies.length; i++) {
            //System.out.println(targetBody.getName());
            //System.out.println(
            //        "VX: " + targetBody.getVelX() + "\nVY: " + targetBody.getVelY() + "\nVZ: " + targetBody.getVelZ());

            //bodies[i].setVelX(bodies[i].getVelX() + bodies[i].getAccX() * timeStep);
            //bodies[i].setVelY(bodies[i].getVelY() + bodies[i].getAccY() * timeStep);
            //bodies[i].setVelZ(bodies[i].getVelZ() + bodies[i].getAccZ() * timeStep);

            bodies[i].changeVelocity(bodies[i].getAccX() * timeStep,bodies[i].getAccY() * timeStep, bodies[i].getAccZ() * timeStep );
        }
    }

    // Updates the locations
    // dx = v*dt
    void updateLocation(CelestialBody[] bodies, int timeStep) {
        for (int i = 0; i < bodies.length; i++) {
            CelestialBody targetBody = bodies[i];
           /* System.out.println(targetBody.getName());
            System.out.println("X: " + targetBody.getXPosition() + "\nY: " + targetBody.getYPosition() + "\nZ: "
                      + targetBody.getZPosition());
            */
            targetBody.setXPosition(targetBody.getXPosition() + targetBody.getVelX() * timeStep);
            targetBody.setYPosition(targetBody.getYPosition() + targetBody.getVelY() * timeStep);
            targetBody.setZPosition(targetBody.getZPosition() + targetBody.getVelZ() * timeStep);

        }
    }

    // Combines the location and velocity update
    public void computeGravityStep(CelestialBody[] bodies, int timeStep) {
        for (int i = 0; i < bodies.length; i++) {

            calculate_single_body_acceleration(bodies, i);
        }

        updateLocation(bodies, timeStep);
        computeVelocity(bodies, timeStep);

    }



}

