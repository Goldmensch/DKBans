package de.fridious.bansystem.extension.gui.guis.kick;

/*
 * (C) Copyright 2019 The DKBans Project (Davide Wietlisbach)
 *
 * @author Philipp Elvin Friedhoff
 * @since 05.01.19 00:34
 * @Website https://github.com/DevKrieger/DKBans
 *
 * The DKBans Project is under the Apache License, version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import de.fridious.bansystem.extension.gui.api.inventory.gui.AnvilInputGui;

public class KickMessageGui extends AnvilInputGui {

    private KickTemplateGui kickTemplateGui;

    public KickMessageGui(KickTemplateGui kickTemplateGui) {
        super(kickTemplateGui.getOwner());
        this.kickTemplateGui = kickTemplateGui;
    }

    @Override
    public String getMessage() {
        return kickTemplateGui.getMessage();
    }

    @Override
    public void setMessage(String message) {
        kickTemplateGui.setMessage(message);
    }

    @Override
    public void updatePage() {
        kickTemplateGui.updatePage(null);
    }

    @Override
    public void open() {
        kickTemplateGui.open();
    }
}