package com.ndsl.graphics.display;

import com.ndsl.graphics.display.mouse.MouseInputHandler;
import com.ndsl.graphics.pos.Rect;

import java.awt.*;

public class DefaultBar implements BorderBar {
    public static final int bar_tin = 30;
    public static final int button_width = 40;
    public Rect bar_rect;
    public Color c;
    public Display display;

    public DefaultBar(Display display) {
        this(display, new Color(120, 120, 120, 255));
    }

    public DefaultBar(Display display, Color c) {
        this.bar_rect = new Rect(0, 0, display.getWidth(), bar_tin);
        this.c = c;
        this.mouseinput = display.mouseInputHandler;
        this.display = display;
        final Rect min_rect = new Rect(display.getWidth() - button_width * 3, 0, display.getWidth() - button_width * 2, bar_tin);
        final int min_bar_rect_tin = 1;
        final int min_bar_rect_width = 20;
        this.minButton = new MinimumButton(min_rect, new Rect(min_rect.left_up.x + (min_rect.getWidth() - min_bar_rect_width) / 2, min_rect.left_up.y + (min_rect.getHeight() - min_bar_rect_tin) / 2, min_rect.left_up.x + (min_rect.getWidth() + min_bar_rect_width) / 2, min_rect.left_up.y + (min_rect.getHeight() + min_bar_rect_tin) / 2), new Color(255, 255, 255), new Color(53, 104, 255));
        final Rect max_rect = new Rect(display.getWidth() - button_width * 2, 0, display.getWidth() - button_width * 1, bar_tin);
        final int max_bar_rect_tin = 20;
        final int max_bar_rect_width = 20;
        this.maxButton = new MaximumButton(max_rect, new Rect(max_rect.left_up.x + (max_rect.getWidth() / 2 - max_bar_rect_width / 2), max_rect.left_up.y + (max_rect.getHeight() / 2 - max_bar_rect_tin / 2), max_rect.left_up.x + (max_rect.getWidth() / 2 + max_bar_rect_width / 2), max_rect.left_up.y + (max_rect.getHeight() / 2 + max_bar_rect_tin / 2)), new Color(255, 255, 255), new Color(53, 104, 255));
        final Rect close_rect = new Rect(display.getWidth() - button_width * 1, 0, display.getWidth()/*-button_width*0*/, bar_tin);
        final int close_bar_rect_tin = 20;
        final int close_bar_rect_width = 20;
        this.closeButton = new CloseButton(close_rect, new Rect(close_rect.left_up.x + (close_rect.getWidth() / 2 - close_bar_rect_width / 2), close_rect.left_up.y + (close_rect.getHeight() / 2 - close_bar_rect_tin / 2), close_rect.left_up.x + (close_rect.getWidth() / 2 + close_bar_rect_width / 2), close_rect.left_up.y + (close_rect.getHeight() / 2 + close_bar_rect_tin / 2)), new Color(255, 255, 255), new Color(255, 19, 19));
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(c);
        g.fillRect(bar_rect.left_up.x, bar_rect.left_up.y, bar_rect.getWidth(), bar_rect.getHeight());
        this.minButton.onDraw(g);
        this.maxButton.onDraw(g);
        this.closeButton.onDraw(g);
    }

    public MouseInputHandler mouseinput;

    public MinimumButton minButton;

    public class MinimumButton {
        private final Rect r;
        private final Color bar_color;
        private final Color onOverRapColor;
        private final Rect bar_rect;

        public MinimumButton(Rect r, Rect bar_rect, Color bar_color, Color onOverRapColor) {
            this.r = r;
            this.bar_rect = bar_rect;
            this.bar_color = bar_color;
            this.onOverRapColor = onOverRapColor;
        }

        public void onDraw(Graphics g) {
            g.setColor(isOverlap() ? onOverRapColor : c);
            g.fillRect(r.left_up.x, r.left_up.y, r.getWidth(), r.getHeight());
            g.setColor(bar_color);
            g.fillRect(bar_rect.left_up.x, bar_rect.left_up.y, bar_rect.getWidth(), bar_rect.getHeight());
            if (isClicked()) {
                display.setVisible(false);
            }
        }

        public boolean isOverlap() {
            return r.contain(mouseinput.now_mouse_pos);
        }

        public boolean isClicked() {
            return isOverlap() && mouseinput.isClicking;
        }
    }

    public MaximumButton maxButton;

    public class MaximumButton {
        private final Rect r;
        private final Color bar_color;
        private final Color onOverRapColor;
        private final Rect bar_rect;

        public MaximumButton(Rect r, Rect bar_rect, Color bar_color, Color onOverRapColor) {
            this.r = r;
            this.bar_rect = bar_rect;
            this.bar_color = bar_color;
            this.onOverRapColor = onOverRapColor;
        }

        public void onDraw(Graphics g) {
            g.setColor(isOverlap() ? onOverRapColor : c);
            g.fillRect(r.left_up.x, r.left_up.y, r.getWidth(), r.getHeight());
            drawWindowIcon(g);
            if (isClicked()) {
                display.setBounds(display.getMaximizedBounds());
            }
        }

        private void drawWindowIcon(Graphics g) {
            g.setColor(bar_color);
            g.drawRect(bar_rect.left_up.x, bar_rect.left_up.y, bar_rect.getWidth(), bar_rect.getHeight());
            g.drawLine(bar_rect.left_up.x, bar_rect.left_up.y + (bar_rect.getHeight() / 3), bar_rect.right_down.x, bar_rect.left_up.y + (bar_rect.getHeight() / 3));
        }

        public boolean isOverlap() {
            return r.contain(mouseinput.now_mouse_pos);
        }

        public boolean isClicked() {
            return isOverlap() && mouseinput.isClicking;
        }
    }

    public CloseButton closeButton;

    public class CloseButton {
        private final Rect r;
        private final Color bar_color;
        private final Color onOverRapColor;
        private final Rect bar_rect;

        public CloseButton(Rect r, Rect bar_rect, Color bar_color, Color onOverRapColor) {
            this.r = r;
            this.bar_rect = bar_rect;
            this.bar_color = bar_color;
            this.onOverRapColor = onOverRapColor;
        }

        public void onDraw(Graphics g) {
            g.setColor(isOverlap() ? onOverRapColor : c);
            g.fillRect(r.left_up.x, r.left_up.y, r.getWidth(), r.getHeight());
            drawX(g);
            if (isClicked()) {
                display.dispose();
            }
        }

        private void drawX(Graphics g) {
            g.setColor(bar_color);
            g.drawLine(bar_rect.left_up.x, bar_rect.left_up.y, bar_rect.right_down.x, bar_rect.right_down.y);
            g.drawLine(bar_rect.left_up.x, bar_rect.right_down.y, bar_rect.right_down.x, bar_rect.left_up.y);
        }

        public boolean isOverlap() {
            return r.contain(mouseinput.now_mouse_pos);
        }

        public boolean isClicked() {
            return isOverlap() && mouseinput.isClicking;
        }
    }
}
