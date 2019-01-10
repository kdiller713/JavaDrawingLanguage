package command.command3D;

import simple3D.Viewer;

import command.DrawCommand;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;

import javax.vecmath.Vector3f;
import javax.vecmath.Point3f;

public class PlaneCommand extends Draw3DCommand {
    private Point3f p1, p2, p3, p4;
    
    // This is for the reflection to work
    public PlaneCommand(){}
    
    public PlaneCommand(float x1, float y1, float z1, float x2, float y2, float z2, float x3, float y3, float z3, float x4, float y4, float z4){
        p1 = new Point3f(x1, y1, z1);
        p2 = new Point3f(x2, y2, z2);
        p3 = new Point3f(x3, y3, z3);
        p4 = new Point3f(x4, y4, z4);
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 12){
            throw new RuntimeException("Sphere command requires 12 arguments. No more, no less");
        }
        
        try{
            return new PlaneCommand(Float.parseFloat(params[0]), Float.parseFloat(params[1]),
                                   Float.parseFloat(params[2]), Float.parseFloat(params[3]),
                                   Float.parseFloat(params[4]), Float.parseFloat(params[5]),
                                   Float.parseFloat(params[6]), Float.parseFloat(params[7]),
                                   Float.parseFloat(params[8]), Float.parseFloat(params[9]),
                                   Float.parseFloat(params[10]), Float.parseFloat(params[11]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be floats");
        }
    }
    
    public void draw3D(Viewer v){
        QuadArray s = new QuadArray(8, QuadArray.COORDINATES);
        s.setCoordinate(0, p1);
        s.setCoordinate(1, p2);
        s.setCoordinate(2, p3);
        s.setCoordinate(3, p4);
        s.setCoordinate(4, p1);
        s.setCoordinate(5, p4);
        s.setCoordinate(6, p3);
        s.setCoordinate(7, p2);
        
        BranchGroup bg = new BranchGroup();
        bg.addChild(new Shape3D(s));
        v.addBranchGroup(bg);
    }
}
