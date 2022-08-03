import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import javax.swing.JFrame;

public class lab3 implements GLEventListener{

    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas
    static GLCanvas glcanvas = new GLCanvas(capabilities);

    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile


        lab3 l= new lab3();
        //creating frame
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(600, 400);

        final JFrame frame = new JFrame ("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glPointSize(5);
        gl.glBegin (GL2.GL_POINTS);//static field
        //8
        midpointLine(gl, 10,10,15,10);
        midpointLine(gl, 10,10,10,5);
        midpointLine(gl, 10,5,10,0);
        midpointLine(gl, 10,0,15,0);
        midpointLine(gl, 15,0,15,5);
        midpointLine(gl, 15,5,15,10);
        //9
        midpointLine(gl, 20,10,25,10);
        midpointLine(gl, 25,10,25,5);
        midpointLine(gl, 20,10,20,5);
        midpointLine(gl, 20,5,25,5);
        midpointLine(gl, 25,5,25,0);




        gl.glEnd();


    }
    public void midpointLine(GL2 gl, double x1, double y1, double x2, double y2) {
        double dx, dy, incrE, incrNE, d, x, y;
        x = x1;
        y = y1;
        gl.glVertex2d(x, y);
        int zone = findZone(x1, y1, x2, y2);
        double[] zone0points = convertToZone0(x1, y1, zone);
        x1 = zone0points[0];
        y1 = zone0points[1];
        zone0points = convertToZone0(x2, y2, zone);
        x2 = zone0points[0];
        y2 = zone0points[1];
        dx = x2 - x1;
        dy = y2 - y1;
        d = 2 * dy - dy;
        incrE = 2 * dy;
        incrNE = 2 * (dy - dx);
        x = x1;
        y = y1;
        while (x < x2) {
            if (d<=0) {
                d = d + incrE;
                x = x + 0.001;
            } else {
                d = d + incrNE;
                x = x + 0.001;
                y = y + 0.001;
            }

            double[] OZP = convertToOriginalZone(x, y, zone);
            double u = OZP[0];
            double v = OZP[1];
            gl.glVertex2d(u, v);
        }
    }

    public int findZone(double x1, double y1, double x2, double y2) {
        int zone=-1;
        double dx=x2-x1;
        double dy=y2-y1;
        if(Math.abs(dx)>Math.abs(dy)) {
            if(dx>=0&&dy>=0) {
                zone=0;
            }
            else {
                if(dx<0&&dy>=0) {

                    zone=3;
                }
                else {
                    if(dx<0&&dy<0) {
                        zone=4;
                    }
                    else {
                        if(dx>=0&&dy<0) {
                            zone=7;
                        }
                    }
                }
            }
        }

        else {
            if(dx>=0&&dy>=0) {


                zone=1;
            }
            else {
                if(dx<0&&dy<0) {
                    zone=2;
                }
                else {
                    if(dx<0&&dy<0) {
                        zone=5;
                    }
                    else {
                        zone=6;
                    }
                }
            }

        }
        return zone;
    }
    public double[] convertToZone0(double x, double y, int zone) {
        double[] result=new double[2];
        if(zone==0) {
            result[0]=x;
            result[1]=y;
        }
        else {
            if(zone==1) {
                result[0]=y;
                result[1]=x;
            }
            else {
                if(zone==2) {
                    result[0]=y;
                    result[1]=-x;
                }
                else {
                    if(zone==3) {
                        result[0]=-x;
                        result[1]=y;
                    }
                    else {
                        if(zone==4) {
                            result[0]=-x;
                            result[1]=-y;
                        }
                        else {
                            if(zone==5) {
                                result[0]=-y;
                                result[1]=-x;
                            }
                            else{
                                if(zone==6) {
                                    result[0]=-y;
                                    result[1]=x;
                                }
                                else {
                                    result[0]=x;
                                    result[1]=-y;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    public double[] convertToOriginalZone(double x, double y, int zone) {
        double[] result=new double[2];
        if(zone==0) {
            result[0]=x;
            result[1]=y;
        }
        else {
            if(zone==1) {
                result[0]=y;
                result[1]=x;
            }
            else {
                if(zone==2) {
                    result[0]=-y;
                    result[1]=x;
                }
                else {
                    if(zone==3) {
                        result[0]=-x;
                        result[1]=y;
                    }
                    else {
                        if(zone==4) {
                            result[0]=-x;
                            result[1]=-y;
                        }
                        else {
                            if(zone==5) {
                                result[0]=-y;
                                result[1]=-x;
                            }
                            else{
                                if(zone==6) {
                                    result[0]=y;
                                    result[1]=-x;
                                }
                                else {
                                    result[0]=x;
                                    result[1]=-y;
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }


    public void dispose(GLAutoDrawable arg0) {
        //method body
    }


    public void init(GLAutoDrawable drawable) {
        // method body
        //4. drive the display() in a loop
    }

    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }
    //end of main
}//end of classimport javax.media.opengl.GL2;

