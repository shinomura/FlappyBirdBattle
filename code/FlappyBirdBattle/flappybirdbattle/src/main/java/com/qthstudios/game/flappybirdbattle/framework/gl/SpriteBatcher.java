package com.qthstudios.game.flappybirdbattle.framework.gl;

import javax.microedition.khronos.opengles.GL10;

import android.util.FloatMath;
import com.qthstudios.game.flappybirdbattle.framework.impl.GLGraphics;
import com.qthstudios.game.flappybirdbattle.framework.math.Vector2;

/**
 *
 */
public class SpriteBatcher {
    // store the vertices of the sprites of the current batch in
    final float[] verticesBuffer;
    // where in float buffer array we start to write next vertices
    int bufferIndex;
    final Vertices vertices;
    // store number of sprites in current batch
    int numSprites;    
    
    public SpriteBatcher(GLGraphics glGraphics, int maxSprites) {
        // four vertices per sprite. each vertex take 4 floats (2 for x-y coordinate and 2 for texture coordinate)
        this.verticesBuffer = new float[maxSprites*4*4];
        this.vertices = new Vertices(glGraphics, maxSprites*4, maxSprites*6, false, true);
        this.bufferIndex = 0;
        this.numSprites = 0;
                
        short[] indices = new short[maxSprites*6];
        int len = indices.length;
        short j = 0;
        for (int i = 0; i < len; i += 6, j += 4) {
                indices[i + 0] = (short)(j + 0);
                indices[i + 1] = (short)(j + 1);
                indices[i + 2] = (short)(j + 2);
                indices[i + 3] = (short)(j + 2);
                indices[i + 4] = (short)(j + 3);
                indices[i + 5] = (short)(j + 0);
        }
        vertices.setIndices(indices, 0, indices.length);                
    }

    /**
     * Usage pattern of SpriteBatcher:
     * SpriteBatcher.beginBatch(texture);
     // call batcher.drawSprite() as often as needed, referencing regions in the texture
     SpriteBatcher.endBatch();
     */

    /**
     * It binds the texture and resets the numSprites and bufferIndex members
     * so the first sprite’s vertices will get inserted at the front of the  verticesBuffer float array.
     */
    public void beginBatch(Texture texture) {
        texture.bind();
        numSprites = 0;
        bufferIndex = 0;
    }

    // signals to the sprite batcher that we are done rendering the batch of sprites.
    // Now it should now upload the vertices to the GPU for actual rendering.
    // We are going to use indexed rendering with a Vertices instance, so we’ll also need to specify indices,
    // in addition to the vertices in the float array buffer.
    // However, since we are always rendering rectangles, we can generate the indices
    // beforehand once in the constructor of the SpriteBatcher.
    // For this we need to know how many sprites the batcher should be able to draw maximally per batch.
    // By putting a hard limited on the number of sprites that can be rendered per batch, we don’t need to grow
    // any arrays of other buffers; we can just allocate these arrays and buffers once in the constructor.
    public void endBatch() {
        vertices.setVertices(verticesBuffer, 0, bufferIndex);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
        vertices.unbind();
    }

    public void drawSprite(float x, float y, float width, float height, TextureRegion region) {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float x1 = x - halfWidth;
        float y1 = y - halfHeight;
        float x2 = x + halfWidth;
        float y2 = y + halfHeight;
        
        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v2;
        
        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v2;
        
        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v1;
        
        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v1;
        
        numSprites++;
    }
    
    public void drawSprite(float x, float y, float width, float height, float angle, TextureRegion region) {
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        
        float rad = angle * Vector2.TO_RADIANS;
        float cos = FloatMath.cos(rad);
        float sin = FloatMath.sin(rad);
                
        float x1 = -halfWidth * cos - (-halfHeight) * sin;
        float y1 = -halfWidth * sin + (-halfHeight) * cos;
        float x2 = halfWidth * cos - (-halfHeight) * sin;
        float y2 = halfWidth * sin + (-halfHeight) * cos;
        float x3 = halfWidth * cos - halfHeight * sin;
        float y3 = halfWidth * sin + halfHeight * cos;
        float x4 = -halfWidth * cos - halfHeight * sin;
        float y4 = -halfWidth * sin + halfHeight * cos;
        
        x1 += x;
        y1 += y;
        x2 += x;
        y2 += y;
        x3 += x;
        y3 += y;
        x4 += x;
        y4 += y;
        
        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v2;
        
        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v2;
        
        verticesBuffer[bufferIndex++] = x3;
        verticesBuffer[bufferIndex++] = y3;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v1;
        
        verticesBuffer[bufferIndex++] = x4;
        verticesBuffer[bufferIndex++] = y4;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v1;
        
        numSprites++;
    }
}