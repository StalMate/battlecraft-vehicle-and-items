package com.battlecraft.vehicleanditems.entity.blocks.interfaces;

import com.battlecraft.vehicleanditems.entity.blocks.components.ProgressBarComponent;

public interface ProgressBarEntity {
    //Метод передает данные о текущем прогрессе в renderer класс блока
    float getRenderProgress(float partialTick);
    ProgressBarComponent getProgressBar();
}
