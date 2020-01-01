package net.onfirenetwork.onsetjava.data;

import net.onfirenetwork.onsetjava.enums.WeaponModel;

public class Weapon {

    private int model;
    private int ammo;

    public Weapon(WeaponModel model, int ammo){
        this(model.id(), ammo);
    }

    public Weapon(int model, int ammo){
        this.model = model;
        this.ammo = ammo;
    }

    public int getModel() {
        return model;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public void setModel(WeaponModel model){
        this.model = model.id();
    }

}
