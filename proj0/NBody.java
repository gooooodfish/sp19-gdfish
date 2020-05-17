public class NBody {
    /**
     * get the radius from the txt file
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    /**
     * get the bodies from txt
     */
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int number = in.readInt();
        in.readDouble();
        Body[] Planets = new Body[number];

        for (int i = 0; i < number; i = i + 1) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return Planets;
    }

    /**
     * the main method
     */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        ;
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius = readRadius(filename);
        Body[] Planets = readBodies(filename);

        /**
         * draw the background.
         */
        StdDraw.setScale(-Radius, Radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");

        /**
         * Draw planets.
         */
        for (Body planet : Planets) {
            planet.draw();
        }

        /**
         * Draw animation.
         */
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while (time <= T){
            double [] xForces = new double [Planets.length];
            double [] yForces = new double [Planets.length];
            for(int i = 0; i < Planets.length; i = i + 1){
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }

            for(int i = 0; i < Planets.length; i = i + 1){
                Planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body planet : Planets) {
                planet.draw();
            }

            StdDraw.show();

            StdDraw.pause(10);

            time += dt;
        }


        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                    Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
        }
    }
}
