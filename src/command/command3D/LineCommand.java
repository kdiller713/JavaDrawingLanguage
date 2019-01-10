package command.command3D;

import simple3D.Viewer;

import command.DrawCommand;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.LineArray;
import javax.media.j3d.Shape3D;

import javax.vecmath.Vector3f;
import javax.vecmath.Point3f;

public class LineCommand extends Draw3DCommand {
    private Point3f p1, p2;
    
    // This is for the reflection to work
    public LineCommand(){}
    
    public LineCommand(float x1, float y1, float z1, float x2, float y2, float z2){
        p1 = new Point3f(x1, y1, z1);
        p2 = new Point3f(x2, y2, z2);
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 6){
            throw new RuntimeException("Sphere command requires 6 arguments. No more, no less");
        }
        
        try{
            return new LineCommand(Float.parseFloat(params[0]), Float.parseFloat(params[1]),
                                   Float.parseFloat(params[2]), Float.parseFloat(params[3]),
                                   Float.parseFloat(params[4]), Float.parseFloat(params[5]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be floats");
        }
    }
    
    public void draw3D(Viewer v){
        LineArray s = new LineArray(2, LineArray.COORDINATES);
        s.setCoordinate(0, p1);
        s.setCoordinate(1, p2);
        
        BranchGroup bg = new BranchGroup();
        bg.addChild(new Shape3D(s));
        v.addBranchGroup(bg);
    }
}
