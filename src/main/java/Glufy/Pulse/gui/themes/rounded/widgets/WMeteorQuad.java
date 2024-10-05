/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package Glufy.Pulse.gui.themes.rounded.widgets;

import Glufy.Pulse.utils.gui.GuiUtils;
import Glufy.Pulse.gui.themes.rounded.MeteorRoundedGuiTheme;
import meteordevelopment.meteorclient.gui.renderer.GuiRenderer;
import meteordevelopment.meteorclient.gui.widgets.WQuad;
import meteordevelopment.meteorclient.utils.render.color.Color;

public class WMeteorQuad extends WQuad {
    public WMeteorQuad(Color color) {
        super(color);
    }

    @Override
    protected void onRender(GuiRenderer renderer, double mouseX, double mouseY, double delta) {
        GuiUtils.quadRounded(renderer, x, y, width, height, color, ((MeteorRoundedGuiTheme)theme).roundAmount());
    }
}
