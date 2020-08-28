package de.melanx.botanicalmachinery.blocks.screens;

import de.melanx.botanicalmachinery.blocks.base.ScreenBase;
import de.melanx.botanicalmachinery.blocks.containers.ContainerMechanicalBrewery;
import de.melanx.botanicalmachinery.blocks.tiles.TileMechanicalBrewery;
import de.melanx.botanicalmachinery.core.LibResources;
import de.melanx.botanicalmachinery.helper.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class ScreenMechanicalBrewery extends ScreenBase<ContainerMechanicalBrewery> {
    public ScreenMechanicalBrewery(ContainerMechanicalBrewery container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
        this.ySize = 192;
        this.manaBar.y = 28;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(LibResources.MECHANICAL_BREWERY_GUI, 100, 49);

        TileMechanicalBrewery tile = (TileMechanicalBrewery) this.container.tile;
        if (tile.getInventory().getStackInSlot(0).isEmpty())
            RenderHelper.renderFadedItem(this, TileMechanicalBrewery.BREW_CONTAINER, this.relX + 44, this.relY + 48);
        if (tile.getProgress() > 0) {
            float pct = Math.min(tile.getProgress() / (float) tile.getWorkingDuration(), 1.0F);
            //noinspection ConstantConditions
            this.minecraft.getTextureManager().bindTexture(LibResources.MECHANICAL_BREWERY_GUI);
            vazkii.botania.client.core.helper.RenderHelper.drawTexturedModalRect(this.relX + 96, this.relY + 48, 176, 0, Math.round(22 * pct), 16);
        }
    }
}
