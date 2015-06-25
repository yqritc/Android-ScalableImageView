package com.yqritc.scalableimageview;

import android.graphics.Matrix;
import android.support.annotation.NonNull;

/**
 * Created by yqritc on 2015/06/12.
 */
public class ScaleManager {

    private Size mViewSize;
    private Size mImageSize;

    public ScaleManager(Size viewSize, Size imageSize) {
        mViewSize = viewSize;
        mImageSize = imageSize;
    }

    public Matrix getScaleMatrix(ScalableType scale) {
        switch (scale) {
            case NONE:
                return getNoScale();

            case FIT_XY:
                return fitXY();
            case FIT_CENTER:
                return fitCenter();
            case FIT_START:
                return fitStart();
            case FIT_END:
                return fitEnd();

            case LEFT_TOP:
                return getOriginalScale(PivotPoint.LEFT_TOP);
            case LEFT_CENTER:
                return getOriginalScale(PivotPoint.LEFT_CENTER);
            case LEFT_BOTTOM:
                return getOriginalScale(PivotPoint.LEFT_BOTTOM);
            case CENTER_TOP:
                return getOriginalScale(PivotPoint.CENTER_TOP);
            case CENTER:
                return getOriginalScale(PivotPoint.CENTER);
            case CENTER_BOTTOM:
                return getOriginalScale(PivotPoint.CENTER_BOTTOM);
            case RIGHT_TOP:
                return getOriginalScale(PivotPoint.RIGHT_TOP);
            case RIGHT_CENTER:
                return getOriginalScale(PivotPoint.RIGHT_CENTER);
            case RIGHT_BOTTOM:
                return getOriginalScale(PivotPoint.RIGHT_BOTTOM);

            case LEFT_TOP_CROP:
                return getCropScale(PivotPoint.LEFT_TOP);
            case LEFT_CENTER_CROP:
                return getCropScale(PivotPoint.LEFT_CENTER);
            case LEFT_BOTTOM_CROP:
                return getCropScale(PivotPoint.LEFT_BOTTOM);
            case CENTER_TOP_CROP:
                return getCropScale(PivotPoint.CENTER_TOP);
            case CENTER_CROP:
                return getCropScale(PivotPoint.CENTER);
            case CENTER_BOTTOM_CROP:
                return getCropScale(PivotPoint.CENTER_BOTTOM);
            case RIGHT_TOP_CROP:
                return getCropScale(PivotPoint.RIGHT_TOP);
            case RIGHT_CENTER_CROP:
                return getCropScale(PivotPoint.RIGHT_CENTER);
            case RIGHT_BOTTOM_CROP:
                return getCropScale(PivotPoint.RIGHT_BOTTOM);

            case START_INSIDE:
                return startInside();
            case CENTER_INSIDE:
                return centerInside();
            case END_INSIDE:
                return endInside();

            default:
                return null;
        }
    }

