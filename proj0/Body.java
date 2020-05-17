public class Body {
    public double xxPos;//current x position
    public double yyPos;//current y position
    public double xxVel;//current velocity in x direction
    public double yyVel;//current velocity in y direction
    public double mass;//its mass
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /**
     * calculate the distance between the body taken and the given body.
     */
    public double calcDistance(Body b) {
        double xxdistance = this.xxPos - b.xxPos;
        double yydistance = this.yyPos - b.yyPos;
        double distance = Math.hypot(xxdistance, yydistance);
        return distance;
    }

    /**
     * calculate the force given by the given body to the this body
     */
    public double calcForceExertedBy(Body b) {
        double G = 6.67e-11;
        double F = G * this.mass * b.mass /
                Math.pow(this.calcDistance(b), 2);
        return F;
    }

    /**
     * calculate the force in the x direction
     */
    public double calcForceExertedByX(Body b) {
        double FX = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) /
                this.calcDistance(b);
        return FX;
    }

    /**
     * calculate the force in the y direction
     */
    public double calcForceExertedByY(Body b) {
        double FY = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) /
                this.calcDistance(b);
        return FY;
    }

    /**
     * calculate the NET force in the x direction
     */
    public double calcNetForceExertedByX(Body[] bs) {
        double FxNET = 0;
        for (Body b : bs) {
            if (this.equals(b) == false) {
                FxNET += this.calcForceExertedByX(b);
            }
        }
        return FxNET;
    }

    /**
     * calculate the NET force in the y direction
     */
    public double calcNetForceExertedByY(Body[] bs) {
        double FyNET = 0;
        for (Body b : bs) {
            if (this.equals(b) == false) {
                FyNET += this.calcForceExertedByY(b);
            }
        }
        return FyNET;
    }

    /**
     * update the body position by time, Force x, Force y
     */
    public void update(double time, double Fx, double Fy) {
        double ax = Fx / this.mass;
        double ay = Fy / this.mass;
        this.xxVel = this.xxVel + ax * time;
        this.yyVel = this.yyVel + ay * time;
        this.xxPos = this.xxPos + this.xxVel * time;
        this.yyPos = this.yyPos + this.yyVel * time;
    }

    /**
     * Draw the body according to its position.
     */
    public void draw() {
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}

