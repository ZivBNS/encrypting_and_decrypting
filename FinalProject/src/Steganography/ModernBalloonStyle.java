/**
 * Balloontip - Balloon tips for Java Swing applications
 * Copyright 2007, 2008 Bernhard Pauler, Tim Molderez
 * 
 * This file is part of Balloontip.
 * 
 * Balloontip is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Balloontip is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Balloontip.  If not, see <http://www.gnu.org/licenses/>.
 */

package Steganography;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;

import net.java.balloontip.styles.BalloonTipStyle;


/**
 * A balloon tip with a vertical linear gradient background
 * You can also choose which corners should be rounded corners or just plain corners
 * @author Bernhard Pauler
 */
public class ModernBalloonStyle extends BalloonTipStyle //implements Tooltip
{

	private int arcWidth;
	private int arcHeight;

	private boolean topLeft = true;
	private boolean topRight = false;
	boolean bottomLeft = false;
	boolean bottomRight = true;

	private int borderThickness = 1;
	private boolean AAenabled = false;

	private Color topFillColor;
	private Color bottomFillColor;
	private Color borderColor;

	/**
	 * Constructor
	 * @param arcWidth			width of the rounded corner
	 * @param arcHeight			height of the rounded color
	 * @param borderColor		line color
	 * @param topFillColor		top color of the lineair gradient fill color
	 * @param bottomFillColor	bottom color of the lineair gradient fill color
	 */
	public ModernBalloonStyle(int arcWidth, int arcHeight, Color topFillColor, Color bottomFillColor, Color borderColor) {
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
		this.topFillColor = topFillColor;
		this.bottomFillColor = bottomFillColor;
		this.borderColor = borderColor;
	}

	/**
	 * Sets the style for each corner.
	 * If true, this corner will be rounded; if false, it's just a regular corner
	 * @param topLeft
	 * @param topRight
	 * @param bottomLeft
	 * @param bottomRight
	 */
	public void setCornerStyles(boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}

	/**
	 * Set the thickness of the balloon tip's border, in px
	 * @param thickness
	 */
	public void setBorderThickness(int thickness) {
		borderThickness = thickness;
	}

	/**
	 * Enable/disable anti-aliasing for this balloon tip
	 * @param enable	if true, AA is enabled; if false, the settings remain untouched
	 */
	public void enableAntiAliasing(boolean enable) {
		AAenabled = enable;
	}
	
	public Insets getBorderInsets(Component c) {
		if (flipY) {
			return new Insets(verticalOffset+arcHeight, arcWidth, arcHeight, arcWidth);
		} else {
			return new Insets(arcHeight, arcWidth, arcHeight+verticalOffset, arcWidth);
		}
	}

	public boolean isBorderOpaque() {
		return true;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g;
		if (AAenabled) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}

		// Make room for the border line
		x+=borderThickness - 1;
		y+=borderThickness - 1;
		width-=borderThickness*2;
		height-=borderThickness*2;

		int yTop;		// Y-coordinate of the top side of the balloon
		int yBottom;	// Y-coordinate of the bottom side of the balloon
		if (flipY) {
			yTop = y + verticalOffset;
			yBottom = y + height;
		} else {
			yTop = y;
			yBottom = y + height - verticalOffset;
		}

		// Draw the outline of the balloon
		GeneralPath outline = new GeneralPath();
		outline.moveTo(x + arcWidth, yTop);

		if (topLeft) {
			outline.quadTo(x, yTop, x, yTop + arcHeight);
		} else {
			outline.lineTo(x, yTop);
			outline.lineTo(x, yTop + arcHeight);
		}

		outline.lineTo(x, yBottom - arcHeight);

		if (bottomLeft) {
			outline.quadTo(x, yBottom, x + arcWidth, yBottom);
		} else {
			outline.lineTo(x, yBottom);
			outline.lineTo(x + arcWidth, yBottom);
		}

		if (!flipX && !flipY) {
			outline.lineTo(x + horizontalOffset, yBottom);
			outline.lineTo(x + horizontalOffset, yBottom + verticalOffset);
			outline.lineTo(x + horizontalOffset + verticalOffset, yBottom);
		} else if (flipX && !flipY) {
			outline.lineTo(x + width - horizontalOffset - verticalOffset, yBottom);
			outline.lineTo(x + width - horizontalOffset, yBottom + verticalOffset);
			outline.lineTo(x + width - horizontalOffset, yBottom);
		}

		outline.lineTo(x + width - arcWidth, yBottom);

		if (bottomRight) {
			outline.quadTo(x + width, yBottom, x + width, yBottom - arcHeight);
		} else {
			outline.lineTo(x + width, yBottom);
			outline.lineTo(x + width, yBottom - arcHeight);
		}

		outline.lineTo(x + width, yTop + arcHeight);

		if (topRight) {
			outline.quadTo(x + width, yTop, x + width - arcWidth, yTop);
		} else {
			outline.lineTo(x + width, yTop);
			outline.lineTo(x + width - arcWidth, yTop);
		}

		if (!flipX && flipY) {
			outline.lineTo(x + horizontalOffset + verticalOffset, yTop);
			outline.lineTo(x + horizontalOffset, yTop - verticalOffset);
			outline.lineTo(x + horizontalOffset, yTop);	
		} else if (flipX && flipY) {
			outline.lineTo(x + width - horizontalOffset, yTop);
			outline.lineTo(x + width - horizontalOffset, yTop - verticalOffset);
			outline.lineTo(x + width - horizontalOffset - verticalOffset, yTop);
		}

		outline.closePath();

		// Now paint the sucker :)
		g2d.setPaint(new GradientPaint(0, yTop,topFillColor, 0, yBottom, bottomFillColor));
		g2d.fill(outline);
		g2d.setPaint(borderColor);
		Stroke backup = g2d.getStroke();
		g2d.setStroke(new BasicStroke(borderThickness));
		g2d.draw(outline);
		g2d.setStroke(backup);
	}
	
	public int getMinimalHorizontalOffset() {
		return arcWidth + verticalOffset + borderThickness;
	}
}
