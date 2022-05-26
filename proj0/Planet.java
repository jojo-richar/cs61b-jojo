public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet other) {
        double xxDiff = this.xxPos - other.xxPos;
        double yyDiff = this.yyPos - other.yyPos;
        return Math.sqrt(xxDiff * xxDiff + yyDiff * yyDiff);
    }
    public double calcForceExertedBy(Planet other) {
        double dist = calcDistance(other);
        return G * this.mass * other.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet other) {
        double dist = calcDistance(other);
        double force = calcForceExertedBy(other);
        return (other.xxPos - this.xxPos) / dist * force;
    }

    public double calcForceExertedByY(Planet other) {
        double dist = calcDistance(other);
        double force = calcForceExertedBy(other);
        return (other.yyPos - this.yyPos) / dist * force;
    }

    public double calcNetForceExertedByX(Planet[] others) {
        double totalForce = 0;
        for (Planet other : others) {
            if (!this.equals(other)) {
                totalForce += this.calcForceExertedByX(other);
            }
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] others) {
        double totalForce = 0;
        for (Planet other : others) {
            if (!this.equals(other)) {
                totalForce += this.calcForceExertedByY(other);
            }
        }
        return totalForce;
    }

    public void update(double duration, double xxForce, double yyForce) {
        double xxAcc = xxForce / this.mass;
        double yyAcc = yyForce / this.mass;
        this.xxVel = this.xxVel + duration * xxAcc;
        this.yyVel = this.yyVel + duration * yyAcc;
        this.xxPos = this.xxPos + duration * this.xxVel;
        this.yyPos = this.yyPos + duration * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
