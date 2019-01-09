package command.command3D;

import simple3D.Viewer;

import command.DrawCommand;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;

public class SphereCommand extends Draw3DCommand {
    private float rad;
    private float x, y, z;
    
    // This is for the reflection to work
    public SphereCommand(){}
    
    public SphereCommand(float x, float y, float z, float r){
        this.x = x;
        this.y = y;
        this.z = z;
        this.rad = r;
    }
    
    public DrawCommand makeCommand(String[] params){
        if(params.length != 4){
            throw new RuntimeException("Sphere command requires 4 arguments. No more, no less");
        }
        
        try{
            return new SphereCommand(Float.parseFloat(params[0]), Float.parseFloat(params[1]),
                                   Float.parseFloat(params[2]), Float.parseFloat(params[3]));
        }catch(Exception e){
            throw new RuntimeException("Arguments should be floats");
        }
    }
    
    public void draw3D(Viewer v){
        Sphere s = new Sphere(rad);
        
        Transform3D trans = new Transform3D();
        trans.setTranslation(new Vector3f(x, y, z));
        
        TransformGroup tg = new TransformGroup();
        tg.setTransform(trans);
        tg.addChild(s);
        
        BranchGroup bg = new BranchGroup();
        bg.addChild(tg);
        v.addBranchGroup(bg);
    }
}