    private Matrix getNoScale() {
        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f);
        return matrix;
    }

    private Matrix fitXY() {
        Matrix matrix = new Matrix();
        float sx = (float) mViewSize.getWidth() / mImageSize.getWidth();
        float sy = (float) mViewSize.getHeight() / mImageSize.getHeight();
        matrix.postScale(sx, sy);
        return matrix;
    }

    private Matrix fitStart() {
        return getFitScale(PivotPoint.LEFT_TOP);
    }

    private Matrix fitCenter() {
        return getFitScale(PivotPoint.CENTER);
    }

    private Matrix fitEnd() {
        return getFitScale(PivotPoint.RIGHT_BOTTOM);
    }

    private Matrix getFitScale(PivotPoint pivotPoint) {
        Matrix matrix = new Matrix();
        float sx = (float) mViewSize.getWidth() / mImageSize.getWidth();
        float sy = (float) mViewSize.getHeight() / mImageSize.getHeight();
        float minScale = Math.min(sx, sy);
        matrix.postScale(minScale, minScale);

        if (sx == minScale) {
            postTranslateY(matrix, minScale, pivotPoint);
        } else {
            postTranslateX(matrix, minScale, pivotPoint);
        }
        return matrix;
    }

    private Matrix getOriginalScale(PivotPoint pivotPoint) {
        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f);
        postTranslate(matrix, pivotPoint);
        return matrix;
    }

    private Matrix getCropScale(PivotPoint pivotPoint) {
        Matrix matrix = new Matrix();
        float sx = (float) mViewSize.getWidth() / mImageSize.getWidth();
        float sy = (float) mViewSize.getHeight() / mImageSize.getHeight();
        float maxScale = Math.max(sx, sy);

        matrix.postScale(maxScale, maxScale);
        if (sx == maxScale) {
            postTranslateY(matrix, maxScale, pivotPoint);
        } else {
            postTranslateX(matrix, maxScale, pivotPoint);
        }
        return matrix;
    }

    private Matrix startInside() {
        if (mImageSize.getHeight() <= mViewSize.getWidth()
                && mImageSize.getHeight() <= mViewSize.getHeight()) {
            // video is smaller than view size
            return getOriginalScale(PivotPoint.LEFT_TOP);
        } else {
            // either of width or height of the video is larger than view size
            return fitStart();
        }
    }

    private Matrix centerInside() {
        if (mImageSize.getHeight() <= mViewSize.getWidth()
                && mImageSize.getHeight() <= mViewSize.getHeight()) {
            // video is smaller than view size
            return getOriginalScale(PivotPoint.CENTER);
        } else {
            // either of width or height of the video is larger than view size
            return fitCenter();
        }
    }

    private Matrix endInside() {
        if (mImageSize.getHeight() <= mViewSize.getWidth()
                && mImageSize.getHeight() <= mViewSize.getHeight()) {
            // video is smaller than view size
            return getOriginalScale(PivotPoint.RIGHT_BOTTOM);
        } else {
            // either of width or height of the video is larger than view size
            return fitEnd();
        }
    }

    private void postTranslate(@NonNull Matrix matrix, PivotPoint pivotPoint) {
        float translateX = 0f;
        float translateY = 0f;
        switch (pivotPoint) {
            case LEFT_TOP:
                break;
            case LEFT_CENTER:
                translateY = (mImageSize.getHeight() - mViewSize.getHeight()) / 2f;
                break;
            case LEFT_BOTTOM:
                translateY = mImageSize.getHeight() - mViewSize.getHeight();
                break;
            case CENTER_TOP:
                translateX = (mImageSize.getWidth() - mViewSize.getWidth()) / 2f;
                break;
            case CENTER:
                translateX = (mImageSize.getWidth() - mViewSize.getWidth()) / 2f;
                translateY = (mImageSize.getHeight() - mViewSize.getHeight()) / 2f;
                break;
            case CENTER_BOTTOM:
                translateX = (mImageSize.getWidth() - mViewSize.getWidth()) / 2f;
                translateY = mImageSize.getHeight() - mViewSize.getHeight();
                break;
            case RIGHT_TOP:
                translateX = mImageSize.getWidth() - mViewSize.getWidth();
                break;
            case RIGHT_CENTER:
                translateX = mImageSize.getWidth() - mViewSize.getWidth();
                translateY = (mImageSize.getHeight() - mViewSize.getHeight()) / 2f;
                break;
            case RIGHT_BOTTOM:
                translateX = mImageSize.getWidth() - mViewSize.getWidth();
                translateY = mImageSize.getHeight() - mViewSize.getHeight();
                break;
        }

        matrix.postTranslate(-translateX, -translateY);
    }

    private void postTranslateX(Matrix matrix, float scale, PivotPoint pivotPoint) {
        float translate;
        switch (pivotPoint) {
            case CENTER_TOP:
            case CENTER:
            case CENTER_BOTTOM:
                translate = (mImageSize.getWidth() * scale - mViewSize.getWidth()) / 2f;
                matrix.postTranslate(-translate, 0);
                break;
            case RIGHT_TOP:
            case RIGHT_CENTER:
            case RIGHT_BOTTOM:
                translate = mImageSize.getWidth() * scale - mViewSize.getWidth();
                matrix.postTranslate(-translate, 0);
                break;
            case LEFT_TOP:
            case LEFT_CENTER:
            case LEFT_BOTTOM:
            default:
                break;
        }
    }

    private void postTranslateY(Matrix matrix, float scale, PivotPoint pivotPoint) {
        float translate;
        switch (pivotPoint) {
            case LEFT_CENTER:
            case CENTER:
            case RIGHT_CENTER:
                translate = (mImageSize.getHeight() * scale - mViewSize.getHeight()) / 2f;
                matrix.postTranslate(0, -translate);
                break;
            case LEFT_BOTTOM:
            case CENTER_BOTTOM:
            case RIGHT_BOTTOM:
                translate = mImageSize.getHeight() * scale - mViewSize.getHeight();
                matrix.postTranslate(0, -translate);
                break;
            case LEFT_TOP:
            case CENTER_TOP:
            case RIGHT_TOP:
            default:
                break;
        }
    }
}