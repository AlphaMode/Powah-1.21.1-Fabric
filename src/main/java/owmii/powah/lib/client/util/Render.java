package owmii.powah.lib.client.util;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.joml.Matrix4f;
import owmii.powah.lib.util.math.V3d;

public class Render {
    public static final int MAX_LIGHT = 15728880;

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, TextureAtlasSprite sprite, float width, float height) {
        quad(matrix4f, buffer, sprite, width, height, MAX_LIGHT, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, TextureAtlasSprite sprite, float width, float height, int light) {
        quad(matrix4f, buffer, sprite, width, height, light, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, TextureAtlasSprite sprite, float width, float height, float r, float g,
            float b) {
        quad(matrix4f, buffer, sprite, width, height, MAX_LIGHT, r, g, b, 1.0F);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, TextureAtlasSprite sprite, float width, float height, float r, float g, float b,
            float a) {
        quad(matrix4f, buffer, sprite, width, height, MAX_LIGHT, r, g, b, a);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, TextureAtlasSprite sprite, float width, float height, int light, float r,
            float g, float b, float a) {
        buffer.addVertex(matrix4f, 0, 0, height).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1()).setLight(light);
        buffer.addVertex(matrix4f, width, 0, height).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1()).setLight(light);
        buffer.addVertex(matrix4f, width, 0, 0).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0()).setLight(light);
        buffer.addVertex(matrix4f, 0, 0, 0).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0()).setLight(light);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, float width, float height) {
        quad(matrix4f, buffer, width, height, MAX_LIGHT, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, float width, float height, int light) {
        quad(matrix4f, buffer, width, height, light, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, float width, float height, float r, float g, float b) {
        quad(matrix4f, buffer, width, height, MAX_LIGHT, r, g, b, 1.0F);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, float width, float height, float r, float g, float b, float a) {
        quad(matrix4f, buffer, width, height, MAX_LIGHT, r, g, b, a);
    }

    public static void quad(Matrix4f matrix4f, VertexConsumer buffer, float width, float height, int light, float r, float g, float b, float a) {
        buffer.addVertex(matrix4f, 0, 0, height).setColor(r, g, b, a).setUv(0.0F, 1.0F).setLight(light);
        buffer.addVertex(matrix4f, width, 0, height).setColor(r, g, b, a).setUv(1.0F, 1.0F).setLight(light);
        buffer.addVertex(matrix4f, width, 0, 0).setColor(r, g, b, a).setUv(1.0F, 0.0F).setLight(light);
        buffer.addVertex(matrix4f, 0, 0, 0).setColor(r, g, b, a).setUv(0.0F, 0.0F).setLight(light);
    }

    public static void cube(Matrix4f matrix4f, VertexConsumer builder, V3d v3d, TextureAtlasSprite sprite, double size, int light) {
        cube(matrix4f, builder, v3d, sprite, size, light, 1.0F);
    }

    public static void cube(Matrix4f matrix4f, VertexConsumer builder, V3d v3d, TextureAtlasSprite sprite, double size, int light, float a) {
        cube(matrix4f, builder, v3d, sprite, size, light, 1.0F, 1.0F, 1.0F, a);
    }

    public static void cube(Matrix4f matrix4f, VertexConsumer builder, V3d v3d, TextureAtlasSprite sprite, double size, int light, float r, float g,
            float b, float a) {
        final float d = (float) (size / 2.0d);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() - d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() - d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() - d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() - d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1())
                .setLight(light);

        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() + d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() + d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() + d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() + d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1())
                .setLight(light);

        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() - d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() + d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() + d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() - d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0())
                .setLight(light);

        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() - d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() + d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() + d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() - d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0())
                .setLight(light);

        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() - d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() + d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() + d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() - d, (float) v3d.z() + d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0())
                .setLight(light);

        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() - d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV0())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() - d, (float) v3d.y() + d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU0(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() + d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV1())
                .setLight(light);
        builder.addVertex(matrix4f, (float) v3d.x() + d, (float) v3d.y() - d, (float) v3d.z() - d).setColor(r, g, b, a).setUv(sprite.getU1(), sprite.getV0())
                .setLight(light);

    }

    public static void cube(Matrix4f matrix4f, VertexConsumer builder, V3d v3d, double size, float u, float v, int light) {
        cube(matrix4f, builder, v3d, size, u, v, light, 1.0F);
    }

    public static void cube(Matrix4f matrix4f, VertexConsumer builder, V3d v3d, double size, float u, float v, int light, float a) {
        cube(matrix4f, builder, v3d, size, u, v, light, 1.0F, 1.0F, 1.0F, a);
    }

    public static void cube(Matrix4f matrix4f, VertexConsumer builder, V3d v3d, double size, float u, float v, int light, float r, float g, float b,
            float a) {
        float half = (float) (size / 2D);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() - half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(0.0f, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() - half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(u, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() - half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(u, v).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() - half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(0.0f, v).setLight(light);

        builder.addVertex((float) v3d.x() - half, (float) v3d.y() + half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(0.0f, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() + half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(u, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() + half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(u, v).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() + half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(0.0f, v).setLight(light);

        builder.addVertex((float) v3d.x() - half, (float) v3d.y() - half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(u, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() + half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(u, v).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() + half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(0.0f, v).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() - half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(0.0f, 0.0f).setLight(light);

        builder.addVertex((float) v3d.x() + half, (float) v3d.y() - half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(u, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() + half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(u, v).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() + half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(0.0f, v).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() - half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(0.0f, 0.0f).setLight(light);

        builder.addVertex((float) v3d.x() + half, (float) v3d.y() - half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(0.0f, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() + half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(0.0f, v).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() + half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(u, v).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() - half, (float) v3d.z() - half).setColor(r, g, b, a).setUv(u, 0.0f).setLight(light);

        builder.addVertex((float) v3d.x() - half, (float) v3d.y() - half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(0.0f, 0.0f).setLight(light);
        builder.addVertex((float) v3d.x() - half, (float) v3d.y() + half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(0.0f, v).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() + half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(u, v).setLight(light);
        builder.addVertex((float) v3d.x() + half, (float) v3d.y() - half, (float) v3d.z() + half).setColor(r, g, b, a).setUv(u, 0.0f).setLight(light);

    }
}
