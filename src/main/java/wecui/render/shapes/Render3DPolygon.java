package wecui.render.shapes;

import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import wecui.render.LineColor;
import wecui.render.LineInfo;
import wecui.util.Vector3;

/**
 * Draws a polygon
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DPolygon {

    protected LineColor color;
    protected Vector3[] vertices;

    public Render3DPolygon(LineColor color, Vector3... vertices) {
        this.color = color;
        this.vertices = vertices;
    }

    public void render() {
        Tessellator tess = Tessellator.instance;

        for (LineInfo tempColor : this.color.getColors()) {
            tempColor.prepareRender();

            tess.startDrawing(GL11.GL_LINE_LOOP);
            tempColor.prepareColor();
            for (Vector3 vertex : vertices) {
                tess.addVertex(vertex.getX(), vertex.getY(), vertex.getZ());
            }
            tess.draw();
        }
    }
}
