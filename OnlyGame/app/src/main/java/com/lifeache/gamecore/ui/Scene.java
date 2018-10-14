package com.lifeache.gamecore.ui;

public class Scene extends ShowableBuilder
{
	SceneTransfer sceneTransfer;

	public void setSceneTransfer(SceneTransfer sceneTransfer)
	{
		this.sceneTransfer = sceneTransfer;
	}

	public SceneTransfer getSceneTransfer()
	{
		return sceneTransfer;
	}
    
    public void removeSceneTransfer(){
        sceneTransfer = null;
    }
	public Scene transferById(int id){
		if (sceneTransfer != null){
			return sceneTransfer.transferTo(id);
		} else {
			return this;
		}
	}
}
