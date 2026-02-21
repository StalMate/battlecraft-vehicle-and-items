package com.battlecraft.vehicleanditems.entity.blocks.interfaces;

public interface ProgressBarEntity {
    //Метод передает данные о текущем прогрессе в renderer класс блока
    float getRenderProgress(float partialTick);
}
